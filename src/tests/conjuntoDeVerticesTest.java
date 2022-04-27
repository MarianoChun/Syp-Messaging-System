package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import grafos.GrafoND;

public class conjuntoDeVerticesTest {

	@Test
	public void grafoDisconexoTest() {
		GrafoND g = new GrafoND(4);
		g.agregarArista(0, 1);
		g.agregarArista(0, 2);

		Set<Integer> esperado = new HashSet<Integer>();
		esperado.add(0);
		esperado.add(1);
		esperado.add(2);
		esperado.add(3);

		assertEquals(g.conjuntoDeVertices(), esperado);
	}

}
