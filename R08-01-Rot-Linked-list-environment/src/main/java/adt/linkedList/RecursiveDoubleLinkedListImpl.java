package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
		
	}

    @Override
    public void insert(T element) {
        if(element != null) {
            if(isEmpty()) {
                this.setData(element);
                this.setNext(new RecursiveDoubleLinkedListImpl<>());
                ((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
            } else {
                this.getNext().insert(element);
            }
        }
    }

    @Override
	public void remove(T element) {
        if(!isEmpty()) {
            if(this.getData().equals(element)) {
                if(previous.isEmpty() && this.getNext().isEmpty()) {
                    this.setData(null);
                    this.setNext(null);
                    this.previous = null;
                } else {
                    this.setData(this.getNext().getData());
                    this.setNext(this.getNext().getNext());
                    if(this.getNext() != null) {
                        ((RecursiveDoubleLinkedListImpl<T>) this.getNext()).previous = this;
                    }
                }
            } else {
                this.getNext().remove(element);
            }
        }
    }

    @Override
	public void insertFirst(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.insert(element);
			} else {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>();
				newNode.setData(this.data);
				this.data = element;
				newNode.setNext(this.next);
				this.next = newNode;
				newNode.setPrevious(this);
				((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(newNode);
			}
		}
	}

	@Override
	public void removeFirst() {
        if (!isEmpty()) {
            if (this.previous != null) {
                this.previous = null;
            }
            if (this.getNext() != null) {
                this.setData(this.getNext().getData());
                this.setNext(this.getNext().getNext());
                if (this.getNext() != null) {
                    ((RecursiveDoubleLinkedListImpl<T>) this.getNext()).previous = this;
                }
            }
        }
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
            if (this.getNext().isEmpty()) {
                this.setData(null);
                this.setNext(null);
                if (this.previous.isEmpty()) {
					this.previous = null;
				}
            } else {
                ((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast();
            }
        }
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
