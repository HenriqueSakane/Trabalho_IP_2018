package cadastros;

import pessoas.*;
import excecoes.*;

public class CadastroClientes {
	private RepositorioContas clientes;
	
	public CadastroClientes(RepositorioContas rep) {
		clientes = rep;
	}
	
	public void cadastrar (ContaAbstrata conta) throws CpfCadastradoException, NumeroCadastroExcedidoException {
		if (!clientes.existe(conta.getCpf())) {
			clientes.inserir(conta);
		} else {
			throw new CpfCadastradoException();
		}
	}
	
	public void remover (String cpf) throws CpfNaoCadastradoException{
		if (clientes.existe(cpf)) {
			clientes.remover(cpf);
		} else {
			throw new CpfNaoCadastradoException();
		}
	}
	
	public void atualizar (ContaAbstrata conta) throws CpfNaoCadastradoException{
		if (conta.existe(conta.getCpf())) {
			clientes.atualizar(conta);
		} else {
			throw new CpfNaoCadastradoException();
		}
	}
	
	public ContaAbstrata procurar (String cpf) throws CpfNaoCadastradoException {
		if (clientes.existe(cpf)) {
			return clientes.procurar(cpf);
		} else {
			throw new CpfNaoCadastradoException();
		}
	}
	
	public boolean existe (String cpf) {
		if (clientes.existe(cpf)) {
			return true;
		} else {
			return false;
		}
	}
	
}
