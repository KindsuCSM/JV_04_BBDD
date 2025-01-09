package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class CtrlPanEntrar {
    private String usuario;
	private String contrasenia;

	public boolean accesoUsuario(Conexion conn, String usuario, String contrasenia) {
		String sql = "SELECT * FROM alumn WHERE user = ? AND password = ?";

		try {
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, contrasenia);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
                this.usuario = usuario;
				this.contrasenia = contrasenia;
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
		return usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}
}
