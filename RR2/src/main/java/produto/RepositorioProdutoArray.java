package produto;

public class RepositorioProdutoArray<T extends Produto> implements RepositorioProdutos<T>{

/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private T[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoArray(int size) {
		super();
		this.produtos = (T[]) new Object[size];
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		for (int i = 0; i < this.produtos.length; i++) {
			if (this.produtos[i] != null && this.produtos[i].getCodigo() == (codigo)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		if (procurarIndice(codigo) != -1) {
			return true;
		}
		return false;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(T produto) {
		for (int i = 0; i < this.produtos.length; i++) {
			if (this.produtos[i] == null) {
				this.produtos[i] = produto;
				return;
			}
		}
		throw new RuntimeException("Repositório cheio");
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(T produto) {
		if (procurarIndice(produto.getCodigo()) != -1) {
			this.produtos[procurarIndice(produto.getCodigo())] = produto;
		}
		throw new RuntimeException("Produto não encontrado para atualizar.");
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		int indice = procurarIndice(codigo);
		if (indice == -1) {
			throw new RuntimeException("Produto não encontrado para remover.");
		}
		for (int i = indice; i < index; i++) {
			this.produtos[i] = this.produtos[i + 1];
		}
		this.produtos[index] = null;
		index--;
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public T procurar(int codigo) {
		if (procurarIndice(codigo) != -1) {
			return this.produtos[procurarIndice(codigo)];
		}
		throw new RuntimeException("Produto não encontrado.");
	}
}
