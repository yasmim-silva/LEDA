package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
		if (node1.isEmpty() && node2.isEmpty()) {
			return true;
		}

		if (node1.isEmpty() || node2.isEmpty()) {
			return false;
		}
	
		boolean data = node1.getData().equals(node2.getData());
    	boolean left = equals((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft());
    	boolean right = equals((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());

    	return data && left && right;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2) {
		if (node1.isEmpty() && node2.isEmpty()) {
			return true;
		}

		if (node1.isEmpty() || node2.isEmpty()) {
			return false;
		}

		boolean left = isSimilar((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft()); 
		boolean right = isSimilar((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
		
		return left && right;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		if(k > tree.size() || k < 1) {
			return null;
		}
		BSTNode<T> element = tree.minimum();
		if(k == 1) {
			return element.getData();
		} else {
			return orderStatistic(tree, tree.sucessor(element.getData()), k - 1);
		}
	}

	private T orderStatistic(BST<T> tree, BSTNode<T> node, int k) {
		if(k == 1) {
			return node.getData();
		} else {
			return orderStatistic(tree, tree.sucessor(node.getData()), k - 1);
		}
	}

}
