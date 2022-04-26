package model;

import java.util.HashMap;
import java.util.Map;

import grafos.GrafoNDPonderado;

public class ComunicadorEspias {
	private RedEspias espias;
	private GrafoNDPonderado arbolComunicador;
	
	public ComunicadorEspias() {
		this.espias = new RedEspias("/lista_de_espias/lista-de-espias.xlsx");
		this.arbolComunicador = new GrafoNDPonderado(espias.cantidadEspias());
	}
	
	public void agregarComunicacion(String nombreEspia1, String nombreEspia2, double probIntercepcion) {
		verificarExisteEspias(nombreEspia1, nombreEspia2);	
		verificarProbIntercepcion(probIntercepcion);
		
		int indiceEspia1 = espias.getIndiceEspia(nombreEspia1);
		int indiceEspia2 = espias.getIndiceEspia(nombreEspia2);
		
		arbolComunicador.agregarArista(indiceEspia1, indiceEspia2, probIntercepcion);
	}

	private void verificarProbIntercepcion(double probIntercepcion) {
		if(probIntercepcion < 0.0 || probIntercepcion > 1.0) {
			throw new IllegalArgumentException("La probabilidad de intercepcion debe estar entre 0.0 y 1.0");
		}
	}

	private void verificarExisteEspias(String nombreEspia1, String nombreEspia2) {
		nombreEspia1 = nombreEspia1.toLowerCase();
		nombreEspia2 = nombreEspia2.toLowerCase();
		
		if(!espias.existeEspia(nombreEspia1) || !espias.existeEspia(nombreEspia2)) {
			throw new IllegalArgumentException("Algunos de los espias no existen");
		}
	}
	
	public boolean existeComunicacion(String nombreEspia1, String nombreEspia2) {
		verificarExisteEspias(nombreEspia1, nombreEspia2);
		
		int indiceEspia1 = espias.getIndiceEspia(nombreEspia1);
		int indiceEspia2 = espias.getIndiceEspia(nombreEspia2);
		
		return arbolComunicador.existeArista(indiceEspia1, indiceEspia2);
	}
}
