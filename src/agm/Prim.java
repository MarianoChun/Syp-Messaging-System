package agm;

import java.util.HashSet;
import java.util.Set;

import grafos.GrafoNDPEtiquetado;
import grafos.GrafoNDPonderado;
import grafos.Vertice;
import recorridos.BFS;

public class Prim {
	private Set<Integer> V;
	private GrafoNDPEtiquetado grafoOutputAGM;
	private GrafoNDPEtiquetado grafoInput;

	public Prim(GrafoNDPEtiquetado grafoInput) {
		verificarGrafoConexo(grafoInput);
		this.V = new HashSet<Integer>();
		this.grafoOutputAGM = new GrafoNDPEtiquetado(grafoInput.tamaño());
		this.grafoInput = grafoInput;
	}

	public GrafoNDPEtiquetado obtenerArbolGeneradorMinimo(int vertice) {
		V.add(vertice);
		int i = 1;
		String etiquetaUmin = null;
		String etiquetaVmin = null;
		int uMin = vertice;
		int vMin = 0;
		double pesoMin;
		Set<Integer> vecinosU;

		while (i <= grafoInput.tamaño() - 1) {
			pesoMin = Double.MAX_VALUE;

			for (int u : V) {
				vecinosU = grafoInput.vecinos(u);
				if (!V.containsAll(vecinosU)) {
					double[] aristaMin = obtenerVecinoMenorPeso(grafoInput, u, vecinosU);
					pesoMin = Math.min(pesoMin, aristaMin[2]);
					if (pesoMin == aristaMin[2]) {
						uMin = (int) aristaMin[0];
						vMin = (int) aristaMin[1];
	
						etiquetaUmin = grafoInput.obtenerEtiquetaVertice(uMin);
						etiquetaVmin = grafoInput.obtenerEtiquetaVertice(vMin);
					}
				}
			}

			grafoOutputAGM.agregarArista(new Vertice(uMin, etiquetaUmin), new Vertice(vMin, etiquetaVmin), pesoMin);
			V.add(vMin);
			i++;
		}
		return grafoOutputAGM;
	}

	private double[] obtenerVecinoMenorPeso(GrafoNDPEtiquetado g, int vertice, Set<Integer> vecinos) {
		int vecinoMinimo = 0;
		double pesoMenor = Double.MAX_VALUE;
		double pesoVecinoActual;

		for (int vecino : vecinos) {
			if (!esVerticeMarcado(vecino)) {
				pesoVecinoActual = g.obtenerPesoArista(new Vertice(vertice), new Vertice(vecino));
				pesoMenor = Math.min(pesoMenor, pesoVecinoActual);
				if (pesoMenor == pesoVecinoActual) {
					vecinoMinimo = vecino;
				}
			}
		}
		return new double[] { vertice, vecinoMinimo, pesoMenor };
	}

	private boolean esVerticeMarcado(int vertice) {
		return V.contains(vertice);
	}

	private void verificarGrafoConexo(GrafoNDPEtiquetado grafoInput) {
		BFS bfs = new BFS(grafoInput);
		if (!bfs.esConexo())
			throw new IllegalArgumentException("El grafo ingresado no es conexo");
	}

}
