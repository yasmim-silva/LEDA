package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	private boolean disableRebalance = false;
	
	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if (array != null && array.length > 0) {
			Arrays.sort(array);

			disableRebalance = true;
			inserirBalanceado(array, 0, array.length - 1);
			disableRebalance = false;
		}
	}


	private void inserirBalanceado(T[] array, int inicio, int fim) {
		if (inicio <= fim) {
			int meio = (inicio + fim) / 2;
			this.insert(array[meio]); 
			inserirBalanceado(array, inicio, meio - 1);
			inserirBalanceado(array, meio + 1, fim);
		}
	}
		
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		if (Math.abs(balance) > 1) {
			BSTNode<T> novaRaiz = null;
			if (balance > 1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					// Caso LL
					novaRaiz = Util.rightRotation(node);
					LLcounter++;
				} else {
					// Caso LR
					Util.leftRotation((BSTNode<T>) node.getLeft());
					novaRaiz = Util.rightRotation(node);
					LRcounter++;
				}
			} else if (balance < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					// Caso RR
					novaRaiz = Util.leftRotation(node);
					RRcounter++;
				} else {
					// Caso RL
					Util.rightRotation((BSTNode<T>) node.getRight());
					novaRaiz = Util.leftRotation(node);
					RLcounter++;
				}
			}
			if (novaRaiz != null && novaRaiz.getParent().isEmpty()) {
				this.root = novaRaiz;
			}
		}
	}

	@Override
	public void insert(T element) {
		insert(root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).right(null).left(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).right(null).left(null).parent(node).build());
			if (node.getParent() == null) {
				node.setParent(new BSTNode.Builder<T>().data(null).right(null).left(null).parent(null).build());
			}
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0) {
				insert((BSTNode<T>) node.getRight(), element);
			}
		} 

		if (!disableRebalance) {
			rebalance(node);  
		}
	}
}
		