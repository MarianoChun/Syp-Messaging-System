package grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class EdicionDeAristasTest
{
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(-1, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(5, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(2, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(2, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(2, 2);
	}

	@Test
	public void aristaExistenteTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(2, 3);
		assertTrue( grafo.existeArista(2, 3) );
	}

	@Test
	public void aristaOpuestaTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(2, 3);
		assertTrue( grafo.existeArista(3, 2) );
	}

	@Test
	public void aristaInexistenteTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(2, 3);
		assertFalse( grafo.existeArista(1, 4) );
	}

	@Test
	public void agregarAristaDosVecesTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(2, 3);

		assertTrue( grafo.existeArista(2, 3) );
	}
	
	@Test
	public void eliminarAristaExistenteTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(2, 4);
		
		grafo.eliminarArista(2, 4);
		assertFalse( grafo.existeArista(2, 4) );
	}

	@Test
	public void eliminarAristaInexistenteTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.eliminarArista(2, 4);
		assertFalse( grafo.existeArista(2, 4) );
	}
	
	@Test
	public void eliminarAristaDosVecesTest()
	{
		GrafoND grafo = new GrafoND(5);
		grafo.agregarArista(2, 4);
		
		grafo.eliminarArista(2, 4);
		grafo.eliminarArista(2, 4);
		assertFalse( grafo.existeArista(2, 4) );
	}
}
