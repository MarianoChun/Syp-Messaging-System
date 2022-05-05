package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CargadorEspiasTest {

	@Test
	public void cargarEspiasTest() {
		CargadorEspias red = new CargadorEspias("/lista_de_espias/lista-de-espias.xlsx");
		assertEquals(red.getIndiceEspia("juan"), 0);
		assertEquals(red.getIndiceEspia("ivan"), 1);
		assertEquals(red.getIndiceEspia("ruben"), 2);
		assertEquals(red.getIndiceEspia("william"), 3);
		assertEquals(red.getIndiceEspia("rodolfo"), 4);
		assertEquals(red.getIndiceEspia("pepe"), 5);
		assertEquals(red.getIndiceEspia("jose"), 6);
		assertEquals(red.getIndiceEspia("mabel"), 7);
		assertEquals(red.getIndiceEspia("alvaro"), 8);
		assertEquals(red.getIndiceEspia("julieta"), 9);
		assertEquals(red.getIndiceEspia("gabriela"), 10);
		assertEquals(red.getIndiceEspia("hugo"), 11);
		assertEquals(red.getIndiceEspia("candela"), 12);
	}

	@Test
	public void cantidadEspiasTest() {
		CargadorEspias red = new CargadorEspias("/lista_de_espias/lista-de-espias.xlsx");
		assertEquals(red.cantidadEspias(), 13);
	}

	@Test
	public void test() {
		CargadorEspias red = new CargadorEspias("/lista_de_espias/lista-de-espias-no-segura.xlsx");
		System.out.println(red.toString());
	}

}
