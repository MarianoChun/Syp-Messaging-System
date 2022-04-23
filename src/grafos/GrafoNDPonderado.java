package grafos;

import java.util.HashSet;
import java.util.Set;

public class GrafoNDPonderado extends GrafoND{
	double [][] pesosA;
	
	public GrafoNDPonderado(int vertices) {
		super(vertices);
		this.pesosA = new double[vertices][vertices];
	}
	
	// Agregado de aristas
		public void agregarArista(int i, int j)
		{
			verificarVertice(i);
			verificarVertice(j);
			verificarDistintos(i, j);
			
			if(!existeArista(i,j)) {
				agregarArista(i, j, 0.0);
			}
		}
		
		public void agregarArista(int i, int j, double peso)
		{
			verificarVertice(i);
			verificarVertice(j);
			verificarDistintos(i, j);
			
			if(!existeArista(i,j)) {
				A[i][j] = A[j][i] = true;
				pesosA[i][j] = pesosA[j][i] = peso;
			}

		}
		
		// Eliminacion de aristas
		public void eliminarArista(int i, int j)
		{
			verificarVertice(i);
			verificarVertice(j);
			verificarDistintos(i, j);

			if(existeArista(i, j)) {
				A[i][j] = A[j][i] = false;
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
		public boolean existeArista(int i, int j)
		{
			verificarVertice(i);
			verificarVertice(j);
			verificarDistintos(i, j);

			return A[i][j];
		}

		// Cantidad de vertices
		public int tamano()
		{
			return A.length;
		}
		
		// Vecinos de un vertice
		public Set<Integer> vecinos(int i)
		{
			verificarVertice(i);
			
			Set<Integer> ret = new HashSet<Integer>();
			for(int j = 0; j < this.tamano(); ++j) if( i != j )
			{
				if( this.existeArista(i,j) )
					ret.add(j);
			}
			
			return ret;		
		}
		
		public Set<Integer> conjuntoDeVertices(){
			Set<Integer> vertices = new HashSet<Integer>();
			
			for(int i = 0; i<this.tamano();i++)
				vertices.add(i);
			return vertices;
		}
		
		// Verifica que sea un vertice valido
		private void verificarVertice(int i)
		{
			if( i < 0 )
				throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
			
			if( i >= A.length )
				throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
		}

		// Verifica que i y j sean distintos
		private void verificarDistintos(int i, int j)
		{
			if( i == j )
				throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
		}

		public int[] obtenerAristaMinima() {
			int[] aristaMinima = new int[2];
			double pesoMinimo = Double.MAX_VALUE;
			double pesoActual;
			for(int col = 0; col < this.tamano(); col++) {
				for(int fila = 0; fila < this.tamano(); fila ++) {
					if(col!=fila && this.existeArista(col, fila)) {
						pesoActual = pesosA[col][fila];
						if(pesoMinimo > pesoActual) {
							pesoMinimo = pesoActual;
							aristaMinima[0] = col;
							aristaMinima[1] = fila;
						}
					}
				}
			}
			return aristaMinima;
		}

		public boolean formaCircuito(int[] arista) {
			// TODO Auto-generated method stub
			return false;
		}
}
