package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import grafos.GrafoNDPEtiquetado;
import grafos.Vertice;

public class GrafoNDPEtiquetadoTest {

	@Test(expected = IllegalArgumentException.class)
	public void agregarArista1VerticeNoEtiquetadoTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
		
		g.agregarArista(new Vertice(0), new Vertice(1, "Pepe"));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarArista2VerticesNoEtiquetadosTest() {
		GrafoNDPEtiquetado g = new GrafoNDPEtiquetado(3);
		
		g.agregarArista(new Vertice(0), new Vertice(2));
	}

}
