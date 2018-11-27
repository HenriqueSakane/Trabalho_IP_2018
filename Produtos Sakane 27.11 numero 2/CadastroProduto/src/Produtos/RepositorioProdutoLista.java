package Produtos;

import ExcecoesProduto.NumeroLimiteExcedido;
import ExcecoesProduto.ProdutoJaCadastrado;
import ExcecoesProduto.ProdutoNaoCadastrado;
import ExcecoesProduto.ProdutoNaoEncontrado;
import Produtos.ClasseProdutos;

public class RepositorioProdutoLista implements RepositorioProduto {
	private ClasseProdutos produtos;
	private RepositorioProdutoLista proxProduto;
	private int quantidadeProdutos;

	public RepositorioProdutoLista() {
		this.produtos = null;
		this.quantidadeProdutos = 0;
		this.proxProduto = null;
	}

	public void inserir(ClasseProdutos ContaProduto) throws NumeroLimiteExcedido, ProdutoJaCadastrado {
		boolean existe = this.existe(ContaProduto.getNome());
		if (existe == false) {
			if (this.quantidadeProdutos < 50) {
				if (this.produtos == null) {
					this.produtos = ContaProduto;
					this.quantidadeProdutos = this.quantidadeProdutos + 1;
					this.proxProduto = new RepositorioProdutoLista();
				} else {
					this.proxProduto.inserir(ContaProduto);
				}
			} else {
				throw new NumeroLimiteExcedido();
			}
		} else {
			throw new ProdutoJaCadastrado();
		}

	}

	public void remover(String nomeProduto) throws ProdutoNaoCadastrado {
		if (this.produtos != null) {
			if (this.produtos.getNome().equals(nomeProduto)) {
				this.produtos = this.proxProduto.produtos;
				this.proxProduto = this.proxProduto.proxProduto;
				this.quantidadeProdutos = this.quantidadeProdutos - 1;
			} else {
				this.proxProduto.remover(nomeProduto);
			}
		} else {
			throw new ProdutoNaoCadastrado();
		}
	}

	public boolean existe(String nomeProduto) {
		if (this.produtos != null) {
			if (this.produtos.getNome().equals(nomeProduto)) {
				return true;
			} else {
				return this.proxProduto.existe(nomeProduto);
			}
		} else {
			return false;
		}
	}

	public ClasseProdutos procurar(String nomeProduto) throws ProdutoNaoEncontrado {
		if (this.produtos != null) {
			if (this.produtos.getNome().equals(nomeProduto)) {
				return this.produtos;
			} else {
				return this.proxProduto.procurar(nomeProduto);
			}
		} else {
			throw new ProdutoNaoEncontrado();
		}
	}

	public void atualizar(ClasseProdutos ContaProduto) throws ProdutoNaoEncontrado {
		if (this.produtos != null) {
			if (this.produtos.getNome().equals(ContaProduto.getNome())) {
				this.produtos = ContaProduto;
			} else {
				this.proxProduto.atualizar(ContaProduto);
			}
		} else {
			throw new ProdutoNaoEncontrado();
		}
	}

}
