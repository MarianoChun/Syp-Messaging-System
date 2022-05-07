package view;

import java.awt.EventQueue;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import grafos.GrafoNDPEtiquetado;
import grafos.Vertice;
import model.ComunicadorEspias;
import recorridos.BFS;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

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
	private ComunicadorEspias comunicador;

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
		iniciarSelectorArchivos();

		iniciarFrmPrincipal();

		crearLblComEspias();

		// Definimos los modelos de las dos tablas (de espias y red segura)

		JScrollPane scrollPanelEspias = iniciarScrollPanelEspias();

		iniciarTablaEspias(scrollPanelEspias);

		JScrollPane scrollPanelRedSegura = iniciarScrollPanelRedSegura();

		iniciarTablaRedSegura(scrollPanelRedSegura);

		crearBtnArmarRedSeguraKruskal();

		crearLblFlecha();

		crearBtnArmarRedSeguraPrim();

		JButton btnCompararTiempos = new JButton("Comparar tiempos Prim vs. Kruskal");
		btnCompararTiempos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				long tiempoPrim = tiempoEjecucionPrim();
				long tiempoKruskal = tiempoEjecucionKruskal();

				popUpInfoTiempoDeEjecución(tiempoPrim, tiempoKruskal);
			}
		});
		btnCompararTiempos.setEnabled(false);
		btnCompararTiempos.setBounds(612, 257, 230, 23);
		frmPrincipal.getContentPane().add(btnCompararTiempos);

		btnSelectorArchivos = new JButton("Seleccionar archivo excel");
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

						Map<String, Integer> listaEspias = comunicador.obtenerEspias();
						int cantRegistros = tablaEspias.getRowCount();
						if (cantRegistros > 1) {
							removerRegistrosTabla(modeloTablaEspias);
						}
						for (Map.Entry<String, Integer> entry : listaEspias.entrySet()) {
							modeloTablaEspias.addRow(new Object[] { entry.getKey() });
						}
						tablaEspias.setModel(modeloTablaEspias);
						btnArmarRedSeguraPrim.setEnabled(true);
						btnArmarRedSeguraKruskal.setEnabled(true);
						btnCompararTiempos.setEnabled(true);

					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				} else {
					System.out.println("No se ha seleccionado ningún fichero");
				}
			}
		});
		btnSelectorArchivos.setBounds(334, 320, 230, 23);
		frmPrincipal.getContentPane().add(btnSelectorArchivos);

		crearLblTituloTablaEspias();

		crearLblTituloRedSegura();

	}
	
	//-------------------------metodos aux apartir de aca---------------------------------//
	private void popUpInfoTiempoDeEjecución(long tiempoPrim, long tiempoKruskal) {
		StringBuilder str = new StringBuilder();

		JOptionPane.showMessageDialog(frmPrincipal,
				str.append("El tiempo de ejecución de Prim fue: ").append(tiempoPrim).append("\n")
						.append("El tiempo de ejecución de Kruskal fue: ").append(tiempoKruskal));
	}

	private void crearLblTituloRedSegura() {
		JLabel lblTituloRedSegura = new JLabel("Red segura");
		lblTituloRedSegura.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloRedSegura.setBounds(598, 21, 112, 14);
		frmPrincipal.getContentPane().add(lblTituloRedSegura);
	}

	private void crearLblTituloTablaEspias() {
		JLabel lblTituloTablaEspias = new JLabel("Lista espias");
		lblTituloTablaEspias.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloTablaEspias.setBounds(77, 21, 122, 14);
		frmPrincipal.getContentPane().add(lblTituloTablaEspias);
	}

	private void crearBtnArmarRedSeguraPrim() {
		btnArmarRedSeguraPrim = new JButton("Armar red segura (Prim)");
		btnArmarRedSeguraPrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				armarRedSeguraPrim();
			}
		});

		btnArmarRedSeguraPrim.setEnabled(false);
		btnArmarRedSeguraPrim.setBounds(334, 257, 230, 23);
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
		long tiempoInicial = System.currentTimeMillis();
		armarRedSeguraPrim();
		long tiempoFinal = (System.currentTimeMillis());
		long tiempoPrim = (tiempoFinal - tiempoInicial) / 1000;

		return tiempoPrim;
	}

	private void crearLblFlecha() {
		JLabel lblFlecha = new JLabel("---------------->");
		lblFlecha.setBounds(301, 117, 87, 14);
		frmPrincipal.getContentPane().add(lblFlecha);
	}

	private void crearBtnArmarRedSeguraKruskal() {
		btnArmarRedSeguraKruskal = new JButton("Armar red segura (Kruskal)");
		btnArmarRedSeguraKruskal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				armarRedSeguraKruskal();
			}
		});
		btnArmarRedSeguraKruskal.setEnabled(false);
		btnArmarRedSeguraKruskal.setBounds(52, 257, 230, 23);
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
		long tiempoInicial = System.currentTimeMillis();
		armarRedSeguraKruskal();
		long tiempoFinal = (System.currentTimeMillis());
		long tiempoKruskal = (tiempoFinal - tiempoInicial) / 1000;

		return tiempoKruskal;
	}

	private void iniciarTablaRedSegura(JScrollPane scrollPanelRedSegura) {
		tablaRedSegura = new JTable();
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
		modeloRedSegura.addColumn("Indice espia");
		modeloRedSegura.addColumn("Nombre espia");
		modeloRedSegura.addColumn("Compañero");
		modeloRedSegura.addColumn("Probab. intercepcion");
	}

	private JScrollPane iniciarScrollPanelEspias() {
		JScrollPane scrollPanelEspias = new JScrollPane();
		scrollPanelEspias.setBounds(38, 46, 203, 160);
		frmPrincipal.getContentPane().add(scrollPanelEspias);
		return scrollPanelEspias;
	}

	private JScrollPane iniciarScrollPanelRedSegura() {
		JScrollPane scrollPanelRedSegura = new JScrollPane();
		scrollPanelRedSegura.setBounds(432, 46, 445, 160);
		frmPrincipal.getContentPane().add(scrollPanelRedSegura);
		return scrollPanelRedSegura;
	}

	private DefaultTableModel iniciarTablaEspias(JScrollPane scrollPanelEspias) {
		tablaEspias = new JTable();
		tablaEspias.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		iniciarModeloTablaEspias();
		scrollPanelEspias.setViewportView(tablaEspias);
		tablaEspias.setBounds(38, 24, 139, 172);
		return modeloTablaEspias;
	}

	private void iniciarModeloTablaEspias() {
		modeloTablaEspias = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeloTablaEspias.addColumn("Nombres espias");
		tablaEspias.setModel(modeloTablaEspias);
	}

	private void crearLblComEspias() {
	}

	private void iniciarFrmPrincipal() {
		frmPrincipal = new JFrame();
		frmPrincipal.setResizable(false);
		frmPrincipal.setTitle("Comunicador de espias");
		frmPrincipal.setBounds(100, 100, 926, 440);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
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
