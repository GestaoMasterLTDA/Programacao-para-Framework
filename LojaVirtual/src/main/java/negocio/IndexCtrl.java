package negocio;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.Pessoa;
import beans.Produto;
import persistencia.ProdutoDAO;
import utils.Utilitarios;

@ManagedBean
@SessionScoped
public class IndexCtrl {
	
	// MODELs
	private Produto produtoSelecionado;
	private List<Produto> listProdutos;
	
	private String nomeUsuarioLogado;
	
	public int init() {
		int resultado = 0;
		produtoSelecionado = null;
		listProdutos = ProdutoDAO.listagem(null); // Busca todos
		nomeUsuarioLogado = "Usuário não logado!";
		Pessoa user = Utilitarios.getUserLogado();
		if(user != null) {
			nomeUsuarioLogado = user.getNome();
		}
		resultado=1;
		return resultado;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public String getNomeUsuarioLogado() {
		return nomeUsuarioLogado;
	}

}
