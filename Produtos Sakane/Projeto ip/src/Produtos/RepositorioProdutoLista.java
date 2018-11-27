package Produtos;

import ExcecoesProdutos.NumeroLimiteExcedido;
import ExcecoesProdutos.ProdutoJaCadastrado;
import ExcecoesProdutos.ProdutoNaoCadastrado;
import ExcecoesProdutos.ProdutoNaoEncontrado;
import Produtos.ClasseProduto;

public class RepositorioProdutoLista implements RepositorioProduto {
	private ClasseProduto nomeProduto;
	private RepositorioProdutoLista proxProduto;
	private int quantidadeProdutos;

	public RepositorioProdutoLista() {
		this.nomeProduto = null;
		this.quantidadeProdutos = 0;
		this.proxProduto = null;
	}

	public void inserir(ClasseProduto nomeProduto) throws NumeroLimiteExcedido, ProdutoJaCadastrado {
		boolean existe = this.existe(nomeProduto.getNome());
		if (existe == false) {
			if (this.quantidadeProdutos < 50) {
				if (this.nomeProduto == null) {
					this.nomeProduto = nomeProduto;
					this.quantidadeProdutos = this.quantidadeProdutos + 1;
					this.proxProduto = new RepositorioProdutoLista();
				} else {
					this.proxProduto.inserir(nomeProduto);
				}
			} else {
				throw new NumeroLimiteExcedido();
			}
		} else {
			throw new ProdutoJaCadastrado();
		}

	}

	public void remover(String nomeProduto) throws ProdutoNaoCadastrado {
		if (this.nomeProduto != null) {
			if (this.nomeProduto.getNome().equals(nomeProduto)) {
				this.nomeProduto = this.proxProduto.nomeProduto;
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
		if (this.nomeProduto != null) {
			if (this.nomeProduto.getNome().equals(nomeProduto)) {
				return true;
			} else {
				return this.proxProduto.existe(nomeProduto);
			}
		} else {
			return false;
		}
	}

	public ClasseProduto procurar(String nomeProduto) throws ProdutoNaoEncontrado {
		if (this.nomeProduto != null) {
			if (this.nomeProduto.getNome().equals(nomeProduto)) {
				return this.nomeProduto;
			} else {
				return this.proxProduto.procurar(nomeProduto);
			}
		} else {
			throw new ProdutoNaoEncontrado();
		}
	}

	public void atualizar(ClasseProduto nomeProduto) throws ProdutoNaoEncontrado {
		if (this.nomeProduto != null) {
			if (this.nomeProduto.getNome().equals(nomeProduto.getNome())) {
				this.nomeProduto = nomeProduto;
			} else {
				this.proxProduto.atualizar(nomeProduto);
			}
		} else {
			throw new ProdutoNaoEncontrado();
		}
	}

}
