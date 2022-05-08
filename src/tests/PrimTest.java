package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import agm.Prim;
import grafo.GrafoNDPEtiquetado;
import grafo.Vertice;

public class PrimTest {

	@Test
	public void AGMPrimPrimerTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0, "ernesto"), new Vertice(1, "angelo"), 7.5);
		g.agregarArista(new Vertice(0, "ernesto"), new Vertice(2, "maite"), 5.5);
		g.agregarArista(new Vertice(0, "ernesto"), new Vertice(4, "walter"), 8.0);

		g.agregarArista(new Vertice(1, "angelo"), new Vertice(4, "walter"), 11.0);
		g.agregarArista(new Vertice(1, "angelo"), new Vertice(2, "maite"), 2.0);
		g.agregarArista(new Vertice(1, "angelo"), new Vertice(3, "mariano"), 8.0);

		g.agregarArista(new Vertice(2, "maite"), new Vertice(3, "mariano"), 3.0);
		g.agregarArista(new Vertice(3, "mariano"), new Vertice(4, "walter"), 15.0);

		GrafoNDPEtiquetado AGMEsperado = new GrafoNDPEtiquetado(5);
		AGMEsperado.agregarArista(new Vertice(0, "ernesto"), new Vertice(2, "maite"), 5.5);
		AGMEsperado.agregarArista(new Vertice(0, "ernesto"), new Vertice(4, "walter"), 8.0);
		AGMEsperado.agregarArista(new Vertice(2, "maite"), new Vertice(1, "angelo"), 2.0);
		AGMEsperado.agregarArista(new Vertice(2, "maite"), new Vertice(3, "mariano"), 3.0);

		GrafoNDPEtiquetado AGMResultante = new Prim(g).obtenerArbolGeneradorMinimo(0);

		assertEquals(AGMEsperado, AGMResultante);
	}

	@Test
	public void AGMPrimSegundoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0, "maite"), new Vertice(1, "ernesto"), 15.2);
		g.agregarArista(new Vertice(0, "maite"), new Vertice(2, "walter"), 20.3);
		g.agregarArista(new Vertice(1, "ernesto"), new Vertice(2, "walter"), 4.5);
		g.agregarArista(new Vertice(2, "walter"), new Vertice(3, "angelo"), 20);
		g.agregarArista(new Vertice(2, "walter"), new Vertice(4, "mariano"), 20.0);
		g.agregarArista(new Vertice(3, "angelo"), new Vertice(4, "mariano"), 30.0);

		GrafoNDPEtiquetado AGMEsperado = new GrafoNDPEtiquetado(5);
		AGMEsperado.agregarArista(new Vertice(0, "maite"), new Vertice(1, "ernesto"), 15.2);
		AGMEsperado.agregarArista(new Vertice(1, "ernesto"), new Vertice(2, "walter"), 4.5);
		AGMEsperado.agregarArista(new Vertice(2, "walter"), new Vertice(3, "angelo"), 20.0);
		AGMEsperado.agregarArista(new Vertice(2, "walter"), new Vertice(4, "mariano"), 20.0);

		GrafoNDPEtiquetado AGMResultante = new Prim(g).obtenerArbolGeneradorMinimo(0);

		assertEquals(AGMEsperado, AGMResultante);
	}

}
