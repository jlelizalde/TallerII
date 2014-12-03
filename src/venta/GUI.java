package venta;

import java.awt.FlowLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class GUI extends JFrame{
	
	private final String[] titulos = {"idCotizacion","Cliente", "Lugar", "Fecha"};
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTable cotTable = new JTable (dtm){
		public boolean isCellEditable(int Row, int vColIndex){
			return false;
		}
	};
	private BDM bdm = new BDM();
	private JScrollPane scroll;
	private JLabel busqueda;
	private JTextField cliente;
	private JButton buscar;
	private JButton registVenta;
	private FrameInfoCot ficot;
	
	private List<Integer> ids = new ArrayList<Integer>();
	
	
	public GUI(){
		super ("Generar Venta");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setSize(510, 510);
		this.setResizable(false);
		this.addComponents();
		this.setVisible(true);
	}
	
	public void busqCotizaciones(){
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
		dtm.setColumnIdentifiers(titulos);
		ids.clear();
		try {
			ResultSet aux = null;
			if (cliente.getText()!=null && !cliente.getText().isEmpty()){
				//buscar cotizaciones por nombre de cliente
				aux = Cotizacion.buscarCotCli(cliente.getText(), bdm);
				while(aux.next()){
					Object[] fila ={aux.getObject(1), aux.getObject(2), aux.getObject(3), aux.getObject(4)};
					ids.add((Integer)aux.getObject(1));
					dtm.addRow(fila);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addComponents(){
		
		cotTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//dtm.setColumnIdentifiers(titulos);
		//cotTable.setEnabled(false);
		busqueda = new JLabel("Nombre del cliente: ");
		cliente = new JTextField(20);
		buscar = new JButton("Buscar");
		buscar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			busqCotizaciones();
			}
		});
		registVenta = new JButton("Registrar Venta");
		//Llamo a el resumen de la cotizacion
		registVenta.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int[] selecciones = cotTable.getSelectedRows();
				if (selecciones.length==1 ){
					int selectedObject = (Integer) cotTable.getModel().getValueAt(selecciones[0],0);
					ficot = new FrameInfoCot(selectedObject);
				}
				else{
					JOptionPane.showMessageDialog(ficot,"Debe seleccionar una cotización");
				}
			}
		});
		scroll = new JScrollPane(cotTable);
		scroll.setBounds(0,0,800,400);
		this.add(busqueda);
		this.add(cliente);
		this.add(buscar);
		this.add(scroll);
		this.add(registVenta);
		
	}

}