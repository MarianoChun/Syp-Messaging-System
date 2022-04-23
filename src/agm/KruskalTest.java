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
			System.out.println(elem.getPrimerExtremo() + "" + elem.getSegundoExtremo()); 
		}
		
//		System.out.println(obtenido[0]);
//		System.out.println(obtenido[1]);
//		assertEquals(esperado[0], obtenido[0]);
//		assertEquals(esperado[1], obtenido[1]);
	}
}
