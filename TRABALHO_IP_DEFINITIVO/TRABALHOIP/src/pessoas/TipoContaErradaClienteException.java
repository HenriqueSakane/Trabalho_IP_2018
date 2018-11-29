package pessoas;

public class TipoContaErradaClienteException extends Exception {
	public TipoContaErradaClienteException() {
		super ("Voce se referiu a uma conta de funcionario, por favor insira uma de CLIENTE");
	}
}
