package grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GrafoNDPonderado extends GrafoND{
	private double[][] pesosA;
	private ArrayList<Arista> aristas;

	public GrafoNDPonderado(int vertices) {
		super(vertices);
		this.pesosA = new double[vertices][vertices];
		this.aristas = new ArrayList<Arista>();
	}

	// Agregado de aristas
	public void agregarArista(int i, int j, double peso) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		if (!existeArista(i, j)) {
			A[i][j] = A[j][i] = true;
			pesosA[i][j] = pesosA[j][i] = peso;
			aristas.add(new Arista(i, j, peso));
		}

	}

	// Eliminacion de aristas
	public void eliminarArista(int i, int j, double peso) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		if (existeArista(i, j)) {
			A[i][j] = A[j][i] = false;
			aristas.remove(new Arista(i, j, peso));
		}
	}

	public double obtenerPesoArista(int i, int j) {
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

	@Override
	public String toString() {
		StringBuffer cadena = new StringBuffer();
		cadena.append("----- Grafo No dirigido Ponderado ----- \n");
		for (int col = 0; col < tamaño(); col++) {
			for (int fila = 0; fila < tamaño(); fila++) {
				if (col != fila && existeArista(col, fila)) {
					double peso = obtenerPesoArista(col, fila);
					cadena.append("(").append(col).append(", ").append(fila).append(", ").append(peso).append(")");
					cadena.append("\n");
				}
			}
		}
		cadena.append("-----------------");
		return cadena.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.deepHashCode(pesosA);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrafoNDPonderado other = (GrafoNDPonderado) obj;
		if (!Arrays.deepEquals(pesosA, other.pesosA))
			return false;
		return true;
	}
	

}
