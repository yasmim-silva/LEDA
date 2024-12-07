package recursao;

import java.util.Arrays;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		if (array.length == 0) {
			return 0;
		}
		return array[0] + calcularSomaArray(Arrays.copyOfRange(array, 1, array.length));
	}
	public long calcularFatorial(int n) {
		if (n == 0) {
			System.out.println(n + "! = 1");
			return 1;
		} else {
			long result = n * (calcularFatorial(n - 1));
			System.out.println(n + "! = " + result);
			return result;
		}
	}

	public int calcularFibonacci(int n) {
		if (n == 0) {
			return 1;
		} 
		if (n == 1) {
			return 1;
		} else {
			int result = calcularFibonacci(n - 1) + calcularFibonacci(n-2);
			return result;
		}
	}

	public int countNotNull(Object[] array) {
		int result = 0;
		int indice = 0;
		if (array[indice] != null) {
			result += 1;
		}
		if ((indice + 1) != array.length) {
			indice++;
			Object[] novoArray = Arrays.copyOfRange(array, indice, array.length);
			result += countNotNull(novoArray);
		}
		return result;
	}

	public long potenciaDe2(int expoente) {
		long result = 0;
		if (expoente == 0) {
			return 1;
		}
		if (expoente == 1) {
			return 2;
		} 
		if (expoente - 1 != 0) {
			result += 2 * potenciaDe2(expoente-1);
		}
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		if (n == 1) {
			return termoInicial;
		} else {
			return razao + progressaoAritmetica(termoInicial, razao, n - 1);
		}
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		if (n == 1) {
			return termoInicial;
		} else {
			return razao * progressaoGeometrica(termoInicial, razao, n - 1);
		}
	}
	
	
}
