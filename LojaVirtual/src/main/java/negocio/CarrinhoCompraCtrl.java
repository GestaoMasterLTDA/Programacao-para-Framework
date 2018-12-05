package negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import beans.FormaPgto;
import beans.PedidoCabecalho;
import beans.PedidoItem;
import beans.Pessoa;
import beans.Produto;
import enums.StatusPedido;
import persistencia.FormaPgtoDAO;
import persistencia.PedidoDAO;
import utils.Constantes;
import utils.Utilitarios;

@ManagedBean
@SessionScoped
public class CarrinhoCompraCtrl {

	// MODELs
	private Produto produtoSelecionado;
	private PedidoItem itemSelecionado;
	private FormaPgto formaPgtoSelecionada;
	private Integer quantidade;

	private int sequencial;

	private List<PedidoItem> listItensInseridos;
	private List<FormaPgto> listFormasPagamento;

	public CarrinhoCompraCtrl() {
		sequencial = 1;
		listItensInseridos = new ArrayList<>();
		listFormasPagamento = FormaPgtoDAO.listagem("");
	}

	// METODOS DO CARRINHO

	public void adicionarProduto() {
		adicionarProduto(produtoSelecionado, quantidade);
	}

	// Adiciona um Item ao Carrinho
	public void adicionarProduto(Produto prod, int quantidade) {
		if (prod != null && quantidade > 0) {
			double total = quantidade * prod.getPreco();
			PedidoItem item = new PedidoItem();
			item.setSequencial(sequencial);
			item.setProduto(prod);
			item.setQuantidade(quantidade);
			item.setValorUnitario((double) prod.getPreco());
			item.setValorTotal(total);

			listItensInseridos.add(item);
			Utilitarios.exibirMensagemInfo("Produto adicionado com sucesso!");

			this.produtoSelecionado = null;
			this.quantidade = null;
			sequencial++;
		} else {
			Utilitarios.exibirMensagemWarn("Produto não foi adicionado verifique os dados informados!");
		}
	}

	public void removerProduto(PedidoItem item) {
		if (item != null) {
			listItensInseridos.remove(item.getSequencial() - 1); // Remove 1, pois o indice em Java comeca por ZERO
			Utilitarios.exibirMensagemInfo("Item removido com sucesso!");
			sequencial--;
		}
	}

	public void validarUsuarioLogado() throws IOException {
		if (getValorTotal() <= 0) {
			Utilitarios.exibirMensagemWarn("Pedido sem itens adicionado!");
			FacesContext.getCurrentInstance().validationFailed();
		} else {
			Pessoa userLogado = Utilitarios.getUserLogado();
			ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
			HttpSession session = (HttpSession) external.getSession(false);
			if (userLogado == null) {
				session.setAttribute(Constantes.KEY_USER_SESSION, "naoLogado");
				external.redirect(external.getRequestContextPath() + "/publico/login.xhtml");
			} else {
				session.setAttribute(Constantes.KEY_USER_SESSION, userLogado.getNome());
			}
		}
	}

	public String finalizarPedido() throws IOException {
		PedidoCabecalho ped = criarNovoPedido();
		PedidoDAO.salvar(ped);
		Utilitarios.exibirMensagemInfo("Pedido salvo com sucesso!");
		listItensInseridos = new ArrayList<>();
		quantidade = null;
		
		return "/publico/index.xhtml?faces-redirect=true";
	}

	// METODOS

	public Double getValorTotal() {
		Double vlTot = 0.00;
		for (PedidoItem i : listItensInseridos) {
			vlTot += i.getValorTotal();
		}
		return vlTot;
	}

	private PedidoCabecalho criarNovoPedido() {
		PedidoCabecalho ped = new PedidoCabecalho();
		ped.setDataEmissao(new Date());
		ped.setCliente(Utilitarios.getUserLogado());
		ped.setStatus(StatusPedido.ABERTO);

		// Define o cabecalho nos itens
		for (PedidoItem item : listItensInseridos) {
			item.setPedido(ped);
		}

		ped.setItens(listItensInseridos);
		ped.setFormaPagamento(formaPgtoSelecionada);
		ped.setQuantidadeParcelas(formaPgtoSelecionada.getNumPadraoParc());
		ped.setValor(getValorTotal());

		return ped;
	}

	// GETTERS E SETETRS

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	public PedidoItem getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(PedidoItem itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public List<PedidoItem> getListItensInseridos() {
		return listItensInseridos;
	}

	public List<FormaPgto> getListFormasPagamento() {
		return listFormasPagamento;
	}

	public FormaPgto getFormaPgtoSelecionada() {
		return formaPgtoSelecionada;
	}

	public void setFormaPgtoSelecionada(FormaPgto formaPgtoSelecionada) {
		this.formaPgtoSelecionada = formaPgtoSelecionada;
	}

}
