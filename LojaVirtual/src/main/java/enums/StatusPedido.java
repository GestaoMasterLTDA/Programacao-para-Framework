package enums;

public enum StatusPedido {
	
	ABERTO("Aberto"), CONFIRMADO("Confirmado"), ENVIADO("Enviado"), CANCELADO("Cancelado"), ENTREGUE("Entregue");

	private String descricao;

	private StatusPedido(String descricao) {
		this.descricao = descricao;
	}
	
	// GETTERS

	public String getDescricao() {
		return descricao;
	}
	
}
