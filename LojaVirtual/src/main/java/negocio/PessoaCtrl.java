package negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.Fone;
import beans.Pessoa;
import persistencia.FormaPgtoDAO;
import persistencia.PessoaDAO;

@ManagedBean(name = "pessoaCtrl")
@SessionScoped
public class PessoaCtrl implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa;
	private int origem;
	private String nome;
	private String filtro = "";

	private List<Pessoa> listagem;

	// Deve colocar o construtor porque está selecionando todos os produtos, sem este "construtor" não busca nada 
	public PessoaCtrl() {
		pessoa = new Pessoa();
		listagem = PessoaDAO.listagem(null);
	}
	
	public void filtrar() {
		listagem = PessoaDAO.listagem(nome);
	}

	public List<Pessoa> getListagem() {
		return PessoaDAO.listagem(filtro);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String actionGravar() {
		if (pessoa.getId() == 0) {
			if(origem == 1) {
				pessoa.setTipo("ROLE_CLIENTE");
			}
			PessoaDAO.inserir(pessoa);
			return actionInserir();
		} else {
			PessoaDAO.alterar(pessoa);
			return "/admin/lista_cliente";
		}
	}

	public String actionInserir() {
		pessoa = new Pessoa();
		if(origem == 1) {
			return "/publico/index";
		} else {
			return "/admin/lista_cliente";
		}
	}

	public String actionExcluir() {
		PessoaDAO.excluir(pessoa);
		return "/admin/lista_cliente";
	}

	public String actionAlterar(Pessoa pes) {
		pessoa = pes;
		return "/admin/lista_cliente";
	}
	
	public String actionInserirFone() {
		Fone fone = new Fone();
		fone.setPessoa(pessoa);
		pessoa.getFones().add(fone);
		if(origem == 1) {
			return "/publico/form_pessoa";
		} else {
			return "/admin/form_cliente";
		}
	}
    // 
	public String actionExcluirFone(Fone f) {
		PessoaDAO.excluirFone(f);
		pessoa.setFones(PessoaDAO.listagemFone(pessoa));
		if(origem == 1) {
			return "/publico/form_pessoa";
		} else {
			return "/admin/form_cliente";
		}
	}

	public void setOrigem(int origem) {
		this.origem = origem;
	}
	
}
