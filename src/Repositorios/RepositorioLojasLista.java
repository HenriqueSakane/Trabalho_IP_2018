package Repositorios;

import Lojas.Lojas;

public class RepositorioLojasLista implements RepositorioLojas {
	private Lojas loja;
	private int countLojas;
	private RepositorioLojasLista nextLoja;

	public RepositorioLojasLista() {
		this.loja = null;
		this.nextLoja = null;
		this.countLojas = 0;
	}

	public void inserir(Lojas lojas) { 
		if (!this.existe(lojas.getCodigo())) {
			if (this.countLojas < 20) {
				if (this.loja != null) {
					this.existe(lojas.getCodigo());
					this.countLojas++;
				} else {
					this.loja = lojas;
					this.nextLoja = new RepositorioLojasLista();
				}
			} else {
				// erro
			}
		} else {
			// erro
		}
	}

	public boolean existe(int codigo) {
		if (this.loja != null) {
			if (this.loja.getCodigo() == codigo) {
				return true;
			} else {
				return this.nextLoja.existe(codigo);
			}
		} else {
			return false;
		}
	}

	public void remover(int codigo) {
		if (this.loja != null) {
			if (this.loja.getCodigo() == codigo) {
				this.loja = this.nextLoja.loja;
				this.nextLoja = this.nextLoja.nextLoja;
				this.countLojas--;
			} else {
				this.nextLoja.remover(codigo);
			}
		} else {
			throw new RuntimeException("CPF NAO CADASTRADO");
		}
	}

	public void atualizar(Lojas loja) {
		if (this.loja != null) {
			if (this.loja.getCodigo() == loja.getCodigo()) {
				this.loja = loja;
			} else {
				this.nextLoja.atualizar(loja);
			}
		} else {
			throw new RuntimeException("CPF NAO CADASTRADO");
		}
	}

	public Lojas procurar(int codigo) {
		if (this.loja != null) {
			if (this.loja.getCodigo() == codigo) {
				return this.loja;
			} else {
				return this.nextLoja.procurar(codigo);
			}
		} else {
			throw new RuntimeException("erro");
		}
	}
}
