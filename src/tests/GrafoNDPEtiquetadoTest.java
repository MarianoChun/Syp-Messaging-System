package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import grafo.Arista;
import grafo.GrafoNDPEtiquetado;
import grafo.Vertice;

public class GrafoNDPEtiquetadoTest {

	@Test
	public void agregarAristaTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(0, "Juan"), new Vertice(1, "Roberto"), 0.2);
		g.agregarArista(new Vertice(2, "Juan"), new Vertice(3, "Thiago"), 0.3);

		assertTrue(g.existeArista(3, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaIguales() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);

		g.agregarArista(new Vertice(0, "Juan"), new Vertice(0, "Juan"), 0.5);

		g.agregarArista(new Vertice(0, "Juan"), new Vertice(0, "Juan"), 0.5);
	}

	@Test
	public void eliminarAristaTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0, "Chacho"), new Vertice(1, "Ernesto"), 0.5);
		g.agregarArista(new Vertice(2, "Rodolfo"), new Vertice(1, "Ernesto"), 0.8);
		g.agregarArista(new Vertice(3, "Felipe"), new Vertice(4, "Kiko"), 0.2);

		g.eliminarArista(new Vertice(3, "Felipe"), new Vertice(4, "Kiko"));
		assertFalse(g.existeArista(3, 4));
	}

	@Test
	public void eliminarAristaDeArrayDeAristasTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);

		ArrayList<Arista> vacio = new ArrayList<Arista>();
		Vertice vertice1 = new Vertice(2, "2");
		Vertice vertice2 = new Vertice(3, "3");

		g.agregarArista(vertice1, vertice2, 0.1);
		g.eliminarArista(vertice1, vertice2);
		assertEquals(vacio, g.getAristas());
	}

	@Test
	public void aristaInexistenteTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(3, "Ricardo"), new Vertice(1, "Roberto"), 0.5);
		g.agregarArista(new Vertice(0, "Juan"), new Vertice(1, "Roberto"), 0.1);

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

	@Test
	public void obtenerPesoInversoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);

		g.agregarArista(new Vertice(1, "1"), new Vertice(2, "2"), 0.8);

		Assert.equals(0.8, g.obtenerPesoArista(new Vertice(2), new Vertice(1)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void obtenerPesoAristaInexistenteTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);

		g.obtenerPesoArista(new Vertice(2), new Vertice(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void obtenerPesoAristaEliminada() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(9);

		Vertice vertice1 = new Vertice(0);
		Vertice vertice2 = new Vertice(1);

		g.agregarArista(vertice1, vertice2, 0.9);
		g.eliminarArista(vertice2, vertice1);

		g.obtenerPesoArista(vertice1, vertice2);
	}
}
