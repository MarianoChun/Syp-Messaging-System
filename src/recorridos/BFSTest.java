package recorridos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import grafos.GrafoND;

public class BFSTest {

	@Test
	public void alcanzablesTrivialTest() {
		GrafoND g = new GrafoND(3);
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
		GrafoND g = new GrafoND(4);
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
		GrafoND g = new GrafoND(3);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 2);
		
		BFS bfs = new BFS(g);
		assertTrue(bfs.esConexo());
	}
	
	public void disconexoTest() {
		GrafoND g = new GrafoND(4);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		BFS bfs = new BFS(g);
		assertFalse(bfs.esConexo());
	}
}
