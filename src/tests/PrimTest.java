package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import agm.Prim;
import grafos.GrafoNDPEtiquetado;
import grafos.GrafoNDPonderado;
import grafos.Vertice;

public class PrimTest {

	@Test
	public void AGMPrimPrimerTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0), new Vertice(1), 7.5);
		g.agregarArista(new Vertice(0), new Vertice(2), 5.5);
		g.agregarArista(new Vertice(0), new Vertice(4), 8.0);

		g.agregarArista(new Vertice(1), new Vertice(4), 11.0);
		g.agregarArista(new Vertice(1), new Vertice(2), 2.0);
		g.agregarArista(new Vertice(1), new Vertice(3), 8.0);

		g.agregarArista(new Vertice(2), new Vertice(3), 3.0);
		g.agregarArista(new Vertice(3), new Vertice(4), 15.0);

		GrafoNDPEtiquetado AGMEsperado = new GrafoNDPEtiquetado(5);
		AGMEsperado.agregarArista(new Vertice(0), new Vertice(2), 5.5);
		AGMEsperado.agregarArista(new Vertice(0), new Vertice(4), 8.0);
		AGMEsperado.agregarArista(new Vertice(2), new Vertice(1), 2.0);
		AGMEsperado.agregarArista(new Vertice(2), new Vertice(3), 3.0);

		GrafoNDPEtiquetado AGMResultante = new Prim(g).obtenerArbolGeneradorMinimo(0);

		assertEquals(AGMEsperado, AGMResultante);
	}

	@Test
	public void AGMPrimSegundoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0), new Vertice(1), 15.2);
		g.agregarArista(new Vertice(0), new Vertice(2), 20.3);
		g.agregarArista(new Vertice(1), new Vertice(2), 4.5);

		g.agregarArista(new Vertice(2), new Vertice(3), 20);
		g.agregarArista(new Vertice(2), new Vertice(4), 20.0);
		g.agregarArista(new Vertice(3), new Vertice(4), 30.0);

		GrafoNDPEtiquetado AGMEsperado = new GrafoNDPEtiquetado(5);
		AGMEsperado.agregarArista(new Vertice(0), new Vertice(1), 15.2);
		AGMEsperado.agregarArista(new Vertice(1), new Vertice(2), 4.5);
		AGMEsperado.agregarArista(new Vertice(2), new Vertice(3), 20.0);
		AGMEsperado.agregarArista(new Vertice(2), new Vertice(4), 20.0);

		GrafoNDPEtiquetado AGMResultante = new Prim(g).obtenerArbolGeneradorMinimo(0);

		assertEquals(AGMEsperado, AGMResultante);
	}

}
