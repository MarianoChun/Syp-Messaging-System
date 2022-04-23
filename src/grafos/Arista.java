package grafos;

public class Arista implements Comparable{
	private int primerExtremo;
	private int segundoExtremo;
	private double peso;
	
	public Arista(int primerExtremo, int segundoExtremo, double peso) {
		this.primerExtremo = primerExtremo;
		this.segundoExtremo = segundoExtremo;
		this.peso = peso;
	}

	public int getPrimerExtremo() {
		return primerExtremo;
	}

	public int getSegundoExtremo() {
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
		result = prime * result + primerExtremo;
		result = prime * result + segundoExtremo;
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
		if (primerExtremo != other.primerExtremo)
			return false;
		if (segundoExtremo != other.segundoExtremo)
			return false;
		if (peso != other.peso)
			return false;
		return true;
	}
	


	@Override
	public int compareTo(Object o) {
		Arista otraArista = (Arista) o;
		if(this.getPeso() < otraArista.getPeso()) {
			return -1;
		}

		if(this.getPeso() > otraArista.getPeso()) {
			return 1;
		}
		
		return 0;
	}
	
}
