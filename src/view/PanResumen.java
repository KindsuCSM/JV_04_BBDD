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
	public JTextField txtNota;
	public JLabel lblAsignatura;
	public JButton btnCambiar, btnGuardar, btnAnterior, btnSiguiente, btnPrimero, btnUltimo;
	private CtrlPanResumen ctrlResumen;
	private Conexion con;


	public PanResumen(Conexion conn, Integer alumn_id) {
		setLayout(null);
		this.con = conn;

		lblAsignatura = new JLabel("Asignatura:");
		lblAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAsignatura.setBounds(150, 90, 157, 30);
		add(lblAsignatura);

		txtNota = new JTextField();
		txtNota.setEditable(false);
		txtNota.setBounds(317, 90, 157, 30);
		add(txtNota);
		txtNota.setColumns(10);

		btnCambiar = new JButton("Cambiar nota");
		btnCambiar.setBounds(250, 165, 120, 23);
		add(btnCambiar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.setVisible(false);
		btnGuardar.setBounds(265, 131, 90, 23);
		add(btnGuardar);

		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(150, 165, 90, 23);
		add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(380, 165, 90, 23);
		add(btnSiguiente);

		btnPrimero = new JButton("Primero");
		btnPrimero.setBounds(218, 195, 89, 23);
		add(btnPrimero);

		btnUltimo = new JButton("Último");
		btnUltimo.setBounds(317, 195, 89, 23);
		add(btnUltimo);

		ctrlResumen = new CtrlPanResumen(this, con, alumn_id);
		ctrlResumen.setData();

		setListeners();
	}

	private void setListeners() {
		btnCambiar.addActionListener(e -> {
			ableGuardar(true);
		});
		btnGuardar.addActionListener(e -> {
			ctrlResumen.cambiarNotaAsignatura();
			ableGuardar(false);
		});
		btnAnterior.addActionListener(e -> {
			ctrlResumen.anteriorAsignatura();
		});
		btnSiguiente.addActionListener(e -> {
			ctrlResumen.siguienteAsignatura();
		});
		btnUltimo.addActionListener(e -> {
			ctrlResumen.ultimaAsignatura();
		});
		btnPrimero.addActionListener(e -> {
			ctrlResumen.primeraAsignatura();
		});
	}

	private void ableGuardar(Boolean esEditable){
		btnGuardar.setVisible(esEditable);
		btnGuardar.setEnabled(esEditable);
		txtNota.setEditable(esEditable);
		txtNota.setEnabled(esEditable);
	}
}
