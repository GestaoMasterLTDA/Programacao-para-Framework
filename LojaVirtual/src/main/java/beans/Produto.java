package beans;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import utils.Constantes;
import utils.Utilitarios;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "pro_id")
	private int id;

	@Column(name = "pro_nome", length = 60, nullable = true)
	private String nome;

	@Column(name = "pro_preco", nullable = true)
	private float preco;
	
	@Transient
	private String imagem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public String getImagem() {
		imagem = Constantes.PATH_IMAGES + File.separator + Constantes.PREFIX_NAME_IMAGES + Utilitarios.completarCaracteres(String.valueOf(id), "0", 6, "E") + ".png";

		File file = new File(imagem);
		if(file.exists()) {
			imagem = Constantes.FOLDER_IMAGES 
					+ "/" // Separador
					+ Constantes.PREFIX_NAME_IMAGES
					+ Utilitarios.completarCaracteres(String.valueOf(id), "0", 6, "E") 
					+ ".png";
		} else {
			imagem = Constantes.FOLDER_IMAGES 
					+ "/" // Separador
					+ "SEM_IMG.jpg";			
		}
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
