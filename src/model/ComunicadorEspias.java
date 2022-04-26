package model;

import grafos.GrafoNDPonderado;

public class ComunicadorEspias {
	private RedEspias espias;
	private GrafoNDPonderado arbolComunicador;

	public ComunicadorEspias() {
		this.espias = new RedEspias("C:\\Users\\Mariano\\Desktop\\datosEspias.xlsx");
		this.arbolComunicador = new GrafoNDPonderado(espias.cantidadEspias());
	}
	// Debemos pensar como etiquetar los vertices del arbolComunicador con los
	// nombres de los espias
	// Opciones
	// - Agregamos un atributo a la clase Arista, que contenga el nombre del espia.

}
