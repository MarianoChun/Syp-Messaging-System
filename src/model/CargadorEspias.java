package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CargadorEspias {
	private Map<String, Integer> espias;
	private String pathExcel;

	public CargadorEspias(String pathExcel) {
		this.pathExcel = pathExcel;
		this.espias = new HashMap<String, Integer>();
		cargarEspiasDesdeExcel();
	}

	private void cargarEspiasDesdeExcel() {
		try {
			Iterator<Row> itr = obtenerIteradorExcel();
			String nombreEspia;
			int i = 0;

			while (itr.hasNext()) {

				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					nombreEspia = cell.getStringCellValue().toLowerCase();

					// Ignora el nombre de la columna !nombreEspia.equals("Nombre") &&
					// !nombreEspia.equals("Compañero") &&
					if (!esNombreVacio(nombreEspia) && !existeEspia(nombreEspia) && cell.getColumnIndex() == 0) {
						espias.putIfAbsent(nombreEspia, i);
						i++;
					}
					if (!esNombreVacio(nombreEspia) && !existeEspia(nombreEspia) && cell.getColumnIndex() == 1) {
						espias.putIfAbsent(nombreEspia, i);
						i++;
					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean esNombreVacio(String nombre) {
		return nombre.equals("");
	}

	private Iterator<Row> obtenerIteradorExcel() throws FileNotFoundException, IOException {
		// Hacemos la asociacion logica al archivo excel
		// "/lista_de_espias/lista-de-espias.xlsx"
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
		itr.next();
		return itr;
	}

	public int cantidadEspias() {
		return espias.size();
	}

	public Map<String, Integer> obtenerRegistroEspias() {
		return espias;
	}

	public boolean existeEspia(String nombre) {
		return espias.containsKey(nombre.toLowerCase());
	}

	public int getIndiceEspia(String nombre) {
		nombre = nombre.toLowerCase();
		verificarExisteEspia(nombre);

		return espias.get(nombre);
	}

	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		for (Map.Entry<String, Integer> entry : espias.entrySet()) {
			strb.append("Indice: ").append(entry.getValue()).append(", Nombre: ").append(entry.getKey()).append("\n");
		}
		return strb.toString();
	}

	private void verificarExisteEspia(String nombre) {
		if (!existeEspia(nombre)) {
			throw new IllegalArgumentException("Error, el espia no existe");
		}

	}

//	public static void main(String[] args) {
//		CargadorEspias red = new CargadorEspias("/lista_de_espias/lista-de-espias.xlsx");
//		red.toString();
//	}

}
