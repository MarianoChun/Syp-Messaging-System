package agm;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import grafos.Arista;
import grafos.GrafoNDPonderado;

public class KruskalTest {

	@Test
	public void aristaMinimaNoPresenteEnAGMTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(4);
		g.agregarArista(0, 1, 1.0);
		g.agregarArista(0, 2, 5.0);
		g.agregarArista(0, 3, 2.0);
		g.agregarArista(1, 3, 9.0);
		g.agregarArista(2, 3, 3.0);
		
		int[] esperado = new int[] {0, 1};
		ArrayList<Arista> lista = new Kruskal(g).getAristasOrdenadas();
		for(Arista elem : lista) {
			int i = elem.getPrimerExtremo();
			int j = elem.getSegundoExtremo();
			double peso = g.obtenerPesoArista(i, j);
			System.out.println(i + ", " + j + ", " + peso); 
		}
		
//		System.out.println(obtenido[0]);
//		System.out.println(obtenido[1]);
//		assertEquals(esperado[0], obtenido[0]);
//		assertEquals(esperado[1], obtenido[1]);
	}
	
	@Test
	public void kruskalTest() {
		GrafoNDPonderado g = new GrafoNDPonderado(5);
		g.agregarArista(0, 1, 4.0);
		g.agregarArista(0, 3, 1.0);
		g.agregarArista(1, 3, 2.0);
		g.agregarArista(1, 2, 0.5);
		g.agregarArista(3, 4, 1.0);
		
		GrafoNDPonderado esperado = new GrafoNDPonderado(5);
		esperado.agregarArista(0, 3, 1.0);
		esperado.agregarArista(3, 4, 1.0);
		esperado.agregarArista(3, 1, 2.0);
		esperado.agregarArista(1, 2, 0.5);
		
		GrafoNDPonderado actual = new Kruskal(g).obtenerArbolGM();
		System.out.println(actual.toString());
		assertEquals(esperado, actual);
		
		

	}
}
