package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			if(!isFull()) {
				int probe = 0;
				int i = ((HashFunctionLinearProbing) this.hashFunction).hash(element, probe);
				while (table[i] != null && probe != this.capacity()) {
					probe++;
					this.COLLISIONS++;
					i = ((HashFunctionLinearProbing) this.hashFunction).hash(element, probe);
				}
				table[i] = element;
				this.elements++;
			}
		}
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T search(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		if(element != null && !this.isEmpty()) {
			
		}
		return index;
	}

}
