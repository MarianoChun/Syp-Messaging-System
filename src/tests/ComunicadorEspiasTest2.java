package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import grafos.GrafoNDPEtiquetado;
import grafos.Vertice;
import sistema_espias.ComunicadorEspias;

public class ComunicadorEspiasTest2 {
	// NOTA: Esta suite de test utiliza los espias de "lista-de-espias-test-2"
	private ComunicadorEspias c;

	@Before
	public void cargarEspias() {
		c = new ComunicadorEspias("/lista_de_espias/lista-de-espias-test-2.xlsx");
	}

	@Test
	public void cargarEspiasYComunicacionesTest() {
		assertTrue(c.existeComunicacion("Ruben", "William"));
	}

	@Test
	public void existeComunicacionMayusculaTest() {
		assertTrue(c.existeComunicacion("RODOLFO", "JUAN"));
	}

	@Test
	public void noExisteComunicacionTest() {
		assertFalse(c.existeComunicacion("Pepe", "Ruben"));
	}

	@Test
	public void obtenerProbIntercepcionTest() {
		assertTrue(0.1 == c.obtenerProbabIntercepcion("Juan", "Ivan"));
	}

	@Test
	public void agmKruskalTest() {
		GrafoNDPEtiquetado agmEsperado = new GrafoNDPEtiquetado(c.cantidadEspias());

		agmEsperado.agregarArista(new Vertice(0, "juan"), new Vertice(1, "ivan"), 0.1); // Juan, Ivan, 0.1
		agmEsperado.agregarArista(new Vertice(2, "RuBen"), new Vertice(11, "HUGO"), 0.4); // Ruben, Hugo, 0.4
		agmEsperado.agregarArista(new Vertice(3, "William"), new Vertice(5, "PePe"), 0.3); // William, Pepe, 0.3
		agmEsperado.agregarArista(new Vertice(4, "RoDOLFo"), new Vertice(0, "Juan"), 0.4); // Rodolfo, Juan, 0.4
		agmEsperado.agregarArista(new Vertice(6, "JOSE"), new Vertice(7, "mabel"), 0.9); // Jose, Mabel, 0.9
		agmEsperado.agregarArista(new Vertice(8, "AlVaro"), new Vertice(5, "pepe"), 0.5); // Alvaro, Pepe, 0.5
		agmEsperado.agregarArista(new Vertice(9, "Julieta"), new Vertice(1, "IVAn"), 0.1); // Julieta, Ivan, 0.1
		agmEsperado.agregarArista(new Vertice(7, "MaBeL"), new Vertice(12, "CandeLA"), 0.8); // Mabel, Candela, 0.8
		agmEsperado.agregarArista(new Vertice(8, "alvaro"), new Vertice(10, "Gabriela"), 0.7); // Alvaro, Gabriela, 0.7
		agmEsperado.agregarArista(new Vertice(0, "juan"), new Vertice(2, "ruben"), 0.9); // Juan, Ruben, 0.9
		agmEsperado.agregarArista(new Vertice(2, "rUBEN"), new Vertice(3, "william"), 0.7); // Ruben, William, 0.7
		agmEsperado.agregarArista(new Vertice(7, "mabel"), new Vertice(10, "gabriela"), 0.9); // Mabel, Gabriela, 0.9

		assertEquals(agmEsperado, c.obtenerRedSeguraKruskal());
	}

	@Test
	public void agmPrimTest() {
		GrafoNDPEtiquetado agmEsperado = new GrafoNDPEtiquetado(c.cantidadEspias());

		agmEsperado.agregarArista(new Vertice(0, "juan"), new Vertice(1, "ivan"), 0.1); // Juan, Ivan, 0.1
		agmEsperado.agregarArista(new Vertice(2, "RuBen"), new Vertice(11, "HUGO"), 0.4); // Ruben, Hugo, 0.4
		agmEsperado.agregarArista(new Vertice(3, "William"), new Vertice(5, "PePe"), 0.3); // William, Pepe, 0.3
		agmEsperado.agregarArista(new Vertice(4, "RoDOLFo"), new Vertice(0, "Juan"), 0.4); // Rodolfo, Juan, 0.4
		agmEsperado.agregarArista(new Vertice(6, "JOSE"), new Vertice(7, "mabel"), 0.9); // Jose, Mabel, 0.9
		agmEsperado.agregarArista(new Vertice(8, "AlVaro"), new Vertice(5, "pepe"), 0.5); // Alvaro, Pepe, 0.5
		agmEsperado.agregarArista(new Vertice(9, "Julieta"), new Vertice(1, "IVAn"), 0.1); // Julieta, Ivan, 0.1
		agmEsperado.agregarArista(new Vertice(7, "MaBeL"), new Vertice(12, "CandeLA"), 0.8); // Mabel, Candela, 0.8
		agmEsperado.agregarArista(new Vertice(8, "alvaro"), new Vertice(10, "Gabriela"), 0.7); // Alvaro, Gabriela, 0.7
		agmEsperado.agregarArista(new Vertice(0, "juan"), new Vertice(2, "ruben"), 0.9); // Juan, Ruben, 0.9
		agmEsperado.agregarArista(new Vertice(2, "rUBEN"), new Vertice(3, "william"), 0.7); // Ruben, William, 0.7
		agmEsperado.agregarArista(new Vertice(7, "mabel"), new Vertice(10, "gabriela"), 0.9); // Mabel, Gabriela, 0.9
		
		assertEquals(agmEsperado, c.obtenerRedSeguraPrim());
	}
	@Test
	public void agregarEspiasMayusculasTest() {
		assertTrue(c.existeComunicacion("JuLieTa", "IVAN") && c.existeComunicacion("ruben", "HuGO"));
	}

	@Test
	public void obtenerProbabIntercepcionTest() {
		double esperado = 0.1;
		assertTrue(esperado == c.obtenerProbabIntercepcion("juan", "ivan"));
	}
	
	@Test
	public void obtenerIndiceEspiaTest() {
		double esperado = 3;
		assertTrue(esperado == c.obtenerIndiceEspia("William"));
	}

	@Test
	public void obtenerEspiasTest() {
		Map<String, Integer> espiasEsperados = new HashMap<String, Integer>();
		espiasEsperados.put("juan", 0);
		espiasEsperados.put("ivan", 1);
		espiasEsperados.put("ruben", 2);
		espiasEsperados.put("william", 3);
		espiasEsperados.put("rodolfo", 4);
		espiasEsperados.put("pepe", 5);
		espiasEsperados.put("jose", 6);
		espiasEsperados.put("mabel", 7);
		espiasEsperados.put("alvaro", 8);
		espiasEsperados.put("julieta", 9);
		espiasEsperados.put("gabriela", 10);
		espiasEsperados.put("hugo", 11);
		espiasEsperados.put("candela", 12);
		
		assertTrue(espiasEsperados.equals(c.obtenerEspias()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verificarExistenEspiasTest() {
		c.agregarComunicacion("Mariano", "Tito", 0.2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verificarProbabilidadIntercepcionNegativaTest() {
		c.agregarComunicacion("Juan", "Rodolfo", -0.5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verificarProbabilidadIntercepcionMayorA1Test() {
		c.agregarComunicacion("William", "Ivan", 2.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void probIntercepcionEspiasNoComunicadosTest() {
		c.obtenerProbabIntercepcion("Pepe", "Ivan");
	}
}
