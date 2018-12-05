package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beans.FormaPgto;
import persistencia.FormaPgtoDAO;

@FacesConverter(forClass = FormaPgto.class)
public class FormaPgtoConverter implements Converter {

	private FormaPgtoDAO dao;
	
	public FormaPgtoConverter() {
		dao = new FormaPgtoDAO();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null) {
			return dao.buscar(Integer.parseInt(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null && value instanceof FormaPgto) {
			return String.valueOf(((FormaPgto) value).getId());
		}
		return null;
	}
	
}
