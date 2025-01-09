package view;

import javax.swing.JPanel;

import controller.Conexion;
import controller.CtrlPanResumen;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class PanResumen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNota;
	private JLabel lblAsignatura;
	private JButton btnCambiar, btnGuardar, btnAnterior, btnSiguiente;
	private CtrlPanResumen ctrlResumen;


	public PanResumen(Conexion conn) {
		setLayout(null);

		lblAsignatura = new JLabel("Asignatura:");
		lblAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAsignatura.setBounds(58, 88, 157, 30);
		add(lblAsignatura);

		txtNota = new JTextField();
		txtNota.setEditable(false);
		txtNota.setBounds(225, 88, 157, 30);
		add(txtNota);
		txtNota.setColumns(10);

		btnCambiar = new JButton("Cambiar nota");
		btnCambiar.setBounds(160, 180, 120, 23);
		add(btnCambiar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.setVisible(false);
		btnGuardar.setBounds(175, 146, 90, 23);
		add(btnGuardar);

		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(60, 180, 90, 23);
		add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(290, 180, 90, 23);
		add(btnSiguiente);

		setListeners();
	}

	private void setListeners() {
		btnCambiar.addActionListener(e -> {
			btnGuardar.setVisible(true);
			btnGuardar.setEnabled(true);
		});
		btnGuardar.addActionListener(e -> {
			ctrlResumen = new CtrlPanResumen();
			ctrlResumen.cambiarNotaAsignatura();
		});
	}
}
