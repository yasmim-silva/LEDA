package recursao;

public class TestarMetodosRecursivos {
	public static void main(String[] args) {
		MetodosRecursivos m = new MetodosRecursivos();

		//Testando soma de array
		int[] array = {1, 2, 8, 7};
		System.out.println(m.calcularSomaArray(array));

		//Testando o cálculo do fatorial
		System.out.println(m.calcularFatorial(4));
		System.out.println(m.calcularFatorial(5));

		//Testando a sequência de Fibonacci
		System.out.println(m.calcularFibonacci(5));
		System.out.println(m.calcularFibonacci(10));

		//Testando o cálculo de objetos não nulos em um array
		Object[] array2 = {2, 3, null, null, 7, null};
		System.out.println(m.countNotNull(array2));

		//Testando o cálculo do n-ésimo termo de uma PA
		System.out.println(m.progressaoAritmetica(2, 2, 5));
		System.out.println(m.progressaoAritmetica(2, 4, 7));

		//Testando o cálculo do n-ésimo termo de uma PG
		System.out.println(m.progressaoGeometrica(2, 2, 5));
		System.out.println(m.progressaoGeometrica(3, 5, 3));
	}
}
