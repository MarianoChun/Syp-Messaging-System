package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import grafos.GrafoNDPonderado;
import recorridos.BFS;

public class BFSTest {

	@Test
	public void alcanzablesTrivialTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(3);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 2);

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
		GrafoNDPonderado g = new GrafoNDPonderado(4);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		BFS bfs = new BFS(g);
		Set<Integer> alcanzablesDesdeElPrimero = bfs.verticesAlcanzablesDesdeVertice(0);
		Set<Integer> verticesEsperados = new HashSet<Integer>();
		verticesEsperados.add(0);
		verticesEsperados.add(1);
		verticesEsperados.add(2);
		assertEquals(alcanzablesDesdeElPrimero, verticesEsperados);
	}

	public void cliqueConexoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(3);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 2);

		BFS bfs = new BFS(g);
		assertTrue(bfs.esConexo());
	}

	public void disconexoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		BFS bfs = new BFS(g);
		assertFalse(bfs.esConexo());
	}
}
