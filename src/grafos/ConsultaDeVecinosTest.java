package grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConsultaDeVecinosTest
{
	@Test(expected = IllegalArgumentException.class)
	public void verticeNegativoTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.vecinos(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticeExcedidoTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.vecinos(5);
	}

	@Test
	public void todosAisladosTest()
	{
		GrafoND grafo = new GrafoND(5);
		assertEquals(0, grafo.vecinos(2).size());
	}
	
	@Test
	public void verticeUniversalTest()
	{
		GrafoND grafo = new GrafoND(4);
		grafo.agregarArista(1, 0);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 3);
		
		int[] esperado = {0, 2, 3};
		Assert.iguales(esperado, grafo.vecinos(1));
	}
	
	@Test
	public void verticeNormalTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(1, 3);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(2, 4);
		
		int[] esperados = {1, 2};
		Assert.iguales(esperados, grafo.vecinos(3));
	}
}
