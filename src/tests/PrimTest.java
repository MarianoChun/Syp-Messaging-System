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
		g.agregarArista(new Vertice(0, "ernesto"), new Vertice(1, "angelo"), 0.5);
		g.agregarArista(new Vertice(0, "ernesto"), new Vertice(2, "maite"), 0.56);
		g.agregarArista(new Vertice(0, "ernesto"), new Vertice(4, "walter"), 0.8);

		g.agregarArista(new Vertice(1, "angelo"), new Vertice(4, "walter"), 1.0);
		g.agregarArista(new Vertice(1, "angelo"), new Vertice(2, "maite"), 0.34);
		g.agregarArista(new Vertice(1, "angelo"), new Vertice(3, "mariano"), 0.53);

		g.agregarArista(new Vertice(2, "maite"), new Vertice(3, "mariano"), 0.10);
		g.agregarArista(new Vertice(3, "mariano"), new Vertice(4, "walter"), 0.15);

		GrafoNDPEtiquetado AGMEsperado = new GrafoNDPEtiquetado(5);
		AGMEsperado.agregarArista(new Vertice(0, "ernesto"), new Vertice(1, "angelo"), 0.5);
		AGMEsperado.agregarArista(new Vertice(1, "angelo"), new Vertice(2, "maite"), 0.34);
		AGMEsperado.agregarArista(new Vertice(2, "maite"), new Vertice(3, "mariano"), 0.10);
		AGMEsperado.agregarArista(new Vertice(3, "mariano"), new Vertice(4, "walter"), 0.15);

		GrafoNDPEtiquetado AGMResultante = new Prim(g).obtenerArbolGeneradorMinimo(0);

		assertEquals(AGMEsperado, AGMResultante);
	}

	@Test
	public void AGMPrimSegundoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0, "maite"), new Vertice(1, "ernesto"), 0.21);
		g.agregarArista(new Vertice(0, "maite"), new Vertice(2, "walter"), 0.23);
		g.agregarArista(new Vertice(1, "ernesto"), new Vertice(2, "walter"), 0.57);
		g.agregarArista(new Vertice(2, "walter"), new Vertice(3, "angelo"), 0.20);
		g.agregarArista(new Vertice(2, "walter"), new Vertice(4, "mariano"), 0.02);
		g.agregarArista(new Vertice(3, "angelo"), new Vertice(4, "mariano"), 1.0);

		GrafoNDPEtiquetado AGMEsperado = new GrafoNDPEtiquetado(5);
		AGMEsperado.agregarArista(new Vertice(0, "maite"), new Vertice(1, "ernesto"), 0.21);
		AGMEsperado.agregarArista(new Vertice(0, "maite"), new Vertice(2, "walter"), 0.23);
		AGMEsperado.agregarArista(new Vertice(2, "walter"), new Vertice(3, "angelo"), 0.20);
		AGMEsperado.agregarArista(new Vertice(2, "walter"), new Vertice(4, "mariano"), 0.02);

		GrafoNDPEtiquetado AGMResultante = new Prim(g).obtenerArbolGeneradorMinimo(0);

		assertEquals(AGMEsperado, AGMResultante);
	}

}
