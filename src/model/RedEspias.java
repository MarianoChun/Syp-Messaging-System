package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RedEspias {

	private LinkedList<Espia> espias;

	public RedEspias() {
		this.espias = new LinkedList<Espia>();
		cargarEspiasDesdeExcel();
		for(Espia espia : espias) {
			System.out.println(espia.getNombre());
		}
	}
	
	private void cargarEspiasDesdeExcel() {
		try {
			// Hacemos la asociacion logica al archivo excel
			FileInputStream fis = new FileInputStream(new File("C:\\Users\\Mariano\\Desktop\\datosEspias.xlsx"));

			// Creamos una instancia Workbook que hace referencia al archivo .xlsx

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);

			Iterator<Row> itr = sheet.iterator();
			String nombreEspia;
			while (itr.hasNext()) {
				Row row = itr.next();

				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					nombreEspia = cell.getStringCellValue();
					if(!nombreEspia.equals("Nombre")) {
						espias.add(new Espia(nombreEspia));
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		RedEspias red = new RedEspias();
	}
	
}
