package controller;

import view.FrmPrincipal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class CtrlPanEntrar {
	private String usuario;
	private String contrasenia;
	private Integer alumn_id;
	private Conexion con;

	public boolean accesoUsuario(Conexion conn, String user, String pass, FrmPrincipal frmPrincipal) {
		this.con = conn;
		String sqlUsuarioContra = "SELECT * FROM alumn WHERE user = ? AND password = ?";
		String sqlObtenerId = "SELECT alumn_id FROM alumn WHERE user = ? AND password = ?";

		try {
			PreparedStatement ps = conn.getConnection().prepareStatement(sqlUsuarioContra);
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				this.usuario = user;
				this.contrasenia = pass;

				ps = conn.getConnection().prepareStatement(sqlObtenerId);
				ps.setString(1, usuario);
				ps.setString(2, contrasenia);

				rs = ps.executeQuery();

				if(rs.next()) {
					this.alumn_id = rs.getInt("alumn_id");
					JOptionPane.showMessageDialog(null, "Bienvenido " + user + ", sesion iniciada!", "Inicio Correcto", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Alumno: " + usuario + ", Contraseña: " + contrasenia + ", id: " + alumn_id);
					activarBotones(frmPrincipal);
					return true;
				}
			}else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña no válidos.", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}

		}catch(Exception e) {
			System.out.println("Error inicio sesión: " + e.getMessage());
			return false;
		}
        return false;
    }

	public Integer getAlumn_id() {
		return alumn_id;
	}
	public String getUsuario() {
		System.out.println("Usuario actual: " + usuario);
		return usuario;
	}
	public String getContrasenia() {
		System.out.println("Contraseña actual: " + contrasenia);
		return contrasenia;
	}
	public void activarBotones(FrmPrincipal frmPrincipal) {
		frmPrincipal.activarBotones();
	}
}
