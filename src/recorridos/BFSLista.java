package recorridos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import grafos.GrafoND;

public class BFSLista {
	
	private Queue<Integer> verticesNoMarcados;
	private List<Integer> verticesMarcados;
	private GrafoND grafo;
	
	public BFSLista(GrafoND grafo) {
		verticesNoMarcados = new LinkedList<Integer>();
		verticesMarcados = new ArrayList<Integer>();
		this.grafo = grafo;
	}
	
	public boolean esConexo() {
		return grafo.conjuntoDeVertices().equals(verticesAlcanzablesDesdeVertice(0)) && grafo.tamano() == verticesAlcanzablesDesdeVertice(0).size();
	}
	
	public List<Integer> verticesAlcanzablesDesdeVertice(int vertice) {
		verticesNoMarcados.add(vertice);
		while(!verticesNoMarcados.isEmpty()) {
			int verticeNoMarcado = verticesNoMarcados.remove();
			marcarVertice(verticeNoMarcado);
			agregarVecinosNoMarcados(verticeNoMarcado);
		}
		return verticesMarcados;
	}

	private void agregarVecinosNoMarcados(int verticeNoMarcado) {
		Set<Integer> vecinosDelVertice = grafo.vecinos(verticeNoMarcado);
		for(int vertice : vecinosDelVertice) {
			if(!estaVerticeMarcado(vertice)) {
				verticesNoMarcados.add(vertice);
			}
		}
	}

	private boolean estaVerticeMarcado(Integer vertice) {
		return verticesMarcados.contains(vertice);
	}

	private void marcarVertice(int verticeNoMarcado) {
		verticesMarcados.add(verticeNoMarcado);
	}
}
