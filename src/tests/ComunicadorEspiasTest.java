package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ComunicadorEspias;

public class ComunicadorEspiasTest {
	@Test
	public void aviso() {
		 fail("Definir lo de las comunicaciones del comentario");
	}
	// Hay que definir si ponemos todas las comunicaciones desde el constructor directamente o 
	// damos la opcion de cargarlas manualmente. Cuando se defina eso, descomentar los test y 
	// corregir los test.
	/*
	@Test
	public void agregarEspiaTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacion("Ruben", "Ivan", 0.5);
		
		assertTrue(c.existeComunicacion("Ruben", "Ivan"));
	}
	
	@Test
	public void noExisteComunicacionTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacion("Ruben", "Ivan", 0.5);
		c.agregarComunicacion("William", "Ruben", 0.8);
		
		assertFalse(c.existeComunicacion("Ruben", "Pepe"));
	}
	
	@Test
	public void agregarEspiasMayusculasTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacion("PEPE", "IVAN", 0.3);
		c.agregarComunicacion("rUBEN", "rOdOlFo", 0.9);
		
		assertTrue(c.existeComunicacion("ivan", "PEPE") && c.existeComunicacion("ruben", "rodolFO"));
	}
	
	@Test
	public void obtenerProbabIntercepcionTest() {
		ComunicadorEspias c = new ComunicadorEspias("/lista_de_espias/lista-de-espias-test-2.xlsx");
		c.agregarComunicacion("juan", "ivan", 0.8);

		double esperado = 0.1;
		assertTrue(esperado == c.obtenerProbabIntercepcion("juan", "ivan"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarExistenEspiasTest() {
		
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacion("Mariano", "Tito", 0.2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarProbabilidadIntercepcionNegativaTest() {
		
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacion("Juan", "Rodolfo", -0.5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarProbabilidadIntercepcionMayorA1Test() {
		
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacion("William", "Ivan", 2.0);
	}
	*/
}
