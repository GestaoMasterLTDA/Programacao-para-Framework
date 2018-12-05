package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beans.Produto;
import persistencia.ProdutoDAO;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	private ProdutoDAO dao;
	
	public ProdutoConverter() {
		dao = new ProdutoDAO();
	}
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null) {
			return dao.buscar(Integer.parseInt(value));
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && value instanceof Produto) {
			return String.valueOf(((Produto) value).getId());
		}
		return null;
	}
	
}
