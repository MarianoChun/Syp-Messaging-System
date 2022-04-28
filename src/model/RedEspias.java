package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RedEspias {
	private Map<String, Integer> espias;
	private String pathExcel;

	public RedEspias(String pathExcel) {
		this.pathExcel = pathExcel;
		this.espias = new HashMap<String, Integer>();
		cargarEspiasDesdeExcel();
		for(Map.Entry<String, Integer> entry : espias.entrySet()) {
			System.out.println("Indice: " + entry.getValue() + ", Nombre: " + entry.getKey());
		}
		System.out.print("\n");
	}

	private void cargarEspiasDesdeExcel() {
		try {
			// Hacemos la asociacion logica al archivo excel
			//"/lista_de_espias/lista-de-espias.xlsx" 
			FileInputStream archivo = new FileInputStream(new File(this.getClass().getResource(pathExcel).getPath()));

			// Creamos una instancia Workbook que hace referencia al archivo .xlsx
			XSSFWorkbook workbook = new XSSFWorkbook(archivo);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> itr = sheet.iterator();
			String nombreEspia;
			int i = 0;

			while (itr.hasNext()) {

				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					nombreEspia = cell.getStringCellValue();
					
					// Ignora el nombre de la columna
					if (!nombreEspia.equals("Nombre") && !nombreEspia.equals("") && cell.getColumnIndex() == 0) {
						espias.putIfAbsent(nombreEspia.toLowerCase(), i);
						i++;
					}
	
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int cantidadEspias() {
		return espias.size();
	}
	
	public boolean existeEspia(String nombre) {
		return espias.containsKey(nombre.toLowerCase());
	}

	public int getIndiceEspia(String nombre) {
		nombre = nombre.toLowerCase();
		verificarExisteEspia(nombre);
		
		return espias.get(nombre);
	}

	private void verificarExisteEspia(String nombre) {
		if(!existeEspia(nombre)) {
			throw new IllegalArgumentException("Error, el espia no existe");
		}
	}
	public static void main(String[] args) {
		RedEspias red = new RedEspias("/lista_de_espias/lista-de-espias.xlsx");
	}

}
