package Produtos;

public class ClasseProdutos {
	private String nome;
	private int tamanho;
	private double preco;
	private int quantidade;

	public ClasseProdutos(String nome, int tamanho, double preco, int quantidade) {
		this.nome = nome;
		this.tamanho = tamanho;
		this.preco = 0.0;
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public String getNome() {
		return nome;
	}

	public int getTamanho(int tamanho) {
		return tamanho;
	}

	public int getQuantidade(int quantidade) {
		return quantidade;
	}

}
