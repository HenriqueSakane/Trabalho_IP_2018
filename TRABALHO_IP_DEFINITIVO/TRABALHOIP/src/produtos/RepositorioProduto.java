package Produtos;

import ExcecoesProduto.NumeroLimiteExcedido;
import ExcecoesProduto.ProdutoJaCadastrado;
import ExcecoesProduto.ProdutoNaoCadastrado;
import ExcecoesProduto.ProdutoNaoEncontrado;
import Produtos.ClasseProduto;

public interface RepositorioProduto {
	void inserir(ClasseProduto produto)throws NumeroLimiteExcedido, ProdutoJaCadastrado;

	void remover(String nome)throws ProdutoNaoCadastrado;

	boolean existe(String nome);

	ClasseProduto procurar(String nome)throws ProdutoNaoEncontrado;

	void atualizar(ClasseProduto nome)throws ProdutoNaoEncontrado;

}
