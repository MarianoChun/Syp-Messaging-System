package grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GrafoNDPonderado extends GrafoND{
	private double [][] pesosA;
	protected ArrayList<Arista> aristas;
	
	
	public GrafoNDPonderado(int vertices) {
		super(vertices);
		this.pesosA = new double[vertices][vertices];
		this.aristas = new ArrayList<Arista>();
	}
	
	// Agregado de aristas
		public void agregarArista(int i, int j){
			verificarVertice(i);
			verificarVertice(j);
			verificarDistintos(i, j);
			
			if(!existeArista(i,j)) {
				agregarArista(i, j, 0.0);
				aristas.add(new Arista(i, j, 0.0));
			}
		}
		
		public void agregarArista(int i, int j, double peso){
			verificarVertice(i);
			verificarVertice(j);
			verificarDistintos(i, j);
			
			if(!existeArista(i,j)) {
				A[i][j] = A[j][i] = true;
				pesosA[i][j] = pesosA[j][i] = peso;
				aristas.add(new Arista(i, j, 0.0));
			}

		}
		
		// Eliminacion de aristas
		public void eliminarArista(int i, int j){
			verificarVertice(i);
			verificarVertice(j);
			verificarDistintos(i, j);

			if(existeArista(i, j)) {
				Arista arista = new Arista(i, j, 0.0); 
				A[i][j] = A[j][i] = false;
				aristas.remove(arista);
			}

		}

		public double obtenerPesoArista(int i, int j) {
			verificarVertice(i);
			verificarVertice(j);
			verificarExisteArista(i, j);
			
			return pesosA[i][j];
		}
		
		private void verificarExisteArista(int i, int j) {
			if(!existeArista(i,j)) {
				throw new IllegalArgumentException("La arista " + i + "" + j +" no existe");
			}	
		}

		// Informa si existe la arista especificada
		public boolean existeArista(int i, int j) {
			verificarVertice(i);
			verificarVertice(j);
			verificarDistintos(i, j);

			return A[i][j];
		}
		
		// Cantidad de vertices
		public int tamaño() {
			return A.length;
		}
		
		// Vecinos de un vertice
		public Set<Integer> vecinos(int i) {
			verificarVertice(i);
			
			Set<Integer> ret = new HashSet<Integer>();
			for(int j = 0; j < this.tamaño(); ++j) 
				if( i != j ) {
					if( this.existeArista(i,j) )
						ret.add(j);
			}
			
			return ret;		
		}
		
		public Set<Integer> conjuntoDeVertices(){
			Set<Integer> vertices = new HashSet<Integer>();
			
			for(int i = 0; i<this.tamaño();i++)
				vertices.add(i);
			return vertices;
		}
		
		// Verifica que sea un vertice valido
		private void verificarVertice(int i) {
			if( i < 0 )
				throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
			
			if( i >= A.length )
				throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
		}

		// Verifica que i y j sean distintos
		private void verificarDistintos(int i, int j){
			if( i == j )
				throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
		}
		
		public ArrayList<Arista> getAristas(){
			return this.aristas;
		}
		
		@Override
		public String toString() {
			StringBuffer cadena = new StringBuffer();
			cadena.append("----- Grafo No dirigido Ponderado ----- \n");
			for(int col = 0; col < tamaño(); col++) {
				for(int fila = 0; fila < tamaño(); fila ++) {
					if(col!=fila && existeArista(col, fila)) {
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
