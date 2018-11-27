package Produtos;

import ExcecoesProdutos.NumeroLimiteExcedido;

import ExcecoesProdutos.ProdutoJaCadastrado;
import ExcecoesProdutos.ProdutoNaoCadastrado;
import ExcecoesProdutos.ProdutoNaoEncontrado;
import Produtos.ClasseProduto;

public interface RepositorioProduto {
	void inserir(ClasseProduto produto) throws NumeroLimiteExcedido, ProdutoJaCadastrado;

	void remover(String nome) throws ProdutoNaoCadastrado;

	boolean existe(String nome);

	ClasseProduto procurar(String nome) throws ProdutoNaoEncontrado;

	void atualizar(ClasseProduto produto) throws ProdutoNaoEncontrado;

}
