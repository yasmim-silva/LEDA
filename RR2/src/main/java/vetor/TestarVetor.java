package vetor;

public class TestarVetor {

	public static void main(String[] args) {
		// preencha esse metodo com codigo para testar a classe vetor.
		// À medida que voce evoluir no exercício o conteúdo deste mpetodo
		// também será modificado.

		Aluno aluno1 = new Aluno("lucas", 7.0);
		Aluno aluno2 = new Aluno("alice", 7.5);
		Aluno aluno3 = new Aluno("maria", 5.0);

		Vetor v = new Vetor(3);
		v.inserir(aluno1);
		v.inserir(aluno2);
		v.inserir(aluno3);

		//System.out.println(v.);
	}
}
