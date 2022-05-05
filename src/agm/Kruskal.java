package agm;

import java.util.ArrayList;
import java.util.Collections;

import grafos.Arista;
import grafos.GrafoNDPEtiquetado;
import grafos.GrafoNDPonderado;
import grafos.Vertice;
import recorridos.BFS;
import unionFind.UnionFind;

public class Kruskal {

	GrafoNDPEtiquetado grafoOutputAGM;
	GrafoNDPEtiquetado grafoInput;
	UnionFind unionFind;
	ArrayList<Arista> aristas;

	public Kruskal(GrafoNDPEtiquetado grafoInput) {
		verificarGrafoConexo(grafoInput);
		this.grafoOutputAGM = new GrafoNDPEtiquetado(grafoInput.tamaño());
		this.grafoInput = grafoInput;
		this.unionFind = new UnionFind(grafoInput);
		this.aristas = grafoInput.getAristas();
		Collections.sort(aristas);
	}

	public GrafoNDPEtiquetado obtenerArbolGM() {
		int i = 1;

		while (i <= grafoInput.tamaño() - 1) {
			Arista aristaMinimaNoCircuito = obtenerAristaMinimaNoCircuito();
			Vertice primerVertice = aristaMinimaNoCircuito.getPrimerExtremo();
			Vertice segundoVertice = aristaMinimaNoCircuito.getSegundoExtremo();
			double peso = grafoInput.obtenerPesoArista(primerVertice, segundoVertice);
			unionFind.union(primerVertice.getIndice(), segundoVertice.getIndice());

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
		while (formaCircuito(aristaMenor)) {
			indice++;
			aristaMenor = aristas.get(indice);
		}

		return aristaMenor;
	}

	public boolean formaCircuito(Arista arista) {
		return unionFind.find(arista.getPrimerExtremo().getIndice(), arista.getSegundoExtremo().getIndice());
	}

	public ArrayList<Arista> getAristasOrdenadas() {
		return this.aristas;
	}

	private void verificarGrafoConexo(GrafoNDPEtiquetado grafoInput) {
		BFS bfs = new BFS(grafoInput);
		if (!bfs.esConexo())
			throw new IllegalArgumentException("El grafo ingresado no es conexo");
	}

}
