package produtos;

public interface RepositorioProduto {
	void inserir(ClasseProduto produto) throws NumeroLimiteExcedido, ProdutoJaCadastrado;

	void remover(String nome) throws ProdutoNaoCadastrado;

	boolean existe(String nome);

	ClasseProduto procurar(String nome) throws ProdutoNaoEncontrado;

	void atualizar(ClasseProduto nome) throws ProdutoNaoEncontrado;

}
