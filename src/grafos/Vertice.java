package grafos;

public class Vertice {
	private int indice;
	private String etiqueta;

	public Vertice(int indice, String etiqueta) {
		this.indice = indice;
		this.etiqueta = etiqueta.toLowerCase();
	}

	public Vertice(int indice) {
		this.indice = indice;
	}

	public boolean esEtiquetado() {
		return !(this.etiqueta == null);
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta.toLowerCase();
	}

	public int getIndice() {
		return indice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((etiqueta == null) ? 0 : etiqueta.hashCode());
		result = prime * result + indice;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Vertice other = (Vertice) obj;
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		if (indice != other.indice)
			return false;
		
		if (etiqueta == null) {
			if (other.etiqueta != null)
				return false;
		} else if (!etiqueta.equals(other.etiqueta)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer cadena = new StringBuffer();
		cadena.append("(").append(indice).append(", ").append(etiqueta).append(")");
		return cadena.toString();
	}

}
