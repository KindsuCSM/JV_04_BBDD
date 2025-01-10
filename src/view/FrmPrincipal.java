package view;

import controller.Conexion;
import controller.CtrlPanEntrar;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrmPrincipal extends JFrame {


    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
	private JPanel panInicio;
    private PanDetalle panDetalle;
    private PanEntrar panEntrar;
    private PanResumen panResumen;
    private MenuBar mnBar;
	Conexion conn;

	public FrmPrincipal() throws SQLException {
		conn = new Conexion();
		conn.conectar();

		setTitle("Notas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400);
		setLocationRelativeTo(null);

		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		panInicio = new JPanel();
		panInicio.add(new JLabel("Inicia sesion para empezar..."));
		//panDetalle = new PanDetalle();
		panEntrar = new PanEntrar(conn);
		panResumen = new PanResumen(conn);

        mnBar = new MenuBar(this, panDetalle, panEntrar, panResumen, conn);

        setJMenuBar(mnBar.mnBar());

		contentPane.add(panInicio, BorderLayout.CENTER);

		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                conn.desconectar();
                System.exit(0);
            }
        });

		setVisible(true);
	}

	public void activarBotones() {
		mnBar.activarBotones();
	}

	public void actualizarMenu(int alumn_id) throws SQLException {
		panDetalle = new PanDetalle(alumn_id);
		mnBar.actualizarItems(panDetalle);
	}
}
