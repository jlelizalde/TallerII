package pruebas;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Fontetizar extends JFrame implements ItemListener{
	private JTextField texto;
	private JCheckBox negrita, cursiva;
	private Font fuente;
	
	public Fontetizar(){
		super("Fontetizar");
		
		this.setLayout(new FlowLayout());
		this.definirVentana();
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void definirVentana(){
		texto = new JTextField(20);
		negrita = new JCheckBox("Negrita");
		cursiva = new JCheckBox("Cursiva");
		add(texto);
		add(cursiva);
		add(negrita);
		negrita.addItemListener(this);
		cursiva.addItemListener(this);
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(negrita.isSelected() && cursiva.isSelected()){
			fuente = new Font("Serief",Font.BOLD|Font.ITALIC,14);
			texto.setFont(fuente);
		}else if (cursiva.isSelected()){
			fuente = new Font("Serief",Font.ITALIC,14);
			texto.setFont(fuente);
		}else if(negrita.isSelected()){
			fuente = new Font("Serief",Font.BOLD,14);
			texto.setFont(fuente);
		}else{
			fuente = new Font("Serief",Font.PLAIN,14);
			texto.setFont(fuente);
		}
		
	}
	
	

}
