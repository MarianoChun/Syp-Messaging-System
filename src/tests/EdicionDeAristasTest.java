package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import grafo.GrafoNDPEtiquetado;
import grafo.Vertice;

public class EdicionDeAristasTest {
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(-1, "-1");
		Vertice v2 = new Vertice(3, "3");
		grafo.agregarArista(v1, v2, 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(5, "5");
		Vertice v2 = new Vertice(3, "3");
		grafo.agregarArista(v1, v2, 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(3, "3");
		Vertice v2 = new Vertice(-1, "-1");
		grafo.agregarArista(v1, v2, 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(2, "2");
		Vertice v2 = new Vertice(5, "5");
		grafo.agregarArista(v1, v2, 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(2, "2");

		grafo.agregarArista(v1, v1, 0.1);
	}

	@Test
	public void aristaExistenteTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(2, "2");
		Vertice v2 = new Vertice(3, "3");

		grafo.agregarArista(v1, v2, 0.1);

		assertTrue(grafo.existeArista(v1.getIndice(), v2.getIndice()));
	}

	@Test
	public void aristaOpuestaTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(2, "2");
		Vertice v2 = new Vertice(3, "3");
		grafo.agregarArista(v1, v2, 0.1);

		assertTrue(grafo.existeArista(v2.getIndice(), v1.getIndice()));
	}

	@Test
	public void aristaInexistenteTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(2, "2");
		Vertice v2 = new Vertice(3, "3");

		grafo.agregarArista(v1, v2, 0.1);

		assertFalse(grafo.existeArista(1, 4));
	}

	@Test
	public void agregarAristaDosVecesTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(2, "2");
		Vertice v2 = new Vertice(3, "3");
		grafo.agregarArista(v1, v2, 0.1);
		grafo.agregarArista(v1, v2, 0.1);

		assertTrue(grafo.existeArista(2, 3));
	}

	@Test
	public void eliminarAristaExistenteTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(2, "2");
		Vertice v2 = new Vertice(4, "4");

		grafo.agregarArista(v1, v2, 0.1);

		grafo.eliminarArista(v1, v2);

		assertFalse(grafo.existeArista(v1.getIndice(), v2.getIndice()));
	}

	@Test
	public void eliminarAristaInexistenteTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(2, "2");
		Vertice v2 = new Vertice(4, "4");

		grafo.eliminarArista(v1, v2);

		assertFalse(grafo.existeArista(v1.getIndice(), v2.getIndice()));
	}

	@Test
	public void eliminarAristaDosVecesTest() {
		GrafoNDPEtiquetado grafo = new GrafoNDPEtiquetado(5);
		Vertice v1 = new Vertice(2, "2");
		Vertice v2 = new Vertice(4, "4");

		grafo.agregarArista(v1, v2, 0.1);
		grafo.eliminarArista(v1, v2);
		grafo.eliminarArista(v1, v2);

		assertFalse(grafo.existeArista(v1.getIndice(), v2.getIndice()));
	}
}
