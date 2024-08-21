// Alyssa Ayala, CSCI 323-14
// Programming Assignment 1: Comparing Average Values of Sorting Algorithms
// Language: Java
public class PA1 {
	private static long isAccumulator, hsAccumulator, msAccumulator, qsAccumulator = 0; 
	private static long isSwapCounter, isComparisonCounter, hsSwapCounter, hsComparisonCounter, msSwapCounter, msComparisonCounter, qsSwapCounter, qsComparisonCounter = 0;
	private static long msTempArrayCounter = 0;
	
	public static void main(String[] args) {
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
		int[] is = new int[10000];
		int[] qs = new int[10000];
		int[] ms = new int[10000];
						
		for (int i = 0; i < 10000; i++) 
			is[i] = (int) (1 + Math.random() * 999999); // A random number between 1 and 1,000,000
						
		InsertionSort(is, 10000);
		for (int i = 0; i < 10000; i++) 
			qs[i] = ms[i] = is[i];
						
		QuickSort(qs, 0, 9999);
		MergeSort(ms, 0, 9999); 
	}
	
	public static void compareClockTime(int n) { // Main Program Code, array size n
		int[] A = new int[n]; // The original array and one array for each sorting algorithm
		int[] is = new int[n];
		int[] hs = new int[n + 1]; // The array starts at index 1 for HeapSort
		int[] ms = new int[n];
		int[] qs = new int[n];
				
		hs[0] = 0; // A 'dummy node' for HeapSort
			
		int range;
		if (n < 10000)
			range = 999; // Small arrays: A random number between 1 and 1,000
		else
			range = 999999; // Large arrays: A random number between 1 and 1,000,000
			
		for (int i = 0; i < n; i++) {
			A[i] = (int) (1 + Math.random() * range);
			is[i] = hs[i + 1] = ms[i] = qs[i] = A[i];
		}
				
		for (int i = 0; i < 100; i++) {
			InsertionSort(is, n);
			HeapSort(hs, n - 1);
			MergeSort(ms, 0, n - 1);
			QuickSort(qs, 0, n - 1);
			for (int j = 0; j < n; j++)
				is[j] = hs[j + 1] = ms[j] = qs[j] = A[j];
		} 
				
		System.out.println("InsertionSort Average Runtime: " + isAccumulator/100.0); 
		System.out.println("HeapSort Average Runtime: " + hsAccumulator/100.0); 
		System.out.println("MergeSort Average Runtime: " + msAccumulator/100.0); 
		System.out.println("QuickSort Average Runtime: " + qsAccumulator/100.0); 
				
		System.out.println("\nInsertionSort Average Swaps: " + isSwapCounter/100); 
		System.out.println("InsertionSort Average Comparisons: " + isComparisonCounter/100); 
		System.out.println("HeapSort Average Swaps: " + hsSwapCounter/100); 
		System.out.println("HeapSort Average Comparisons: " + hsComparisonCounter/100); 
		System.out.println("MergeSort Average Swaps: " + msSwapCounter/100); 
		System.out.println("MergeSort Average Comparisons: " + msComparisonCounter/100); 
		System.out.println("QuickSort Average Swaps: " + qsSwapCounter/100); 
		System.out.println("QuickSort Average Comparisons: " + qsComparisonCounter/100); 
				
		System.out.println("\nMergeSort Average Number of Temporary Arrays: " + msTempArrayCounter/100); 
	}
	
	// InsertionSort best-case runtime: Ω(n). The fastest sorting algorithm in this program.
	// InsertionSort worst-case runtime: O(n^2). The same runtime as QuickSort's worst-case runtime. The slowest sorting algorithm in this program.
	public static void InsertionSort(int[] A, int n) {
		long timer; // Timer for InsertionSort
		if (n < 9999)
			timer = System.nanoTime(); // Small arrays: Nanoseconds (ns)
		else
			timer = System.currentTimeMillis(); // Large arrays: Milliseconds (ms)
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
		if (n < 9999)
			timer = System.nanoTime() - timer; // Small arrays: Nanoseconds (ns)
		else
			timer = System.currentTimeMillis() - timer; // Large arrays: Milliseconds (ms)
		isAccumulator += timer;
	}
		
