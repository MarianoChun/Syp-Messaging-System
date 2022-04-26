package model;

import grafos.GrafoNDPonderado;

public class ComunicadorEspias {
	private RedEspias espias;
	private GrafoNDPonderado arbolComunicador;

	public ComunicadorEspias() {
		this.espias = new RedEspias("/lista_de_espias/lista-de-espias.xlsx");
		this.arbolComunicador = new GrafoNDPonderado(espias.cantidadEspias());
	}
	// Debemos pensar como etiquetar los vertices del arbolComunicador con los
	// nombres de los espias
	// Opciones
	// - Agregamos un atributo a la clase Arista, que contenga el nombre del espia.
	// - Agregamos un atriubto a la clase Grafo, que contenga a los espias. Cada vez que se agrega un vertice,
	// se agrega el nombre del espia. Así como tenemos un array de aristas se podria tener un array de vertices(espias)
}
