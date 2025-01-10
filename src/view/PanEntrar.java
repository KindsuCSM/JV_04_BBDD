package view;

import javax.swing.*;

import controller.Conexion;
import controller.CtrlPanDetalle;
import controller.CtrlPanEntrar;

import java.awt.Font;
import java.sql.SQLException;

public class PanEntrar extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtUsuario;
    private JPasswordField pwdContrasenia;
    private JLabel lblUsuario, lblContrasenia;
    private JButton btnCancelar, btnAceptar;
    private CtrlPanEntrar ctrlPanelEntrar;
    private CtrlPanDetalle ctrlPanDetalle;
    private Conexion conexion;

    public PanEntrar(Conexion conn) {
        this.conexion = conn;

        setLayout(null);

        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblUsuario.setBounds(235, 45, 106, 14);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtUsuario.setBounds(235, 70, 170, 30);
        add(txtUsuario);
        txtUsuario.setColumns(10);

        lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblContrasenia.setBounds(235, 119, 106, 14);
        add(lblContrasenia);

        pwdContrasenia = new JPasswordField();
        pwdContrasenia.setBounds(235, 144, 170, 30);
        add(pwdContrasenia);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(225, 200, 89, 23);
        add(btnCancelar);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(325, 200, 89, 23);
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
            if (accesoCorrecto) {
                try {
                    ctrlPanelEntrar.actualizarPaneles((FrmPrincipal) SwingUtilities.getWindowAncestor(this));
                } catch (SQLException ex) {
                    System.out.println("No actualiza paneles");
                    throw new RuntimeException(ex);
                }
                borrarCampos();
            }
        });
    }

    private void borrarCampos() {
        txtUsuario.setText("");
        pwdContrasenia.setText("");
    }
}
