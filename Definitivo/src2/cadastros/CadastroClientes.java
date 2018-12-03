package cadastros;

import pessoas.*;

public class CadastroClientes {
	private RepositorioContas clientes;

	public CadastroClientes(RepositorioContas rep) {
		clientes = rep;
	}

	public void cadastrar(ContaAbstrata conta) throws CpfCadastradoException, NumeroCadastroExcedidoException {
		if (!clientes.existe(conta.getCpf())) {
			clientes.inserir(conta);
		} else {
			throw new CpfCadastradoException();
		}
	}

	public void remover(long cpf) throws CpfNaoCadastradoException {
		if (clientes.existe(cpf)) {
			clientes.remover(cpf);
		} else {
			throw new CpfNaoCadastradoException();
		}
	}

	public void atualizar(ContaAbstrata conta) throws CpfNaoCadastradoException {
		if (conta.existe(conta.getCpf())) {
			clientes.atualizar(conta);
		} else {
			throw new CpfNaoCadastradoException();
		}
	}

	public ContaAbstrata procurar(long cpf) throws CpfNaoCadastradoException {
		if (clientes.existe(cpf)) {
			return clientes.procurar(cpf);
		} else {
			throw new CpfNaoCadastradoException();
		}
	}

	public boolean existe(long cpf) {
		if (clientes.existe(cpf)) {
			return true;
		} else {
			return false;
		}
	}

	public void creditar(double dinheiro, ContaAbstrata conta) {
		conta.creditar(dinheiro);
	}

	public void debitar(double dinheiro, ContaCliente conta) throws SaldoInsuficiente {
		conta.debitar(dinheiro);
	}

	public void setSaldo(double dinheiro, ContaAbstrata conta) {
		conta.setSaldo(dinheiro);
	}

}
