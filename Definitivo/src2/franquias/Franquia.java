package franquias;

import produtos.*;

public class Franquia {
	private int codigo; 
	private long cnpj; 
	private String endereco;
	
	public Franquia(int codigo, long cnpj, String endereco) {
		this.codigo = codigo;
		this.cnpj = cnpj;
		this.endereco = endereco; 
	}

	public boolean existe(int codigo) {
		if (this.codigo == codigo) {
			return true;
		} else {
			return false;
		}
	}

	// CODIGO
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}

	// CNPJ
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public long getCnpj() {
		return this.cnpj;
	}

	// ENDERECO
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return this.endereco;
	}

}
