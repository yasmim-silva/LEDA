package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return isBST() && isBalanced(avlTree.getRoot());
	}

	public boolean isBalanced(BSTNode<T> node) {
		boolean out = true;

		if (node != null && !node.isEmpty()) {
			int balance = avlTree.calculateBalance(node);

			if (Math.abs(balance) > 1) {
				out = false;
			}

			out = out && isBalanced((BSTNode<T>) node.getLeft());
			out = out && isBalanced((BSTNode<T>) node.getRight());
		}

		return out;
	}

}
