package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import grafos.GrafoNDPEtiquetado;
import grafos.Vertice;

public class GrafoNDPEtiquetadoTest {

	@Test
	public void agregarAristaTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(0, "Juan"), new Vertice(1, "Roberto"), 2.0);
		g.agregarArista(new Vertice(2, "Juan"), new Vertice(3, "Thiago"), 3.0);

		assertTrue(g.existeArista(3, 2));
	}

	@Test
	public void eliminarAristaTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0, "Chacho"), new Vertice(1, "Kiko"), 0.5);
		g.agregarArista(new Vertice(2, "Rodolfo"), new Vertice(1, "Ernesto"), 0.8);
		g.agregarArista(new Vertice(3, "Felipe"), new Vertice(4, "Kiko"), 0.2);

		g.eliminarArista(new Vertice(3, "Felipe"), new Vertice(4, "Kiko"));
		assertFalse(g.existeArista(3, 4));
	}

	@Test
	public void existeAristaFalseTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(3, "Ricardo"), new Vertice(1, "Roberto"), 5.0);
		g.agregarArista(new Vertice(0, "Juan"), new Vertice(1, "Thiago"), 6.0);

		assertFalse(g.existeArista(0, 3));
	}

	@Test
	public void consultarEtiquetaTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(3, "Ivan"), new Vertice(1, "Pepe"), 1.0);
		String esperado = "ivan";

		assertEquals(esperado, g.obtenerEtiquetaVertice(3));
	}

	@Test
	public void consultarPesoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);
		g.agregarArista(new Vertice(4, "Jose"), new Vertice(0, "Tito"), 1.0);
		double esperado = 1.0;

		assertTrue(esperado == g.obtenerPesoArista(new Vertice(4), new Vertice(0)));
	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public void agregarArista1VerticeNoEtiquetadoTest() {
//		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
//		
//		g.agregarArista(new Vertice(0), new Vertice(1, "Pepe"), 1.5);
//	}
	
	
//	@Test(expected = IllegalArgumentException.class)
//	public void agregarArista2VerticesNoEtiquetadosTest() {
//		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
//		
//		g.agregarArista(new Vertice(0), new Vertice(2), 2.2);
//	}
}
