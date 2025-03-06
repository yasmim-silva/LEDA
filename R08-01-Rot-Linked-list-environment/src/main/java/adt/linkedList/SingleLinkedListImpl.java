package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int cont = 0;
		SingleLinkedListNode<T> aux = new SingleLinkedListNode<>();
		aux = head;
		while (!aux.isNIL()) {
			aux = aux.getNext();
			cont++;
		}
		return cont;
	}

	@Override
	public T search(T element) {
		if(isEmpty()) {
			return null;
		}
		SingleLinkedListNode<T> aux = new SingleLinkedListNode<>();
		aux = head;
		while (!aux.isNIL() && aux.getData() != element) {
			aux = aux.getNext();
		}
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if(head.isNIL()) {
				SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>();
				newNode.setData(element);
				newNode.setNext(head);
				head = newNode;
			} else {
				SingleLinkedListNode<T> aux = new SingleLinkedListNode<>();
				aux = head;
				while (!aux.isNIL()) {
					aux = aux.getNext();
				}
				aux.setData(element);
				aux.setNext(new SingleLinkedListNode<>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!head.isNIL()) {
			if(head.data.equals(element)) {
				head = head.getNext();
			} else {
				SingleLinkedListNode<T> aux = new SingleLinkedListNode<>();
				aux = head;
				while (!aux.getNext().isNIL() && !aux.getNext().getData().equals(element)) {
					aux = aux.getNext();
				}
				if(!aux.getNext().isNIL()) {
					aux.setNext(aux.getNext().getNext());
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		int size = size();
    	T[] array = (T[]) new Object[size];
    	SingleLinkedListNode<T> aux = head;
    	int i = 0;
    	while (!aux.isNIL()) {
        	array[i++] = aux.getData();
        	aux = aux.getNext();
    	}
    	return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
