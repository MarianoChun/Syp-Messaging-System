package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import agm.Kruskal;
import grafos.Arista;
import grafos.Assert;
import grafos.GrafoNDPonderado;
import grafos.Vertice;

public class KruskalTest {

	@Test
	public void aristasOrdenadasTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);
		g.agregarArista(0, 1, 1.0);
		g.agregarArista(0, 2, 5.0);
		g.agregarArista(0, 3, 2.0);
		g.agregarArista(1, 3, 9.0);
		g.agregarArista(2, 3, 3.0);

		ArrayList<Arista> esperado = new ArrayList<Arista>();
		esperado.add(new Arista(new Vertice(0), new Vertice(0), 1.0));
		esperado.add(new Arista(new Vertice(0), new Vertice(3), 2.0));
		esperado.add(new Arista(new Vertice(2), new Vertice(3), 3.0));
		esperado.add(new Arista(new Vertice(0), new Vertice(2), 5.0));
		esperado.add(new Arista(new Vertice(1), new Vertice(3), 3.0));

		ArrayList<Arista> listaActual = new Kruskal(g).getAristasOrdenadas();
		System.out.println(listaActual);
		for (Arista elem : listaActual) {
			int i = elem.getPrimerExtremo().getIndice();
			int j = elem.getSegundoExtremo().getIndice();
			double peso = g.obtenerPesoArista(i, j);
			System.out.println(i + ", " + j + ", " + peso);
		}

//		Assert.equals(esperado, listaActual);
//		System.out.println(obtenido[0]);
//		System.out.println(obtenido[1]);
//		assertEquals(esperado[0], obtenido[0]);
//		assertEquals(esperado[1], obtenido[1]);
	}

	// Happy path
	@Test
	public void kruskalTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(5);
		g.agregarArista(0, 1, 4.0);
		g.agregarArista(0, 3, 1.0);
		g.agregarArista(1, 3, 2.0);
		g.agregarArista(1, 2, 0.5);
		g.agregarArista(3, 4, 1.0);

		GrafoNDPonderado esperado = new GrafoNDPonderado(5);
		esperado.agregarArista(0, 3, 1.0);
		esperado.agregarArista(3, 4, 1.0);
		esperado.agregarArista(3, 1, 2.0);
		esperado.agregarArista(1, 2, 0.5);

		GrafoNDPonderado actual = new Kruskal(g).obtenerArbolGM();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);

	}

	// Caso borde. Componentes conexas distintas, en cierto punto habra 2 caminos
	// posibles,
	// uno de los caminos forma circuito, el otro no
	@Test
	public void kruskalSegundoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(6);
		g.agregarArista(0, 1, 0.2);
		g.agregarArista(0, 2, 0.3);
		g.agregarArista(1, 2, 0.4);
		g.agregarArista(2, 4, 0.6);
		g.agregarArista(2, 3, 0.2);
		g.agregarArista(2, 5, 2.5);

		GrafoNDPonderado esperado = new GrafoNDPonderado(6);
		esperado.agregarArista(0, 1, 0.2);
		esperado.agregarArista(0, 2, 0.3);
		esperado.agregarArista(2, 4, 0.6);
		esperado.agregarArista(2, 3, 0.2);
		esperado.agregarArista(2, 5, 2.5);

		GrafoNDPonderado actual = new Kruskal(g).obtenerArbolGM();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);
	}

	@Test
	public void kruskaAristasMismoPesoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(6);
		g.agregarArista(0, 1, 0.2);
		g.agregarArista(0, 2, 0.2);
		g.agregarArista(1, 2, 0.2);
		g.agregarArista(2, 4, 0.2);
		g.agregarArista(2, 3, 0.2);
		g.agregarArista(2, 5, 0.2);

		GrafoNDPonderado esperado = new GrafoNDPonderado(6);
		esperado.agregarArista(0, 1, 0.2);
		esperado.agregarArista(0, 2, 0.2);
		esperado.agregarArista(2, 4, 0.2);
		esperado.agregarArista(2, 3, 0.2);
		esperado.agregarArista(2, 5, 0.2);

		GrafoNDPonderado actual = new Kruskal(g).obtenerArbolGM();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);
	}

	@Test
	public void kruskalPesosNegativosTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(6);
		g.agregarArista(0, 1, -0.2);
		g.agregarArista(0, 2, -0.3);
		g.agregarArista(1, 2, -0.4);
		g.agregarArista(2, 4, -0.6);
		g.agregarArista(2, 3, -0.2);
		g.agregarArista(2, 5, -2.5);

		GrafoNDPonderado esperado = new GrafoNDPonderado(6);
		esperado.agregarArista(0, 2, -0.3);
		esperado.agregarArista(1, 2, -0.4);
		esperado.agregarArista(2, 4, -0.6);
		esperado.agregarArista(2, 3, -0.2);
		esperado.agregarArista(2, 5, -2.5);

		GrafoNDPonderado actual = new Kruskal(g).obtenerArbolGM();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(3);
		new Kruskal(g);
	}
}
