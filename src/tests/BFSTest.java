package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import grafos.GrafoNDPEtiquetado;
import grafos.Vertice;
import recorridos.BFS;

public class BFSTest {

	@Test
	public void alcanzablesTrivialTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
		g.agregarArista(new Vertice(0, "Hugo"), new Vertice(1, "Jose"), 0.2);
		g.agregarArista(new Vertice(0, "Hugo"), new Vertice(2, "Mabel"), 0.6);
		g.agregarArista(new Vertice(1, "Jose"), new Vertice(2, "Mabel"), 0.9);

		BFS bfs = new BFS(g);
		Set<Integer> alcanzablesDesdeElPrimero = bfs.verticesAlcanzablesDesdeVertice(0);
		Set<Integer> verticesEsperados = new HashSet<Integer>();
		verticesEsperados.add(0);
		verticesEsperados.add(1);
		verticesEsperados.add(2);
		assertEquals(alcanzablesDesdeElPrimero, verticesEsperados);
	}

	@Test
	public void alcanzablesDisconexoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(0, "Mariano"), new Vertice(1, "Thiago"), 0.2);
		g.agregarArista(new Vertice(0, "Mariano"), new Vertice(2, "Guille"), 0.1);
		BFS bfs = new BFS(g);
		Set<Integer> alcanzablesDesdeElPrimero = bfs.verticesAlcanzablesDesdeVertice(0);
		Set<Integer> verticesEsperados = new HashSet<Integer>();
		verticesEsperados.add(0);
		verticesEsperados.add(1);
		verticesEsperados.add(2);
		assertEquals(alcanzablesDesdeElPrimero, verticesEsperados);
	}

	public void cliqueConexoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
		g.agregarArista(new Vertice(0, "Tito"), new Vertice(1, "Graciela"), 0.9);
		g.agregarArista(new Vertice(0, "Pepe"), new Vertice(2, "Paula"), 0.8);
		g.agregarArista(new Vertice(1, "Mario"), new Vertice(2, "Milagros"), 0.7);

		BFS bfs = new BFS(g);
		assertTrue(bfs.esConexo());
	}

	public void disconexoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(0, "Omar"), new Vertice(1, "Raul"), 0.4);
		g.agregarArista(new Vertice(0, "Roberto"), new Vertice(2, "Diana"), 0.2);

		BFS bfs = new BFS(g);
		assertFalse(bfs.esConexo());
	}
}
