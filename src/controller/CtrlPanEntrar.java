package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class CtrlPanEntrar {
	private String usuario;
	private String contrasenia;


	public boolean accesoUsuario(Conexion conn, String user, String pass) {
		String sql = "SELECT * FROM alumn WHERE user = ? AND password = ?";

		try {
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				this.usuario = user;
				this.contrasenia = pass;
				System.out.println("Inicio de sesión correcta");
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña no válidos.", "Error", JOptionPane.ERROR_MESSAGE);

				return false;
			}

		}catch(Exception e) {
			System.out.println("Error inicio sesión: " + e.getMessage());
			return false;
		}
	}

	public String getUsuario() {
		System.out.println("Usuario actual: " + usuario);
		return usuario;
	}

	public String getContrasenia() {
		System.out.println("Contraseña actual: " + contrasenia);
		return contrasenia;
	}
}
