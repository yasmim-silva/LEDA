package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	@Override
	public boolean isEmpty() {
		return (data == null);
	}

	@Override
	public int size() {
		if(next == null) {
			return 0;
		} else {
			return 1 + next.size();
		}
	}

	@Override
	public T search(T element) {
		if(isEmpty()) {
			return null;
		} else {
			if(data.equals(element)) {
				return data;
			} else {
				return next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if(isEmpty()) {
				data = element;
				next = new RecursiveSingleLinkedListImpl<>();
			} else {
				next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(!isEmpty()) {
			if(data.equals(element)) {
				data = next.getData();
				next = next.getNext();
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		toArrayAux(array, this, 0);
		return array;
	}

	private void toArrayAux(T[] array, RecursiveSingleLinkedListImpl<T> node, int index) {
		if(!node.isEmpty()) {
			array[index] = node.getData();
			toArrayAux(array, node.getNext(), index + 1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
