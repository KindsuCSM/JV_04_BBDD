package view;

import controller.Conexion;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrmPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private PanDetalle panDetalle;
    private PanEntrar panEntrar;
    private PanResumen panResumen;
    private MenuBar mnBar;
	Conexion conn;

    public FrmPrincipal() {
        conn = new Conexion();
		conn.conectar();

        setTitle("Notas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        panDetalle = new PanDetalle();
        panEntrar = new PanEntrar();
        panResumen = new PanResumen();

        mnBar = new MenuBar(this, panDetalle, panEntrar, panResumen);
        setJMenuBar(mnBar.mnBar());

        contentPane.add(panEntrar, BorderLayout.CENTER);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                conn.desconectar();
                System.exit(0);
            }
        });

        setVisible(true);
    }

	public Conexion getConn() {
		return conn;
	}
}