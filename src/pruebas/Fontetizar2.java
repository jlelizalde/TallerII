package pruebas;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Fontetizar2 extends JFrame implements ItemListener{
	private JTextField texto;
	private JRadioButton negrita, cursiva, ambos, plano;
	private Font fuente;
	private ButtonGroup grupo;
	
	public Fontetizar2(){
		super("Fontetizar2");
		
		this.setLayout(new FlowLayout());
		this.definirVentana();
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void definirVentana(){
		texto = new JTextField(20);
		negrita = new JRadioButton("Negrita");
		cursiva = new JRadioButton("Cursiva");
		ambos = new JRadioButton("Ambos");
		plano = new JRadioButton("Plano");
		grupo = new ButtonGroup();
		add(texto);
		add(negrita);
		add(cursiva);
		add(ambos);
		add(plano);
		
		grupo.add(negrita);
		grupo.add(cursiva);
		grupo.add(ambos);
		grupo.add(plano);
		
		
		
		negrita.addItemListener(this);
		cursiva.addItemListener(this);
		ambos.addItemListener(this);
		plano.addItemListener(this);
		
	}
	
	private Font obtenerFuente(int n){
		Font resultado = null;
		switch(n){
		case 0:
			resultado = new Font("Serief",Font.BOLD,14);
			break;
		case 1:
			resultado = new Font("Serief",Font.ITALIC,14);
			break;
		case 2:
			resultado = new Font("Serief",Font.BOLD|Font.ITALIC,14);
			break;
		case 3:
			resultado = new Font("Serief",Font.PLAIN,14);
		}
		return resultado;
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(negrita.isSelected() ){
			texto.setFont(obtenerFuente(0));
		}else if (cursiva.isSelected()){
			texto.setFont(obtenerFuente(1));
		}else if(ambos.isSelected()){
			texto.setFont(obtenerFuente(2));
		}else if (plano.isSelected()){
			texto.setFont(obtenerFuente(3));
		}
		
	}
	
	

}
