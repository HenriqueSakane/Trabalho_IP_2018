package pessoas;

public class SaldoInsuficiente extends Exception {
	private double saldo;
	private String cpf;
	public SaldoInsuficiente(double saldo, String cpf) {
		super ("Saldo Insuficiente! Por favor, credite dinheiro à sua conta.");
		this.saldo = saldo;
		this.cpf = cpf;	
	}
	
	public double getSaldo() {
		return this.saldo;
	}
	
	public String getCpf() {
		return this.cpf;
	}
}
