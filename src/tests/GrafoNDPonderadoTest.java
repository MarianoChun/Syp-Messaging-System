package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import grafos.Arista;
import grafos.Assert;
import grafos.GrafoNDPonderado;
import grafos.Vertice;

public class GrafoNDPonderadoTest {

	@Test
	public void obtenerPesoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);

		g.agregarArista(new Vertice(2), new Vertice(3), 0.1);

		Assert.equals(0.1, g.obtenerPesoArista(new Vertice(2), new Vertice(3)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaIguales() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);

		g.agregarArista(new Vertice(2), new Vertice(2), 0.5);
	}

	@Test
	public void eliminarAristaDeArrayDeAristasTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);

		ArrayList<Arista> vacio = new ArrayList<Arista>();

		g.agregarArista(new Vertice(2), new Vertice(3), 0.1);
		g.eliminarArista(new Vertice(2), new Vertice(3));

		assertEquals(vacio, g.getAristas());
	}

	@Test
	public void eliminarAristaDeArrayDeAristasInversoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);

		ArrayList<Arista> vacio = new ArrayList<Arista>();

		g.agregarArista(new Vertice(3), new Vertice(1), 0.20);
		g.eliminarArista(new Vertice(1), new Vertice(3));
		
		assertEquals(vacio, g.getAristas());
	}

	@Test
	public void obtenerPesoInversoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);

		g.agregarArista(new Vertice(1), new Vertice(2), 0.8);

		Assert.equals(0.8, g.obtenerPesoArista(new Vertice(2), new Vertice(1)));
	}

	@Test
	public void cambiarPesoAristaExistente() {
		GrafoNDPonderado g = new GrafoNDPonderado(6);

		g.agregarArista(new Vertice(4), new Vertice(5), 2.0);
		g.agregarArista(new Vertice(5), new Vertice(4), 1.0);

		Assert.equals(2.0, g.obtenerPesoArista(new Vertice(5), new Vertice(4)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void obtenerPesoAristaInexistenteTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(6);

		g.obtenerPesoArista(new Vertice(2), new Vertice(0));

	}

	@Test
	public void agregarPesoSinEspecificarTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);

		g.agregarArista(2, 3);

		Assert.equals(0.0, g.obtenerPesoArista(new Vertice(2), new Vertice(3)));

	}

	@Test(expected = IllegalArgumentException.class)
	public void obtenerPesoAristaEliminada() {
		GrafoNDPonderado g = new GrafoNDPonderado(9);

		g.agregarArista(new Vertice(0), new Vertice(1), 0.9);
		g.eliminarArista(new Vertice(1), new Vertice(0));

		g.obtenerPesoArista(new Vertice(0), new Vertice(1));

	}

}
