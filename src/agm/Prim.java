package agm;

import java.util.HashSet;
import java.util.Set;
import grafos.GrafoNDPonderado;
import recorridos.BFS;

public class Prim {
	private Set<Integer> V;
	private GrafoNDPonderado grafoOutputAGM;
	private GrafoNDPonderado grafoInput;

	public Prim(GrafoNDPonderado grafoInput) {
		verificarGrafoConexo(grafoInput);
		this.V = new HashSet<Integer>();
		this.grafoOutputAGM = new GrafoNDPonderado(grafoInput.tamaño());
		this.grafoInput = grafoInput;
	}

	public GrafoNDPonderado obtenerArbolGeneradorMinimo(int vertice) {
		V.add(vertice);
		int i = 1;
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
					}
				}
			}

			grafoOutputAGM.agregarArista(uMin, vMin, pesoMin);
			V.add(vMin);
			i++;
		}
		return grafoOutputAGM;
	}

	private double[] obtenerVecinoMenorPeso(GrafoNDPonderado g, int vertice, Set<Integer> vecinos) {
		int vecinoMinimo = 0;
		double pesoMenor = Double.MAX_VALUE;
		double pesoVecinoActual;

		for (int vecino : vecinos) {
			if (!esVerticeMarcado(vecino)) {
				pesoVecinoActual = g.obtenerPesoArista(vertice, vecino);
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

	private void verificarGrafoConexo(GrafoNDPonderado grafoInput) {
		BFS bfs = new BFS(grafoInput);
		if (!bfs.esConexo())
			throw new IllegalArgumentException("El grafo ingresado no es conexo");
	}

}
