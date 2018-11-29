package pessoas;

public class TipoContaErradaFuncionarioException extends Exception {
	public TipoContaErradaFuncionarioException() {
		super ("Voce se referiu a uma conta de cliente, por favor insira uma de FUNCIONARIO");
	}
}
