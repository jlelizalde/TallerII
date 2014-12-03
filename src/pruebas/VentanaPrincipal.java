package pruebas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class VentanaPrincipal  extends JFrame implements ActionListener{
	
	private JButton jbtn_boton;
	private JTextField jtxt_texto;
	private JLabel jlbl_label; 
	
	public VentanaPrincipal(){
		super("Mi ventana");
		this.defVentana();
		
		this.setResizable(false);
		//this.setLocationRelativeTo(null);
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void defVentana(){
		this.setLayout(new FlowLayout());
		jbtn_boton = new JButton("enviar");
		jtxt_texto = new JTextField(20);
		jlbl_label = new JLabel();
		this.add(jtxt_texto);
		this.add(jbtn_boton);
		this.add(jlbl_label);
		jbtn_boton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jbtn_boton){
			jlbl_label.setText(jtxt_texto.getText());
		}
		
	}

}
