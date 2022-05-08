package agm;

import java.util.HashSet;
import java.util.Set;

import grafos.GrafoNDPEtiquetado;
import grafos.Vertice;
import recorridos.BFS;

public class Prim {
	private Set<Integer> vertices;
	private GrafoNDPEtiquetado grafoOutputAGM;
	private GrafoNDPEtiquetado grafoInput;

	public Prim(GrafoNDPEtiquetado grafoInput) {
		verificarGrafoConexo(grafoInput);
		this.vertices = new HashSet<Integer>();
		this.grafoOutputAGM = new GrafoNDPEtiquetado(grafoInput.tamaño());
		this.grafoInput = grafoInput;
	}

	public GrafoNDPEtiquetado obtenerArbolGeneradorMinimo(int vertice) {
		vertices.add(vertice);
		int i = 1;
		String etiquetaVerticeMin = null;
		String etiquetaVecinoMin = null;
		int verticeMin = vertice;
		int vecinoMin = 0;
		double pesoMin;
		Set<Integer> vecinosVerticeMarcado;

		while (i <= grafoInput.tamaño() - 1) {
			pesoMin = Double.MAX_VALUE;

			for (int verticeMarcado : vertices) {
				
				vecinosVerticeMarcado = grafoInput.vecinos(verticeMarcado);
				
				if (!estanTodosLosVecinosDelVerticeMarcados(vecinosVerticeMarcado)) {
					
					double[] aristaMin = obtenerVecinoMenorPeso(grafoInput, verticeMarcado, vecinosVerticeMarcado);
					pesoMin = Math.min(pesoMin, aristaMin[2]);
					
					if (pesoEsIgual(pesoMin, aristaMin[2])) {
						
						verticeMin = (int) aristaMin[0];
						vecinoMin = (int) aristaMin[1];

						etiquetaVerticeMin = grafoInput.obtenerEtiquetaVertice(verticeMin);
						etiquetaVecinoMin = grafoInput.obtenerEtiquetaVertice(vecinoMin);
					}
				}
			}

			grafoOutputAGM.agregarArista(new Vertice(verticeMin, etiquetaVerticeMin), new Vertice(vecinoMin, etiquetaVecinoMin), pesoMin);
			vertices.add(vecinoMin);
			i++;
		}
		return grafoOutputAGM;
	}

	private boolean estanTodosLosVecinosDelVerticeMarcados(Set<Integer> vecinosVerticeMarcado) {
		return vertices.containsAll(vecinosVerticeMarcado);
	}
	
	private boolean pesoEsIgual(double pesoMinActual, double pesoMin) {
		return pesoMinActual == pesoMin;
	}

	private double[] obtenerVecinoMenorPeso(GrafoNDPEtiquetado g, int vertice, Set<Integer> vecinos) {
		int vecinoMinimo = 0;
		double pesoMenor = Double.MAX_VALUE;
		double pesoVecinoActual;

		for (int vecino : vecinos) {
			if (!esVerticeMarcado(vecino)) {
				pesoVecinoActual = g.obtenerPesoArista(new Vertice(vertice), new Vertice(vecino));
				pesoMenor = Math.min(pesoMenor, pesoVecinoActual);
				if (pesoEsIgual(pesoMenor, pesoVecinoActual)) {
					vecinoMinimo = vecino;
				}
			}
		}
		return new double[] { vertice, vecinoMinimo, pesoMenor };
	}

	private boolean esVerticeMarcado(int vertice) {
		return vertices.contains(vertice);
	}

	private void verificarGrafoConexo(GrafoNDPEtiquetado grafoInput) {
		BFS bfs = new BFS(grafoInput);
		if (!bfs.esConexo())
			throw new IllegalArgumentException("El grafo ingresado no es conexo");
	}

}
