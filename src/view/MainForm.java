package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

public class MainForm {

	private JFrame frmPrincipal;
	private JTable tablaEspias;
	private JTable tablaRedSegura;

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
		frmPrincipal = new JFrame();
		frmPrincipal.setBounds(100, 100, 584, 326);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
		
		JLabel lblComEspias = new JLabel("Comunicador de espias");
		lblComEspias.setHorizontalAlignment(SwingConstants.CENTER);
		lblComEspias.setBounds(184, 11, 139, 14);
		frmPrincipal.getContentPane().add(lblComEspias);
		
		// Definimos los modelos de las dos tablas (de espias y red segura)
		
		DefaultTableModel modeloTablaEspias = new DefaultTableModel();
		modeloTablaEspias.addColumn("Nombre espia");
		modeloTablaEspias.addRow(new Object[] {"Juan"});
		modeloTablaEspias.addRow(new Object[] {"PEPE"});
		modeloTablaEspias.addRow(new Object[] {"osvaldo"});
		modeloTablaEspias.addRow(new Object[] {"roque"});
		
		
		DefaultTableModel modeloRedSegura = new DefaultTableModel();
		modeloRedSegura.addColumn("Nombre espia");
		modeloRedSegura.addColumn("Compañero");
		modeloRedSegura.addColumn("Probab. intercepcion");
		
		JScrollPane scrollPanelEspias = new JScrollPane();
		scrollPanelEspias.setBounds(10, 36, 214, 160);
		frmPrincipal.getContentPane().add(scrollPanelEspias);
		
		tablaEspias = new JTable();
		tablaEspias.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tablaEspias.setModel(modeloTablaEspias);
		scrollPanelEspias.setViewportView(tablaEspias);
		tablaEspias.setBounds(38, 24, 139, 172);

		
		JScrollPane scrollPanelRedSegura = new JScrollPane();
		scrollPanelRedSegura.setBounds(317, 36, 224, 160);
		frmPrincipal.getContentPane().add(scrollPanelRedSegura);
		
		tablaRedSegura = new JTable();
		tablaRedSegura.setModel(modeloRedSegura);
		tablaRedSegura.setBounds(259, 24, 139, 172);
		scrollPanelRedSegura.setViewportView(tablaRedSegura);

		
		JButton btnArmarRedSegura = new JButton("Armar red segura");
		btnArmarRedSegura.setBounds(213, 207, 139, 23);
		frmPrincipal.getContentPane().add(btnArmarRedSegura);
		
		JLabel lblFlecha = new JLabel("------------->");
		lblFlecha.setBounds(234, 112, 73, 14);
		frmPrincipal.getContentPane().add(lblFlecha);
		
	
	}
}
