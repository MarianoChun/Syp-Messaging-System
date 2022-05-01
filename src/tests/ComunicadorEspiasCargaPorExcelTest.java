package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import grafos.GrafoNDPonderado;
import model.ComunicadorEspias;

public class ComunicadorEspiasCargaPorExcelTest {

	@Test
	public void cargarEspiasYComunicacionesTest() {
		ComunicadorEspias c = new ComunicadorEspias("/lista_de_espias/lista-de-espias-test-2.xlsx");

		
		assertTrue(c.existeComunicacion("Ruben", "William"));
	}

	@Test
	public void existeComunicacionMayusculaTest() {
		ComunicadorEspias c = new ComunicadorEspias("/lista_de_espias/lista-de-espias-test-2.xlsx");
		
		assertTrue(c.existeComunicacion("RODOLFO", "JUAN"));
	}
	
	@Test
	public void noExisteComunicacionTest() {
		ComunicadorEspias c = new ComunicadorEspias("/lista_de_espias/lista-de-espias-test-2.xlsx");

		
		assertFalse(c.existeComunicacion("Pepe", "Ruben"));
	}
	
	@Test
	public void obtenerProbIntercepcionTest() {
		ComunicadorEspias c = new ComunicadorEspias("/lista_de_espias/lista-de-espias-test-2.xlsx");

		
		assertTrue(0.1 == c.obtenerProbabIntercepcion("Juan", "Ivan"));
	}
	
	@Test
	public void agmKruskalTest() {
		ComunicadorEspias c = new ComunicadorEspias("/lista_de_espias/lista-de-espias-test-2.xlsx");

		
		GrafoNDPonderado agmEsperado = new GrafoNDPonderado(c.cantidadEspias());
		System.out.println(c.toString());
		//System.out.println(c.obtenerProbabIntercepcion("Alvaro", "Jose"));
		agmEsperado.agregarArista(0, 1, 0.1); // Juan, Ivan, 0.1
		agmEsperado.agregarArista(2, 11, 0.4); // Ruben, Hugo, 0.4
		agmEsperado.agregarArista(3, 5, 0.3); // William, Pepe, 0.3
		agmEsperado.agregarArista(4, 0, 0.4); // Rodolfo, Juan, 0.4
		agmEsperado.agregarArista(6, 7, 0.9); // Jose, Mabel, 0.9
		agmEsperado.agregarArista(8, 5, 0.5); // Alvaro, Pepe, 0.5
		agmEsperado.agregarArista(9, 1, 0.1); // Julieta, Ivan, 0.1
		agmEsperado.agregarArista(7, 12, 0.8); // Mabel, Candela, 0.8
		agmEsperado.agregarArista(8, 10, 0.7); // Alvaro, Gabriela, 0.7
		agmEsperado.agregarArista(0, 2, 0.9); // Juan, Ruben, 0.9
		agmEsperado.agregarArista(2, 3, 0.7); // Ruben, William, 0.7
		agmEsperado.agregarArista(7, 10, 0.9); // Mabel, Gabriela, 0.9
		
		assertEquals(agmEsperado, c.obtenerRedSegura());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void probIntercepcionEspiasNoComunicadosTest() {
		ComunicadorEspias c = new ComunicadorEspias("/lista_de_espias/lista-de-espias-test-2.xlsx");

		
		c.obtenerProbabIntercepcion("Pepe", "Ivan");
	}
	
}
