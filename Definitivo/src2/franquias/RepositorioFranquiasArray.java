package franquias;

public class RepositorioFranquiasArray implements RepositorioFranquias {
	private Franquia[] lojas;
	private int countLojas;

	public RepositorioFranquiasArray() {
		this.lojas = new Franquia[20];
		this.countLojas = 0;
	}

	public void inserir(Franquia loja) throws CodigoCadastradoException, LimiteLojasAtingidoException {
		if (!this.existe(loja.getCodigo())) {
			boolean stop = false;
			for (int i = 0; i <= this.lojas.length && !stop; i++) {
				if (i == this.lojas.length) {
					throw new LimiteLojasAtingidoException();
				} else {
					if (this.lojas[i] != null) {
						continue;
					} else {
						this.lojas[i] = loja;
						this.countLojas = i + 1;
						stop = true;
					}
				}
			}
		} else {
			throw new CodigoCadastradoException();
		}

	}

	public boolean existe(int codigo) {
		for (int i = 0; i < this.lojas.length; i++) {
			if (lojas[i] != null && this.lojas[i].getCodigo() == codigo) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}

	public void remover(int codigo) throws CodigoInexistenteException {
		boolean resultado = this.existe(codigo);
		if (resultado == true) {
			boolean stop = false;
			for (int i = 0; i < this.lojas.length && stop == false; i++) {
				if (this.lojas[i].getCodigo() == codigo) {
					for (int j = i + 1; j < this.lojas.length;i++, j++) {
						this.lojas[i] = this.lojas[j];
						this.lojas[j] = null;
					}
					this.countLojas -= 1;
					stop = true;
				}
			}
		} else {
			throw new CodigoInexistenteException();
		}
	}

	public void atualizar(Franquia lojas) {
		if (this.existe(lojas.getCodigo())) {
			boolean stop = false;
			for (int i = 0; i < this.lojas.length && stop == false; i++) {
				if (this.lojas[i].getCodigo() == lojas.getCodigo()) {
					this.lojas[i] = lojas;
					stop = true;
				}
			}
		} 
	}

	public Franquia procurar(int codigo) throws CodigoInexistenteException {
		boolean resultado = this.existe(codigo);
		if (resultado == true) {
			boolean stop = false;
			Franquia procurada = null;
			for (int i = 0; i < this.lojas.length && stop == false; i++) {
				if (this.lojas[i].getCodigo() == codigo) {
					stop = true;
					procurada = lojas[i];
				}
			}
			return procurada;
		} else {
			throw new CodigoInexistenteException();
		}
	}
}
