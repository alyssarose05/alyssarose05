// Alyssa Ayala, CSCI 323-14
// Programming Assignment 1: Comparing Average Values of Sorting Algorithms
// Language: C
#include <stdio.h>
#include <time.h>
    static double isAccumulator, hsAccumulator, msAccumulator, qsAccumulator = 0; 
	static double isSwapCounter, isComparisonCounter, hsSwapCounter, hsComparisonCounter, msSwapCounter, msComparisonCounter, qsSwapCounter, qsComparisonCounter = 0; 
	static double msTempArrayCounter = 0; 
int main()
{
   // Large Arrays
   compareClockTime(10000);
   compareClockTime(100000);
   
   // Small Arrays
   compareClockTime(16);
   compareClockTime(32);
   compareClockTime(64);
   compareClockTime(128);
   compareClockTime(256); 
   
   // Run QuickSort and MergeSort on array sorted by InsertionSort
   int is[10000];
   int qs[10000];
   int ms[10000];
   
   for (int i = 0; i < 10000; i++) {
       srand(time(NULL));
       is[i] = rand() % 1000000 + 1; // A random number between 1 and 1,000,000
   }
   InsertionSort(is, 10000);
   for (int i = 0; i < 10000; i++) {
       qs[i] = ms[i] = is[i];
   }
   QuickSort(qs);
   MergeSort(ms); 
}

void compareClockTime(int n) { // Main Program Code, array size n
	int A[n]; // The original array and one array for each sorting algorithm
	int is[n];
	int hs[n + 1]; // The array starts at index 1 for HeapSort
	int ms[n];
	int qs[n];
	
	hs[0] = 0; // A 'dummy node' for HeapSort
	
    int range;
    if (n < 10000)
        range = 1000; // Small arrays: A random number between 1 and 1,000
    else
        range = 1000000; // Large arrays: A random number between 1 and 1,000,000
    
	for (int i = 0; i < n; i++) {
	    srand(time(NULL));
	    A[i] = rand() % range + 1;
	    is[i] = hs[i + 1] = ms[i] = qs[i] = A[i];
	}
	
	for (int i = 0; i < 100; i++) { 
		InsertionSort(is, n);
		HeapSort(hs, n - 1);
		MergeSort(ms, 0, n - 1);
		QuickSort(qs, 0, n - 1);
		memcpy(is, A, sizeof(A));
		memcpy(hs, A, sizeof(A));
		memcpy(ms, A, sizeof(A));
		memcpy(qs, A, sizeof(A));
	} 
	 
	printf("\nInsertionSort Average Runtime: %f", isAccumulator/100.0);
	printf("\nHeapSort Average Runtime: %f", hsAccumulator/100.0);
	printf("\nMergeSort Average Runtime: %f", msAccumulator/100.0);
	printf("\nQuickSort Average Runtime: %f", qsAccumulator/100.0);
	
	printf(" \r\n\nInsertionSort Average Swaps: %f", isSwapCounter/100);
	printf("\nInsertionSort Average Comparisons: %f", isComparisonCounter/100);
	printf("\nHeapSort Average Swaps: %f", hsSwapCounter/100);
	printf("\nHeapSort Average Comparisons: %f", hsSwapCounter/100);
	printf("\nMergeSort Average Swaps: %f", msSwapCounter/100);
	printf("\nMergeSort Average Comparisons: %f", msComparisonCounter/100);
	printf("\nQuickSort Average Swaps: %f", qsSwapCounter/100);
	printf("\nQuickSort Average Comparisons: %f", qsComparisonCounter/100);
    
	printf("\n\nMergeSort Average Number of Temporary Arrays: %f", msTempArrayCounter/100);
	
}

// InsertionSort best-case runtime: Ω(n). The fastest sorting algorithm in this program.
// InsertionSort worst-case runtime: O(n^2). The same runtime as QuickSort's worst-case runtime. The slowest sorting algorithm in this program.
void InsertionSort(int A[], int n) {
    clock_t start = clock(); // Timer for InsertionSort
	int j, x;
	n--;
	for (int i = 0; i <= n; i++) {
		x = A[i];
		j = i - 1;
				
		while (j >= 0 && A[j] >= x) {
			A[j + 1] = A[j];
			j--;
		}
		A[j + 1] = x;
	}
    clock_t end = clock();
    double timer;
    if (n < 9999)
        timer = (end - start)/ (double) (CLOCKS_PER_SEC)*1000000000; // Small Arrays: Convert fron seconds to nanoseconds (ns)
    else
        timer = (end - start)/ (double) (CLOCKS_PER_SEC)*1000; // Large Arrays: Convert from seconds to milliseconds (ms)
    isAccumulator += timer;
}

// HeapSort every-case runtime: Θ(nlgn). The same every-case runtime as MergeSort.
void HeapSort(int A[], int n) {
	clock_t start = clock(); // Timer for HeapSort
	BuildHeap(A, n); // Θ(n)
	for (int i = n; i >= 2; i--)
		A[i] = DeleteRoot(A, i); // Θ(lgn)
    clock_t end = clock();
    double timer;
    if (n < 9999)
        timer = (end - start) / (double) (CLOCKS_PER_SEC)*1000000000; // Small Arrays: Convert from seconds to nanoseconds (ns)
    else
        timer = (end - start)/ (double) (CLOCKS_PER_SEC)*1000; // Large Arrays: Convert from seconds to milliseconds (ms)
	hsAccumulator += timer;
}
	
