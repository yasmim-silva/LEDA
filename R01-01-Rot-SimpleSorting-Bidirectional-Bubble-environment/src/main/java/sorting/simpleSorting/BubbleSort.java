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
		boolean troca = true;
		while (troca) {
			troca = false;
			for (int i = leftIndex; i < rightIndex; i ++) {
				int indice = leftIndex;
				while ((indice + 1) <= rightIndex) {
					if (array[indice].compareTo(array[indice + 1]) > 0) {
						Util.swap(array, indice, indice + 1);
						troca = true;
					}
					indice++;
				}
				rightIndex--; 
			}
		}
	}
}
