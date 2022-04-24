package agm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import grafos.Arista;
import grafos.GrafoND;
import grafos.GrafoNDPonderado;
import recorridos.BFS;
import unionFind.UnionFind;

public class Kruskal {

	GrafoNDPonderado grafoOutputAGM;
	GrafoNDPonderado GrafoInput;
	UnionFind unionFind;
	ArrayList<Arista> aristas;
	
	public Kruskal(GrafoNDPonderado GrafoInput) {
		this.grafoOutputAGM = new GrafoNDPonderado(GrafoInput.tamano());
		this.GrafoInput = GrafoInput;
		this.unionFind = new UnionFind(GrafoInput);
		this.aristas = new ArrayList<Arista>();
		generarListaAristas();
		Collections.sort(aristas);
	}

	public GrafoNDPonderado obtenerArbolGM() {
		int i = 1;

		while (i <= GrafoInput.tamano() - 1) {
			Arista aristaMinimaNoCircuito = obtenerAristaMinimaNoCircuito();
			int primerVertice = aristaMinimaNoCircuito.getPrimerExtremo();
			int segundoVertice = aristaMinimaNoCircuito.getSegundoExtremo();
			double peso = GrafoInput.obtenerPesoArista(primerVertice, segundoVertice);
			unionFind.union(primerVertice, segundoVertice);

			grafoOutputAGM.agregarArista(primerVertice, segundoVertice, peso);
			i++;
		}
		return grafoOutputAGM;
	}

	/*
	 * Elegir una arista del grafo dado, tal que su su peso sea mínimo entre las
	 * aristas que no forman un circuito con las aristas ya elegidas.
	 */
	private Arista obtenerAristaMinimaNoCircuito() {
		int indice = 0;
		Arista aristaMenor = aristas.get(indice);
		while(formaCircuito(aristaMenor)) {
			indice++; 
			aristaMenor = aristas.get(indice);
		}
		
		return aristaMenor;
	}

	public void generarListaAristas() {
		for(int col = 0; col < GrafoInput.tamano(); col++) {
			for(int fila = 0; fila < GrafoInput.tamano(); fila ++) {
				if(col!=fila && GrafoInput.existeArista(col, fila)) {
					aristas.add(new Arista (col, fila, GrafoInput.obtenerPesoArista(col, fila)));	
				}
			}
		}
	}
	
	public ArrayList<Arista> getAristasOrdenadas(){
		return aristas;
	}
	public boolean formaCircuito(Arista arista) {
		return unionFind.find(arista.getPrimerExtremo(), arista.getSegundoExtremo());
	}
}
