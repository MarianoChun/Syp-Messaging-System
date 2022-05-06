package grafos;

public class Arista implements Comparable<Object> {
	private Vertice primerExtremo;
	private Vertice segundoExtremo;
	private double peso;

	public Arista(Vertice primerExtremo, Vertice segundoExtremo, double peso) {
		this.primerExtremo = primerExtremo;
		this.segundoExtremo = segundoExtremo;
		this.peso = peso;
	}

	public Vertice getPrimerExtremo() {
		return primerExtremo;
	}

	public Vertice getSegundoExtremo() {
		return segundoExtremo;
	}

	public double getPeso() {
		return peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(peso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + primerExtremo.getIndice();
		result = prime * result + segundoExtremo.getIndice();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arista other = (Arista) obj;
		if (peso != other.peso) {
			System.out.println(this.toString() + " " + other.toString());
			System.out.println("Entro arista peso false");
			return false;
		}
		if (!primerExtremo.equals(other.primerExtremo) && !primerExtremo.equals(other.segundoExtremo)) {
			System.out.println(this.toString() + " " + other.toString());
			System.out.println("Entro arista 1er false");
			return false;
		}
		if (!segundoExtremo.equals(other.segundoExtremo) && !segundoExtremo.equals(other.primerExtremo)) {
			System.out.println("Entro arista 2do false");
			return false;
		}
		if (primerExtremo.equals(other.primerExtremo) && segundoExtremo.equals(other.segundoExtremo))
			return true;
		if (primerExtremo.equals(other.primerExtremo) && segundoExtremo.equals(other.segundoExtremo))
			return true;
		if (primerExtremo.equals(other.segundoExtremo) && segundoExtremo.equals(other.primerExtremo))
			return true;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		Arista otraArista = (Arista) o;
		if (this.getPeso() < otraArista.getPeso()) {
			return -1;
		}

		if (this.getPeso() > otraArista.getPeso()) {
			return 1;
		}

		return 0;
	}

	@Override
	public String toString() {
		StringBuffer cadena = new StringBuffer();
		cadena.append("[").append(this.primerExtremo.toString());
		cadena.append(", ");
		cadena.append(this.segundoExtremo.toString());
		cadena.append(", ");
		cadena.append(this.peso).append("]");
		return cadena.toString();
	}

}
