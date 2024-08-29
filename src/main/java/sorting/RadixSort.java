package sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the classic Radix Sort Example.  Given a disordered list of integers, rearrange them in natural order.
 * 
 * Runtime Complexity: 
 *   Best Case O(k*n); Average Case O(k*n); Worst Case O(k*n), where k is the length of the longest number and n is the
 *   size of the input array.
 *
 *   Note: if k is greater than log(n) then an n*log(n) algorithm would be a better fit. In reality we can always 
 *   change the radix to make k less than log(n).
 *   
 * Approach:
 *   radix sort, like counting sort and bucket sort, is an integer based algorithm (i.e. the values of the input array 
 *   are assumed to be integers). Hence radix sort is among the fastest sorting algorithms around, in theory. The 
 *   particular distinction for radix sort is that it creates a bucket for each cipher (i.e. digit); as such, similar to
 *   bucket sort, each bucket in radix sort must be a growable list that may admit different keys.
 *
 *   For decimal values, the number of buckets is 10, as the decimal system has 10 
 *   numerals/cyphers (i.e. 0,1,2,3,4,5,6,7,8,9). Then the keys are continuously sorted by significant digits.
 * 
 * @author bgoff
 * @since 29 Aug 2024
 */
public class RadixSort 
{
	public static void sort(int[] input) 
	{
		final int RADIX = 10;
		// declare and initialize bucket[]
		
		@SuppressWarnings("unchecked")
		List<Integer>[] bucket = new ArrayList[RADIX];
		
		for (int i = 0; i < bucket.length; i++)
			bucket[i] = new ArrayList<Integer>();
		
		 
		// sort
		boolean maxLength = false;
		int tmp = -1, placement = 1;
		
		while (!maxLength) {
			maxLength = true;
			// split input between lists
			for (Integer i : input) {
				tmp = i / placement;
				bucket[tmp % RADIX].add(i);
				if (maxLength && tmp > 0) {
					maxLength = false;
				}
			}
			// empty lists into input array
			int a = 0;
			for (int b = 0; b < RADIX; b++) {
				for (Integer i : bucket[b]) {
					input[a++] = i;
				}
				bucket[b].clear();
			}
			// move to next digit
			placement *= RADIX;
		}
	}
}
