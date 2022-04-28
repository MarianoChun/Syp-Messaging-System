package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import grafos.GrafoNDPonderado;
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
		
		assertFalse(c.existeComunicacion("Pepe", "Ruben"));
	}
	
	@Test
	public void obtenerProbIntercepcionTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacionDesdeExcel();
		
		assertTrue(0.1 == c.obtenerProbabIntercepcion("Juan", "Ivan"));
	}
	
	@Test
	public void agmKruskalTest() {
		// TODO: El AGM es el esperado, esta fallando el equals del grafoND. Al parecer
		// la matriz de aristas de ambas no es la misma, coloque prints en los equals para
		// ver donde entraba el false (El equals esta mal salvo que haya comparado los arboles mal, 
		// por ahi conviene hacer una lista de espias mas chica para testear, estuve un buen rato
		// tratando de buscar el error)
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacionDesdeExcel();
		
		GrafoNDPonderado agmEsperado = new GrafoNDPonderado(c.cantidadEspias());
		agmEsperado.agregarArista(0, 1, 0.1); // Juan, Ivan, 0.1
		agmEsperado.agregarArista(2, 11, 0.4); // Ruben, Hugo, 0.4
		agmEsperado.agregarArista(3, 5, 0.3); // William, Pepe, 0.3
		agmEsperado.agregarArista(4, 0, 0.4); // Rodolfo, Juan, 0.4
		agmEsperado.agregarArista(6, 9, 0.9); // Jose, Mabel, 0.9
		agmEsperado.agregarArista(7, 5, 0.5); // Alvaro, Pepe, 0.5
		agmEsperado.agregarArista(8, 1, 0.1); // Julieta, Ivan, 0.1
		agmEsperado.agregarArista(9, 12, 0.8); // Mabel, Candela, 0.8
		agmEsperado.agregarArista(10, 7, 0.7); // Gabriela, Alvaro, 0.7
		agmEsperado.agregarArista(0, 2, 0.9); // Juan, Ruben, 0.9
		
		assertEquals(agmEsperado, c.obtenerAGMComunicador());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void probIntercepcionEspiasNoComunicadosTest() {
		ComunicadorEspias c = new ComunicadorEspias();
		c.agregarComunicacionDesdeExcel();
		
		c.obtenerProbabIntercepcion("Pepe", "Ivan");
	}
	
}
