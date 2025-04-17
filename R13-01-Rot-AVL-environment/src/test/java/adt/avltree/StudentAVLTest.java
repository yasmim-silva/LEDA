package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import adt.bst.BSTNode;

public class StudentAVLTest {

	private AVLTree<Integer> avl;
	private BSTNode<Integer> NIL = new BSTNode<Integer>();
	private AVLTreeVerifierImpl<Integer> verifier;

	@Before
	public void setUp() {
		avl = new AVLTreeImpl<>();
		verifier = new AVLTreeVerifierImpl<>(avl);
	}

	@Test
	public void testInit() {
		assertTrue(avl.isEmpty());
		assertEquals(0, avl.size());
		assertEquals(-1, avl.height());
		assertEquals(NIL, avl.getRoot());
	}

	@Test
	public void testInsert() {
		avl.insert(-10);
		assertEquals(1, avl.size());
		assertEquals(0, avl.height());
		assertArrayEquals(new Integer[] { -10 }, avl.preOrder());

		assertFalse(avl.isEmpty());
		assertEquals(new Integer(-10), avl.getRoot().getData());

		avl.insert(-15);
		assertEquals(2, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15 }, avl.preOrder());

		avl.insert(20);
		assertEquals(3, avl.size());
		assertEquals(1, avl.height());
		assertArrayEquals(new Integer[] { -10, -15, 20 }, avl.preOrder());
	}

	@Test
	public void testRemove() {
		avl.insert(55);
		avl.insert(9);
		avl.insert(91);
		avl.insert(12);

		avl.remove(-1);
		assertEquals(4, avl.size());

		avl.remove(91);
		assertEquals(3, avl.size());
		assertArrayEquals(new Integer[] { 12, 9, 55 }, avl.preOrder());

		avl.remove(12);
		assertEquals(2, avl.size());
		assertArrayEquals(new Integer[] { 55, 9 }, avl.preOrder());

		avl.remove(9);
		avl.remove(55);
		assertEquals(NIL, avl.getRoot());
		assertTrue(avl.isEmpty());
	}

	// Testes da classe AVLTreeVerifierImpl
	@Test
	public void testIsAVLSimples() {
		assertTrue(verifier.isAVLTree());

		avl.insert(10);
		assertTrue(verifier.isAVLTree());

		avl.insert(5);
		avl.insert(15);
		assertTrue(verifier.isAVLTree());
	}

	@Test
	public void testIsAVLComRotacoes() {
		avl.insert(30);
		avl.insert(20);
		avl.insert(10); // LL
		assertTrue(verifier.isAVLTree());

		avl.insert(25);
		avl.insert(28); // LR/RL
		assertTrue(verifier.isAVLTree());

		avl.insert(40);
		avl.insert(50); // RR
		assertTrue(verifier.isAVLTree());
	}

	@Test
	public void testIsAVLComRemocao() {
		avl.insert(50);
		avl.insert(30);
		avl.insert(70);
		avl.insert(20);
		avl.insert(40);
		avl.insert(60);
		avl.insert(80);

		assertTrue(verifier.isAVLTree());

		avl.remove(20);
		avl.remove(70); 

		assertTrue(verifier.isAVLTree());
	}
}
