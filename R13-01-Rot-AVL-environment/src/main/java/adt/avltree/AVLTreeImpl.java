package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

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
			rebalance(node);
		} 
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		removeRecursive(node);
	}
	
	private void removeRecursive(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				rebalanceUp((BSTNode<T>) node.getParent());
			} 
			else if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {
				BSTNode<T> filho;

				if (node.getLeft().isEmpty()) {
					filho = (BSTNode<T>) node.getRight();
				} else {
					filho = (BSTNode<T>) node.getLeft();
				}
		
				if (!node.getParent().isEmpty()) {
					if (node == node.getParent().getLeft()) {
						node.getParent().setLeft(filho);
					} else {
						node.getParent().setRight(filho);
					}
					filho.setParent(node.getParent());
				} else {
					root = filho;
					root.setParent(new BSTNode.Builder<T>().data(null).right(null).left(null).parent(null).build());
				}
		
				rebalanceUp((BSTNode<T>) filho);
			} 
			else {
				// Caso com 2 filhos
				BSTNode<T> successor = (BSTNode<T>) sucessor(node.getData());
				node.setData(successor.getData());
				removeRecursive(successor);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int out;
		if (node.isEmpty()) {
			out = 0;
		} else {
			out = height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
		}
		return out;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);

		if (Math.abs(balance) > 1) {
			BSTNode<T> novaRaiz = null;
			if (balance > 1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					// Caso LL
					novaRaiz = Util.rightRotation(node);
				} else {
					// Caso LR
					Util.leftRotation((BSTNode<T>) node.getLeft());
					novaRaiz = Util.rightRotation(node);
				}
			} else if (balance < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					// Caso RR
					novaRaiz = Util.leftRotation(node);
				} else {
					// Caso RL
					Util.rightRotation((BSTNode<T>) node.getRight());
					novaRaiz = Util.leftRotation(node);
				}
			}
			if (novaRaiz != null && novaRaiz.getParent().isEmpty()) {
				this.root = novaRaiz;
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> nodeAux = node;
		while (!nodeAux.isEmpty()) {
			rebalance(nodeAux);
			nodeAux = (BSTNode<T>) nodeAux.getParent();
		}
	}
}
