package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import agm.Prim;
import grafos.GrafoNDPonderado;

public class PrimTest {

	@Test
	public void AGMPrimPrimerTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(5);
		g.agregarArista(0, 1, 7.5);
		g.agregarArista(0, 2, 5.5);
		g.agregarArista(0, 4, 8.0);

		g.agregarArista(1, 4, 11.0);
		g.agregarArista(1, 2, 2.0);
		g.agregarArista(1, 3, 8.0);

		g.agregarArista(2, 3, 3.0);
		g.agregarArista(3, 4, 15.0);

		GrafoNDPonderado AGMEsperado = new GrafoNDPonderado(5);
		AGMEsperado.agregarArista(0, 2, 5.5);
		AGMEsperado.agregarArista(0, 4, 8.0);
		AGMEsperado.agregarArista(2, 1, 2.0);
		AGMEsperado.agregarArista(2, 3, 3.0);

		GrafoNDPonderado AGMResultante = new Prim(g).obtenerArbolGeneradorMinimo(0);

		assertEquals(AGMEsperado, AGMResultante);
	}

	@Test
	public void AGMPrimSegundoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(5);
		g.agregarArista(0, 1, 15.2);
		g.agregarArista(0, 2, 20.3);
		g.agregarArista(1, 2, 4.5);

		g.agregarArista(2, 3, 20);
		g.agregarArista(2, 4, 20.0);
		g.agregarArista(3, 4, 30.0);

		GrafoNDPonderado AGMEsperado = new GrafoNDPonderado(5);
		AGMEsperado.agregarArista(0, 1, 15.2);
		AGMEsperado.agregarArista(1, 2, 4.5);
		AGMEsperado.agregarArista(2, 3, 20.0);
		AGMEsperado.agregarArista(2, 4, 20.0);

		GrafoNDPonderado AGMResultante = new Prim(g).obtenerArbolGeneradorMinimo(0);

		assertEquals(AGMEsperado, AGMResultante);
	}

}
