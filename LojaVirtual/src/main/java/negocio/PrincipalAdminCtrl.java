package negocio;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import beans.Pessoa;
import utils.Constantes;
import utils.Utilitarios;

@ManagedBean
@SessionScoped
public class PrincipalAdminCtrl {

	private String pageRedirect;
	
	public void preRender() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(false);
		Object param = session.getAttribute(Constantes.KEY_USER_SESSION);
		if(param != null && param.toString().equals("naoLogado")) {
			external.redirect(external.getRequestContextPath() + "/cliente/carrinho.xhtml");
		}
		

		Pessoa user = Utilitarios.getUserLogado();
		if("ROLE_CLIENTE".equals(user.getTipo())) {
			external.redirect(external.getRequestContextPath() + "/cliente/principal.xhtml");
		}
		
		if(pageRedirect != null && !pageRedirect.isEmpty()) {
			external.redirect(external.getRequestContextPath() + pageRedirect);
			pageRedirect = null;
		}
	}
	
	// GETTERS E SETTERS

	public String getPageRedirect() {
		return pageRedirect;
	}

	public void setPageRedirect(String pageRedirect) {
		this.pageRedirect = pageRedirect;
	}
	
}
