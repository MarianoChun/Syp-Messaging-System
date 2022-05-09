package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import agm.Kruskal;
import grafo.GrafoNDPEtiquetado;
import grafo.Vertice;

public class KruskalTest {

	// Happy path
	@Test
	public void kruskalTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0, "Alvaro"), new Vertice(1, "Candela"), 0.4);
		g.agregarArista(new Vertice(0, "Alvaro"), new Vertice(3, "Sasha"), 1.0);
		g.agregarArista(new Vertice(1, "Candela"), new Vertice(3, "Sasha"), 0.2);
		g.agregarArista(new Vertice(1, "Candela"), new Vertice(2, "Gerardo"), 0.5);
		g.agregarArista(new Vertice(3, "Sasha"), new Vertice(4, "Pocho"), 1.0);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(5);
		esperado.agregarArista(new Vertice(0, "Alvaro"), new Vertice(1, "Candela"), 0.4);
		esperado.agregarArista(new Vertice(1, "Candela"), new Vertice(2, "Gerardo"), 0.5);
		esperado.agregarArista(new Vertice(3, "Sasha"), new Vertice(1, "Candela"), 0.2);
		esperado.agregarArista(new Vertice(3, "Sasha"), new Vertice(4, "Pocho"), 1.0);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGeneradorMinimo();
		assertEquals(esperado, actual);

	}

	// Caso borde. Componentes conexas distintas, en cierto punto habra 2 caminos
	// posibles,
	// uno de los caminos forma circuito, el otro no
	@Test
	public void kruskalSegundoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);
		g.agregarArista(new Vertice(0, "ivan"), new Vertice(1, "Alvaro"), 0.2);
		g.agregarArista(new Vertice(0, "ivan"), new Vertice(2, "Mabel"), 0.3);
		g.agregarArista(new Vertice(1, "Alvaro"), new Vertice(2, "Mabel"), 0.4);
		g.agregarArista(new Vertice(2, "Mabel"), new Vertice(4, "Hugo"), 0.6);
		g.agregarArista(new Vertice(2, "Mabel"), new Vertice(3, "Lola"), 0.2);
		g.agregarArista(new Vertice(2, "Mabel"), new Vertice(5, "Flavia"), 0.5);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(6);
		esperado.agregarArista(new Vertice(0, "ivan"), new Vertice(1, "Alvaro"), 0.2);
		esperado.agregarArista(new Vertice(0, "ivan"), new Vertice(2, "Mabel"), 0.3);
		esperado.agregarArista(new Vertice(2, "Mabel"), new Vertice(4, "Hugo"), 0.6);
		esperado.agregarArista(new Vertice(2, "Mabel"), new Vertice(3, "Lola"), 0.2);
		esperado.agregarArista(new Vertice(2, "Mabel"), new Vertice(5, "Flavia"), 0.5);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGeneradorMinimo();
		assertEquals(esperado, actual);
	}

	@Test
	public void kruskaAristasMismoPesoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);
		g.agregarArista(new Vertice(0, "John"), new Vertice(1, "Pepe"), 0.2);
		g.agregarArista(new Vertice(0, "John"), new Vertice(2, "ivan"), 0.2);
		g.agregarArista(new Vertice(1, "Pepe"), new Vertice(2, "ivan"), 0.2);
		g.agregarArista(new Vertice(2, "ivan"), new Vertice(4, "Ruben"), 0.2);
		g.agregarArista(new Vertice(2, "ivan"), new Vertice(3, "Julia"), 0.2);
		g.agregarArista(new Vertice(2, "ivan"), new Vertice(5, "Candela"), 0.2);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(6);
		esperado.agregarArista(new Vertice(0, "John"), new Vertice(1, "Pepe"), 0.2);
		esperado.agregarArista(new Vertice(0, "John"), new Vertice(2, "ivan"), 0.2);
		esperado.agregarArista(new Vertice(2, "ivan"), new Vertice(4, "Ruben"), 0.2);
		esperado.agregarArista(new Vertice(2, "ivan"), new Vertice(3, "Julia"), 0.2);
		esperado.agregarArista(new Vertice(2, "ivan"), new Vertice(5, "Candela"), 0.2);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGeneradorMinimo();
		assertEquals(esperado, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void kruskalPesosNegativosTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);
		g.agregarArista(new Vertice(0, "John"), new Vertice(1, "ivan"), -0.2);
		g.agregarArista(new Vertice(0, "John"), new Vertice(2, "Ricardo"), -0.3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
		new Kruskal(g);
	}
}
