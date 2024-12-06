package vetor;

import static org.junit.Assert.*;
import org.junit.Test;


public class TestVetor {

    @Test
	public void testInserir() {
		Aluno aluno1 = new Aluno("lucas", 7.0);
		Aluno aluno2 = new Aluno("alice", 7.5);

		Vetor<Aluno> v = new Vetor<Aluno>(3);
		v.inserir(aluno1);
		v.inserir(aluno2);
		assertFalse(v.isVazio());
	}

	@Test
	public void testRemover() {
		Aluno aluno1 = new Aluno("lucas", 7.0);

		Vetor<Aluno> v = new Vetor<Aluno>(1);
		v.inserir(aluno1);
		assertFalse(v.isVazio());

		v.remover(aluno1);
		assertTrue(v.isVazio());
	}

	@Test
	public void testProcurar() {
		Aluno aluno1 = new Aluno("lucas", 7.0);

		Vetor<Aluno> v = new Vetor<Aluno>(1);
		v.inserir(aluno1);

		assertEquals(aluno1, v.procurar(aluno1));
	}

	@Test
	public void testVetorMaximo() {
		Aluno aluno1 = new Aluno("lucas", 7.0);
		Aluno aluno2 = new Aluno("alice", 7.5);

		Vetor<Aluno> v = new Vetor<Aluno>(3);
		v.inserir(aluno1);
		v.inserir(aluno2);

		ComparadorMaximo<Aluno> comparadorMaximo = new ComparadorMaximo<>();
        ComparadorMinimo<Aluno> comparadorMinimo = new ComparadorMinimo<>();

		v.setComparadorMaximo(comparadorMaximo);
		v.setComparadorMinimo(comparadorMinimo);

		assertEquals(aluno2, v.vetorMaximo());
		assertEquals(aluno1, v.vetorMinimo());
	}
}
