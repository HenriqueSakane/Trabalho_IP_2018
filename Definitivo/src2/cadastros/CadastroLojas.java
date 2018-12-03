 package cadastros;

import franquias.*;
import produtos.*;

public class CadastroLojas {
	private RepositorioFranquias lojas;

	public CadastroLojas(RepositorioFranquias rep) {
		lojas = rep;
	}

	public void cadastrar(Franquia loja) throws CodigoCadastradoException, LimiteLojasAtingidoException {
		if (!lojas.existe(loja.getCodigo())) {
			lojas.inserir(loja);
		} else {
			throw new CodigoCadastradoException();
		}
	}

	public void remover(int code) throws CodigoInexistenteException {
		if (lojas.existe(code)) {
			lojas.remover(code);
		} else {
			throw new CodigoInexistenteException();
		}
	}

	public boolean existe(int code) {
		if (lojas.existe(code)) {
			return true;
		} else {
			return false;
		}
	}

	public Franquia procurar(int code) throws CodigoInexistenteException {
		if (lojas.existe(code)) {
			return lojas.procurar(code);
		} else {
			throw new CodigoInexistenteException();
		}
	}

	public void atualizar(Franquia loja) throws CodigoInexistenteException {
		if (lojas.existe(loja.getCodigo())) {
			lojas.atualizar(loja);
		} else {
			throw new CodigoInexistenteException();
		}
	}
	
}