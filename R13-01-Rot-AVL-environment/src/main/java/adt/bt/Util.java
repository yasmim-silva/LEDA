package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return - noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		node.setRight(pivot.getLeft());

		if (!pivot.getLeft().isEmpty()) {
			pivot.getLeft().setParent(node);
		}
		
		pivot.setLeft(node);
		pivot.setParent(node.getParent());

		if (!node.getParent().isEmpty()) {
			if (node.getParent().getLeft() == node) {
				node.getParent().setLeft(pivot);
			} else {
				node.getParent().setRight(pivot);
			}
		}
		node.setParent(pivot);
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return noh que se tornou a nova raiz
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setLeft(pivot.getRight());

		if (!pivot.getRight().isEmpty()) {
			pivot.getRight().setParent(node);
		}

		pivot.setRight(node);
		pivot.setParent(node.getParent());

		if (!node.getParent().isEmpty()) {
			if (node.getParent().getLeft() == node) {
				node.getParent().setLeft(pivot);
			} else {
				node.getParent().setRight(pivot);
			}
		} 
		node.setParent(pivot);
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
