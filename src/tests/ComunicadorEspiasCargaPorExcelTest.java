package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ComunicadorEspias;

public class ComunicadorEspiasCargaPorExcelTest {

	@Test
	public void cargarEspiasYComunicacionesTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacionDesdeExcel();
		
		assertTrue(c.existeComunicacion("Ruben", "William"));
	}

	@Test
	public void existeComunicacionMayusculaTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacionDesdeExcel();
		
		assertTrue(c.existeComunicacion("RODOLFO", "JUAN"));
	}
	
	@Test
	public void noExisteComunicacionTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacionDesdeExcel();
		
		assertFalse(c.existeComunicacion("Juan", "Ruben"));
	}
	
	@Test
	public void obtenerProbIntercepcionTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacionDesdeExcel();
		
		assertTrue(0.1 == c.obtenerProbabIntercepcion("Juan", "Ivan"));
	}
	
	@Test
	public void cantidadEspiasTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacionDesdeExcel();
		
		assertEquals(6, c.cantidadEspias());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void probIntercepcionEspiasNoComunicadosTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacionDesdeExcel();
		
		c.obtenerProbabIntercepcion("Pepe", "Ivan");
	}
	
}
