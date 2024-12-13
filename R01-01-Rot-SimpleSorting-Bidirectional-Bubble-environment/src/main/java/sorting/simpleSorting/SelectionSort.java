package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean troca = true;
		while(troca) {
			troca = false;
			int maior = leftIndex;
			for (int i = 0; i < rightIndex; i ++) {
				if (array[i].compareTo(array[maior]) < 0) {
					Util.swap(array, i, maior);
					maior = i;
					troca = true;
				}
			}
		}	
	}
}
