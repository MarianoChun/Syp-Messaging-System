package sistema_espias;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import agm.Kruskal;
import agm.Prim;
import grafo.GrafoNDPEtiquetado;
import grafo.Vertice;

public class ComunicadorEspias {
	private CargadorEspias espias;
	private String pathExcel;
	private GrafoNDPEtiquetado redEspias;

	public ComunicadorEspias(String pathExcel) {
		this.pathExcel = pathExcel;
		this.espias = new CargadorEspias(pathExcel);
		this.redEspias = new GrafoNDPEtiquetado(espias.cantidadEspias());
		agregarComunicacionDesdeExcel();
	}

	public GrafoNDPEtiquetado obtenerRedSeguraKruskal() {
		return new Kruskal(redEspias).obtenerArbolGeneradorMinimo();
	}

	public GrafoNDPEtiquetado obtenerRedSeguraPrim() {
		return new Prim(redEspias).obtenerArbolGeneradorMinimo(0);
	}

	public Map<String, Integer> obtenerEspias() {
		return espias.obtenerRegistroEspias();
	}

	public void agregarComunicacionDesdeExcel() {
		try {
			Iterator<Row> itr = obtenerIteradorExcel();
			String nombreEspia;
			String nombreCompañero;
			double probIntercepcion;

			while (itr.hasNext()) {

				Row row = itr.next();
				if (!(row.getCell(0) == null) && !(row.getCell(1) == null) && !(row.getCell(2) == null)) {

					nombreEspia = row.getCell(0).getStringCellValue();
					nombreCompañero = row.getCell(1).getStringCellValue();

					// Ignora el nombre de la columna y se asegura que no estemos en una celda vacia
					if ((!nombreEspia.equals("Nombre")) && !esNombreVacio(nombreEspia)) {

						probIntercepcion = Double.parseDouble(row.getCell(2).getStringCellValue());
						agregarComunicacion(nombreEspia, nombreCompañero, probIntercepcion);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean esNombreVacio(String nombre) {
		return nombre.equals("");
	}

	public boolean redYaEsSegura() {
		return this.obtenerRedSeguraKruskal().equals(this.redEspias);
	}

	private Iterator<Row> obtenerIteradorExcel() throws FileNotFoundException, IOException {
		FileInputStream archivo;
		try {
			archivo = new FileInputStream(this.getClass().getResource(pathExcel).getPath());
		} catch (NullPointerException e) {
			archivo = new FileInputStream(pathExcel);
		}
		// Creamos una instancia Workbook que hace referencia al archivo .xlsx
		XSSFWorkbook workbook = new XSSFWorkbook(archivo);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> itr = sheet.iterator();
		workbook.close();
		return itr;
	}

	public void agregarComunicacion(String nombreEspia1, String nombreEspia2, double probIntercepcion) {
		verificarExisteEspia(nombreEspia1);
		verificarExisteEspia(nombreEspia2);
		verificarProbIntercepcion(probIntercepcion);

		int indiceEspia1 = espias.getIndiceEspia(nombreEspia1);
		int indiceEspia2 = espias.getIndiceEspia(nombreEspia2);
		Vertice verticeEspia1 = new Vertice(indiceEspia1, nombreEspia1);
		Vertice verticeEspia2 = new Vertice(indiceEspia2, nombreEspia2);

		redEspias.agregarArista(verticeEspia1, verticeEspia2, probIntercepcion);

	}

	public int obtenerIndiceEspia(String nombreEspia) {
		verificarExisteEspia(nombreEspia);
		return espias.getIndiceEspia(nombreEspia);
	}

	private void verificarProbIntercepcion(double probIntercepcion) {
		if (probIntercepcion < 0.0 || probIntercepcion > 1.0) {
			throw new IllegalArgumentException("La probabilidad de intercepcion debe estar entre 0.0 y 1.0");
		}
	}

	private void verificarExisteEspia(String nombreEspia) {
		nombreEspia = nombreEspia.toLowerCase();

		if (!espias.existeEspia(nombreEspia)) {
			throw new IllegalArgumentException("El espia " + nombreEspia + " no existe");
		}

	}

	public boolean existeComunicacion(String nombreEspia1, String nombreEspia2) {
		verificarExisteEspia(nombreEspia1);
		verificarExisteEspia(nombreEspia2);

		int indiceEspia1 = espias.getIndiceEspia(nombreEspia1);
		int indiceEspia2 = espias.getIndiceEspia(nombreEspia2);

		return redEspias.existeArista(indiceEspia1, indiceEspia2);
	}

	public double obtenerProbabIntercepcion(String nombreEspia1, String nombreEspia2) {
		verificarExisteEspia(nombreEspia1);
		verificarExisteEspia(nombreEspia2);

		int indiceEspia1 = espias.getIndiceEspia(nombreEspia1);
		int indiceEspia2 = espias.getIndiceEspia(nombreEspia2);
		Vertice verticeEspia1 = new Vertice(indiceEspia1, nombreEspia1);
		Vertice verticeEspia2 = new Vertice(indiceEspia2, nombreEspia2);

		return redEspias.obtenerPesoArista(verticeEspia1, verticeEspia2);
	}

	public int cantidadEspias() {
		return espias.cantidadEspias();
	}

	@Override
	public String toString() {
		StringBuffer cadena = new StringBuffer();
		cadena.append("------ Comunicador Espias ------\nEspias:\n").append(espias.toString());
		cadena.append("\nRed espias segura:\n" + redEspias.toString());
		return cadena.toString();
	}

}
