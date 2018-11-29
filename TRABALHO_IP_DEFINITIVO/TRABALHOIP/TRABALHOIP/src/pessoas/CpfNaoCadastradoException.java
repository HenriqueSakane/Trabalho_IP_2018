package pessoas;

public class CpfNaoCadastradoException extends Exception {
	public CpfNaoCadastradoException() {
		super("Cpf nao cadastrado");
	}
}
