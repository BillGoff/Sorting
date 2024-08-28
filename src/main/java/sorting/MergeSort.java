package sorting;

/**
 * This class is the classic Recursive Merge Sort.
 * 
 * Runtime Complexity: 
 * 
 * The time complexity of merge sort is O(n log n) in all three cases: best, average, and worst. This is because the 
 * algorithm always divides an array into two halves and merges them, regardless of the initial order of the elements. 
 * The merging of all sublists into a single list takes O(N) time. 
 * 
 * @author bgoff
 * @since 28 Aug 2024
 */
public class MergeSort 
{

	/**
	 * Recursive Merge Sort.  Merge sort is a “divide and conquer” algorithm.  
	 * This has a time complexity will come to O(nLogn).
	 * @param a
	 * @param n
	 */
	public static void sort(int [] a, int n)
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
	
		sort(l, mid);
		sort(r, n - mid);

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
}
