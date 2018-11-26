package Repositorios;

import ContasCliente.ContaAbstrata;
import Excecoes.CpfCadastradoException;
import Excecoes.CpfNaoCadastradoException;
import Excecoes.NumeroCadastroExcedidoException;

public class RepositorioContasLista implements RepositorioContas {
	private ContaAbstrata conta;
	private RepositorioContasLista contaProxima;
	private int quantContas;

	public RepositorioContasLista() {
		this.conta = null;
		this.contaProxima = null;
		this.quantContas = 0;
	}

	public void inserir(ContaAbstrata conta) throws CpfCadastradoException, NumeroCadastroExcedidoException { // inserir em ordem crescente de idades
		boolean resultado = this.existe(conta.getCpf());
		if (resultado == false) {
			if (this.quantContas < 100) {
				if (this.conta != null) {
					this.contaProxima.inserir(conta);
				} else {
					this.conta = conta;
					this.quantContas +=1 ;
					this.contaProxima = new RepositorioContasLista();
				}
			} else {
				throw new NumeroCadastroExcedidoException();
			}
		} else {
			throw new CpfCadastradoException();
		}
	}

	public boolean existe(String cpf) {
		if (this.conta != null) {
			if (this.conta.getCpf().equals(cpf)) {
				return true;
			} else {
				return this.contaProxima.existe(cpf);
			}
		} else {
			return false;
		}
	}

	public void remover(String cpf) throws CpfNaoCadastradoException {
		boolean existe = this.existe(cpf);
		if (existe == true) {
			if (this.conta != null) {
				if (this.conta.getCpf().equals(cpf)) {
					this.conta = this.contaProxima.conta;
					this.contaProxima = this.contaProxima.contaProxima;
					this.quantContas -= 1;
				} else {
					this.contaProxima.remover(cpf);
				}
			} else {
				throw new CpfNaoCadastradoException();
			}
		}
	}

	public void atualizar(ContaAbstrata conta) {
		if (this.conta != null) {
			if (this.conta.getCpf().equals(conta.getCpf())) {
				this.conta = conta;
			} else {
				this.contaProxima.atualizar(conta);
			}
		}
	}

	public ContaAbstrata procurar(String cpf) throws CpfNaoCadastradoException {
		if (this.conta != null) {
			if (this.conta.getCpf().equals(cpf)) {
				return this.conta;
			} else {
				return this.contaProxima.procurar(cpf);
			}
		} else {
			throw new CpfNaoCadastradoException();
		}
	}
}