package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComunicadorEspiasTest {

	@Test
	public void redEspiasComunicaciónTest() {
		ComunicadorEspias comunicadorEspias = new ComunicadorEspias("/lista_de_espias/lista-de-espias.xlsx");
		assertTrue(comunicadorEspias.existeComunicacion("Juan", "Ivan"));
		assertTrue(comunicadorEspias.existeComunicacion("Ruben", "William"));
		assertTrue(comunicadorEspias.existeComunicacion("Rodolfo", "Juan"));
		assertTrue(comunicadorEspias.existeComunicacion("Pepe", "William"));
		assertTrue(comunicadorEspias.existeComunicacion("Jose", "Mabel"));
		assertTrue(comunicadorEspias.existeComunicacion("Alvaro", "Pepe"));
		assertTrue(comunicadorEspias.existeComunicacion("Julieta", "Ivan"));
		assertTrue(comunicadorEspias.existeComunicacion("Mabel", "Gabriela"));
		assertTrue(comunicadorEspias.existeComunicacion("Gabriela", "Alvaro"));
		assertTrue(comunicadorEspias.existeComunicacion("Hugo", "Ruben"));
		assertTrue(comunicadorEspias.existeComunicacion("Candela", "Mabel"));
		assertTrue(comunicadorEspias.existeComunicacion("Ruben", "Juan"));
	}

	@Test
	public void EspiasSinComunicarTest() {
		ComunicadorEspias comunicadorEspias = new ComunicadorEspias("/lista_de_espias/lista-de-espias.xlsx");
		assertFalse(comunicadorEspias.existeComunicacion("Juan", "William"));
		assertFalse(comunicadorEspias.existeComunicacion("Juan", "Pepe"));
		assertFalse(comunicadorEspias.existeComunicacion("Juan", "Jose"));
		assertFalse(comunicadorEspias.existeComunicacion("Juan", "Mabel"));
		assertFalse(comunicadorEspias.existeComunicacion("Juan", "Alvaro"));
		assertFalse(comunicadorEspias.existeComunicacion("Juan", "Julieta"));
		assertFalse(comunicadorEspias.existeComunicacion("Juan", "Gabriela"));
		assertFalse(comunicadorEspias.existeComunicacion("Juan", "Hugo"));
		assertFalse(comunicadorEspias.existeComunicacion("Juan", "Candela"));
	}

	@Test
	public void redSeguraTest() {
		ComunicadorEspias comunicadorEspias = new ComunicadorEspias("/lista_de_espias/lista-de-espias.xlsx");
		assertTrue(comunicadorEspias.redYaEsSegura());
	}

	@Test
	public void redNoSeguraTest() {
		ComunicadorEspias comunicador2 = new ComunicadorEspias("/lista_de_espias/lista-de-espias.xlsx");
		System.out.println(comunicador2.obtenerRedSegura());
	}

}
