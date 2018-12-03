package franquias;

public class CodigoInexistenteException extends Exception {
	public CodigoInexistenteException() {
		super("Codigo não cadastrado");
	}
}
