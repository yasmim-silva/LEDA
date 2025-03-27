package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.bst.BSTImpl;
import adt.bt.BTNode;
import adt.bst.SimpleBSTManipulationImpl;
import adt.bst.extended.FloorCeilBSTImpl;

public class StudentBSTTest {

	private BSTImpl<Integer> tree;
	private BSTImpl<Integer> tree2;
	private SimpleBSTManipulationImpl manipulation;
	private FloorCeilBSTImpl floorCeil;
	private BTNode<Integer> NIL = new BTNode<Integer>();

	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}

	@Before
	public void setUp() {
		tree = new BSTImpl<>();
		tree2 = new BSTImpl<>();
		manipulation = new SimpleBSTManipulationImpl<>();
		floorCeil = new FloorCeilBSTImpl();
	}

	@Test
	public void testInit() {
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(-1, tree.height());

		assertEquals(NIL, tree.getRoot());

		assertArrayEquals(new Integer[] {}, tree.order());
		assertArrayEquals(new Integer[] {}, tree.preOrder());
		assertArrayEquals(new Integer[] {}, tree.postOrder());

		assertEquals(NIL, tree.search(12));
		assertEquals(NIL, tree.search(-23));
		assertEquals(NIL, tree.search(0));

		assertEquals(null, tree.minimum());
		assertEquals(null, tree.maximum());

		assertEquals(null, tree.sucessor(12));
		assertEquals(null, tree.sucessor(-23));
		assertEquals(null, tree.sucessor(0));

		assertEquals(null, tree.predecessor(12));
		assertEquals(null, tree.predecessor(-23));
		assertEquals(null, tree.predecessor(0));
	}

	@Test
	public void testMinMax() {
		tree.insert(6);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(6), tree.maximum().getData());

		tree.insert(23);
		assertEquals(new Integer(6), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(-34);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(5);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());

		tree.insert(9);
		assertEquals(new Integer(-34), tree.minimum().getData());
		assertEquals(new Integer(23), tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, tree.predecessor(-40));
		assertEquals(new Integer(-34), tree.sucessor(-40).getData());

		assertEquals(new Integer(-40), tree.predecessor(-34).getData());
		assertEquals(new Integer(0), tree.sucessor(-34).getData());

		assertEquals(new Integer(-34), tree.predecessor(0).getData());
		assertEquals(new Integer(2), tree.sucessor(0).getData());

		assertEquals(new Integer(0), tree.predecessor(2).getData());
		assertEquals(new Integer(5), tree.sucessor(2).getData());
	}

	@Test
	public void testSize() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		int size = 12;
		assertEquals(size, tree.size());

		while (!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
	}

	@Test
	public void testHeight() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] preOrder = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12,
				76, 67, 232 };
		assertArrayEquals(preOrder, tree.preOrder());
		assertEquals(4, tree.height());

		tree.remove(0);
		assertEquals(3, tree.height());

		tree.remove(2);
		assertEquals(3, tree.height());
	}

	@Test
	public void testRemove() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		Integer[] order = { -40, -34, 0, 2, 5, 6, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(6);
		order = new Integer[] { -40, -34, 0, 2, 5, 9, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		tree.remove(9);
		order = new Integer[] { -40, -34, 0, 2, 5, 12, 23, 67, 76, 232 };
		assertArrayEquals(order, tree.order());

		assertEquals(NIL, tree.search(6));
		assertEquals(NIL, tree.search(9));
	}

	@Test
	public void testSearch() {

		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(new Integer(-40), tree.search(-40).getData());
		assertEquals(new Integer(-34), tree.search(-34).getData());
		assertEquals(NIL, tree.search(2534));
	}


	//Testes da classe SimpleBSTManipulationImpl
	@SuppressWarnings("unchecked")
	@Test
	public void testEquals() {
		assertEquals(true, manipulation.equals(tree, tree2));

		tree.insert(9);
		tree.insert(5);
		tree.insert(4);
		tree.insert(10);

		tree2.insert(9);
		tree2.insert(5);
		tree2.insert(4);
		tree2.insert(10);

		assertEquals(true, manipulation.equals(tree, tree2));

		tree2.remove(10);
		assertEquals(false, manipulation.equals(tree, tree2));
	}	

	@SuppressWarnings("unchecked")
	@Test
	public void testSimilar() {
		assertEquals(true, manipulation.isSimilar(tree, tree2));

		tree.insert(9);
		tree.insert(5);
		tree.insert(4);
		tree.insert(10);

		tree2.insert(8);
		tree2.insert(7);
		tree2.insert(3);
		tree2.insert(11);

		assertEquals(true, manipulation.isSimilar(tree, tree2));

		tree2.remove(11);
		assertEquals(false, manipulation.isSimilar(tree, tree2));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testOrderStatistic() {
		fillTree(); // -40 -34 0 2 5 6 9 12 23 67 76 232

		assertEquals(null, manipulation.orderStatistic(tree, 0));
		assertEquals(null, manipulation.orderStatistic(tree, 13));

		assertEquals(-40, manipulation.orderStatistic(tree, 1));
		assertEquals(0, manipulation.orderStatistic(tree, 3));
		assertEquals(9, manipulation.orderStatistic(tree, 7));
		assertEquals(67, manipulation.orderStatistic(tree, 10));
		assertEquals(232, manipulation.orderStatistic(tree, 12));
	}

	//Testes da classe FloorCeilBSTImpl
	@Test
	public void testFloor() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		
		assertEquals((Integer)2, floorCeil.floor(array, 2));
		assertEquals((Integer)2, floorCeil.floor(array, 4));
		assertEquals((Integer)12, floorCeil.floor(array, 13));
		assertEquals((Integer)232, floorCeil.floor(array, 232));

		assertEquals((Integer)232, floorCeil.floor(array, 240));
		assertEquals(null, floorCeil.floor(array, -45));
	}

	@Test
	public void testCeil() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		
		assertEquals((Integer)5, floorCeil.ceil(array, 4));
		assertEquals((Integer)5, floorCeil.ceil(array, 5));
		assertEquals((Integer)2, floorCeil.ceil(array, 1));
		assertEquals((Integer)76, floorCeil.ceil(array, 70));

		assertEquals(null, floorCeil.ceil(array, 240));
		assertEquals((Integer)(-40), floorCeil.ceil(array, -45));
	}
}
