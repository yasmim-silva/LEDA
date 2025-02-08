package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || leftIndex < 0 || rightIndex >= array.length || leftIndex >= rightIndex) {
            return;
        }
		ordenaArray(array, leftIndex, rightIndex);	
	}

	private int[] calculaExtremos(Integer[] array, int leftIndex, int rightIndex) {
		int maior = array[rightIndex];
		int menor = array[leftIndex];

		for (int i = leftIndex + 1; i < rightIndex; i++) {
			if (array[i] > maior) {
				maior = array[i]; 
			}
			if (array[i] < menor) {
				menor = array[i];
			}
		}

		return new int[]{menor, maior};
	}

	private Integer[] frequenciaAcumulada(Integer[] array, int leftIndex, int rightIndex) {
		int[] extremos = calculaExtremos(array, leftIndex, rightIndex);
    	int menor = extremos[0];
    	int maior = extremos[1];
		Integer[] A = new Integer[maior - menor + 1];

		for (int i = 0; i < A.length; i++) {
            A[i] = 0;
        }

		//Calcula o número de ocorrências dos elementos do array.
		for (int i = leftIndex; i <= rightIndex; i++) {
			A[array[i] - menor]++;
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
		int[] extremos = calculaExtremos(array, leftIndex, rightIndex);
    	int menor = extremos[0];

		for (int i = rightIndex; i >= leftIndex; i--) {
			int indice = frequencia[array[i] - menor] - 1;
			B[indice] = array[i];
			frequencia[array[i] - menor]--; //Atualiza a posição.
		}

		//Copia os elementos do array B para o array original.
		for (int i = 0; i < B.length; i++) {
            array[leftIndex + i] = B[i]; 
        }

		return array;
	}
}
