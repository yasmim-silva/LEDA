package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(element != null && !isFull()) {
			int probe = 0;
			int index = ((HashFunctionQuadraticProbing) this.hashFunction).hash(element, probe);
			while (table[index] != null && !(table[index] instanceof DELETED) && probe != this.capacity()) {
				probe++;
				this.COLLISIONS++;
				index = ((HashFunctionQuadraticProbing) this.hashFunction).hash(element, probe);
			}
			table[index] = element;
			this.elements++;
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !this.isEmpty()) {
			int index = indexOf(element);
			if(index != -1 && table[index].equals(element)) {
				table[index] = new DELETED();
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T saida = null;
		if(element != null && !this.isEmpty()) {
			int index = indexOf(element);
			if(index != -1 && table[index] != null && !(table[index] instanceof DELETED) && table[index].equals(element)) {
				saida = (T) table[index];
			}
		}
		return saida;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		if(element != null) {
			int probe = 0;
			int i = ((HashFunctionQuadraticProbing) this.hashFunction).hash(element, probe);
			while (table[i] != null && index == -1 && probe != this.capacity()) {
				if(!(table[i] instanceof DELETED) && table[i].equals(element)) {
					index = i;
				} else {
					probe++;
					i = ((HashFunctionQuadraticProbing) this.hashFunction).hash(element, probe);
				}
			}
		}
		return index;
	}
}
