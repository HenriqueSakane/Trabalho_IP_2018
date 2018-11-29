package pessoas;

public class ContaCliente extends ContaAbstrata {


	public ContaCliente(long cpf, String nome, int idade) {
		super(cpf, nome, idade);
		this.setCliente(true);
	}

	public void creditar(double valor) { // depositando dinheiro
		this.setSaldo(this.getSaldo() + valor);
	}

	public void debitar(double valor) { // realizado uma compra ao chamado esse metodo
		this.setBonus(this.getBonus()+1);
		if ((this.getBonus() < 10)) {
			this.setSaldo(this.getSaldo() - valor);
		} else {
			this.setBonus(0);
		}
	}
}
