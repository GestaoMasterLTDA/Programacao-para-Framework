package web.publico;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import org.junit.Before;
import org.junit.Test;

import beans.Pessoa;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;
import utils.Utilitarios;

public class IndexTest {
	
	@Before
	public void prepare() {
		setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
		setBaseUrl("http://localhost:8080/LojaVirtual");
	}
	
	@Test
	public void testMenuInicio() {
		beginAt("publico/index.xhtml");
		assertTitleEquals("Loja Virtual versão 2.0");
	}
	
	@Test
	public void testMenuCliente() {
		beginAt("publico/index.xhtml");
		assertTitleEquals("Loja Virtual versão 2.0");
		
		beginAt("publico/form_pessoa.xhtml");
		assertTitleEquals("Formulário de pessoas");
	}
	
	@Test
	public void testMenuPedidos() {
		beginAt("publico/index.xhtml");
		assertTitleEquals("Loja Virtual versão 2.0");
		
		beginAt("cliente/principal.xhtml");
		
		Pessoa user = Utilitarios.getUserLogado();
		if(user == null) { // Login			
			assertTitleEquals("Login");
		} else { // Area do cliente
			assertTitleEquals("Área do cliente");
		}
		
	}
	
	@Test
	public void testMenuAdministracao() {
		// Chama a index e testa ela
		beginAt("publico/index.xhtml");
		assertTitleEquals("Loja Virtual versão 2.0");
		
		// Chama a area administrativa
		beginAt("admin/principal.xhtml");
		assertTitleEquals("Login");		
	}
	
	@Test
	public void testMenuLogout() {
		beginAt("publico/index.xhtml");
		assertTitleEquals("Loja Virtual versão 2.0");
		
		beginAt("j_spring_security_logout");
		assertTitleEquals("Loja Virtual versão 2.0");
		assertTextInElement("name-user", "Usuário não logado!");
	}

	@Test
	public void testMenuSobre() {
		// Chama a index e testa ela
		beginAt("publico/index.xhtml");
		assertTitleEquals("Loja Virtual versão 2.0");
		
		// Chama a pagina de SOBRE
		beginAt("publico/sobre.xhtml");
		assertTitleEquals("Loja Virtual - Sobre...");
	}
	
}