	// HeapSort every-case runtime: Θ(nlgn). The same every-case runtime as MergeSort.
	public static void HeapSort(int[] A, int n) {
		long timer; // Timer for HeapSort
		if (n < 10000)
			timer = System.nanoTime(); // Small arrays: Nanoseconds (ns)
		else
			timer = System.currentTimeMillis(); // Large arrays: Milliseconds (ms)
		BuildHeap(A, n); // Θ(n)
		for (int i = n; i >= 2; i--)
			A[i] = DeleteRoot(A, i); // Θ(lgn)

		if (n < 10000)
			timer = System.nanoTime() - timer; // Small arrays: Nanoseconds (ns)
		else
			timer = System.currentTimeMillis() - timer; // Large arrays: Milliseconds (ms)
		hsAccumulator += timer;
	}
		
	// Corresponds with HeapSort. Every-case runtime: Θ(lgn)
	private static int DeleteRoot(int[] A, int n) {
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
	private static void BuildHeap(int[] A, int n) {
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
	public static void MergeSort(int[] A, int first, int last) {
		long timer; // Timer for MergeSort
		if (A.length < 10000)
			timer = System.nanoTime(); // Small arrays: Nanoseconds (ns)
		else
			timer = System.currentTimeMillis(); // Large arrays: Milliseconds (ms)
		if (first < last) {
			int mid = (first + last) / 2;
			MergeSort(A, first, mid);
			MergeSort(A, mid + 1, last);
			Merge(A, first, mid, last); // Θ(n)
		}
		msComparisonCounter++;
		if (A.length < 10000)
			timer = System.nanoTime() - timer; // Small arrays: Nanoseconds (ns)
		else
			timer = System.currentTimeMillis() - timer; // Large arrays: Milliseconds (ms)
		msAccumulator += timer;
	}
		
	// Corresponds with MergeSort. Every-case runtime: Θ(n). The same every-case runtime as BuildHeap and Partition.
	private static void Merge(int[] A, int first, int mid, int last) {
		int left = first;
		int right = mid + 1;
		int[] B = new int[last - first + 1];
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
	public static void QuickSort(int[] A, int first, int last) {
		long timer; // Timer for QuickSort
		if (A.length < 10000)
			timer = System.nanoTime(); // Small arrays: Nanoseconds (ns)
		else
			timer = System.currentTimeMillis(); // Large arrays: Milliseconds (ms)
		if (first < last) {
			int pivot = Partition(A, first, last); // Θ(n)
			QuickSort(A, first, pivot - 1);
			QuickSort(A, pivot + 1, last);
		}
		qsComparisonCounter++;
		if (A.length < 10000)
			timer = System.nanoTime() - timer; // Small arrays: Nanoseconds (ns)
		else
			timer = System.currentTimeMillis() - timer; // Large arrays: Milliseconds (ms)
		qsAccumulator += timer;
	}
		
	// Corresponds with QuickSort. Every-case runtime: Θ(n). The same every-case runtime as BuildHeap and Merge.
	private static int Partition(int[] A, int first, int last) {
		int pivElt = A[first];
		int left = first + 1;
		int right = last;
		
		while (A[left] < pivElt)
			left++;
			
		while(A[right] > pivElt) 
			right--;
			
		if (left < right) {
			swap(A, left++, right--);
			qsSwapCounter++;
		}
		qsComparisonCounter++;
		
		swap(A, first, left - 1);
		qsSwapCounter++;

		return right;
	}
		
	// Used by both HeapSort and QuickSort. Every-case runtime: Θ(1).
	private static void swap(int[] A, int a, int b) {
		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
}