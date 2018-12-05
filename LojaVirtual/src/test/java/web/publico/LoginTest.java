package web.publico;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;

import beans.Pessoa;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import utils.Utilitarios;

public class LoginTest {
	
	@Before
	public void prepare() {
		setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
		setBaseUrl("http://localhost:8080/LojaVirtual");
	}
	
	@Test
	public void testLogin() {
		// Chama a index e testa ela
		beginAt("publico/index.xhtml");
		assertTitleEquals("Loja Virtual versão 2.0");
		
		// Chama a area administrativa
		beginAt("admin/principal.xhtml");
		assertTitleEquals("Login");
		
		// Preenche os dados do login
		setTextField("j_username", "email01");
		setTextField("j_password", "01");
		
		// Clica no botao login
		clickButton("btn-logar");
		
		// Testa a area administrativa
		assertTitleEquals("Área Administrativa");
	}
	
}
