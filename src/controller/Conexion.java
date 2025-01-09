package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private String bd = "gestor_alumno_asignatura";
	private String url = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String password = "";
	private String driver = "com.mysql.cj.jdbc.Driver";
	private Connection conn;

	public Conexion() {}
	public Connection conectar() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url + bd, user, password);
			System.out.println("Conexion realizada con éxito");
		} catch (ClassNotFoundException | SQLException error) {
			System.out.println("No se pudo conectar a la base de datos. ERROR : " + error.getMessage());
		}
		return conn;
	}

	public void desconectar() {
		try {
			conn.close();
			System.out.println("La conexión se ha cerrado con éxito");
		} catch (SQLException e) {
			System.out.println("Error desconexión: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
