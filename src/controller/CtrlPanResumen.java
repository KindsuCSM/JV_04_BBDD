package controller;

import model.Asignatura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CtrlPanResumen {
	private ArrayList<Asignatura> asignaturas;
	private Integer alumn_id;
	private CtrlPanEntrar datos;
	private Conexion conn;

	public CtrlPanResumen(Conexion conexion) {
		this.conn = conexion;
		this.asignaturas = new ArrayList<>();
	}

	public void cambiarNotaAsignatura() {

	}

	private ArrayList<Asignatura> getAsignaturas() {
		ArrayList<Asignatura> lstAsignaturas = new ArrayList<Asignatura>();
		String codigo;
		String nombre;
		double nota;
		int alumn_id;
		String sql = "SELECT * FROM subject WHERE alumn_id = ?";
		datos = new CtrlPanEntrar();

		try{
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setInt(1, datos.getAlumn_id());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				codigo = rs.getString("code_subject");
				nombre = rs.getString("name");
				nota = rs.getDouble("score");
				alumn_id = rs.getInt("alumn_id");

				lstAsignaturas.add(new Asignatura(codigo, nombre, nota, alumn_id));
			}
		} catch (Exception e) {
			System.out.println("Error cambiar nota: " + e.getMessage());
		}
		return lstAsignaturas;
	}
}
