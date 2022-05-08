package agm;

import java.util.HashSet;
import java.util.Set;

import grafo.Arista;
import grafo.GrafoNDPEtiquetado;
import grafo.Vertice;
import recorridos.BFS;

public class Prim {
	private Set<Integer> verticesMarcados;
	private GrafoNDPEtiquetado grafoOutputAGM;
	private GrafoNDPEtiquetado grafoInput;

	public Prim(GrafoNDPEtiquetado grafoInput) {
		verificarGrafoConexo(grafoInput);
		this.verticesMarcados = new HashSet<Integer>();
		this.grafoOutputAGM = new GrafoNDPEtiquetado(grafoInput.tamaño());
		this.grafoInput = grafoInput;
	}

	public GrafoNDPEtiquetado obtenerArbolGeneradorMinimo(int vertice) {
		int i = 1;
		double pesoMin;
		Vertice verticeMin = new Vertice(vertice, grafoInput.obtenerEtiquetaVertice(vertice));
		Vertice vecinoMin = null;
		Set<Integer> vecinosVerticeMarcado;
		
		verticesMarcados.add(vertice);
		
		while (i <= grafoInput.tamaño() - 1) {
			pesoMin = Double.MAX_VALUE;

			for (int verticeMarcado : verticesMarcados) {			
				
				vecinosVerticeMarcado = grafoInput.vecinos(verticeMarcado);		
				
				if (!estanTodosLosVecinosDelVerticeMarcados(vecinosVerticeMarcado)) {
					
					Arista aristaMin = obtenerAristaVecinaConMenorPeso(grafoInput, verticeMarcado, vecinosVerticeMarcado);
					pesoMin = Math.min(pesoMin, aristaMin.getPeso());

					if (pesoEsIgual(pesoMin, aristaMin.getPeso())) {		
						verticeMin = aristaMin.getPrimerExtremo();
						vecinoMin = aristaMin.getSegundoExtremo();
					}
				}
			}

			grafoOutputAGM.agregarArista(verticeMin, vecinoMin, pesoMin);
			verticesMarcados.add(vecinoMin.getIndice());
			i++;
		}
		return grafoOutputAGM;
	}

	private boolean estanTodosLosVecinosDelVerticeMarcados(Set<Integer> vecinosVerticeMarcado) {
		return verticesMarcados.containsAll(vecinosVerticeMarcado);
	}
	
	private boolean pesoEsIgual(double pesoActual, double pesoNuevo) {
		return pesoActual == pesoNuevo;
	}

	private Arista obtenerAristaVecinaConMenorPeso(GrafoNDPEtiquetado g, int vertice, Set<Integer> vecinosVertice) {
		Set<Integer> vecinos = vecinosVertice;
		double pesoMenor = Double.MAX_VALUE;
		double pesoVecinoActual;
		String etiquetaVecino = null;
		Vertice verticeInput = new Vertice(vertice, g.obtenerEtiquetaVertice(vertice));
		Vertice verticeMin = null;
		
		for (int vecino : vecinos) {
			if (!esVerticeMarcado(vecino)) {
				
				etiquetaVecino = g.obtenerEtiquetaVertice(vecino);
				pesoVecinoActual = g.obtenerPesoArista(verticeInput, new Vertice(vecino, etiquetaVecino));
				pesoMenor = Math.min(pesoMenor, pesoVecinoActual);

				if (pesoEsIgual(pesoMenor, pesoVecinoActual)) {
					verticeMin =  new Vertice(vecino, etiquetaVecino);
				}
			}
		}
		return new Arista(verticeInput, verticeMin, pesoMenor);
	}

	private boolean esVerticeMarcado(int vertice) {
		return verticesMarcados.contains(vertice);
	}

	private void verificarGrafoConexo(GrafoNDPEtiquetado grafoInput) {
		BFS bfs = new BFS(grafoInput);
		if (!bfs.esConexo())
			throw new IllegalArgumentException("El grafo ingresado no es conexo");
	}

}
