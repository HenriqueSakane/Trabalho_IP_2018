package franquias;

import produtos.ClasseProduto;
import produtos.NumeroLimiteExcedido;
import produtos.ProdutoJaCadastrado;

public class RepositorioFranquiasLista implements RepositorioFranquias {
	private Franquia loja;
	private int countLojas;
	private RepositorioFranquiasLista nextLoja;

	public RepositorioFranquiasLista() {
		this.loja = null;
		this.nextLoja = null;
		this.countLojas = 0;
	}

	public void inserir(Franquia lojas) throws LimiteLojasAtingidoException, CodigoCadastradoException { 
		if (!this.existe(lojas.getCodigo())) {
			if (this.countLojas < 20) {
				if (this.loja != null) {
					this.nextLoja.inserir(lojas);
				} else {
					this.loja = lojas;
					this.countLojas ++;
					this.nextLoja = new RepositorioFranquiasLista();
				}
			} else {
				throw new LimiteLojasAtingidoException();
			}
		} else {
			throw new CodigoCadastradoException();
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

	public void remover(int codigo) throws CodigoInexistenteException {
		if (this.existe(codigo)) {
			if (this.loja != null) {
				if (this.loja.getCodigo() == codigo) {
					this.loja = this.nextLoja.loja;
					this.nextLoja = this.nextLoja.nextLoja;
					this.countLojas--;
				} else {
					this.nextLoja.remover(codigo);
				}
			}
		} else {
			throw new CodigoInexistenteException();
		}
	}

	public void atualizar(Franquia loja) {
		if (this.loja != null) {
			if (this.loja.getCodigo() == loja.getCodigo()) {
				this.loja = loja;
			} else {
				this.nextLoja.atualizar(loja);
			}
		} 
	}

	public Franquia procurar(int codigo) throws CodigoInexistenteException {
		if (this.loja != null) {
			if (this.loja.getCodigo() == codigo) {
				return this.loja;
			} else {
				return this.nextLoja.procurar(codigo);
			}
		} else {
			throw new CodigoInexistenteException();
		}
	}
}
