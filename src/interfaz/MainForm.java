package interfaz;

import java.awt.EventQueue;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import recorridos.BFS;
import sistema_espias.ComunicadorEspias;
import sistema_espias.ThreadTime;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import grafo.GrafoNDPEtiquetado;
import grafo.Vertice;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MainForm {

	private JFrame frmPrincipal;
	private JTable tablaEspias;
	private JTable tablaRedSegura;
	private JFileChooser selectorArchivos;
	private DefaultTableModel modeloTablaEspias;
	private DefaultTableModel modeloRedSegura;
	private JButton btnArmarRedSeguraKruskal;
	private JButton btnArmarRedSeguraPrim;
	private JButton btnSelectorArchivos;
	private JButton btnCompararTiempos;
	private ComunicadorEspias comunicador;
	private static ThreadTime threadTiempo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frmPrincipal.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		threadTiempo = new ThreadTime();
		threadTiempo.start();
		iniciarSelectorArchivos();

		iniciarFrmPrincipal();

		// Definimos los modelos de las dos tablas (de espias y red segura)

		JScrollPane scrollPanelEspias = iniciarScrollPanelEspias();

		iniciarTablaRedNoSegura(scrollPanelEspias);

		JScrollPane scrollPanelRedSegura = iniciarScrollPanelRedSegura();

		iniciarTablaRedSegura(scrollPanelRedSegura);

		crearBtnArmarRedSeguraKruskal();

		crearLblFlecha();
		
		crearBtnArmarRedSeguraPrim();

		crearBtnCompararTiempos();

		seleccionarArchivoExcel();

		crearLblTituloTablaEspias();

		crearLblTituloRedSegura();
	}

	// ----------------------------------------------------------//
	private void seleccionarArchivoExcel() {
		btnSelectorArchivos = new JButton("Seleccionar archivo Excel");
		btnSelectorArchivos.setRequestFocusEnabled(false);
		btnSelectorArchivos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSelectorArchivos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSelectorArchivos.setForeground(new Color(0, 0, 0));
				btnSelectorArchivos.setBackground(new Color(204, 204, 255));
				btnSelectorArchivos.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSelectorArchivos.setForeground(new Color(0, 0, 0));
				btnSelectorArchivos.setBackground(new Color(230, 230, 250));
				btnSelectorArchivos.setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		});
		btnSelectorArchivos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSelectorArchivos.setForeground(new Color(0, 0, 0));
		btnSelectorArchivos.setBackground(new Color(230, 230, 250));
		btnSelectorArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileFilter filtro = new FileNameExtensionFilter("Archivos xlsx (.xlsx)", "xlsx");
				selectorArchivos.setFileFilter(filtro);
				int valor = selectorArchivos.showOpenDialog(selectorArchivos);

				if (valor == JFileChooser.APPROVE_OPTION) {
					try {
						File archivo = selectorArchivos.getSelectedFile();
						String path = archivo.getAbsolutePath().replaceAll("\\\\", "/");
						comunicador = new ComunicadorEspias(path);

						limpiarTablas();
						armarTablaConEspias(path);

						btnArmarRedSeguraPrim.setEnabled(true);
						btnArmarRedSeguraKruskal.setEnabled(true);
						btnCompararTiempos.setEnabled(true);
						cambiarEstiloBotones(btnArmarRedSeguraPrim);
						cambiarEstiloBotones(btnArmarRedSeguraKruskal);
						cambiarEstiloBotones(btnCompararTiempos);

					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				} else {
					System.out.println("No se ha seleccionado ningún fichero");
				}
			}
		});
		btnSelectorArchivos.setBounds(428, 414, 253, 23);
		frmPrincipal.getContentPane().add(btnSelectorArchivos);
	}
	
	private void cambiarEstiloBotones(JButton btn) {
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn.setBackground(new Color(204, 153, 204));
				btn.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btn.setBackground(new Color(216, 191, 216));
				btn.setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		});
	}

	private void crearBtnCompararTiempos() {
		btnCompararTiempos = new JButton("Comparar tiempos Prim vs. Kruskal");
		btnCompararTiempos.setRequestFocusEnabled(false);
		btnCompararTiempos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCompararTiempos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCompararTiempos.setBackground(new Color(216, 191, 216));
		btnCompararTiempos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				long tiempoPrim = tiempoEjecucionPrim();
				long tiempoKruskal = tiempoEjecucionKruskal();

				popUpInfoTiempoDeEjecución(tiempoPrim, tiempoKruskal);
			}
		});
		btnCompararTiempos.setEnabled(false);
		btnCompararTiempos.setBounds(708, 345, 253, 34);
		frmPrincipal.getContentPane().add(btnCompararTiempos);
	}

	private void armarTablaConEspias(String path) {
		String nombreEspia;
		String nombreCompañero;
		String probabilidad;

		try {
			limpiarTablas();
			Iterator<Row> itr = obtenerIteradorExcel(path);

			while (itr.hasNext()) {
				Row row = itr.next();
				Cell cell = row.getCell(0);
				Cell cell1 = row.getCell(1);
				Cell cell2 = row.getCell(2);
				if (cell != null && cell1 != null && cell2 != null) {
					nombreEspia = cell.getStringCellValue().toLowerCase();
					nombreCompañero = cell1.getStringCellValue().toLowerCase();
					probabilidad = cell2.getStringCellValue().toLowerCase();
					modeloTablaEspias.addRow(new Object[] { nombreEspia, nombreCompañero, probabilidad });
				}

				tablaEspias.setModel(modeloTablaEspias);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void limpiarTablas() {
		int cantRegistros = tablaEspias.getRowCount();
		if (cantRegistros > 1) {
			removerRegistrosTabla(modeloTablaEspias);
		}
		
		cantRegistros = tablaRedSegura.getRowCount();
		if (cantRegistros > 1) {
			removerRegistrosTabla(modeloRedSegura);
		}
	}

	private Iterator<Row> obtenerIteradorExcel(String path) throws FileNotFoundException, IOException {
		FileInputStream archivo;
		try {
			archivo = new FileInputStream(this.getClass().getResource(path).getPath());
		} catch (NullPointerException e) {
			archivo = new FileInputStream(path);
		}
		// Creamos una instancia Workbook que hace referencia al archivo .xlsx
		XSSFWorkbook workbook = new XSSFWorkbook(archivo);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> itr = sheet.iterator();
		itr.next();
		return itr;
	}

	private void popUpInfoTiempoDeEjecución(long tiempoPrim, long tiempoKruskal) {
		StringBuilder str = new StringBuilder();

		JOptionPane.showMessageDialog(frmPrincipal,
				str.append("El tiempo de ejecución de Prim fue: ").append(tiempoPrim).append(" ms.").append("\n")
						.append("El tiempo de ejecución de Kruskal fue: ").append(tiempoKruskal).append(" ms."));

	}

	private void crearLblTituloRedSegura() {
		JLabel lblTituloRedSegura = new JLabel("Red Segura");
		lblTituloRedSegura.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloRedSegura.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloRedSegura.setBounds(762, 19, 112, 19);
		frmPrincipal.getContentPane().add(lblTituloRedSegura);
	}

	private void crearLblTituloTablaEspias() {
		JLabel lblTituloTablaEspias = new JLabel("Red No Segura");
		lblTituloTablaEspias.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloTablaEspias.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloTablaEspias.setBounds(200, 17, 122, 23);
		frmPrincipal.getContentPane().add(lblTituloTablaEspias);
	}

	private void crearBtnArmarRedSeguraPrim() {
		btnArmarRedSeguraPrim = new JButton("Armar red segura (Prim)");
		btnArmarRedSeguraPrim.setRequestFocusEnabled(false);
		btnArmarRedSeguraPrim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnArmarRedSeguraPrim.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnArmarRedSeguraPrim.setBackground(new Color(216, 191, 216));
		btnArmarRedSeguraPrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				armarRedSeguraPrim();
			}
		});

		btnArmarRedSeguraPrim.setEnabled(false);
		btnArmarRedSeguraPrim.setBounds(428, 345, 253, 34);
		frmPrincipal.getContentPane().add(btnArmarRedSeguraPrim);
	}

	private void armarRedSeguraPrim() {
		GrafoNDPEtiquetado redSegura = comunicador.obtenerRedSeguraPrim();
		Set<Integer> recorrido = new BFS(redSegura).verticesAlcanzablesDesdeVertice(0);
		int cantRegistros = tablaEspias.getRowCount();
		if (cantRegistros > 1) {
			removerRegistrosTabla(modeloRedSegura);
		}
		for (Integer vertice : recorrido) {
			String nombreEspia = redSegura.obtenerEtiquetaVertice(vertice);
			for (Integer vecinoActual : redSegura.vecinos(vertice)) {
				String nombreVecino = redSegura.obtenerEtiquetaVertice(vecinoActual);
				double probIntercepcionVecino = redSegura.obtenerPesoArista(new Vertice(vertice),
						new Vertice(vecinoActual));
				modeloRedSegura.addRow(new Object[] { vertice, nombreEspia, nombreVecino, probIntercepcionVecino });
				
			}
			tablaRedSegura.setModel(modeloRedSegura);
		}
	}

	private long tiempoEjecucionPrim() {
		long tiempoInicial = threadTiempo.getTiempoActualMs();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		armarRedSeguraPrim();
		long tiempoFinal = threadTiempo.getTiempoActualMs();
		long tiempoPrim = (tiempoFinal - tiempoInicial);
		
		return tiempoPrim;
	}

	private void crearLblFlecha() {
		JLabel lblFlecha = new JLabel("=========>");
		lblFlecha.setForeground(Color.DARK_GRAY);
		lblFlecha.setBounds(511, 174, 87, 14);
		frmPrincipal.getContentPane().add(lblFlecha);
	}

	private void crearBtnArmarRedSeguraKruskal() {
		btnArmarRedSeguraKruskal = new JButton("Armar red segura (Kruskal)");
		btnArmarRedSeguraKruskal.setRequestFocusEnabled(false);
		btnArmarRedSeguraKruskal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnArmarRedSeguraKruskal.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnArmarRedSeguraKruskal.setBackground(new Color(216, 191, 216));
		btnArmarRedSeguraKruskal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				armarRedSeguraKruskal();
			}
		});
		btnArmarRedSeguraKruskal.setEnabled(false);
		btnArmarRedSeguraKruskal.setBounds(148, 345, 253, 34);
		frmPrincipal.getContentPane().add(btnArmarRedSeguraKruskal);
	}

	private void armarRedSeguraKruskal() {
		GrafoNDPEtiquetado redSegura = comunicador.obtenerRedSeguraKruskal();
		Set<Integer> recorrido = new BFS(redSegura).verticesAlcanzablesDesdeVertice(0);
		int cantRegistros = tablaEspias.getRowCount();
		if (cantRegistros > 1) {
			removerRegistrosTabla(modeloRedSegura);
		}
		for (Integer vertice : recorrido) {
			String nombreEspia = redSegura.obtenerEtiquetaVertice(vertice);
			for (Integer vecinoActual : redSegura.vecinos(vertice)) {
				String nombreVecino = redSegura.obtenerEtiquetaVertice(vecinoActual);
				double probIntercepcionVecino = redSegura.obtenerPesoArista(new Vertice(vertice),
						new Vertice(vecinoActual));
				modeloRedSegura.addRow(new Object[] { vertice, nombreEspia, nombreVecino, probIntercepcionVecino });
			}
			tablaRedSegura.setModel(modeloRedSegura);
		}
	}

	private long tiempoEjecucionKruskal() {
		long tiempoInicial = threadTiempo.getTiempoActualMs();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		armarRedSeguraKruskal();
		long tiempoFinal = threadTiempo.getTiempoActualMs();
		long tiempoKruskal = (tiempoFinal - tiempoInicial);

		return tiempoKruskal;
	}

	private void iniciarTablaRedSegura(JScrollPane scrollPanelRedSegura) {
		tablaRedSegura = new JTable();
		tablaRedSegura.setBackground(new Color(204, 255, 204));
		iniciarModeloRedSegura();
		tablaRedSegura.setBounds(259, 24, 139, 172);
		scrollPanelRedSegura.setViewportView(tablaRedSegura);
	}

	private void iniciarModeloRedSegura() {
		modeloRedSegura = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeloRedSegura.addColumn("Indice espía");
		modeloRedSegura.addColumn("Nombre espía");
		modeloRedSegura.addColumn("Compañero");
		modeloRedSegura.addColumn("Prob. intercepción");
		tablaRedSegura.getTableHeader().setReorderingAllowed(false);
		tablaRedSegura.getTableHeader().setResizingAllowed(false);
	}

	private JScrollPane iniciarScrollPanelEspias() {
		JScrollPane scrollPanelEspias = new JScrollPane();
		scrollPanelEspias.setBounds(50, 46, 445, 249);
		frmPrincipal.getContentPane().add(scrollPanelEspias);
		return scrollPanelEspias;
	}

	private JScrollPane iniciarScrollPanelRedSegura() {
		JScrollPane scrollPanelRedSegura = new JScrollPane();
		scrollPanelRedSegura.setBounds(608, 46, 445, 249);
		frmPrincipal.getContentPane().add(scrollPanelRedSegura);
		return scrollPanelRedSegura;
	}

	private DefaultTableModel iniciarTablaRedNoSegura(JScrollPane scrollPanelEspias) {
		tablaEspias = new JTable();
		tablaEspias.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tablaEspias.setBackground(new Color(255, 204, 153));
		tablaEspias.setRowSelectionAllowed(false);
		iniciarModeloRedNoSegura();
		scrollPanelEspias.setViewportView(tablaEspias);
		tablaEspias.setBounds(38, 24, 139, 172);
		return modeloTablaEspias;
	}

	private void iniciarModeloRedNoSegura() {
		modeloTablaEspias = new DefaultTableModel() {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeloTablaEspias.addColumn("Nombre espía");
		modeloTablaEspias.addColumn("Compañero");
		modeloTablaEspias.addColumn("Prob. intercepción");
		tablaEspias.getTableHeader().setReorderingAllowed(false);
		tablaEspias.getTableHeader().setResizingAllowed(false);
	}

	private void iniciarFrmPrincipal() {
		frmPrincipal = new JFrame();
		frmPrincipal.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmPrincipal.getContentPane().setBackground(new Color(250, 235, 215));
		frmPrincipal.setResizable(false);
		frmPrincipal.setTitle("Comunicador de espías");
		frmPrincipal.setBounds(100, 100, 1123, 533);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
		frmPrincipal.setLocationRelativeTo(null);
	}

	private void iniciarSelectorArchivos() {
		File directorioAMostrar = new File(System.getProperty("user.dir") + "/src/lista_de_espias");
		selectorArchivos = new JFileChooser();
		selectorArchivos.setCurrentDirectory(directorioAMostrar);
	}

	private void removerRegistrosTabla(DefaultTableModel modeloTabla) {
		modeloTabla.getDataVector().removeAllElements();
		modeloTabla.fireTableDataChanged();
	}
}
