package sorting;

/**
 * This class is the classic Quick Sort Example.  
 * 
 * Runtime Complexity: 
 * 
 * In the best case, the algorithm will divide the list into two equal 
 * size sub-lists. So, the first iteration of the full n-sized list needs O(n). Sorting the remaining two sub-lists 
 * with n/2 elements takes 2*O(n/2) each. As a result, the QuickSort algorithm has the complexity of O(n log n).
 * 
 * @author bgoff
 * @since 28 Aug 2024
 */
public class QuickSort 
{
	/**
	 * This method is the quick sort.  The first method is sort() which takes as parameters the array to be sorted, 
	 * the first and the last index. First, we check the indices and continue only if there are still elements to be 
	 * sorted.  We get the index of the sorted pivot and use it to recursively call partition() method with the same 
	 * parameters as the quickSort() method, but with different indices:
	 * 
	 * @param arr
	 * @param begin
	 * @param end
	 */
	public static void sort(int arr[], int begin, int end) 
	{
		if (begin < end) 
		{
			int partitionIndex = partition(arr, begin, end);

			sort(arr, begin, partitionIndex-1);
			sort(arr, partitionIndex+1, end);
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
}
