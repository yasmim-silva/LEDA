package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if(node.isEmpty() || element.equals(node.getData())) {
			return node;
		}
		if(node.getData().compareTo(element) > 0) {
			return search(element, (BSTNode<T>) node.getLeft());
		} else {
			return search(element, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	}

	public void insert(BSTNode<T> node, T element) {
		if(node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().data(null).right(null).left(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).right(null).left(null).parent(node).build());
		} else {
			if(node.getData().compareTo(element) > 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else {
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if(!isEmpty()) {
			return maximum(root);
		} else {
			return null;
		}
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if(node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>)node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if(!isEmpty()) {
			return minimum(root);
		} else {
			return null;
		}
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if(node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>)node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if(!isEmpty() && !node.isEmpty()) {
			if(!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());
			} else {
				return sucessor(node);
			}
		}
		return null;
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		BSTNode<T> sucessor = (BSTNode<T>) node.getParent();
		if(sucessor != null && node.equals(sucessor.getRight())) {
			sucessor = sucessor((BSTNode<T>) node.getParent());
		}
		return sucessor;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if(!isEmpty() && !node.isEmpty()) {
			if(!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());
			} else {
				return predecessor(node);
			}
		}
		return null;
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> predecessor = (BSTNode<T>) node.getParent();
		if(predecessor != null && node.equals(predecessor.getLeft())) {
			predecessor = predecessor((BSTNode<T>) node.getParent());
		}
		return predecessor;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if(!node.isEmpty()) {
			remove(node, element);
		}
	}

	public void remove(BSTNode<T> node, T element) {
		if(!node.isEmpty()) {
			if(node.getData().compareTo(element) == 0) {
				//se não possui filhos
				if(node.getLeft().isEmpty() && node.getRight().isEmpty()) {
					node.setData(null);
				} 
				//se possui 2 filhos
				else if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
					BSTNode<T> sucessor = sucessor(node);
					node.setData(sucessor.getData());
					remove(sucessor, sucessor.getData());
				} 
				//se possui 1 filho
				else {
					if(!node.getLeft().isEmpty()) {
						if(node.equals(root)) {
							root = (BSTNode<T>) node.getLeft();
						} else {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}
					} else {
						node.getParent().setRight(node.getRight());
						node.getRight().setParent(node.getParent());
					}
				}
			}
		}
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
