package view;

import javax.swing.*;

import controller.Conexion;
import controller.CtrlPanEntrar;

import java.awt.Font;

public class PanEntrar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField pwdContrasenia;
	private JLabel lblUsuario, lblContrasenia;
	private JButton btnCancelar, btnAceptar;
	private CtrlPanEntrar ctrlPanelEntrar;
	private Conexion conexion;

	public PanEntrar(Conexion conn) {
		this.conexion = conn;

		setLayout(null);

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsuario.setBounds(200, 46, 106, 14);
		add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUsuario.setBounds(200, 71, 170, 30);
		add(txtUsuario);
		txtUsuario.setColumns(10);

		lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContrasenia.setBounds(200, 120, 106, 14);
		add(lblContrasenia);

		pwdContrasenia = new JPasswordField();
		pwdContrasenia.setBounds(200, 145, 170, 30);
		add(pwdContrasenia);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(190, 201, 89, 23);
		add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(290, 201, 89, 23);
		add(btnAceptar);

		setListeners();
	}

	private void setListeners() {
		btnCancelar.addActionListener(e -> {
			borrarCampos();
		});

		btnAceptar.addActionListener(e -> {
			String user = txtUsuario.getText();
			String contrasenia = new String(pwdContrasenia.getPassword());

			ctrlPanelEntrar = new CtrlPanEntrar();
			boolean accesoCorrecto = ctrlPanelEntrar.accesoUsuario(conexion, user, contrasenia, (FrmPrincipal) SwingUtilities.getWindowAncestor(this));

			if(accesoCorrecto){
				borrarCampos();
			}
		});
	}

	private void borrarCampos() {
		txtUsuario.setText("");
		pwdContrasenia.setText("");
	}
}
