package produtos;

public class ProdutoNaoEncontrado extends Exception {
	public ProdutoNaoEncontrado() {
		super("Produto nao encontrado");
	}
}
