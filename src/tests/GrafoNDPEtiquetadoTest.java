package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import grafos.GrafoNDPEtiquetado;
import grafos.Vertice;

public class GrafoNDPEtiquetadoTest {

	@Test
	public void agregarAristaTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(0, "Juan"), new Vertice(1, "Roberto"));
		g.agregarArista(new Vertice(2, "Juan"), new Vertice(3, "Thiago"));
		
		assertTrue(g.existeArista(3, 2));
	}
	
	@Test
	public void existeAristaFalseTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(3, "Ricardo"), new Vertice(1, "Roberto"));
		g.agregarArista(new Vertice(0, "Juan"), new Vertice(1, "Thiago"));
		
		assertFalse(g.existeArista(0, 3));
	}
	
	@Test
	public void consultarEtiquetaTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(3, "Ivan"), new Vertice(1, "Pepe"));
		String esperado = "Ivan";
		
		assertEquals(esperado, g.obtenerEtiquetaVertice(3));
	}
	
	@Test
	public void consultarPesoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);
		g.agregarArista(new Vertice(4, "Jose"), new Vertice(0, "Tito"), 1.0);
		double esperado = 1.0;
		
		assertTrue(esperado == g.obtenerPesoArista(4, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarArista1VerticeNoEtiquetadoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
		
		g.agregarArista(new Vertice(0), new Vertice(1, "Pepe"));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarArista2VerticesNoEtiquetadosTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
		
		g.agregarArista(new Vertice(0), new Vertice(2));
	}

}
