package fachada;

import pessoas.*;
import produtos.*;
import franquias.*;
import cadastros.*;

public class Loja {
	CadastroClientes pessoa;
	CadastroProduto produtos;
	CadastroLojas lojas;

	public Loja(boolean repositorio) {
		if (repositorio) {
			this.pessoa = new CadastroClientes(new RepositorioContasLista());
			this.produtos = new CadastroProduto(new RepositorioProdutoLista());
			this.lojas = new CadastroLojas(new RepositorioFranquiasLista());
		} else {
			this.pessoa = new CadastroClientes(new RepositorioContasArray());
			this.produtos = new CadastroProduto(new RepositorioProdutoArray());
			this.lojas = new CadastroLojas(new RepositorioFranquiasArray());
		}
	}

	// clientes
	public void cadastroPessoas(ContaAbstrata conta) throws CpfCadastradoException, NumeroCadastroExcedidoException {
		if (!pessoa.existe(conta.getCpf())) {
			pessoa.cadastrar(conta);
		} else {
			throw new CpfCadastradoException();
		}
	}

	public void removerPessoa(long cpf) throws CpfNaoCadastradoException {
		if (pessoa.existe(cpf)) {
			pessoa.remover(cpf);
		} else {
			throw new CpfNaoCadastradoException();
		}
	}

	public ContaAbstrata procurarPessoa(long cpf) throws CpfNaoCadastradoException {
		if (pessoa.existe(cpf)) {
			return pessoa.procurar(cpf);
		} else {
			throw new CpfNaoCadastradoException();
		}
	}

	public void atualizarPessoa(ContaAbstrata conta) throws CpfNaoCadastradoException {
		if (pessoa.existe(conta.getCpf())) {
			pessoa.atualizar(conta);
		} else {
			throw new CpfNaoCadastradoException();
		}
	}

	public boolean existePessoa(long cpf) throws CpfNaoCadastradoException {
		if (pessoa.existe(cpf)) {
			return true;
		} else {
			throw new CpfNaoCadastradoException();
		}
	}

	public ContaAbstrata criarContaCliente(long cpf, String nome, int idade, int escolhaPessoa) {
		if (escolhaPessoa % 2 == 0) {
			ContaFuncionario conta = new ContaFuncionario(cpf, nome, idade);
			return conta;
		} else {
			ContaCliente conta = new ContaCliente(cpf, nome, idade);
			return conta;
		}
	}
	
	public void creditar(double valor, ContaAbstrata conta) {
		pessoa.creditar(valor, conta);
	}
	
	public void setSaldo(double valor, ContaAbstrata conta) {
		pessoa.setSaldo(valor, conta);
	}

	// produto
	public void cadastrarProduto(ClasseProduto ContaProduto) throws NumeroLimiteExcedido, ProdutoJaCadastrado {
		if (!produtos.existe(ContaProduto.getNome())) {
			produtos.cadastrar(ContaProduto);
		} else {
			throw new ProdutoJaCadastrado();
		}
	}

	public void removerProduto(String nomeProduto) throws ProdutoNaoCadastrado {
		if (produtos.existe(nomeProduto)) {
			produtos.remover(nomeProduto);
		} else {
			throw new ProdutoNaoCadastrado();
		}
	}

	public boolean existeProduto(String nomeProduto) {
		if (produtos.existe(nomeProduto)) {
			return true;
		} else {
			return false;
		}
	}

	public ClasseProduto procurarProduto(String nomeProduto) throws ProdutoNaoEncontrado {
		if (produtos.existe(nomeProduto)) {
			return produtos.procurar(nomeProduto);
		} else {
			throw new ProdutoNaoEncontrado();
		}
	}

	public void atualizarProduto(ClasseProduto ContaProduto) throws ProdutoNaoEncontrado {
		if (produtos.existe(ContaProduto.getNome())) {
			produtos.atualizar(ContaProduto);
		} else {
			throw new ProdutoNaoEncontrado();
		}
	}

	public ClasseProduto criarContaProduto(int escolhaProduto, String nomeProduto, double preco, int tamanho) {
		ClasseProduto rep = new ClasseProduto(nomeProduto, tamanho, preco);
		return rep;
	}

	// franquia
	public Franquia criarContaFranquia(int codigo, String endereco, long cnpj) {
		Franquia rep = new Franquia(codigo, cnpj, endereco);
		return rep;
	}
	
	public void cadastrarFranquia(Franquia loja) throws CodigoCadastradoException, LimiteLojasAtingidoException {
		if (!lojas.existe(loja.getCodigo())) {
			lojas.cadastrar(loja);
		} else {
			throw new CodigoCadastradoException();
		}
	}

	public void removerFranquia(int code) throws CodigoInexistenteException {
		if (lojas.existe(code)) {
			lojas.remover(code);
		} else {
			throw new CodigoInexistenteException();
		}
	}

	public boolean existeFranquia(int code) {
		if (lojas.existe(code)) {
			return true;
		} else {
			return false;
		}
	}

	public Franquia procurarFranquia(int code) throws CodigoInexistenteException {
		if (lojas.existe(code)) {
			return lojas.procurar(code);
		} else {
			throw new CodigoInexistenteException();
		}
	}

	public void atualizarFranquia(Franquia loja) throws CodigoInexistenteException {
		if (lojas.existe(loja.getCodigo())) {
			lojas.atualizar(loja);
		} else {
			throw new CodigoInexistenteException();
		}
	}
	
}