package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.PedidoCabecalho;
import enums.StatusPedido;
import persistencia.PedidoDAO;
import utils.Utilitarios;

@ManagedBean
@ViewScoped
public class PrincipalClienteCtrl {

	private List<PedidoCabecalho> listPedidosEmitidos;

	public PrincipalClienteCtrl() {
		listPedidosEmitidos = new ArrayList<>(PedidoDAO.listagem());
	}
	
	public List<PedidoCabecalho> getListPedidosEmitidos() {
		return listPedidosEmitidos;
	}
	
	public void confirmarPedido(PedidoCabecalho ped) {
		if(ped != null) {
			if(StatusPedido.ABERTO.equals(ped.getStatus()))   {
				ped.setStatus(StatusPedido.CONFIRMADO);
				PedidoDAO.mudaSituacao(ped);
				Utilitarios.exibirMensagemInfo("Pedido confirmado com sucesso!");
			} else {
				Utilitarios.exibirMensagemWarn("Não é possível CONFIRMAR pedido " + ped.getStatus());
			}
		}
	}
	
	public void cancelarPedido(PedidoCabecalho ped) {
		if(ped != null) {
			if(StatusPedido.ENVIADO.equals(ped.getStatus())) {
				Utilitarios.exibirMensagemWarn("Pedido já enviado, não pode ser cancelado!");
			} else 	if(StatusPedido.ENTREGUE.equals(ped.getStatus())) {
					Utilitarios.exibirMensagemWarn("Pedido já entregue, não pode ser cancelado!");
			} else {
				ped.setStatus(StatusPedido.CANCELADO);
				PedidoDAO.mudaSituacao(ped);
				Utilitarios.exibirMensagemInfo("Pedido cancelado com sucesso!");
			}
		}
	}

	public void enviadoPedido(PedidoCabecalho ped) {
		if(ped != null) {
			if(StatusPedido.CANCELADO.equals(ped.getStatus())) {
				Utilitarios.exibirMensagemWarn("Pedido cancelado, não pode ser entregue!");
			} else 	if(StatusPedido.ENTREGUE.equals(ped.getStatus())) {
				Utilitarios.exibirMensagemWarn("Pedido já entregue!");
			} else 	if(StatusPedido.ABERTO.equals(ped.getStatus())) {
				Utilitarios.exibirMensagemWarn("Pedido deve ser CONFIRMADO antes de enviar!");
			} else {
				ped.setStatus(StatusPedido.ENVIADO);
				PedidoDAO.mudaSituacao(ped);
				Utilitarios.exibirMensagemInfo("Pedido enviado!");
			}
		}
	}

	public void entreguePedido(PedidoCabecalho ped) {
		if(ped != null) {
			if(StatusPedido.ENVIADO.equals(ped.getStatus()))   {
				ped.setStatus(StatusPedido.ENTREGUE);
				PedidoDAO.mudaSituacao(ped);
				Utilitarios.exibirMensagemInfo("Pedido entregue!");
			} else {
				Utilitarios.exibirMensagemWarn("Não é possível marcar ENTREGUE se pedido " + ped.getStatus());
			}
		}
	}
	
	public Double getValorTotalPedidos() {
		Double vlTot = 0.00;
		for(PedidoCabecalho ped : listPedidosEmitidos) {
			vlTot += ped.getValor();
		}
		return vlTot;
	}
	
}
