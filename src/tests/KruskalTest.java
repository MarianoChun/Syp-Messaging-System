package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import agm.Kruskal;
import grafos.Arista;
import grafos.GrafoNDPEtiquetado;
import grafos.GrafoNDPonderado;
import grafos.Vertice;

public class KruskalTest {

	@Test
	public void aristasOrdenadasTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(0), new Vertice(1), 1.0);
		g.agregarArista(new Vertice(0), new Vertice(2), 5.0);
		g.agregarArista(new Vertice(0), new Vertice(3), 2.0);
		g.agregarArista(new Vertice(1), new Vertice(3), 9.0);
		g.agregarArista(new Vertice(2), new Vertice(3), 3.0);

		ArrayList<Arista> esperado = new ArrayList<Arista>();
		esperado.add(new Arista(new Vertice(0), new Vertice(0), 1.0));
		esperado.add(new Arista(new Vertice(0), new Vertice(3), 2.0));
		esperado.add(new Arista(new Vertice(2), new Vertice(3), 3.0));
		esperado.add(new Arista(new Vertice(0), new Vertice(2), 5.0));
		esperado.add(new Arista(new Vertice(1), new Vertice(3), 3.0));

		ArrayList<Arista> listaActual = new Kruskal(g).getAristasOrdenadas();
		System.out.println(listaActual);
		for (Arista elem : listaActual) {
			Vertice i = elem.getPrimerExtremo();
			Vertice j = elem.getSegundoExtremo();
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
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(5);
		g.agregarArista(new Vertice(0), new Vertice(1), 4.0);
		g.agregarArista(new Vertice(0), new Vertice(3), 1.0);
		g.agregarArista(new Vertice(1), new Vertice(3), 2.0);
		g.agregarArista(new Vertice(1), new Vertice(2), 0.5);
		g.agregarArista(new Vertice(3), new Vertice(4), 1.0);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(5);
		esperado.agregarArista(new Vertice(0), new Vertice(3), 1.0);
		esperado.agregarArista(new Vertice(3), new Vertice(4), 1.0);
		esperado.agregarArista(new Vertice(3), new Vertice(1), 2.0);
		esperado.agregarArista(new Vertice(1), new Vertice(2), 0.5);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGM();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);

	}

	// Caso borde. Componentes conexas distintas, en cierto punto habra 2 caminos
	// posibles,
	// uno de los caminos forma circuito, el otro no
	@Test
	public void kruskalSegundoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);
		g.agregarArista(new Vertice(0), new Vertice(1), 0.2);
		g.agregarArista(new Vertice(0), new Vertice(2), 0.3);
		g.agregarArista(new Vertice(1), new Vertice(2), 0.4);
		g.agregarArista(new Vertice(2), new Vertice(4), 0.6);
		g.agregarArista(new Vertice(2), new Vertice(3), 0.2);
		g.agregarArista(new Vertice(2), new Vertice(5), 2.5);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(6);
		esperado.agregarArista(new Vertice(0), new Vertice(1), 0.2);
		esperado.agregarArista(new Vertice(0), new Vertice(2), 0.3);
		esperado.agregarArista(new Vertice(2), new Vertice(4), 0.6);
		esperado.agregarArista(new Vertice(2), new Vertice(3), 0.2);
		esperado.agregarArista(new Vertice(2), new Vertice(5), 2.5);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGM();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);
	}

	@Test
	public void kruskaAristasMismoPesoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);
		g.agregarArista(new Vertice(0), new Vertice(1), 0.2);
		g.agregarArista(new Vertice(0), new Vertice(2), 0.2);
		g.agregarArista(new Vertice(1), new Vertice(2), 0.2);
		g.agregarArista(new Vertice(2), new Vertice(4), 0.2);
		g.agregarArista(new Vertice(2), new Vertice(3), 0.2);
		g.agregarArista(new Vertice(2), new Vertice(5), 0.2);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(6);
		esperado.agregarArista(new Vertice(0), new Vertice(1), 0.2);
		esperado.agregarArista(new Vertice(0), new Vertice(2), 0.2);
		esperado.agregarArista(new Vertice(2), new Vertice(4), 0.2);
		esperado.agregarArista(new Vertice(2), new Vertice(3), 0.2);
		esperado.agregarArista(new Vertice(2), new Vertice(5), 0.2);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGM();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);
	}

	@Test
	public void kruskalPesosNegativosTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(6);
		g.agregarArista(new Vertice(0), new Vertice(1), -0.2);
		g.agregarArista(new Vertice(0), new Vertice(2), -0.3);
		g.agregarArista(new Vertice(1), new Vertice(2), -0.4);
		g.agregarArista(new Vertice(2), new Vertice(4), -0.6);
		g.agregarArista(new Vertice(2), new Vertice(3), -0.2);
		g.agregarArista(new Vertice(2), new Vertice(5), -2.5);

		GrafoNDPEtiquetado esperado = new GrafoNDPEtiquetado(6);
		esperado.agregarArista(new Vertice(0), new Vertice(2), -0.3);
		esperado.agregarArista(new Vertice(1), new Vertice(2), -0.4);
		esperado.agregarArista(new Vertice(2), new Vertice(4), -0.6);
		esperado.agregarArista(new Vertice(2), new Vertice(3), -0.2);
		esperado.agregarArista(new Vertice(2), new Vertice(5), -2.5);

		GrafoNDPEtiquetado actual = new Kruskal(g).obtenerArbolGM();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void grafoNoConexoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
		new Kruskal(g);
	}
}
