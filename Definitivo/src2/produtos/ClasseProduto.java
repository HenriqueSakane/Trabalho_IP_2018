package produtos;

public class ClasseProduto {
	private String nome;
	private int tamanho;
	private double preco;
	private int estoque;

	public ClasseProduto(String nome, int tamanho, double preco) {
		this.nome = nome;
		this.tamanho = tamanho;
		this.preco = preco;
		this.estoque = 0;
	}

	public double getPreco() {
		return preco;
	}

	public String getNome() {
		return nome;
	}

	public int getTamanho() {
		return tamanho;
	}

	public int getEstoque() {
		return estoque;
	}

}
