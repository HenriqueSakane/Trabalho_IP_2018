package pessoas;

import produtos.NumeroLimiteExcedido;

public class ContaCliente extends ContaAbstrata {

	public ContaCliente(long cpf, String nome, int idade) {
		super(cpf, nome, idade);
		this.setCliente(true);
	}

	public void creditar(double valor) { // depositando dinheiro
		this.setSaldo(this.getSaldo() + valor);
	}

	public void debitar(double valor) throws SaldoInsuficiente { // realizado uma compra ao chamar esse metodo
		this.setBonus(this.getBonus() + 1);
		if ((this.getBonus() < 10)) {
			double resultado = this.getSaldo() - valor;
			if (resultado >= 0) {
				this.setSaldo(resultado);
			} else {
				throw new SaldoInsuficiente(this.getSaldo(), "");
			}
		} else {
			this.setBonus(0);
		}
	}
}
