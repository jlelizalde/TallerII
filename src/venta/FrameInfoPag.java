package venta;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class FrameInfoPag extends JFrame{
	private JLabel lblSumaTotTxt, lblSumaTotRs, lblTotalRestTxt, lblTotRestRs, lblabonoNuevo;
	private JButton regAbono;
	private int idVenta;
	private BDM bdm;
	JFormattedTextField abono;
	private final String[] titulos = {"No. Pago","Monto","Fecha"};
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTable pagosTable = new JTable (dtm){
		public boolean isCellEditable(int Row, int vColIndex){
			return false;
		}
	};
	private JScrollPane scroll;
	private List<Integer> ids = new ArrayList<Integer>();
	private double totalCot;
	private double sumaPagos;
	
	public FrameInfoPag(int idVenta){
		super("Historial Ventas");
		this.idVenta = idVenta;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(490,550);
		bdm = new BDM();
		this.obtDatos();
		this.setVisible(true);
	}
	
	public void obtDatos(){
		totalCot=0;
		sumaPagos=0;
		ResultSet aux2 = null;
		try {
			aux2=((Cotizacion.obtTotal(idVenta, bdm)));
			while(aux2.next()){
				totalCot=(Double)aux2.getObject(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//lleno la tabla de pagos
		pagosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
		dtm.setColumnIdentifiers(titulos);
		ids.clear();
		try {
			ResultSet aux = null;
			//obtener pagos de una venta
				aux = Pago.obtPagos(idVenta, bdm);
				int numPag=1;
				while(aux.next()){
					Object[] fila ={numPag++, aux.getObject(3), aux.getObject(2)};
					sumaPagos=sumaPagos+(Double)aux.getObject(3);
					ids.add((Integer)aux.getObject(1));
					dtm.addRow(fila);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(sumaPagos);
		regAbono = new JButton("Registrar Abono");
		//Registro nuevo abono a la BD
		regAbono.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Registrar el abono 
			}
		});
		scroll = new JScrollPane(pagosTable);
		scroll.setBounds(0,0,800,400);
		lblSumaTotTxt = new JLabel("Suma Total: ");
		lblSumaTotRs = new JLabel(String.valueOf(sumaPagos));
		lblTotalRestTxt = new JLabel("Total Restante: ");
		double totRest = totalCot-sumaPagos;
		lblTotRestRs = new JLabel(String.valueOf(totRest));
		this.add(scroll,BorderLayout.PAGE_START);
		this.add(regAbono,BorderLayout.PAGE_END);
		JPanel pan = new JPanel(new FlowLayout());
		pan.add(lblSumaTotTxt);
		pan.add(lblSumaTotRs);
		pan.add(lblTotalRestTxt);
		pan.add(lblTotRestRs);
		this.add(pan,BorderLayout.CENTER);
	}
	
	

}
