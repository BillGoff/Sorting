package sorting;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Stream;


/**
 * In bubble sort you always compare two adjacent elements and bubble the larger one to the right. At the end of the 
 * first iteration of the outer loop, you would have the largest element on the right-most position. The swap flag 
 * stops the outer loop when the array is already sorted.
 * @author bgoff
 *
 */
public class Sort 
{
	/**
	 * Convenience method used to print out the array.
	 * @param list integer array to print out.
	 */
	public static void printArray(int[] list)
	{
		for(int i=0; i<list.length; i++)
			System.out.print(list[i] + ", ");
	}
	
	/**
	 * Recursive Merge Sort.  Merge sort is a “divide and conquer” algorithm.  
	 * This has a time complexity will come to O(nLogn).
	 * @param a
	 * @param n
	 */
	public static void recursiveMergeSort(int [] a, int n)
	{
		if (n < 2) {
			return;
		}
	
		int mid = n / 2;
		int[] l = new int[mid];
		int[] r = new int[n - mid];

		for (int i = 0; i < mid; i++)
			l[i] = a[i];

		for (int i = mid; i < n; i++)
			r[i - mid] = a[i];
	
		recursiveMergeSort(l, mid);
		recursiveMergeSort(r, n - mid);

		merge(a, l, r, mid, n - mid);
	}

	/**
	 *  This method is used to merge the two halfs of the merge sort back into a single array.
	 * @param a
	 * @param l
	 * @param r
	 * @param left
	 * @param right
	 */
	private static void merge (int[] a, int[] l, int[] r, int left, int right) 
	{		 
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) 
		{
			if (l[i] <= r[j]) 
				a[k++] = l[i++];
			else 
				a[k++] = r[j++];
		}

		while (i < left)
			a[k++] = l[i++];
		
		while (j < right)
			a[k++] = r[j++];
	}
	
	/**
	 * method used to perform the bubble sort.  This has an average (worst) time complexity of O(n2).
	 * @param array int array we are sorting.
	 */
	public static void bubbleSort(int[] array) 
	{
		int temp = 0;
		boolean swap = true;
		int range = array.length - 1;
		while (swap) 
		{
			swap = false;
			for (int i = 0; i < range; i++) 
			{
				if (array[i] > array[i + 1]) 
				{
					temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					swap = true;
				}
			}
			range--;
		}
	}
	
	/**
	 * This method is the quick sort.  The first method is quickSort() which takes as parameters the array to be sorted, 
	 * the first and the last index. First, we check the indices and continue only if there are still elements to be 
	 * sorted.  We get the index of the sorted pivot and use it to recursively call partition() method with the same 
	 * parameters as the quickSort() method, but with different indices:
	 * 
	 * In the best case, the algorithm will divide the list into two equal size sub-lists. So, the first iteration of 
	 * the full n-sized list needs O(n). Sorting the remaining two sub-lists with n/2 elements takes 2*O(n/2) each. 
	 * As a result, the QuickSort algorithm has the complexity of O(n log n).
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	public static void quickSort(int arr[], int begin, int end) 
	{
		if (begin < end) 
		{
			int partitionIndex = partition(arr, begin, end);

			quickSort(arr, begin, partitionIndex-1);
			quickSort(arr, partitionIndex+1, end);
		}
	}
	
	/**
	 * This method is the second method within the quick sort.  Let’s continue with the partition() method. For 
	 * simplicity, this function takes the last element as the pivot. Then, checks each element and swaps it before 
	 * the pivot if its value is smaller.
	 * @param arr
	 * @param begin
	 * @param end
	 * @return
	 */
	private static int partition(int arr[], int begin, int end) 
	{
		int pivot = arr[end];
		int i = (begin-1);

		for (int j = begin; j < end; j++) 
		{
			if (arr[j] <= pivot) 
			{
				i++;

				int swapTemp = arr[i];
	            arr[i] = arr[j];
	            arr[j] = swapTemp;
	        }
	    }

		int swapTemp = arr[i+1];
		arr[i+1] = arr[end];
		arr[end] = swapTemp;

		return i+1;
	}
	
	/**
	 * method used to make a duration human readable.
	 * @param duration Duration to make human readable.
	 * @return String Duration in a human readable form.
	 */
	public static String formatDuration(Duration duration) {
		return duration.toString()
	            .substring(2)
	            .replaceAll("(\\d[HMS])(?!$)", "$1 ")
	            .toLowerCase();
	}
	
	/**
	 * Convenience method used to generate our test data.
	 * @return int array of integers to test our sorts with.
	 */
	public static int [] generateRandomIntArray()
	{
		Random generator = new Random();

		int[] list = new int[100];
		for(int i = 0; i < list.length; i++)  
			list[i] = generator.nextInt(1000);
		
		return list;
	}
	
	
	 
	/**
	 * Driver program to test above functions 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Map<String, Duration> sortMap = new HashMap<String, Duration>();
				
		int[] list = generateRandomIntArray();

		//Bubble Sort Example.
		System.out.println("Original Random array: ");
		printArray(list);
		Instant start = Instant.now();         
		bubbleSort(list);
		Instant stop = Instant.now();
		System.out.println("\nSorted array (BubbleSort): ");
		printArray(list);
		Duration bubbleSortDuration = Duration.between(start, stop);
		sortMap.put("BubbleSort", bubbleSortDuration);
		
		System.out.println("\nIt Took " + formatDuration(bubbleSortDuration) + " to do the Bubble Sort");

		//Generate a new list.
		list = generateRandomIntArray();

		//Recursive Merge Sort Example
		System.out.println("\n\nOriginal Random array: ");
		printArray(list);
		start = Instant.now();         
		recursiveMergeSort(list, list.length);
		stop = Instant.now();
		System.out.println("\nSorted array (Recursive Merge Sort): ");
		printArray(list);
		Duration mergeSortDuration = Duration.between(start, stop);
		sortMap.put("MergeSort", mergeSortDuration);
		
		System.out.println("\nIt Took " + formatDuration(mergeSortDuration) + 
				" to do the Recursive Merge Sort");
		
		//Generate a new list.
		list = generateRandomIntArray();

		//Recursive Quick Sort Example
		System.out.println("\n\nOriginal Random array: ");
		printArray(list);
		start = Instant.now();         
		quickSort(list, 0, list.length - 1);
		stop = Instant.now();
		System.out.println("\nSorted array (Quick Sort): ");
		printArray(list);
		
		Duration quickSortDuration = Duration.between(start, stop);
		sortMap.put("QuickSort", quickSortDuration);

		System.out.println("\nIt Took " + formatDuration(quickSortDuration) + 
				" to do the Recursive Quick Sort");
		
		System.out.println("\n\nWhich sorting was the fastest!!!!  And the winner was:");
		
		//Print before we sorted the duration runs.
		// sortMap.forEach((key,value)-> System.out.println(key + "	" + value));

		List<Map.Entry<String, Duration>> sortedList = new ArrayList<>(sortMap.entrySet());

		sortedList.sort(Map.Entry.comparingByValue());

		sortedList.forEach((duration)->System.out.println(duration.getKey() + "	" + duration.getValue()));

		
	}
}
