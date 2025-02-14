package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearchImpl implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		return buscaBinariaFloor(array, 0, array.length - 1, x);
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		return buscaBinariaCeil(array, 0, array.length - 1, x);
	}

	private Integer buscaBinariaFloor(Integer[] array, Integer left, Integer right, Integer x) {
		if (left > right) {
			return null;
		}
		int mid = (left + right) / 2;
		if (array[mid] <= x && array[mid+1] > x) {
			return array[mid];
		}
		if (array[mid] < x) {
			return buscaBinariaFloor(array, mid+1, right, x);
		} else {
			return buscaBinariaFloor(array, left, mid-1, x);
		}
	}

	private Integer buscaBinariaCeil(Integer[] array, Integer left, Integer right, Integer x) {
		if (left > right) {
			return null;
		}
		int mid = (left + right) / 2;
		if (array[mid] >= x && array[mid-1] < x) {
			return array[mid];
		}
		if (array[mid] > x) {
			return buscaBinariaCeil(array, left, mid-1, x);
		} else {
			return buscaBinariaCeil(array, mid+1, right, x);
		}
	}
}
