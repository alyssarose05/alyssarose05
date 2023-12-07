// Alyssa Ayala, CSCI 340-25
// Project 2

// THIS PROGRAM COMPILES AND RUNS SUCCESSFULLY WHEN USING THESE COMMANDS:
// Compile: gcc AYALA_23881429.c -o AYALA_23881429.exe -lpthread
// FCFS: ./AYALA_23881429.exe FCFS FCFS_Section25.txt AYALA_23881429.out
// SJF: ./AYALA_23881429.exe SJF SJF_Section25.txt AYALA_23881429.out
// PRIORITY: ./AYALA_23881429.exe PRIORITY PRIORITY_Section25.txt AYALA_23881429.out

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_SIZE 15

/* This is what a process is for this project.
The burstTimeOrPriority variable is modified by SJF and PRIORITY only. */
typedef struct {
	int processNumber;
	int burstTimeOrPriority;
	char operation[20];
	int i;
	int j;
	int returnValue;
} MiniPCB;

// Global variables used by all threads: main, scheduler/dispatcher, logger.
pthread_mutex_t mutex;
int size, output_file, min, memoryIndex;
MiniPCB* ptr[MAX_SIZE];

/* These operands below are how each process is executed.
The input parameters of a process are subjected to these functions, which is its return value. */

int sum(int i, int j) {
	return i+j;
}

int product(int i, int j) {
	return i*j;
}

int power(int i, int j) {
	int result = 1;
	for (int k = 0; k < j; k++) result *= i;
	return result;
}

int fibonacci(int i, int j) {
	int first = 0;
	int second = 1;
	int result = 0;
	for (int k = 1; k < j; k++) {
		result = first + second;
		first = second;
		second = result;
	}
	return result;
}

int (*functions[4]) (int i, int j) = {&sum, &product, &power, &fibonacci};

// The operation (one of the four functions above) can be referred to its index immediately.
int funcIndex(char input[]) { 
	if (strcmp(input,"sum") == 0) return 0;
	else if (strcmp(input,"product") == 0) return 1;
	else if (strcmp(input,"power") == 0) return 2;
	return 3;
}

/* Each scheduling algorithm takes the ready queue as input and returns the next process to be executed.
Dispatch: Calculate the result of the operand and input parameters.
Schedule: Decide which process to execute and send it to the logger. */

MiniPCB *FCFS(void *arg) {
    pthread_mutex_lock(&mutex);
    MiniPCB **Ready_Queue = (MiniPCB **) arg;
    for (int k = 0; k < size; k++) if (Ready_Queue[k]->processNumber < Ready_Queue[min]->processNumber) min = k;
    Ready_Queue[min]->returnValue = (*functions[funcIndex(Ready_Queue[min]->operation)]) (Ready_Queue[min]->i,Ready_Queue[min]->j);
    Ready_Queue[min]->processNumber = 1000;
    ptr[memoryIndex++] = malloc(sizeof(MiniPCB));
    pthread_mutex_unlock(&mutex);
    pthread_exit(Ready_Queue[min]);
}

MiniPCB *SJF(void *arg) {
  	pthread_mutex_lock(&mutex);
    MiniPCB **Ready_Queue = (MiniPCB **) arg;
    for (int k = 0; k < size; k++) if (Ready_Queue[k]->burstTimeOrPriority < Ready_Queue[min]->burstTimeOrPriority) min = k;
    Ready_Queue[min]->returnValue = (*functions[funcIndex(Ready_Queue[min]->operation)]) (Ready_Queue[min]->i,Ready_Queue[min]->j);
    Ready_Queue[min]->burstTimeOrPriority = 1000;
    ptr[memoryIndex++] = malloc(sizeof(MiniPCB));
    pthread_mutex_unlock(&mutex);
    pthread_exit(Ready_Queue[min]);
}

MiniPCB *PRIORITY(void *arg) {
	pthread_mutex_lock(&mutex);
    MiniPCB **Ready_Queue = (MiniPCB **) arg;
    for (int k = 0; k < size; k++) if (Ready_Queue[k]->burstTimeOrPriority < Ready_Queue[min]->burstTimeOrPriority) min = k;
    Ready_Queue[min]->returnValue = (*functions[funcIndex(Ready_Queue[min]->operation)]) (Ready_Queue[min]->i,Ready_Queue[min]->j);
    Ready_Queue[min]->burstTimeOrPriority = 1000;
    ptr[memoryIndex++] = malloc(sizeof(MiniPCB));
    pthread_mutex_unlock(&mutex);
    pthread_exit(Ready_Queue[min]);
}

