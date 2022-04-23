package recorridos;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import grafos.GrafoND;

public class BFSListaTest {

	@Test
	public void alcanzablesTrivialTest() {
		GrafoND g = new GrafoND(4);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 3);
		g.agregarArista(2, 3);
		BFS bfs = new BFS(g);
		BFSLista bfsLista = new BFSLista(g);
		System.out.println(bfsLista.verticesAlcanzablesDesdeVertice(3));
		System.out.println(bfs.verticesAlcanzablesDesdeVertice(3));
	}

	@Test
	public void alcanzablesNoTrivialTest() {
		GrafoND g = new GrafoND(4);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);
		g.agregarArista(1, 3);
		g.agregarArista(2, 3);
		
		BFSLista bfsLista = new BFSLista(g);
//		System.out.println(bfsLista.verticesAlcanzablesDesdeVertice(3));
	}
	
}
