package sorting;

/**
 * This class is the classic Bubble Sort.
 * In a bubble sort you always compare two adjacent elements and bubble the larger one to the right. At the end of the 
 * first iteration of the outer loop, you would have the largest element on the right-most position. The swap flag 
 * stops the outer loop when the array is already sorted.
 * 
 * Runtime Complexity: 
 * 
 * The bubble sort algorithm has an average and worst-case time complexity of O(n^2), where n is the number of elements 
 * in the array. This is because the algorithm must pass through the array as many times as there are pairs in the 
 * array. In the best-case scenario, when the list is already sorted, bubble sort has a time complexity of O(n)
 * 
 * @author bgoff
 * @since 28 Aug 2024
 */
public class BubbleSort 
{
	/**
	 * method used to perform the bubble sort.  This has an average (worst) time complexity of O(n2).
	 * @param array int array we are sorting.
	 */
	public static void sort(int[] array) 
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
}
