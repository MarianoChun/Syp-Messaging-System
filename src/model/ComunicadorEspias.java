package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import agm.Kruskal;
import grafos.GrafoNDPonderado;

public class ComunicadorEspias {
	private CargadorEspias espias;
	private String pathExcel;
	private GrafoNDPonderado redEspias;
	
	public ComunicadorEspias() {
		this.pathExcel = "/lista_de_espias/lista-de-espias.xlsx";
		this.espias = new CargadorEspias(pathExcel);
		this.redEspias = new GrafoNDPonderado(espias.cantidadEspias());
		agregarComunicacionDesdeExcel();
	}
	
	public ComunicadorEspias(String pathExcel) {
		this.pathExcel = pathExcel;
		this.espias = new CargadorEspias(pathExcel);
		this.redEspias = new GrafoNDPonderado(espias.cantidadEspias());
		agregarComunicacionDesdeExcel();
	}
	
	public GrafoNDPonderado obtenerRedSegura() {
		return new Kruskal(redEspias).obtenerArbolGM();
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
					if ((!nombreEspia.equals("Nombre")) && !nombreEspia.equals("")) {
						
						probIntercepcion = Double.parseDouble(row.getCell(2).getStringCellValue());	
						agregarComunicacion(nombreEspia, nombreCompañero, probIntercepcion);					
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean redYaEsSegura() {
		return this.obtenerRedSegura().equals(this.redEspias);
	}
	
	private Iterator<Row> obtenerIteradorExcel() throws FileNotFoundException, IOException {
		// Hacemos la asociacion logica al archivo excel
		// "/lista_de_espias/lista-de-espias.xlsx"
		FileInputStream archivo = new FileInputStream(new File(this.getClass().getResource(pathExcel).getPath()));

		// Creamos una instancia Workbook que hace referencia al archivo .xlsx
		XSSFWorkbook workbook = new XSSFWorkbook(archivo);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> itr = sheet.iterator();
		return itr;
	}
	public void agregarComunicacion(String nombreEspia1, String nombreEspia2, double probIntercepcion) {
		verificarExistenEspias(nombreEspia1, nombreEspia2);	
		verificarProbIntercepcion(probIntercepcion);
		
		int indiceEspia1 = espias.getIndiceEspia(nombreEspia1);
		int indiceEspia2 = espias.getIndiceEspia(nombreEspia2);
		
		redEspias.agregarArista(indiceEspia1, indiceEspia2, probIntercepcion);
	}

	private void verificarProbIntercepcion(double probIntercepcion) {
		if(probIntercepcion < 0.0 || probIntercepcion > 1.0) {
			throw new IllegalArgumentException("La probabilidad de intercepcion debe estar entre 0.0 y 1.0");
		}
	}

	private void verificarExistenEspias(String nombreEspia1, String nombreEspia2) {
		nombreEspia1 = nombreEspia1.toLowerCase();
		nombreEspia2 = nombreEspia2.toLowerCase();
		
		if(!espias.existeEspia(nombreEspia1)) {
			throw new IllegalArgumentException("El espia " + nombreEspia1 + " no existe");
		}
		if(!espias.existeEspia(nombreEspia2)) {
			throw new IllegalArgumentException("El espia " + nombreEspia2 + " no existe");
		}
	}
	
	public boolean existeComunicacion(String nombreEspia1, String nombreEspia2) {
		verificarExistenEspias(nombreEspia1, nombreEspia2);
		
		int indiceEspia1 = espias.getIndiceEspia(nombreEspia1);
		int indiceEspia2 = espias.getIndiceEspia(nombreEspia2);
		
		return redEspias.existeArista(indiceEspia1, indiceEspia2);
	}
	
	public double obtenerProbabIntercepcion(String nombreEspia1, String nombreEspia2) {
		verificarExistenEspias(nombreEspia1, nombreEspia2);
		
		int indiceEspia1 = espias.getIndiceEspia(nombreEspia1);
		int indiceEspia2 = espias.getIndiceEspia(nombreEspia2);
		
		return redEspias.obtenerPesoArista(indiceEspia1, indiceEspia2);
	}
	
	public int cantidadEspias() {
		return espias.cantidadEspias();
	}
}
