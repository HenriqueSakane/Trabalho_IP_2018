package franquias;

public class CodigoCadastradoException extends Exception {
	public CodigoCadastradoException() {
		super ("Codigo ja utilizado");
	}
}