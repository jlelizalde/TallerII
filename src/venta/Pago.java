package venta;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Pago {
private String fechaPago;
private int idVenta;
private double montoPago;

	public Pago(int idVenta, String fechaPago, double montoPago){
		this.idVenta = idVenta;
		this.fechaPago=fechaPago;
		this.montoPago=montoPago;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public double getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(double montoPago) {
		this.montoPago = montoPago;
	}
	
	public void regPago(int idVenta, String fecha, double montoPago, BDM bdm) throws SQLException{
		if (this.idVenta!=0 && !this.fechaPago.isEmpty()){
			String query2 = "INSERT INTO pagos (Venta_idVenta,fechaPago,montoPago) VALUES ('"+idVenta+"','"+fecha+"','"+montoPago+"')";
			bdm.getSt().executeUpdate(query2);
		}
	}
	
	public static ResultSet obtPagos(int idVenta, BDM bdm) throws SQLException{
		return bdm.getSt().executeQuery("SELECT idPago,fechaPago,montoPago FROM pagos where Venta_idVenta ="+idVenta);
	}
}
