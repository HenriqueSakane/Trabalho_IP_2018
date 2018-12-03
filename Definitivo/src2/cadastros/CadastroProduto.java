package cadastros;

import produtos.*;

public class CadastroProduto {
	private RepositorioProduto produtos;

	public CadastroProduto(RepositorioProduto rep) {
		produtos = rep;
	}

	public void cadastrar(ClasseProduto ContaProduto) throws NumeroLimiteExcedido, ProdutoJaCadastrado {
		if (!produtos.existe(ContaProduto.getNome())) {
			produtos.inserir(ContaProduto);
		} else {
			throw new ProdutoJaCadastrado();
		}
	}

	public void remover(String nomeProduto) throws ProdutoNaoCadastrado {
		if (produtos.existe(nomeProduto)) {
			produtos.remover(nomeProduto);
		} else {
			throw new ProdutoNaoCadastrado();
		}
	}

	public boolean existe(String nomeProduto) {
		if (produtos.existe(nomeProduto)) {
			return true;
		} else {
			return false;
		}
	}

	public ClasseProduto procurar(String nomeProduto) throws ProdutoNaoEncontrado {
		if (produtos.existe(nomeProduto)) {
			return produtos.procurar(nomeProduto);
		} else {
			throw new ProdutoNaoEncontrado();
		}
	}

	public void atualizar(ClasseProduto ContaProduto) throws ProdutoNaoEncontrado {
		if (produtos.existe(ContaProduto.getNome())) {
			produtos.atualizar(ContaProduto);
		} else {
			throw new ProdutoNaoEncontrado();
		}
	}
}