package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.Conexion;
import controller.CtrlPanResumen;

import java.awt.*;
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
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Resumen de asignaturas", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)));
        this.con = conn;
        // Label que irá cambiando segun el nombre de la asignatura que recojamos
        lblAsignatura = new JLabel("XXXXXXXXXX");
        lblAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblAsignatura.setBounds(317, 49, 157, 30);
        add(lblAsignatura);

        // TextField que irá cambiando con el nombre de la asignatura
        txtNota = new JTextField();
        txtNota.setEditable(false);
        txtNota.setBounds(317, 90, 157, 30);
        add(txtNota);
        txtNota.setColumns(10);

        // Botón para cambiar la nota, lo que hace es habilitar los botones de guardar y cancelar
        btnCambiar = new JButton("Cambiar nota");
        btnCambiar.setBounds(250, 188, 120, 23);
        add(btnCambiar);

        // Botón que cancela el cambio de asignatura y pone la nota que estaba
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.setVisible(false);
        btnCancelar.setBounds(216, 154, 90, 23);
        add(btnCancelar);

        // Botón que guarda la nueva nota en la base de datos
        btnGuardar = new JButton("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.setVisible(false);
        btnGuardar.setBounds(316, 154, 90, 23);
        add(btnGuardar);

        // Botón para ir a la anterior asignatura
        btnAnterior = new JButton("Anterior");
        btnAnterior.setBounds(150, 188, 90, 23);
        add(btnAnterior);
        // Botón para ir a la siguiente asignatura
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(380, 188, 90, 23);
        add(btnSiguiente);
        // Botón para ir directamente a la primera asignatura de la lista
        btnPrimero = new JButton("Primero");
        btnPrimero.setBounds(218, 222, 89, 23);
        add(btnPrimero);
        // Botón para ir directamente a la ultima asignatura de la lista
        btnUltimo = new JButton("Último");
        btnUltimo.setBounds(317, 222, 89, 23);
        add(btnUltimo);
        // Titulos asignatura y nota
        lblTitleAsignatura = new JLabel("Asignatura:");
        lblTitleAsignatura.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitleAsignatura.setBounds(150, 49, 157, 30);
        add(lblTitleAsignatura);

        lblTitleNota = new JLabel("Nota:");
        lblTitleNota.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTitleNota.setBounds(150, 90, 157, 30);
        add(lblTitleNota);

        ctrlResumen = new CtrlPanResumen(this, con, alumn_id);
        ctrlResumen.setData();

        setListeners();
    }

    // Listeners de cada botón
    private void setListeners() {
        btnCambiar.addActionListener(e -> {
            ableGuardar(true);
        });
        // Si cancela, recoge la nota que hay en la bd y la vuelve a poner en el txt
        btnCancelar.addActionListener(e -> {
            ctrlResumen.recogerNota();
            ableGuardar(false);
        });
        // Guarda la nota que ha introducido el usuario en la base de datos
        btnGuardar.addActionListener(e -> {
            try {
                ctrlResumen.cambiarNotaAsignatura();
            } catch (SQLException ex) {
                System.out.println("Error cambiar nota: " + ex);
            }
            ableGuardar(false);
        });
        // Botones para pasar entre asignaturas
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

    // Activar que sean visibles o no los botones de guardar y cancelar la nota
    private void ableGuardar(Boolean esEditable) {
        btnGuardar.setVisible(esEditable);
        btnGuardar.setEnabled(esEditable);
        txtNota.setEditable(esEditable);
        txtNota.setEnabled(esEditable);
        btnCancelar.setVisible(esEditable);
        btnCancelar.setEnabled(esEditable);
    }
}
