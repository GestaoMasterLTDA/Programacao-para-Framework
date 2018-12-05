package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.PedidoCabecalho;
import beans.PedidoItem;
import beans.Pessoa;
import persistencia.ItemDAO;
import persistencia.PedidoDAO;

@ManagedBean
@SessionScoped
public class PedidosCtrl {

	private PedidoDAO dao;
	private ItemDAO itemDao;

	private PedidoCabecalho pedidoSelecionado;
	private Pessoa cliente;
	
	private List<PedidoCabecalho> pedidos;
	private List<PedidoItem> itens;

	public PedidosCtrl() {
		dao = new PedidoDAO();
		itemDao = new ItemDAO();
	}
	
	public void preRender() {
		pedidos = new ArrayList<>(dao.listagem(cliente));
	}
	
	public Double getValorTotalPedidos() {
		Double vlTot = 0.00;
		for(PedidoCabecalho ped : pedidos) {
			vlTot += ped.getValor();
		}
		return vlTot;
	}
	
	public void visualizarItens() {
		itens = new ArrayList<>(itemDao.listagem(pedidoSelecionado));
	}
	

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public List<PedidoCabecalho> getPedidos() {
		return pedidos;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public PedidoCabecalho getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(PedidoCabecalho pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}
	
}
