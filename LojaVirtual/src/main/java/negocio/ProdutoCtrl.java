package negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.Produto;
import persistencia.ProdutoDAO;

@ManagedBean(name = "produtoCtrl")
@SessionScoped
public class ProdutoCtrl implements Serializable {
	private static final long serialVersionUID = 1L;
	private Produto produto;

	//Novo 1
	private String nome;
	private List<Produto> listagem;
	//Novo 2
	// Deve colocar o construtor porque está selecionando todos os produtos, sem este "construtor" não busca nada 
	public ProdutoCtrl() {
		listagem = ProdutoDAO.listagem(null);
	}
    // Novo 3	
	public void filtrar() {
		listagem = ProdutoDAO.listagem(nome);
	}
	// Alterado
	public List<Produto> getListagem() {
		//return ProdutoDAO.listagem("");
		return listagem; 
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//
	//
	
	public Produto getProduto() {
		return produto;
	}

	public void SetProduto(Produto produto) {
		this.produto = produto;
	}

	public String actionGravar() {
		if (produto.getId() == 0) {
			ProdutoDAO.inserir(produto);
			return actionInserir();
		} else {
			ProdutoDAO.alterar(produto);
			return "/admin/lista_produto";
		}
	}

	public String actionInserir() {
		produto = new Produto();
		return "/admin/form_produto";
	}

	public String actionExcluir(Produto p) {
		ProdutoDAO.excluir(p);
		return "/admin/lista_produto";
	}

	public String actionAlterar(Produto p) {
		produto = p;
		return "/admin/form_produto";
	}


}

