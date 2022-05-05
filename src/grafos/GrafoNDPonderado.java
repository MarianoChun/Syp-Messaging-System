package grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class GrafoNDPonderado extends GrafoND{
	protected double[][] pesosA;
	protected ArrayList<Arista> aristas;

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
			aristas.add(new Arista(new Vertice(i), new Vertice(j), peso));
		}

	}

	// Eliminacion de aristas
	@Override
	public void eliminarArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		if (existeArista(i, j)) {
			double pesoArista = obtenerPesoArista(i, j);
			eliminarArista(i, j, pesoArista);		
		}
	}

	private void eliminarArista(int i, int j, double peso) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		if (existeArista(i, j)) {
			A[i][j] = A[j][i] = false;
			aristas.remove(new Arista(new Vertice(i), new Vertice(j), peso));
			aristas.remove(new Arista(new Vertice(j), new Vertice(i), peso));
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
	
	// Debe agresarse esta verificacion cada vez que agregamos una Arista en el caso que cambiemos
	// la implementacion de int i, int j a Vertice i, Vertice j
//	private void verificarVerticesNoEtiquetados(Vertice primerVertice, Vertice segundoVertice) {
//		if(primerVertice.esEtiquetado() || segundoVertice.esEtiquetado()) {
//			throw new IllegalArgumentException("Los vertices ingresados deben no estar etiquetados");
//		}
//	}
	
	@Override
	public String toString() {
		StringBuffer cadena = new StringBuffer();
		cadena.append("----- Grafo No dirigido Ponderado ----- \n");
		int fila;
		for (int col = 0; col < tamaño(); col++) {
			fila = 0;
			while(col != fila) {
				if (existeArista(col, fila)) {
					double peso = obtenerPesoArista(col, fila);
					cadena.append("(").append(col).append(", ").append(fila).append(", ").append(peso).append(")");
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
		int result = super.hashCode();
		result = prime * result + Arrays.deepHashCode(pesosA);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj)) {
			System.out.println("Falso el super");
			return false;
		}
		if (getClass() != obj.getClass())
			return false;
		GrafoNDPonderado other = (GrafoNDPonderado) obj;
		if (!Arrays.deepEquals(pesosA, other.pesosA))
			return false;
		return true;
	}
	

}
