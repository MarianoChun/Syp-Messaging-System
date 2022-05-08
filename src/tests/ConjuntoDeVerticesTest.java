package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import grafo.GrafoNDPEtiquetado;
import grafo.Vertice;

public class ConjuntoDeVerticesTest {

	@Test
	public void grafoDisconexoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(4);
		g.agregarArista(new Vertice(0, "0"), new Vertice(1, "1"), 1.0);
		g.agregarArista(new Vertice(0, "0"), new Vertice(2, "2"), 1.0);

		Set<Integer> esperado = new HashSet<Integer>();
		esperado.add(0);
		esperado.add(1);
		esperado.add(2);
		esperado.add(3);

		assertEquals(g.conjuntoDeVertices(), esperado);
	}

}
