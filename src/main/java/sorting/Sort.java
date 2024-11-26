package sorting;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * This class is the runner to compare various types of sorting algorithms.  To include the Merge Sort, Bubble Sort,
 * and Quick Sort.
 * 
 * @author bgoff
 * @since 28 Aug 2024
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

		System.out.println("\n************* Bubble Sort Algorithm ********************");
		System.out.println("Original Random array: ");
		printArray(list);
		Instant start = Instant.now();         
		BubbleSort.sort(list);
		Instant stop = Instant.now();
		System.out.println("\nSorted array (BubbleSort): ");
		printArray(list);
		Duration bubbleSortDuration = Duration.between(start, stop);
		sortMap.put("BubbleSort", bubbleSortDuration);
		
		System.out.println("\nIt Took " + formatDuration(bubbleSortDuration) + " to do the Bubble Sort");

		//Generate a new list.
		list = generateRandomIntArray();

		System.out.println("\n************ Recursive Merge Sort Algorithm *****************");
		System.out.println("Original Random array: ");
		printArray(list);
		start = Instant.now();         
		MergeSort.sort(list, list.length);
		stop = Instant.now();
		System.out.println("\nSorted array (Recursive Merge Sort): ");
		printArray(list);
		Duration mergeSortDuration = Duration.between(start, stop);
		sortMap.put("MergeSort", mergeSortDuration);
		
		System.out.println("\nIt Took " + formatDuration(mergeSortDuration) + 
				" to do the Recursive Merge Sort");
		
		//Generate a new list.
		list = generateRandomIntArray();

		System.out.println("\n*************** Quick Sort Algorithm ******************");
		System.out.println("Original Random array: ");
		printArray(list);
		start = Instant.now();         
		QuickSort.sort(list, 0, list.length - 1);
		stop = Instant.now();
		System.out.println("\nSorted array (Quick Sort): ");
		printArray(list);
		
		Duration quickSortDuration = Duration.between(start, stop);
		sortMap.put("QuickSort", quickSortDuration);

		System.out.println("\nIt Took " + formatDuration(quickSortDuration) + 
				" to do the Recursive Quick Sort");

		//Generate a new list.
		list = generateRandomIntArray();
		System.out.println("\n**************** Bucket Sort Algorithm ******************");
		System.out.println("Original Random array: ");

		printArray(list);
		start = Instant.now();         
		BucketSort.sort(list);
		stop = Instant.now();
		System.out.println("\nSorted array (Bucket Sort): ");
		printArray(list);
		
		Duration bucketSortDuration = Duration.between(start, stop);
		sortMap.put("BucketSort", bucketSortDuration);
		System.out.println("\nIt Took " + formatDuration(bucketSortDuration) + 
				" to do the Bucket Sort");
		
		list = generateRandomIntArray();
		System.out.println("\n\n************** Radix Sort Algorithm **************");
		System.out.println("Original Random array: ");

		printArray(list);
		start = Instant.now();         
		RadixSort.sort(list);
		stop = Instant.now();
		System.out.println("\nSorted array (Radix Sort): ");
		printArray(list);
		
		Duration RadixSortDuration = Duration.between(start, stop);
		sortMap.put("RadixSort", RadixSortDuration);
		System.out.println("\nIt Took " + formatDuration(RadixSortDuration) + 
				" to do the Radix Sort");
		
		System.out.println("\n\n************** Fastest Algorithm **************");
		//Figure out which is the fasts.
		//Print before we sorted the duration runs.
		// sortMap.forEach((key,value)-> System.out.println(key + "	" + value));
		System.out.println("\n\nWhich sorting was the fastest!!!!  And the winner was:");
		
		List<Map.Entry<String, Duration>> sortedList = new ArrayList<>(sortMap.entrySet());

		sortedList.sort(Map.Entry.comparingByValue());

		sortedList.forEach((duration)->System.out.println(duration.getKey() + "	" + formatDuration(duration.getValue())));		
		
		System.out.println("\n\n************** Count Sort Algorithm **************");
		int[] input = {6, 4, 3, 2, 1, 4, 3, 6, 6, 2, 4, 3, 4};

		System.out.println("Original input array: ");
		printArray(input);
		
		CountingSort.sort(input, 6);
		
		System.out.println("\nOriginal input array: ");
		printArray(input);
	}
}
