package agm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import grafos.Arista;
import grafos.GrafoND;
import grafos.GrafoNDPonderado;
import recorridos.BFS;

public class Kruskal {

	GrafoNDPonderado grafoAGM;
	GrafoNDPonderado gNDP;	
	ArrayList<Arista> aristas;
	
	public Kruskal(GrafoNDPonderado gNDP) {
		grafoAGM = new GrafoNDPonderado(gNDP.tamano());
		this.gNDP = gNDP;
		this.aristas = new ArrayList<Arista>();
		generarListaAristas();
		Collections.sort(aristas);
	}

	public GrafoNDPonderado obtenerArbolGM() {
		int i = 1;

		while (i <= gNDP.tamano() - 1) {
			int[] aristaMinimaNoCircuito = obtenerAristaMinimaNoCircuito();
			int j = aristaMinimaNoCircuito[0];
			int k = aristaMinimaNoCircuito[1];

			grafoAGM.agregarArista(j, k);
			i++;
		}
		return grafoAGM;
	}

	/*
	 * Elegir una arista del grafo dado, tal que su su peso sea mínimo entre las
	 * aristas que no forman un circuito con las aristas ya elegidas.
	 */
	private int[] obtenerAristaMinimaNoCircuito() {
		int[] arista = new int[2];
 //obtenerListaAristasMayorAMenor();
		while (formaCircuito(arista)) {
			//arista = obtenerListaAristasMayorAMenor();
		}

		return arista;
	}

	public void generarListaAristas() {
		for(int col = 0; col < gNDP.tamano(); col++) {
			for(int fila = 0; fila < gNDP.tamano(); fila ++) {
				if(col!=fila && gNDP.existeArista(col, fila)) {
					aristas.add(new Arista (col, fila, gNDP.obtenerPesoArista(col, fila)));	
				}
			}
		}
	}
	
	public ArrayList<Arista> getAristasOrdenadas(){
		return aristas;
	}
	public boolean formaCircuito(int[] arista) {
		BFS bfs = new BFS(grafoAGM);
		Set<Integer> alcanzables = bfs.verticesAlcanzablesDesdeVertice(arista[0]);
			
		return alcanzables.contains(arista[1]);
	}
}
