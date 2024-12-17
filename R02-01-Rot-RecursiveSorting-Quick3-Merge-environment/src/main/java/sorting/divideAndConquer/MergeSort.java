package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex) {
            return;
        }

		int mid = (leftIndex + rightIndex)/2;

		sort(array, leftIndex, mid);
		sort(array, mid + 1, rightIndex);
		mergeSort(array, leftIndex, mid, rightIndex);
	}

	@SuppressWarnings("unchecked")
	private void mergeSort(T[] array, int leftIndex, int mid, int rightIndex) {
		T[] arrayLeft = (T[]) new Comparable[mid - leftIndex + 1];
		T[] arrayRight = (T[]) new Comparable[rightIndex - mid];

		for (int i = 0; i < arrayLeft.length; i++) {
			arrayLeft[i] = array[leftIndex + i];
		}

		for (int i = 0; i < arrayRight.length; i++) {
			arrayRight[i] = array[mid + 1 + i];
		}

		int i = 0;
		int j = 0;
		int k = leftIndex;

		while (i < arrayLeft.length && j < arrayRight.length) {
			if (arrayLeft[i].compareTo(arrayRight[j]) <= 0) {
				array[k] = arrayLeft[i];
				i++;
			} else {
				array[k] = arrayRight[j];
				j++;
			}
			k++;
		}

		while (i < arrayLeft.length) {
			array[k] = arrayLeft[i];
			i++;
			k++;
		}

		while (j < arrayRight.length) {
			array[k] = arrayRight[j];
			j++;
			k++;
		}
	}
}
