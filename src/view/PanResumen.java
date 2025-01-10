package view;

import javax.swing.JPanel;

import controller.Conexion;
import controller.CtrlPanResumen;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.SQLException;

public class PanResumen extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField txtNota;
	public JLabel lblAsignatura, lblTitleAsignatura, lblTitleNota;
	public JButton btnCambiar, btnGuardar, btnCancelar, btnAnterior, btnSiguiente, btnPrimero, btnUltimo;
	private CtrlPanResumen ctrlResumen;
	private Conexion con;


	public PanResumen(Conexion conn, Integer alumn_id) {
		setLayout(null);
		this.con = conn;

		lblAsignatura = new JLabel("XXXXXXXXXX");
		lblAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAsignatura.setBounds(317, 49, 157, 30);
		add(lblAsignatura);

		txtNota = new JTextField();
		txtNota.setEditable(false);
		txtNota.setBounds(317, 90, 157, 30);
		add(txtNota);
		txtNota.setColumns(10);

		btnCambiar = new JButton("Cambiar nota");
		btnCambiar.setBounds(250, 188, 120, 23);
		add(btnCambiar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setVisible(false);
		btnCancelar.setBounds(216, 154, 90, 23);
		add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.setVisible(false);
		btnGuardar.setBounds(316, 154, 90, 23);
		add(btnGuardar);


		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(150, 188, 90, 23);
		add(btnAnterior);

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(380, 188, 90, 23);
		add(btnSiguiente);

		btnPrimero = new JButton("Primero");
		btnPrimero.setBounds(218, 222, 89, 23);
		add(btnPrimero);

		btnUltimo = new JButton("Último");
		btnUltimo.setBounds(317, 222, 89, 23);
		add(btnUltimo);

		JLabel lblTitleAsignatura = new JLabel("Asignatura:");
		lblTitleAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitleAsignatura.setBounds(150, 49, 157, 30);
		add(lblTitleAsignatura);

		JLabel lblTitleNota = new JLabel("Nota:");
		lblTitleNota.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitleNota.setBounds(150, 90, 157, 30);
		add(lblTitleNota);

		ctrlResumen = new CtrlPanResumen(this, con, alumn_id);
		ctrlResumen.setData();

		setListeners();
	}

	private void setListeners() {
		btnCambiar.addActionListener(e -> {
			ableGuardar(true);
		});
		btnCancelar.addActionListener(e -> {
			ctrlResumen.recogerNota();
			ableGuardar(false);
		});
		btnGuardar.addActionListener(e -> {
            try {
                ctrlResumen.cambiarNotaAsignatura();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
		btnCancelar.setVisible(esEditable);
		btnCancelar.setEnabled(esEditable);
	}
}
