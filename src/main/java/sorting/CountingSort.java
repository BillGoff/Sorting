package sorting;

/**
 * Counting sort, like radix sort and bucket sort, is an integer based algorithm (i.e. the values of the input array 
 * are assumed to be integers). Hence counting sort is among the fastest sorting algorithms around, in theory. The
 * particular distinction for counting sort is that it creates a bucket for each value and keep a counter in each 
 * bucket.  Then each time a value is encountered in the input collection, the appropriate counter is incremented. 
 * Because counting sort creates a bucket for each value, an imposing restriction is that the maximum value in the 
 * input array be known beforehand.
 *
 *   There is a great number of counting sort code on the Internet,
 *   including on university websites, that erroneously claim to be
 *   bucket sort. Bucket sort uses a hash function to distribute
 *   values; counting sort, on the other hand, creates a counter for
 *   each value -- hence the name.
 *   
 * Runtime Complexity: 
 * Best Case O(n+k); Average Case O(n+k); Worst Case O(n+k), where n is the size of the input array and k means the 
 * values range from 0 to k.
 * 
 * @author bgoff
 * @since 28 Aug 2024
 */
public class CountingSort 
{
	/**
	 * This method does that actual sorting.  
	 * @param input int array of values.
	 * @param k number of unique values plus 1.
	 */
	public static void sort(int[] input, int k) 
	{
		// create buckets
		int counter[] = new int[k + 1];
		// fill buckets
		for (int i : input) {
			counter[i]++;
		}
	    
		// sort array
		int ndx = 0;
		for (int i = 0; i < counter.length; i++) 
		{
			while (0 < counter[i]) {
				input[ndx++] = i;
				counter[i]--;
			}
		}
	}
}
