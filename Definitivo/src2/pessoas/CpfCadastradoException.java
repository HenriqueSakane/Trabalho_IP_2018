package pessoas;

public class CpfCadastradoException extends Exception {
	public CpfCadastradoException() {
		super ("Cpf ja cadastrado");
	}
}