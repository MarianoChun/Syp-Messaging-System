package agm;

import grafos.GrafoND;
import grafos.GrafoNDPonderado;

public class Kruskal {
	
GrafoNDPonderado grafoAGM;
GrafoNDPonderado gNDP;

public Kruskal(GrafoNDPonderado gNDP) {
	grafoAGM = new GrafoNDPonderado(gNDP.tamano());
	this.gNDP = gNDP;
}

public GrafoNDPonderado obtenerArbolGM() {
	int i = 1;
	
	while(i<= gNDP.tamano() - 1) {
		int[] aristaMinimaNoCircuito = obtenerAristaMinimaNoCircuito();
		int j = aristaMinimaNoCircuito[0];
		int k = aristaMinimaNoCircuito[1];
		
		grafoAGM.agregarArista(j, k);
		i++;
	}
	return grafoAGM;
}

/*Elegir una arista del grafo dado, tal que su su peso sea mínimo
 * entre las aristas que no forman un circuito con las aristas
 * ya elegidas.
 */
private int[] obtenerAristaMinimaNoCircuito() {
	int[] arista = new int[2];
	
	arista = gNDP.obtenerAristaMinima();
	while(grafoAGM.formaCircuito(arista)){
		arista = gNDP.obtenerAristaMinima();
	}
	
	return arista;
}

}
