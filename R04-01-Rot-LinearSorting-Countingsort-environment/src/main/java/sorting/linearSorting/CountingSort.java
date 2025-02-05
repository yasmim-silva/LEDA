package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		Integer maior = leftIndex;
		//private void calculaMaior(array, leftIndex) {
			for (int i = leftIndex + 1; i < array.length; i++) {
				if (i > maior) {
					maior = i;
				}
			}
		//}
		//Criando um novo array do tamanho do maior elemento.
		Integer[] A = new Integer[maior];
		for (int i = leftIndex; i < array.length; i++) {
			A[array[i] - 1] += 1;
		}
		for (int i = 1; i < A.length; i++) {
			A[i] = A[i] + A[i-1];
		}
	}

}
