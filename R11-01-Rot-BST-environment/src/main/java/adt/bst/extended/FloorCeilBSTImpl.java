package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		for (Integer element: array){
			this.insert(element);
		}	
		return floor((BSTNode<Integer>) this.getRoot(), numero, null);
	}

	private Integer floor(BSTNode<Integer> node, double numero, Integer candidato) {
		if(node == null || node.getData() == null) {
			return candidato;
		}
		if(node.getData() == numero) {
			return node.getData();
		}
		if (node.getData() > numero) {
			return floor((BSTNode<Integer>) node.getLeft(), numero, candidato);
		} else {
			return floor((BSTNode<Integer>) node.getRight(), numero, node.getData());
		}
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		for (Integer element: array){
			this.insert(element);
		}	
		return ceil((BSTNode<Integer>) this.getRoot(), numero, null);
	}

	private Integer ceil(BSTNode<Integer> node, double numero, Integer candidato) {
		if(node == null || node.getData() == null) {
			return candidato;
		}
		if(node.getData() == numero) {
			return node.getData();
		}
		if (node.getData() > numero) {
			return ceil((BSTNode<Integer>) node.getLeft(), numero, node.getData()); 
		} else {
			return ceil((BSTNode<Integer>) node.getRight(), numero, candidato);
		}
	}
}
