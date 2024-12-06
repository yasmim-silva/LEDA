package vetor;

public class TestarVetor {

	public static void main(String[] args) {
		// preencha esse metodo com codigo para testar a classe vetor.
		// À medida que voce evoluir no exercício o conteúdo deste mpetodo
		// também será modificado.

		Aluno aluno1 = new Aluno("lucas", 7.0);
		Aluno aluno2 = new Aluno("alice", 7.5);

		Vetor<Aluno> v = new Vetor<Aluno>(2);
		v.inserir(aluno1);
		v.inserir(aluno2);

		ComparadorMaximo compMax = new ComparadorMaximo();
		v.setComparadorMaximo(compMax);
		ComparadorMinimo compMin = new ComparadorMinimo();
		v.setComparadorMinimo(compMin);

		System.out.println(compMax.compare(aluno1.getMedia(), aluno2.getMedia()));
		System.out.println(compMin.compare(aluno1.getMedia(), aluno2.getMedia()));
	}
}
