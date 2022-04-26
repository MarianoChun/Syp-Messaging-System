package agm;

import java.util.ArrayList;
import java.util.Collections;

import grafos.Arista;
import grafos.GrafoNDPonderado;
import recorridos.BFS;
import unionFind.UnionFind;

public class Kruskal {

	GrafoNDPonderado grafoOutputAGM;
	GrafoNDPonderado grafoInput;
	UnionFind unionFind;
	ArrayList<Arista> aristas;
	
	public Kruskal(GrafoNDPonderado grafoInput) {
		verificarGrafoConexo(grafoInput);
		this.grafoOutputAGM = new GrafoNDPonderado(grafoInput.tamaño());
		this.grafoInput = grafoInput;
		this.unionFind = new UnionFind(grafoInput);
		this.aristas = new ArrayList<Arista>();
		generarListaAristas();
		Collections.sort(aristas);
	}

	

	public GrafoNDPonderado obtenerArbolGM() {
		int i = 1;

		while (i <= grafoInput.tamaño() - 1) {
			Arista aristaMinimaNoCircuito = obtenerAristaMinimaNoCircuito();
			int primerVertice = aristaMinimaNoCircuito.getPrimerExtremo();
			int segundoVertice = aristaMinimaNoCircuito.getSegundoExtremo();
			double peso = grafoInput.obtenerPesoArista(primerVertice, segundoVertice);
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
		for(int col = 0; col < grafoInput.tamaño(); col++) {
			for(int fila = 0; fila < grafoInput.tamaño(); fila ++) {
				if(col!=fila && grafoInput.existeArista(col, fila)) {
					Arista aristaAAgregar = new Arista (col, fila, grafoInput.obtenerPesoArista(col, fila));
					Arista aristaInversa = new Arista (fila, col, grafoInput.obtenerPesoArista(col, fila));
					if(!aristas.contains(aristaAAgregar) && !aristas.contains(aristaInversa)) {
						aristas.add(aristaAAgregar);	
					}
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
	
	private void verificarGrafoConexo(GrafoNDPonderado grafoInput) {
//		GrafoND grafoND = transformarNDPaND(grafoInput);
//		Parece que no es necesario transformarNDPaND, BFS toma un GrafoNDPonderado
		BFS bfs = new BFS(grafoInput);
		if(!bfs.esConexo())
			throw new IllegalArgumentException("El grafo ingresado no es conexo");
	}

}
