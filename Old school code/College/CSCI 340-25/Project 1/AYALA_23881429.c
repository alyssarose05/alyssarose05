// Alyssa Ayala, CSCI 340-25
// Project 1

// THIS FILE COMPILES AND RUNS SUCCESSFULLY WHEN USING THESE COMMANDS:
// Compile: gcc AYALA_23881429.c -o AYALA_23881429.exe -lrt
// Run: ./AYALA_23881429.exe Project1InputSection25.txt TGT_FILE 48 10 > AYALA_23881429.out

#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <string.h>
#include <unistd.h>

int main(int argc, const char *argv[]) {
	
	// Make sure that there are 4 arguments: source file, target file, chunk size, and buffer size.
	if (argc != 5){
		fprintf(stderr, "Wrong number of command line arguments\n");
		exit(1);
		return 1;
	}
	
	// Initialize and allocate dynamic memory to the size of the buffer, chunk, and shared memory.
	int* BUFFER_SIZE = (int*) malloc(sizeof(int)); 
	*BUFFER_SIZE = atoi(argv[4]);
	int* CHUNK_SIZE = (int*) malloc(sizeof(int)); 
	*CHUNK_SIZE = atoi(argv[3]);
	int* SHARED_SIZE = (int*) malloc(sizeof(int)); 
	*SHARED_SIZE = *BUFFER_SIZE * *CHUNK_SIZE;
	
	// Create a bounded buffer type: a char String array with the buffer size, an in index, and an out index.
	typedef struct {
		char value[(u_int64_t)*BUFFER_SIZE][(u_int64_t)*CHUNK_SIZE + 1];
		int in;
		int out;
		int n;
	} buffer;
	
	// Make a bounded buffer shared so that the parent and child process can see each other's changes.
	buffer *BUFFER = (buffer*) malloc(sizeof(buffer));
	BUFFER->in = 0;
	BUFFER->out = 0;
	BUFFER->n = 1;
	int fd = shm_open(BUFFER, O_CREAT | O_RDWR, 0666); 
	ftruncate(fd, SHARED_SIZE); 
	buffer *PTR = mmap(0, SHARED_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED | MAP_ANONYMOUS, fd, 0); 
	
	setbuf(stdout, NULL);
	
	// Create a pipe to get the number of characters produced by the producer.
	int shMemPrdCharCount = -(*CHUNK_SIZE); 
	int shMemCsrCharCount = 0;
	int fdd[2];
	if (pipe(fdd) < 0) {
		fprintf(stderr, "Can't create pipe");
		return 1;
	}
	
	// Fork a child process. The parent is the producer, and the child is the consumer.
	pid_t pid = fork();
	if (pid < 0) { 
		fprintf(stderr, "Can't fork process");
		return 1;
	}
	
	else if (pid == 0) { 
	
		// Create the target file.
		int TGT_FILE = creat(argv[2], 0644);
		if (TGT_FILE == -1){
			fprintf(stderr, "Can't create target file");
			close(TGT_FILE);
			return 3;
		}
		

		char *next_consumed = malloc((u_int64_t)*CHUNK_SIZE);
	
		while (PTR->n) {
			// Get the produced item, a chunk of the source file, from shared memory.
			while (PTR->in == PTR->out)
				; 
			next_consumed = PTR->value[PTR->out];
			PTR->out = (PTR->out + 1) % (u_int64_t)*BUFFER_SIZE;

			// Consume the item by writing the produced item to the target file.
			write(TGT_FILE, next_consumed, PTR->n);
			if (TGT_FILE == -1) {
				printf("Could not write to target file");
				close(TGT_FILE);
				return 3;
			}
			
			write(stdout, printf("CHILD: OUT = %d\n", PTR->out), (u_int64_t)*CHUNK_SIZE);
			write(stdout, printf("CHILD: ITEM = %s\n", next_consumed), (u_int64_t)*CHUNK_SIZE);
			// Get the number of characters produced from the pipe.
			close(fdd[1]);
			int m = read(fdd[0], &shMemPrdCharCount, sizeof(shMemPrdCharCount));
			shMemCsrCharCount += PTR->n;
		}

		write(stdout, printf("CHILD: The parent value of shMemPrdCharCount  = %d\n", shMemPrdCharCount), sizeof(shMemPrdCharCount));
		write(stdout, printf("CHILD: The child value of shMemCsrCharCount  = %d\n", shMemCsrCharCount), sizeof(shMemCsrCharCount));
		close(TGT_FILE);
		exit(0);
	}
	
	else { 

		// Access the source file.
		int SRC_FILE = open(argv[1], O_RDONLY, 0);
		if (SRC_FILE == -1) {
			fprintf(stderr, "Can't access source file");
			close(SRC_FILE);
			free(SRC_FILE);
			return 2;
		}
	
		char buf[(u_int64_t)*CHUNK_SIZE];
		char *next_produced = malloc((u_int64_t)*CHUNK_SIZE);
		while ((PTR->n = read(SRC_FILE, buf, (u_int64_t)*CHUNK_SIZE)) > 0) {
			
			// Produce the item by placing a chunk of the source file into the bounded buffer.
			next_produced = buf;
			while (((PTR->in + 1) % (u_int64_t)*BUFFER_SIZE) == PTR->out);
				; 
			snprintf(PTR->value[PTR->in],(u_int64_t)*CHUNK_SIZE + 1,"%s",next_produced);
			PTR->in = (PTR->in + 1) % (u_int64_t)*BUFFER_SIZE;
			
			write(STDOUT_FILENO, printf("PARENT: IN = %d\n", PTR->in), (u_int64_t)*CHUNK_SIZE);
			write(STDOUT_FILENO, printf("PARENT: ITEM = %s\n", next_produced), (u_int64_t)*CHUNK_SIZE);
			
			// Parent writes number of characters produced to pipe
			shMemPrdCharCount += PTR->n;
			close(fdd[0]);
			write(fdd[1], &shMemPrdCharCount, sizeof(shMemPrdCharCount));
		}	
		
		write(stdout, printf("PARENT: The parent value of shMemPrdCharCount  = %d\n", shMemPrdCharCount), sizeof(shMemPrdCharCount));
		close(SRC_FILE);
		wait(NULL);
	}	
	
	shm_unlink(BUFFER);
	free(BUFFER_SIZE);
	free(CHUNK_SIZE);
	free(BUFFER);
}