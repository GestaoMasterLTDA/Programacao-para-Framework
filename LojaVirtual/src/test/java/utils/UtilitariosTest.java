package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class UtilitariosTest {
	
	@Test
	public void testCompletarCaracteres() {
		assertEquals("00055", Utilitarios.completarCaracteres("55", "0", 5, "E"));
		assertEquals("55000", Utilitarios.completarCaracteres("55", "0", 5, "D"));
		assertEquals("66666", Utilitarios.completarCaracteres("66666666", "0", 5, "D"));
	}

}
