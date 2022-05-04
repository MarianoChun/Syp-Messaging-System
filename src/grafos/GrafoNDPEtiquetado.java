package grafos;

import java.util.HashMap;
import java.util.Map;

public class GrafoNDPEtiquetado extends GrafoNDPonderado{
	private Map<Integer, String> etiquetas;
	public GrafoNDPEtiquetado(int vertices) {
		super(vertices);
		this.etiquetas = new HashMap<Integer, String>();
	}

	//opino que no va, porque solo nos interesa que ingresen aristas con peso
//	public void agregarArista(Vertice primerVertice, Vertice segundoVertice) {
//		int i = primerVertice.getIndice();
//		int j = segundoVertice.getIndice();
//		verificarVerticesEtiquetados(primerVertice, segundoVertice);
//		verificarVertice(i);
//		verificarVertice(j);
//		verificarDistintos(i, j);
//
//		if (!existeArista(i, j)) {
//			agregarArista(primerVertice, segundoVertice, 0.0);
//		}
//	}
	
	public void agregarArista(Vertice primerVertice, Vertice segundoVertice, double peso) {
		int i = primerVertice.getIndice();
		int j = segundoVertice.getIndice();
		verificarVerticesEtiquetados(primerVertice, segundoVertice);
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		if (!existeArista(i, j)) {
			A[i][j] = A[j][i] = true;
			pesosA[i][j] = pesosA[j][i] = peso;
			aristas.add(new Arista(primerVertice, segundoVertice, peso));
			etiquetas.put(i, primerVertice.getEtiqueta());
			etiquetas.put(j, segundoVertice.getEtiqueta());
		}

	}

	@Override
	public void eliminarArista(Vertice primerVertice, Vertice segundoVertice) {
		int i = primerVertice.getIndice();
		int j = segundoVertice.getIndice();
		verificarVerticesEtiquetados(primerVertice, segundoVertice);
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

		if (existeArista(i, j)) {
			A[i][j] = A[j][i] = false;
			aristas.remove(new Arista(primerVertice, segundoVertice, peso));
			aristas.remove(new Arista(segundoVertice, primerVertice, peso));
		}
	}

	public double obtenerPesoArista(Vertice primerVertice, Vertice segundoVertice) {
		int i = primerVertice.getIndice();
		int j = segundoVertice.getIndice();
		verificarVerticesEtiquetados(primerVertice, segundoVertice);
		verificarVertice(i);
		verificarVertice(j);
		verificarExisteArista(i, j);

		return pesosA[i][j];
	}
	
	public String obtenerEtiquetaVertice(int i) {
		verificarVertice(i);
		return etiquetas.get(i);
	}

	private void verificarVerticesEtiquetados(Vertice primerVertice, Vertice segundoVertice) {
		if(!primerVertice.esEtiquetado() || !segundoVertice.esEtiquetado()) {
			throw new IllegalArgumentException("Los vertices ingresados deben estar etiquetados");
		}
	}
}