// The logger takes the next process to be executed as input and writes its contents to the output file.
void *LOGGER(void *arg) { 
    pthread_mutex_lock(&mutex);
    MiniPCB *chosen = (MiniPCB*) arg;
    char *output = malloc(sizeof(MiniPCB));
	sprintf(output,"%s,%d,%d,%d\n",chosen->operation,chosen->i,chosen->j,chosen->returnValue);
	if ((write(output_file, output, sizeof(MiniPCB))) != sizeof(MiniPCB)) {
		printf("Can't write output to output file: %s",output);
		free(output);
		pthread_mutex_unlock(&mutex);
		pthread_exit(4);
	}
	free(output);
    pthread_mutex_unlock(&mutex);
    pthread_exit(0);
}

// The user's desired scheduling algorithm can be referred to its index immediately.
int algorithmIndex(char input[]) { 
	if (strcmp(input,"FCFS") == 0) return 0;
	else if (strcmp(input,"SJF") == 0) return 1;
	return 2;
}

int main(int argc, char *argv[]) {

    pthread_mutex_lock(&mutex);

	/* Check for the right number of arguments: 3
	scheduling algorithm, input file, output file */
	if (argc != 4) {
		printf("Wrong number of command line arguments: %d",argc-1);
		pthread_mutex_unlock(&mutex);
		pthread_exit(1);
	}

	/* Components that will be used by the scheduler/dispatcher and logger threads once they are created/joined
	(excluding temp and input_file; only used by main thread) */
	void (*scheduling_algorithms[3]) = {&FCFS, &SJF, &PRIORITY};
    MiniPCB *Ready_Queue[MAX_SIZE], *FIFO[MAX_SIZE], temp;
    int point;
	FILE *input_file;
    
    if ((input_file = fopen(argv[2],"r")) == NULL) {
		printf("Can't open input file: %s",argv[2]);
		pthread_mutex_unlock(&mutex);
		pthread_exit(2);
	}
	
	if ((output_file = creat(argv[3],0644)) == -1) {
		printf("Can't create output file: %s",argv[3]);
		pthread_mutex_unlock(&mutex);
		pthread_exit(3);
	}
    
    // Fill the ready queue.
    if (strcmp(argv[1],"FCFS") != 0) {
    	while (fscanf(input_file,"%d,%d,%[^,],%d,%d\n",&temp.processNumber,&temp.burstTimeOrPriority,temp.operation,&temp.i,&temp.j) > 0) {
    		Ready_Queue[size++] = malloc(sizeof(MiniPCB));
			memcpy(Ready_Queue[size-1],&temp,sizeof(MiniPCB));
		}
    }
	else {
		while (fscanf(input_file,"%d,%[^,],%d,%d\n",&temp.processNumber,temp.operation,&temp.i,&temp.j) > 0) {
			Ready_Queue[size++] = malloc(sizeof(MiniPCB));
			memcpy(Ready_Queue[size-1],&temp,sizeof(MiniPCB));
		}
	}
	
	fclose(input_file);
    pthread_mutex_unlock(&mutex);
 
   	/* Scheduler/dispatcher and logger threads work to send and receive chosen MiniPCBs.
   	send(): join scheduler/dispatcher thread
   	recv(): create logger thread */
   	pthread_t Scheduler_Dispatcher[size], Logger[size];
   	for (int i = 0; i < size; i++) {
    	pthread_create(&Scheduler_Dispatcher[i], NULL, scheduling_algorithms[algorithmIndex(argv[1])], &(*Ready_Queue));
    	pthread_join(Scheduler_Dispatcher[i], &FIFO[point++]);

    	pthread_create(&Logger[i], NULL, LOGGER, &(*FIFO[point-1]));
    	pthread_join(Logger[i], 0);
    }
   	
    for (int k = 0; k < size; k++) {
    	free(Ready_Queue[k]);
    	free(ptr[k]);
    }
 
    close(output_file);
	pthread_exit(0);
}