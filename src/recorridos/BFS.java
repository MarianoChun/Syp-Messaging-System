package recorridos;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import grafos.GrafoNDPEtiquetado;

public class BFS {

	private Queue<Integer> verticesNoMarcados;
	private Set<Integer> verticesMarcados;
	private GrafoNDPEtiquetado grafo;

	public BFS(GrafoNDPEtiquetado grafo) {
		verticesNoMarcados = new LinkedList<Integer>();
		verticesMarcados = new HashSet<Integer>();
		this.grafo = grafo;
	}

	public boolean esConexo() {
		return grafo.conjuntoDeVertices().equals(verticesAlcanzablesDesdeVertice(0))
				&& grafo.tamaño() == verticesAlcanzablesDesdeVertice(0).size();
	}

	public Set<Integer> verticesAlcanzablesDesdeVertice(int vertice) {
		verticesNoMarcados.add(vertice);
		while (!verticesNoMarcados.isEmpty()) {
			int verticeNoMarcado = verticesNoMarcados.remove();
			marcarVertice(verticeNoMarcado);
			agregarVecinosNoMarcados(verticeNoMarcado);
		}
		return verticesMarcados;
	}

	private void agregarVecinosNoMarcados(int verticeNoMarcado) {
		Set<Integer> vecinosDelVertice = grafo.vecinos(verticeNoMarcado);
		for (int vertice : vecinosDelVertice) {
			if (!estaVerticeMarcado(vertice)) {
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
