package unionFind;

import java.util.ArrayList;

import grafos.GrafoNDPEtiquetado;

public class UnionFind {
	private ArrayList<Integer> vertices;

	public UnionFind(GrafoNDPEtiquetado g) {
		this.vertices = new ArrayList<Integer>();
		this.vertices.addAll(g.conjuntoDeVertices());
	}

	// Me devuelve la raiz de la componente conexa del vertice i
	public int root(int i) {
		while (vertices.get(i) != i) {
			i = vertices.get(i);
		}

		return i;
	}

	// Determina si dos vertices estan en la misma componente conexa
	public boolean find(int i, int j) {
		return root(i) == root(j);
	}

	// Hace que la raiz de una componente conexa apunte a la otra (union)
	public void union(int i, int j) {
		int raizI = root(i);
		int raizJ = root(j);

		vertices.set(raizI, raizJ);
	}

}