// Corresponds with HeapSort. Every-case runtime: Θ(lgn)
int DeleteRoot(int A[], int n) {
	int temp = A[1]; 
	A[1] = A[n];
	n--;
	int i = 1;
	while (i < n) {
		if (2 * i + 1 <= n) { 
			if (A[i] >= A[2 *i] && A[i] >= A[2 * i + 1])
				return temp;
			else {
				int j;
				if (A[2 * i] > A[2 * i + 1])
					j = 2 * i;
				else
					j = 2 * i + 1;
				hsComparisonCounter++;
				swap(A, i, j);
				hsSwapCounter++;
				i = j;
			}
			hsComparisonCounter++;
		}
		else {
			if (2 * i <= n) { 
				if (A[i] < A[2 * i]) {
					swap(A, i, 2 * i);
					hsSwapCounter++;
				}
				hsComparisonCounter++;
			}
			hsComparisonCounter++;
			return temp;
		}
		hsComparisonCounter++;
	}
	return temp; 
}

// Corresponds with HeapSort. Every-case runtime: Θ(n). The same every-case runtime as Merge and Partition.
void BuildHeap(int A[], int n) {
	if (n == 1) return; 
	int i = n; 
	while (i >= 1) {
		if (2 * i + 1 <= n) { 
			int j;
			if (A[2 * i] > A[2 * i + 1])
				j = 2 * i;
				else
				j = 2 * i + 1;
			hsComparisonCounter++;
			swap(A, i, j);
			hsSwapCounter++;
		}
		else if (2 * i <= n) { 
			if (A[2 * i] > A[i]) {
				swap(A, i, 2 * i);
				hsSwapCounter++;
			}
			hsComparisonCounter++;
		}
		hsComparisonCounter++;
		i--; // Go to the next node.
	}
}

// MergeSort every-case runtime: Θ(nlgn). The same every-case runtime as HeapSort.
void MergeSort(int A[], int first, int last) {
	clock_t start = clock(); // Timer for MergeSort
	if (first < last) {
		int mid = (first + last) / 2;
		MergeSort(A, first, mid);
		MergeSort(A, mid + 1, last);
		Merge(A, first, mid, last); // Θ(n)
	}
	msComparisonCounter++;
	clock_t end = clock();
	double timer;
	if (last < 9999)
        timer = (end - start) / (double) (CLOCKS_PER_SEC)*1000000000; // Small arrays: Convert from seconds to nanoseconds (ns)
    else
        timer = (end - start)/ (double) (CLOCKS_PER_SEC)*1000; // Large arrays: Convert from seconds to milliseconds (ms)
	msAccumulator += timer;
}

// Corresponds with MergeSort. Every-case runtime: Θ(n). The same every-case runtime as BuildHeap and Partition.
void Merge(int A[], int first, int mid, int last) {
	int left = first;
	int right = mid + 1;
	int B[last - first + 1];
	msTempArrayCounter++;

	int i = 0;
	while ((left <= mid) && (right <= last)) {
		if (A[left] <= A[right])
			B[i++] = A[left++];
		else
			B[i++] = A[right++];
		msComparisonCounter++;
	}
		
	if (left > mid)
		for (int j = right; j <= last; j++)
			B[i++] = A[j];
	else
		for (int j = left; j <= mid; j++)
			B[i++] = A[j];
	msComparisonCounter++;

	int j = 0;
	for (i = first; i <= last; i++)
		A[i] = B[j++];
}   

// QuickSort best-case runtime: Ω(nlgn).
// QuickSort worst-case runtime: O(n^2). The same worst-case runtime as InsertionSort. The slowest sorting algorithm in this program.
void QuickSort(int A[], int first, int last) {
	clock_t start = clock(); // Timer for QuickSort
	if (first < last) {
		int pivot = Partition(A, first, last); // Θ(n)
		QuickSort(A, first, pivot - 1);
		QuickSort(A, pivot + 1, last);
	}
	qsComparisonCounter++;
	clock_t end = clock();
	double timer;
	if (last < 9999)
        timer = (end - start) / (double) (CLOCKS_PER_SEC)*1000000000; // Large Arrays: Convert from seconds to nanoseconds (ns)
    else
        timer = (end - start)/ (double) (CLOCKS_PER_SEC)*1000; // Small Arrays: Convert from seconds to milliseconds(ms)
	qsAccumulator += timer;
}

// Corresponds with QuickSort. Every-case runtime: Θ(n). The same every-case runtime as BuildHeap and Merge.
int Partition(int A[], int first, int last) {
	int pivElt = A[first];
	int left = first + 1;
	int right = last;
			
	while (A[left] < pivElt)
		left++;
			
	while(A[right] > pivElt) 
		right--;
			
	if (left < right) {
		swap(A, left ++, right--);
		qsSwapCounter++;
	}
	qsComparisonCounter++;
			
	swap(A, first, left - 1);
	qsSwapCounter++;

	return right;
}

// Used by both HeapSort and QuickSort. Every-case runtime: Θ(1).
void swap(int A[], int a, int b) {
	int temp = A[a];
	A[a] = A[b];
	A[b] = temp;
}