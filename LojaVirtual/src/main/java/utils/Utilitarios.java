package utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import beans.Pessoa;
import persistencia.PessoaDAO;

public class Utilitarios {

	public static String completarCaracteres(String valor, String caracter, int qtde, String posicao) {
		String result = "";
		if(valor != null) {
			// Preenche os caracteres
			for(int i = 0; i < qtde; i++) {
				result += caracter;
			}
			// Esquerda
			if("E".equals(posicao)) {
				result += valor;
			} 
			// Direita
			else if("D".equals(posicao)) {
				result = valor + result;
			} else {
				throw new IllegalArgumentException("Posição informada " + posicao + " inválida! Use E = Esquerda & D = Direita.");
			}
			
			// Corta a String caso seja maior que a quantidade a completar
			int length = result.length();
			if(length > qtde) {
				// Corta para a esquerda
				if("E".equals(posicao)) {
					result = result.substring(length - qtde, length);
				} 
				// Direita
				else if("D".equals(posicao)) {
					result = result.substring(0, qtde);
				}
			}
		}
		return result;
	}
	public static void exibirMensagemInfo(String msg) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	public static void exibirMensagemWarn(String msg) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	public static Pessoa getUserLogado() {
		Pessoa user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null && auth.getPrincipal() != null && auth.getPrincipal() instanceof UserDetails) {
			UserDetails details = (UserDetails) auth.getPrincipal();
			if(details != null) {
				user = PessoaDAO.buscar(details.getUsername());
			}
		}
		return user;
	}
	
}
