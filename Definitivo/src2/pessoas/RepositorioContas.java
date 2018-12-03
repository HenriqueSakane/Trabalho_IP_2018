package pessoas;

public interface RepositorioContas {
	void inserir(ContaAbstrata conta) throws CpfCadastradoException, NumeroCadastroExcedidoException;
	ContaAbstrata procurar(long cpf) throws CpfNaoCadastradoException;
	void remover (long cpf) throws CpfNaoCadastradoException;
	void atualizar (ContaAbstrata conta);
	boolean existe (long cpf);
}
