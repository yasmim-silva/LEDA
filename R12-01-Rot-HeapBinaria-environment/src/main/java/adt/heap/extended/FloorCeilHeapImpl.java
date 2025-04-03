package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		if(array == null) {
			return null;
		}
		HeapImpl<Integer> heap = new HeapImpl<>(comparator);
		for(int i = 0; i < array.length; i++) {
			heap.insert(array[i]);
		}
		if(comparator.compare(heap.rootElement(), (int) numero) > 0) {
			return null;
		}
		return floor(heap, numero, null);
	}

	private Integer floor(HeapImpl<Integer> heap, double numero, Integer candidato) {
		if (heap.isEmpty()) {
			return candidato; 
		}
		Integer element = heap.extractRootElement(); 
		if (comparator.compare(element, (int) numero) > 0) {
			return candidato;
		}
		return floor(heap, numero, element);
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		if(array == null) {
			return null;
		}
		HeapImpl<Integer> heap = new HeapImpl<>(comparator);
		for(int i = 0; i < array.length; i++) {
			heap.insert(array[i]);
		}
		return ceil(heap, numero, null);
	}
	
	private Integer ceil(HeapImpl<Integer> heap, double numero, Integer candidato) {
		if (heap.isEmpty()) {
			return candidato; 
		}
		Integer element = heap.extractRootElement(); 
		if (element >= numero && (candidato == null || comparator.compare(element, candidato) < 0)) {
			candidato = element;
		}
		return ceil(heap, numero, candidato);
	}
}