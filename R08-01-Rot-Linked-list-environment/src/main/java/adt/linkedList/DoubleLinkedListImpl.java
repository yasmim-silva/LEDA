package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<>();
		this.last = (DoubleLinkedListNode<T>) this.head;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<>(), this.last);
			if (this.last.isNIL()) {
				this.setHead(newNode);
			} else {
				this.last.setNext(newNode);
			}
			this.last = newNode;
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.getHead().isNIL()) {
			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.getHead();
			while (!aux.isNIL() && !aux.getData().equals(element)) {
				aux = (DoubleLinkedListNode<T>) aux.getNext();
			}
			if (!aux.isNIL()) {
				if (aux == (DoubleLinkedListNode<T>) this.getHead()) {
					removeFirst();
				} else if (aux == last) {
					removeLast();
				} else {
					aux.getPrevious().setNext(aux.getNext());
					((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> left = (DoubleLinkedListNode<T>) this.getHead();
			DoubleLinkedListNode<T> right = last;

			while (!left.isNIL() && !right.isNIL() && left != right && left.getPrevious() != right) {
				if (left.getData().equals(element)) {
					return left.getData();
				}
				if (right.getData().equals(element)) {
					return right.getData();
				}
				left = (DoubleLinkedListNode<T>) left.getNext();
				right = right.getPrevious();
			}
			if (left == right && left.getData().equals(element)) {
				return left.getData();
			}
		}
		return null;
	}

	@Override
	public void insertFirst(T element) {
		if(element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, (DoubleLinkedListNode<T>) this.getHead(), new DoubleLinkedListNode<T>());
			((DoubleLinkedListNode<T>) this.getHead()).setPrevious(newNode);
			if (this.getHead().isNIL()) {
				this.setLast(newNode);
			} 
			this.setHead(newNode); 
		}
	}

	@Override
	public void removeFirst() {
		if(!head.isNIL()) {
			head = (DoubleLinkedListNode<T>) head.getNext();
			if(head.isNIL()) {
				last = ((DoubleLinkedListNode<T>) head);
			} else {
				((DoubleLinkedListNode<T>) head).setPrevious(new DoubleLinkedListNode<>());
			}
		}
	}

	@Override
	public void removeLast() {
		if(!last.isNIL()) {
			last = last.previous;
			if(last.isNIL()) {
				head = last;
			} else {
				last.next = new DoubleLinkedListNode<>();
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
