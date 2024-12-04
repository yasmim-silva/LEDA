package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T extends Comparable<T>>{

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator comparadorMaximo;
	private Comparator comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = -1;
		this.arrayInterno = (T[]) new Object[tamanho];
	}

	private int procurarIndice(T o) {
		for (int i = 0; i < this.arrayInterno.length; i++) {
			if (this.arrayInterno[i] != null && this.arrayInterno[i] == o) {
				return i;
			}
		}
		return -1;
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		this.indice++;
		this.arrayInterno[this.indice] = o;
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		int indice = procurarIndice(o);
		if (indice == -1) {
			throw new RuntimeException("Objeto não encontrado no vetor.");
		}
		for (int i = indice; i < this.tamanho-1; i++) {
			this.arrayInterno[i] = this.arrayInterno[i+1];
		}
		this.arrayInterno[tamanho-1] = null;
		return o;
	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		int indice = procurarIndice(o);
		if (indice == -1) {
			throw new RuntimeException("Objeto não encontrado no vetor.");
		} 
		return this.arrayInterno[indice];
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		return this.indice == -1;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		return this.indice == this.tamanho-1;
	}

	public T vetorMaximo() {
		if (isVazio()) {
			return null;
		}
		T max = this.arrayInterno[0];
		for (int i = 1; i < this.arrayInterno.length; i++) {
			if (this.comparadorMaximo.compare(max, this.arrayInterno[i]) < 0) {
				max = this.arrayInterno[i];
			}
		}
		return max;
	}

	public T vetorMinimo() {
		if (isVazio()) {
			return null;
		}
		T min = this.arrayInterno[0];
		for (int i = 1; i < this.arrayInterno.length; i++) {
			if (this.comparadorMinimo.compare(min, this.arrayInterno[i]) > 0) {
				min = this.arrayInterno[i];
			}
		}
		return min;
	}
}

class ComparadorMaximo implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		return Double.compare(o1.getMedia(), o2.getMedia());
	}

}

class ComparadorMinimo implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		return Double.compare(o2.getMedia(), o1.getMedia());
	}

}
