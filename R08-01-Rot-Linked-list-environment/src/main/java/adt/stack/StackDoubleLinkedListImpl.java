package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
            throw new StackOverflowException();
        }
		if(element != null) {
        	top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
            throw new StackUnderflowException();
        }
        T element = top.toArray()[top.toArray().length - 1];
        top.removeLast(); 
        return element;
	}

	@Override
	public T top() {
		return top.toArray()[top.toArray().length - 1];
	}

	@Override
	public boolean isEmpty() {
		return top.size() == 0;
	}

	@Override
	public boolean isFull() {
		return top.size() == size;
	}

}
