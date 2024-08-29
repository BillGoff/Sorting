package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a disordered list of integers, rearrange them in natural order.
 * 
 * Runtime Complexity: 
 * Best Case O(n); Average Case O(n); Worst Case O(n).
 * 
 * Approach:
 *   If it sounds too good to be true, then most likely it's not true.  Bucketsort is not an exception to this adage. 
 *   For bucketsort to work at its blazing efficiency, there are multiple prerequisites. First the hash function that 
 *   is used to partition the elements need to be very good and must produce ordered hash: 
 *   	if i < k then hash(i) < hash(k).
 *   Second, the elements to be sorted must be uniformly distributed.
 *
 *   The aforementioned aside, bucket sort is actually very good considering that counting sort is reasonably speaking 
 *   its upper bound. And counting sort is very fast. The particular distinction for bucket sort is that it uses a hash 
 *   function to partition the keys of the input array, so that multiple keys may hash to the same bucket. Hence each 
 *   bucket must effectively be a growable list; similar to radix sort.
 *
 *   Numerous Internet sites, including university pages, have erroneously written counting sort code and call them 
 *   bucket sort. Bucket sort uses a hash function to distribute keys; counting sort creates a bucket for each key. 
 *   Indeed there are perhaps greater similarities between radix sort and bucket sort, than there are between counting 
 *   sort and bucket sort.
 *
 *   In the presented program Java's Collections.sort(C) is used to sort each bucket. This is to install the idea that 
 *   the bucket sort algorithm does not specify which sorting technique to use on the buckets. A programmer may choose 
 *   to continuously use bucket sort on each bucket until the collection is sorted (in the manner of the radix sort 
 *   program below). Whichever sorting method is used on the buckets, bucket sort still tends toward O(n).
 *   
 * @author bgoff
 * @since 29 Aug 2024
 */
public class BucketSort {
	
	public static void sort(int[] input) 
	{
		//get hash codes
		final int[] code = hash(input);
		
		int size = code [1];
		
		//create and initialize buckets to ArrayList: O(n)
		@SuppressWarnings("unchecked")
		List<Integer> [] buckets = new List [size];
		
		for (int i = 0; i < code[1]; i++)
			buckets[i] = new ArrayList<Integer>();
		
		//distribute data into buckets: O(n)
		for (int i : input) {
			buckets[hash(i, code)].add(i);
		}
		/**
		 * Sort each bucket: O(n).
		 * I mentioned above that the worst case for bucket sort is counting
		 * sort. That's because in the worst case, bucket sort may end up
		 * with one bucket per key. In such case, sorting each bucket would
		 * take 1^2 = O(1). Even after allowing for some probabilistic
		 * variance, to sort each bucket would still take 2-1/n, which is
		 * still a constant. Hence, sorting all the buckets takes O(n).
		 ***/
		for (List<Integer> bucket : buckets) 
			Collections.sort(bucket);
	
		int ndx = 0;
		//merge the buckets: O(n)
		for (int b = 0; b < buckets.length; b++) 
		{
			for (int v : buckets[b]) {
				input[ndx++] = v;
			}
		}
	}
		 
	private static int[] hash(int[] input) 
	{
		int m = input[0];
		for (int i = 1; i < input.length; i++) {
			if (m < input[i]) {
				m = input[i];
			}
		}
		return new int[]{m, (int) Math.sqrt(input.length)};
	}
		 
	private static int hash(int i, int[] code) {
		return (int) ((double) i / code[0] * (code[1] - 1));
	}

}
