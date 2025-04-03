package adt.heap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import adt.heap.extended.FloorCeilHeap;
import adt.heap.extended.FloorCeilHeapImpl;
import orderStatistic.OrderStatisticsHeapImpl;

public class StudentMinHeapTest {

	Heap<Integer> heap;
	OrderStatisticsHeapImpl<Integer> ordem;
	FloorCeilHeapImpl teste;

	@Before
	public void setUp() {
		// TODO Instancie seu comparator para fazer sua estrutura funcionar como
		// uma min heap aqui. Use instanciacao anonima da interface
		// Comparator!!!!
		Comparator<Integer> comparator = new ComparatorMaxHeap<>();
		heap = new HeapImpl<Integer>(comparator);
		ordem = new OrderStatisticsHeapImpl<>();
		teste = new FloorCeilHeapImpl(comparator);
	}

	@Test
	public void testBuild() {
		heap.buildHeap(new Integer[] { 82, 6, 99, 12, 34, 64, 58, 1 });

		assertEquals(8, heap.size());
		assertFalse(heap.isEmpty());
		//verifyHeap(new Integer[] { 1, 6, 58, 12, 34, 64, 99, 82 });
		verifyHeap(new Integer[] { 1, 6, 58, 12, 34, 99, 64, 82 });
	}

	@Test
	public void testInsert() {
		heap.insert(8);
		heap.insert(12);
		heap.insert(-2);
		heap.insert(7);
		heap.insert(8);
		heap.insert(-5);
		heap.insert(14);
		heap.insert(3);
		heap.insert(-10);
		heap.insert(0);

		assertEquals(10, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { -10, -5, -2, 3, 0, 8, 14, 12, 7, 8 });
	}

	@Test
	public void testRemove() {
		heap.insert(22);
		heap.insert(45);
		heap.insert(38);
		heap.insert(17);
		heap.insert(40);
		heap.insert(15);
		heap.insert(26);
		heap.insert(79);
		heap.insert(53);
		heap.insert(30);

		assertEquals(new Integer(15), heap.extractRootElement());
		assertEquals(new Integer(17), heap.extractRootElement());
		assertEquals(new Integer(22), heap.extractRootElement());
		assertEquals(new Integer(26), heap.extractRootElement());
		assertEquals(new Integer(30), heap.extractRootElement());

		assertEquals(5, heap.size());
		assertFalse(heap.isEmpty());

		verifyHeap(new Integer[] { 38, 40, 79, 45, 53 });
	}

	@Test
	public void testSort() {
		assertArrayEquals(new Integer[] { 5, 6, 12, 20, 34, 43, 49, 92 },
				heap.heapsort(new Integer[] { 34, 92, 5, 12, 49, 20, 43, 6 }));

		assertEquals(0, heap.size());
		assertTrue(heap.isEmpty());

		assertArrayEquals(new Integer[] {}, heap.toArray());
	}

	private void verifyHeap(Integer[] expected) {
		boolean isHeap = true;

		Comparable<Integer>[] original = heap.toArray();

		Arrays.sort(expected);
		Arrays.sort(original);

		if (Arrays.equals(expected, original) == false)
			isHeap = false;

		original = heap.toArray();

		for (int i = 0; i < original.length; i++) {
			if (2 * i + 1 < original.length && original[i].compareTo((Integer) original[2 * i + 1]) > 0)
				isHeap = false;
			if (2 * i + 2 < original.length && original[i].compareTo((Integer) original[2 * i + 2]) > 0)
				isHeap = false;
		}

		assertTrue(isHeap);
	}


	//Teste da classe OrderStatisticsHeapImpl
	@Test
	public void testStatistic() {
		Integer[] array = {3, 5, 7, 2, 8};
		assertEquals((Integer) 2, ordem.getOrderStatistics(array, 1)); // 1º menor
        assertEquals((Integer) 3, ordem.getOrderStatistics(array, 2)); // 2º menor
        assertEquals((Integer) 5, ordem.getOrderStatistics(array, 3)); // 3º menor
        assertEquals((Integer) 7, ordem.getOrderStatistics(array, 4)); // 4º menor
        assertEquals((Integer) 8, ordem.getOrderStatistics(array, 5)); // 5º menor
		
        assertNull(ordem.getOrderStatistics(array, 7)); // k maior que o tamanho
        assertNull(ordem.getOrderStatistics(array, 0)); // k inválido
        assertNull(ordem.getOrderStatistics(new Integer[]{}, 1)); // Array vazio
	}


	//Teste da classe FloorCeilHeapImpl
	@Test
	public void testFloorMinHeap() {
		Integer[] array = {10, 12, 20, 21, 7, 11, 8};

		assertEquals((Integer) 8, teste.floor(array, 9));
		assertEquals((Integer) 12, teste.floor(array, 12));
		assertEquals((Integer) 21, teste.floor(array, 25));
		assertEquals(null, teste.floor(array, 6)); // Nenhum número menor ou igual a 6
}

	@Test
	public void testCeilMinHeap() {
		Integer[] array = {10, 12, 20, 21, 7, 11, 8};

		assertEquals((Integer) 10, teste.ceil(array, 9));
		assertEquals((Integer) 12, teste.ceil(array, 12));
		assertEquals(null, teste.ceil(array, 22)); // Nenhum número maior ou igual a 22
		assertEquals((Integer) 7, teste.ceil(array, 6)); // Menor valor da heap
	}

}
