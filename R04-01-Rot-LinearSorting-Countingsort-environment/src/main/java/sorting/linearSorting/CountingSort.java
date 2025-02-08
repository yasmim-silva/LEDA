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
		if (array == null || leftIndex < 0 || rightIndex >= array.length || leftIndex >= rightIndex) {
            return;
        }
		ordenaArray(array, leftIndex, rightIndex);
	}

	private int calculaMaior(Integer[] array, int leftIndex, int rightIndex) {
		int maior = array[leftIndex];

		//Obtém o valor do maior elemento do array.
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] > maior) {
				maior = array[i]; 
			}
		}

		return maior;
	}

	private Integer[] frequenciaAcumulada(Integer[] array, int leftIndex, int rightIndex) {
		Integer[] A = new Integer[calculaMaior(array, leftIndex, rightIndex)+1];
		for (int i = 0; i < A.length; i++) {
            A[i] = 0;
        }

		//Calcula o número de ocorrências dos elementos do array.
		for (int i = leftIndex; i <= rightIndex; i++) {
			A[array[i]] += 1; 
		}

		//Acumula as ocorrências.
		for (int i = 1; i < A.length; i++) {
			A[i] = A[i] + A[i-1]; 
		}

		return A;
	}

	private Integer[] ordenaArray(Integer[] array, int leftIndex, int rightIndex) {
		Integer[] B = new Integer[rightIndex - leftIndex + 1];
		Integer[] frequencia = frequenciaAcumulada(array, leftIndex, rightIndex);

		for (int i = rightIndex; i >= leftIndex; i--) {
			int indice = frequencia[array[i]] - 1;
			B[indice] = array[i];
			frequencia[array[i]]--; //Atualiza a posição.
		}

		//Copia os elementos do array B para o array original.
		for (int i = 0; i < B.length; i++) {
            array[leftIndex + i] = B[i]; 
        }

		return array;
	}
}
