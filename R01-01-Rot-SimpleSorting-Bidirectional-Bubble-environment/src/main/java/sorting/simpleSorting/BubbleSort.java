package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int indice = leftIndex;
		for (int i = 0; i < rightIndex; i ++) {
			while ((indice + 1) != rightIndex + 1) {
				if (array[indice].compareTo(array[indice + 1]) > 0) {
					Util.swap(array, indice, indice + 1);
				}
				indice++;
			}
			rightIndex--; 
			indice = leftIndex;
		}
	}
}
