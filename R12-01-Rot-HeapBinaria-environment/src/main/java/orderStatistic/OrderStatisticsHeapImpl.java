package orderStatistic;

import java.util.Arrays;
import java.util.PriorityQueue;

public class OrderStatisticsHeapImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Existem diversas formas de se calcular uma estatistica de ordem. 
	 * Voce deve fazer isso usando uma heap restricoes:
	 * - nenhuma copia ou estrutura array auxiliar serah permitida, exceto o uso de
	 *   uma PriorityQueue
	 * - caso a estatistica de ordem procurada nao exista no array o metodo deve retornar null 
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	
	@Override
	public T getOrderStatistics(T[] array, int k) {
		T result;
		if(array == null || k < 1 || k > array.length) {
			result = null;
		} else {
			PriorityQueue<T> heap = new PriorityQueue<T>();
			heap.addAll(Arrays.asList(array));
			result = getOrderStatistics(heap, k);
		}
		return result;
	}

	private T getOrderStatistics(PriorityQueue<T> heap, int k) {
		T result;
		if (k == 1) {
			result = heap.poll();
		} else {
			heap.poll();
			result = getOrderStatistics(heap, k - 1);
		}
		return result;
	}
	
}
