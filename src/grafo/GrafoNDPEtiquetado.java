package grafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GrafoNDPEtiquetado {
	private boolean[][] A;
	private double[][] pesosA;
	private ArrayList<Arista> aristas;
	private Map<Integer, String> etiquetas;

	public GrafoNDPEtiquetado(int vertices) {
		A = new boolean[vertices][vertices];
		this.pesosA = new double[vertices][vertices];
		this.aristas = new ArrayList<Arista>();
		this.etiquetas = new HashMap<Integer, String>();
	}

	public void agregarArista(Vertice primerVertice, Vertice segundoVertice, double peso) {
		verificarVerticeEtiquetado(primerVertice);
		verificarVerticeEtiquetado(segundoVertice);
		verificarEtiquetaVertice(primerVertice);
		verificarEtiquetaVertice(segundoVertice);
		int i = primerVertice.getIndice();
		int j = segundoVertice.getIndice();
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		if (!existeArista(i, j)) {
			A[i][j] = A[j][i] = true;
			pesosA[i][j] = pesosA[j][i] = peso;
			aristas.add(new Arista(primerVertice, segundoVertice, peso));
		}
		agregarEtiquetaAGrafo(primerVertice);
		agregarEtiquetaAGrafo(segundoVertice);
	}

	private void agregarEtiquetaAGrafo(Vertice vertice) {
		etiquetas.put(vertice.getIndice(), vertice.getEtiqueta());
	}

	public void eliminarArista(Vertice primerVertice, Vertice segundoVertice) {
		int i = primerVertice.getIndice();
		int j = segundoVertice.getIndice();
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		if (existeArista(i, j)) {
			double pesoArista = obtenerPesoArista(primerVertice, segundoVertice);
			eliminarArista(primerVertice, segundoVertice, pesoArista);
		}
	}

	private void eliminarArista(Vertice primerVertice, Vertice segundoVertice, double peso) {
		int i = primerVertice.getIndice();
		int j = segundoVertice.getIndice();

		A[i][j] = A[j][i] = false;
		removerAristaListaAristas(primerVertice, segundoVertice, peso);
		removerAristaListaAristas(segundoVertice, primerVertice, peso);
	}

	private boolean removerAristaListaAristas(Vertice primerVertice, Vertice segundoVertice, double peso) {
		return aristas.remove(new Arista(primerVertice, segundoVertice, peso));
	}

	public boolean existeArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return A[i][j];
	}

	public double obtenerPesoArista(Vertice primerVertice, Vertice segundoVertice) {
		int i = primerVertice.getIndice();
		int j = segundoVertice.getIndice();
		verificarVertice(i);
		verificarVertice(j);
		verificarExisteArista(i, j);

		return pesosA[i][j];
	}

	public int cantidadAristas() {
		return this.aristas.size();
	}

	public ArrayList<Arista> getAristas() {
		return this.aristas;
	}

	public String obtenerEtiquetaVertice(int i) {
		verificarVerticeEtiquetado(i);
		verificarVertice(i);
		return etiquetas.get(i);
	}

	public Set<Integer> conjuntoDeVertices() {
		Set<Integer> vertices = new HashSet<Integer>();

		for (int i = 0; i < this.tamaño(); i++)
			vertices.add(i);
		return vertices;
	}

	public int tamaño() {
		return A.length;
	}

	public Set<Integer> vecinos(int i) {
		verificarVertice(i);

		Set<Integer> ret = new HashSet<Integer>();
		for (int j = 0; j < this.tamaño(); ++j)
			if (i != j) {
				if (this.existeArista(i, j))
					ret.add(j);
			}

		return ret;
	}

	public boolean estaVerticeYaEtiquetado(Vertice vertice) {
		int indiceVertice = vertice.getIndice();
		String etiquetaEnGrafo = etiquetas.get(indiceVertice);

		return etiquetaEnGrafo != null;
	}

	private void verificarEtiquetaVertice(Vertice vertice) {
		if (estaVerticeYaEtiquetado(vertice)) {
			String etiquetaVertice = vertice.getEtiqueta();
			int indiceVertice = vertice.getIndice();
			String etiquetaEnGrafo = etiquetas.get(indiceVertice);
			if(!sonEtiquetasIguales(etiquetaVertice, etiquetaEnGrafo)) {
				throw new IllegalArgumentException("Ingrese correctamente la etiqueta. Para el vertice " + indiceVertice
						+ " es " + etiquetaEnGrafo + " no es, " + etiquetaVertice);
			}
		}
	}

	private boolean sonEtiquetasIguales(String etiqueta, String etiqueta2) {
		return etiqueta.equals(etiqueta2);
	}

	private void verificarVerticeEtiquetado(Vertice vertice) {
		if (!vertice.esEtiquetado()) {
			throw new IllegalArgumentException("El vertice ingresado debe estar etiquetado");
		}
	}

	private void verificarVerticeEtiquetado(int indice) {
		String etiquetaVertice = etiquetas.get(indice);
		if (etiquetaVertice == null) {
			throw new IllegalArgumentException("El vertice ingresado debe esta etiquetado");
		}
	}

	private void verificarVertice(int i) {
		if (i < 0)
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);

		if (i >= A.length)
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	private void verificarDistintos(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}

	private void verificarExisteArista(int i, int j) {
		if (!existeArista(i, j)) {
			throw new IllegalArgumentException("La arista " + i + "" + j + " no existe");
		}
	}

	@Override
	public String toString() {
		StringBuffer cadena = new StringBuffer();
		cadena.append("----- Grafo No dirigido Ponderado Etiquetado ----- \n");
		int fila;
		for (int col = 0; col < tamaño(); col++) {
			fila = 0;
			while (col != fila) {
				if (existeArista(col, fila)) {
					double peso = obtenerPesoArista(new Vertice(col), new Vertice(fila));
					String etiquetaCol = obtenerEtiquetaVertice(col);
					String etiquetaFila = obtenerEtiquetaVertice(fila);
					cadena.append("[ (").append(col).append(", ").append(etiquetaCol).append("), (");
					cadena.append(fila).append(", ").append(etiquetaFila).append("), ");
					cadena.append(peso).append(" ]");
					cadena.append("\n");
				}
				fila++;
			}

		}
		cadena.append("----------------- \n");
		return cadena.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(A);
		result = prime * result + ((aristas == null) ? 0 : aristas.hashCode());
		result = prime * result + ((etiquetas == null) ? 0 : etiquetas.hashCode());
		result = prime * result + Arrays.deepHashCode(pesosA);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		GrafoNDPEtiquetado other = (GrafoNDPEtiquetado) obj;

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		if (!Arrays.deepEquals(A, other.A)) {
			return false;
		}

		if (getClass() != obj.getClass())
			return false;
		if (!Arrays.deepEquals(pesosA, other.pesosA))
			return false;

		if (etiquetas == null) {
			if (other.etiquetas != null) {
				return false;
			}
		} else if (!etiquetas.equals(other.etiquetas)) {
			return false;
		}

		if (aristas == null) {
			if (other.aristas != null)
				return false;
		} else {
			return sonAristasIguales(other);
		}

		return true;
	}

	private boolean sonAristasIguales(GrafoNDPEtiquetado other) {
		boolean aux1 = true;
		boolean aux2 = true;

		Iterator<Arista> it1 = aristas.iterator();
		Iterator<Arista> it2 = other.aristas.iterator();

		while (it1.hasNext()) {
			Arista arista1 = it1.next();
			Arista arista2 = it2.next();
			aux1 = aux1 && aristas.contains(arista2);
			aux2 = aux2 && other.aristas.contains(arista1);
		}
		return aux1 && aux2;
	}

}
