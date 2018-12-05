package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enums.StatusPedido;

@Entity
@Table(name = "ped_cab")
public class PedidoCabecalho implements Serializable {

	private static final long serialVersionUID = -8033899248227006310L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pc_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "pc_cli")
	private Pessoa cliente;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "pc_data")
	private Date dataEmissao;
	
	@Column(name = "pc_valor", precision = 10, scale = 2)
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "pc_fpg_id")
	private FormaPgto formaPagamento;
	
	@Column(name = "pc_qtd_parcela")
	private Integer quantidadeParcelas;
	
	@OneToMany(
		cascade = {CascadeType.ALL}, targetEntity = PedidoItem.class, mappedBy = "pedido"
	)
	private List<PedidoItem> itens;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "pc_status", length = 255)
	private StatusPedido status;
	
	// GETTERS E SETTERS
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public FormaPgto getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPgto formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoCabecalho other = (PedidoCabecalho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
