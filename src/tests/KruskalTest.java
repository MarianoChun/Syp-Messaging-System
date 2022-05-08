package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import agm.Kruskal;
import grafo.Arista;
import grafo.GrafoNDPEtiquetado;
import grafo.Vertice;

public class KruskalTest {

	@Test
	public void aristasOrdenadasTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(0, "Pocho"), new Vertice(1, "Zoe"), 1.0);
		g.agregarArista(new Vertice(0, "Pocho"), new Vertice(2, "Roberto"), 5.0);
		g.agregarArista(new Vertice(0, "Pocho"), new Vertice(3, "Santiago"), 2.0);
		g.agregarArista(new Vertice(1, "Zoe"), new Vertice(3, "Santiago"), 9.0);
		g.agregarArista(new Vertice(2, "Roberto"), new Vertice(3, "Santiago"), 3.0);

		ArrayList<Arista> esperado = new ArrayList<Arista>();
		esperado.add(new Arista(new Vertice(0, "Pocho"), new Vertice(1, "Zoe"), 1.0));
		esperado.add(new Arista(new Vertice(0, "Pocho"), new Vertice(3, "Santiago"), 2.0));
		esperado.add(new Arista(new Vertice(2, "Roberto"), new Vertice(3, "Santiago"), 3.0));
		esperado.add(new Arista(new Vertice(0, "Pocho"), new Vertice(2, "Roberto"), 5.0));
		esperado.add(new Arista(new Vertice(1, "Zoe"), new Vertice(3, "Santiago"), 3.0));

		ArrayList<Arista> listaActual = new Kruskal(g).getAristasOrdenadas();
		System.out.println(listaActual);
		for (Arista elem : listaActual) {
			Vertice i = elem.getPrimerExtremo();
			Vertice j = elem.getSegundoExtremo();
			double peso = g.obtenerPesoArista(i, j);
			System.out.println(i + ", " + j + ", " + peso);
		}

	}

	// Happy path
	@Test
	public void kruskalTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0, "Alvaro"), new Vertice(1, "Candela"), 4.0);
		g.agregarArista(new Vertice(0, "Alvaro"), new Vertice(3, "Sasha"), 1.0);
		g.agregarArista(new Vertice(1, "Candela"), new Vertice(3, "Sasha"), 2.0);
		g.agregarArista(new Vertice(1, "Candela"), new Vertice(2, "Gerardo"), 0.5);
		g.agregarArista(new Vertice(3, "Sasha"), new Vertice(4, "Pocho"), 1.0);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(5);
		esperado.agregarArista(new Vertice(0, "Alvaro"), new Vertice(3, "Sasha"), 1.0);
		esperado.agregarArista(new Vertice(3, "Sasha"), new Vertice(4, "Pocho"), 1.0);
		esperado.agregarArista(new Vertice(3, "Sasha"), new Vertice(1, "Candela"), 2.0);
		esperado.agregarArista(new Vertice(1, "Candela"), new Vertice(2, "Gerardo"), 0.5);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGeneradorMinimo();
		System.out.println(actual.toString());
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
		g.agregarArista(new Vertice(2, "Mabel"), new Vertice(5, "Flavia"), 2.5);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(6);
		esperado.agregarArista(new Vertice(0, "ivan"), new Vertice(1, "Alvaro"), 0.2);
		esperado.agregarArista(new Vertice(0, "ivan"), new Vertice(2, "Mabel"), 0.3);
		esperado.agregarArista(new Vertice(2, "Mabel"), new Vertice(4, "Hugo"), 0.6);
		esperado.agregarArista(new Vertice(2, "Mabel"), new Vertice(3, "Lola"), 0.2);
		esperado.agregarArista(new Vertice(2, "Mabel"), new Vertice(5, "Flavia"), 2.5);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGeneradorMinimo();
		System.out.println(actual.toString());
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
		System.out.println(actual.toString());
		assertEquals(esperado, actual);
	}

	@Test
	public void kruskalPesosNegativosTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);
		g.agregarArista(new Vertice(0, "John"), new Vertice(1, "ivan"), -0.2);
		g.agregarArista(new Vertice(0, "John"), new Vertice(2, "Ricardo"), -0.3);
		g.agregarArista(new Vertice(1, "ivan"), new Vertice(2, "Ricardo"), -0.4);
		g.agregarArista(new Vertice(2, "Ricardo"), new Vertice(4, "Julia"), -0.6);
		g.agregarArista(new Vertice(2, "Ricardo"), new Vertice(3, "Ismael"), -0.2);
		g.agregarArista(new Vertice(2, "Ricardo"), new Vertice(5, "Paula"), -2.5);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(6);
		esperado.agregarArista(new Vertice(0, "John"), new Vertice(2, "Ricardo"), -0.3);
		esperado.agregarArista(new Vertice(1, "ivan"), new Vertice(2, "Ricardo"), -0.4);
		esperado.agregarArista(new Vertice(2, "Ricardo"), new Vertice(4, "Julia"), -0.6);
		esperado.agregarArista(new Vertice(2, "Ricardo"), new Vertice(3, "Ismael"), -0.2);
		esperado.agregarArista(new Vertice(2, "Ricardo"), new Vertice(5, "Paula"), -2.5);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGeneradorMinimo();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
		new Kruskal(g);
	}
}
