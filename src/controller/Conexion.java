package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	private static String bd = "gestor_alumno_asignatura";
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String user = "root";
	private static String password = "";
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static Connection conn;

	public Conexion() {}
	public static Connection conectar() {
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
			if(conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("La conexión se ha cerrado con éxito");
			}
		} catch (SQLException e) {
			System.out.println("Error desconexión: " + e.getMessage());
		}
	}

	public static Statement obtenerStatementResumen() throws SQLException {
		return conectar().createStatement();
	}

	public Connection getConnection() {
		return conn;
	}


}
