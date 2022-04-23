package grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class GrafoNDPonderadoTest {

	@Test
	public void obtenerPesoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);
		
		g.agregarArista(2, 3, 0.1);
		
		Assert.equals(0.1, g.obtenerPesoArista(2, 3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarAristaIguales() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);
		
		g.agregarArista(2, 2, 0.5);	
	}
	
	@Test
	public void obtenerPesoInversoTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);
		
		g.agregarArista(1, 2, 0.8);
		
		Assert.equals(0.8, g.obtenerPesoArista(2, 1));
	}
	
	@Test
	public void cambiarPesoAristaExistente() {
		GrafoNDPonderado g = new GrafoNDPonderado(6);
		
		g.agregarArista(4, 5, 2.0);
		g.agregarArista(5, 4, 1.0);
		
		Assert.equals(2.0, g.obtenerPesoArista(5, 4));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void obtenerPesoAristaInexistenteTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(6);
		
		g.obtenerPesoArista(2, 0);

	}
	
	@Test
	public void agregarPesoSinEspecificarTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);
		
		g.agregarArista(2, 3);
		
		Assert.equals(0.0, g.obtenerPesoArista(2, 3));

	}

	@Test(expected = IllegalArgumentException.class)
	public void obtenerPesoAristaEliminada() {
		GrafoNDPonderado g = new GrafoNDPonderado(9);
		
		g.agregarArista(0, 1, 0.9);
		g.eliminarArista(1, 0);
		
		g.obtenerPesoArista(0, 1);

	}
	
	@Test
	public void aristaMinimaTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);
		g.agregarArista(0, 1, 1.0);
		g.agregarArista(0, 2, 5.0);
		g.agregarArista(0, 3, 2.0);
		g.agregarArista(1, 3, 9.0);
		g.agregarArista(2, 3, 3.0);
		int[] esperado = new int[] {0, 1};
		int[] obtenido = g.obtenerAristaMinima();
		System.out.println(obtenido[0]);
		System.out.println(obtenido[1]);
		assertEquals(esperado[0], obtenido[0]);
		assertEquals(esperado[1], obtenido[1]);
	}

}
