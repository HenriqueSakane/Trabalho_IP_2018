package pessoas;

public class ContaFuncionario extends ContaAbstrata {

	public ContaFuncionario(long cpf, String nome, int idade) {
		super(cpf, nome, idade);
	}

	public void creditar(double valor) {
		this.setBonus(this.getBonus()+1);
		if (this.getBonus() < 10) {
			this.setSaldo(this.getSaldo() + (0.30 * valor));
		} else { // na 10ª venda o funcionario ganha um bonus
			this.setSaldo(this.getSaldo() + (0.30 * valor) + valor);
			this.setBonus(0);
		}
	}
}
