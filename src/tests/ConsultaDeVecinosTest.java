package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import grafos.Assert;
import grafos.GrafoNDPEtiquetado;
import grafos.Vertice;

public class ConsultaDeVecinosTest {
	@Test(expected = IllegalArgumentException.class)
	public void verticeNegativoTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		grafo.vecinos(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticeExcedidoTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		grafo.vecinos(5);
	}

	@Test
	public void todosAisladosTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		assertEquals(0, grafo.vecinos(2).size());
	}

	@Test
	public void verticeUniversalTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(4);
		Vertice v1 = new Vertice(0, "0");
		Vertice v2 = new Vertice(1, "1");
		Vertice v3 = new Vertice(2, "2");
		Vertice v4 = new Vertice(3, "3");

		grafo.agregarArista(v1, v2, 0.1);
		grafo.agregarArista(v1, v3, 0.1);
		grafo.agregarArista(v1, v4, 0.1);

		int[] esperado = { 1, 2, 3 };
		Assert.iguales(esperado, grafo.vecinos(0));
	}

	@Test
	public void verticeNormalTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);

		Vertice v1 = new Vertice(1, "1");
		Vertice v2 = new Vertice(2, "2");
		Vertice v3 = new Vertice(3, "3");
		Vertice v4 = new Vertice(4, "4");

		grafo.agregarArista(v1, v3, 0.1);
		grafo.agregarArista(v2, v3, 0.1);
		grafo.agregarArista(v2, v4, 0.1);

		int[] esperados = { 1, 2 };
		Assert.iguales(esperados, grafo.vecinos(3));
	}
}
