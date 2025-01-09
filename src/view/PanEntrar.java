package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Conexion;
import controller.CtrlPanEntrar;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

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
		lblUsuario.setBounds(130, 26, 106, 14);
		add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUsuario.setBounds(130, 51, 170, 30);
		add(txtUsuario);
		txtUsuario.setColumns(10);

		lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContrasenia.setBounds(130, 100, 106, 14);
		add(lblContrasenia);

		pwdContrasenia = new JPasswordField();
		pwdContrasenia.setBounds(130, 125, 170, 30);
		add(pwdContrasenia);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(120, 181, 89, 23);
		add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(220, 181, 89, 23);
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
			ctrlPanelEntrar.accesoUsuario(conexion, user, contrasenia);
		});
	}

	private void borrarCampos() {
		txtUsuario.setText("");
		pwdContrasenia.setText("");
	}
}
