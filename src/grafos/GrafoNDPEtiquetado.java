package grafos;

import java.util.HashMap;
import java.util.Map;

public class GrafoNDPEtiquetado extends GrafoNDPonderado {
	private Map<Integer, String> etiquetas;

	public GrafoNDPEtiquetado(int vertices) {
		super(vertices);
		this.etiquetas = new HashMap<Integer, String>();
	}

	public void agregarArista(Vertice primerVertice, Vertice segundoVertice, double peso) {
		super.agregarArista(primerVertice, segundoVertice, peso);
		etiquetas.put(primerVertice.getIndice(), primerVertice.getEtiqueta());
		etiquetas.put(segundoVertice.getIndice(), segundoVertice.getEtiqueta());
	}

	public String obtenerEtiquetaVertice(int i) {
		verificarVerticeEtiquetado(i);
		verificarVertice(i);
		return etiquetas.get(i);
	}


	private void verificarVerticeEtiquetado(Vertice vertice) {
		if (!vertice.esEtiquetado()) {
			throw new IllegalArgumentException("El vertice ingresado debe esta etiquetado");
		}
	}
	
	private void verificarVerticeEtiquetado(int indice) {
		String etiquetaVertice = etiquetas.get(indice);
		if(etiquetaVertice == null) {
			throw new IllegalArgumentException("El vertice ingresado debe esta etiquetado");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((etiquetas == null) ? 0 : etiquetas.hashCode());
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
		GrafoNDPEtiquetado other = (GrafoNDPEtiquetado) obj;
		if (etiquetas == null) {
			if (other.etiquetas != null)
				return false;
		} else if (!etiquetas.equals(other.etiquetas))
			return false;
		return true;
	}
	
	
}
