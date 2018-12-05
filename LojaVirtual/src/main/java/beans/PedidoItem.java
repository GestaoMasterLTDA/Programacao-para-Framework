package beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ped_item")
public class PedidoItem implements Serializable {

	private static final long serialVersionUID = 3435958642825876675L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pi_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "pi_ped_id")
	private PedidoCabecalho pedido;
	
	@ManyToOne
	@JoinColumn(name = "pi_pro_numero")
	private Produto produto;
	
	@Column(name = "pi_qtd")
	private Integer quantidade;
	
	@Column(name = "pi_valor_unit", precision = 10, scale = 2)
	private Double valorUnitario;
	
	@Column(name = "pi_valor_total", precision = 10, scale = 2)
	private Double valorTotal;
	
	// TRANSIENTES
	@Transient
	private int sequencial;
	
	// GETTERS E SETTERS

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PedidoCabecalho getPedido() {
		return pedido;
	}

	public void setPedido(PedidoCabecalho pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getSequencial() {
		return sequencial;
	}

	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}
	
}
