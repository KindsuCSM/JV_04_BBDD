package view;

import controller.Conexion;
import controller.CtrlPanEntrar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuBar implements ActionListener {
	private JMenuBar menuBar;
	private JMenu mnValidar, mnVisualizar, mnAcercaDe;
	private JMenuItem itmEntrar, itmSalir, itmDetalle, itmResumen, itmAcerdaDe;
	private FrmPrincipal frmPrincipal;
	private JPanel panDetalle, panEntrar, panResumen;
	private Conexion con;
	private CtrlPanEntrar ctrlEntrar;

	public MenuBar(FrmPrincipal frmPrincipal, PanDetalle panDetalle, PanEntrar panEntrar, PanResumen panResumen, Conexion con) {

		this.frmPrincipal = frmPrincipal;
		this.panDetalle = panDetalle;
		this.panEntrar = panEntrar;
		this.panResumen = panResumen;
		this.con = con;

	}

	public JMenuBar mnBar() {
		menuBar = new JMenuBar();

		mnValidar = new JMenu("Validar");
		mnVisualizar = new JMenu("Visualizar");
		mnAcercaDe = new JMenu("Acerca de...");

		itmEntrar = new JMenuItem("Entrar");
		itmSalir = new JMenuItem("Salir");
		itmDetalle = new JMenuItem("Detalle");
		itmResumen = new JMenuItem("Resumen");
		itmAcerdaDe = new JMenuItem("Acerca de...");

		mnValidar.add(itmEntrar);
		mnValidar.add(itmSalir);

		mnVisualizar.add(itmDetalle);
		mnVisualizar.add(itmResumen);
		mnAcercaDe.add(itmAcerdaDe);

		itmEntrar.addActionListener(this);
		itmSalir.addActionListener(this);
		itmDetalle.addActionListener(this);
		itmResumen.addActionListener(this);
		itmAcerdaDe.addActionListener(this);

		menuBar.add(mnValidar);
		menuBar.add(mnVisualizar);
		menuBar.add(mnAcercaDe);

		mnVisualizar.setEnabled(false);

		return menuBar;
	}

	public void activarBotones(){
		mnVisualizar.setEnabled(true);
	}

	public void actualizarItems(JPanel panel){
		panDetalle = panel;
	}

	public void actualizarResumen(JPanel panel) {
		panResumen = panel;
	}
	
	private void cambiarPanel(JPanel panelNuevo) {
		frmPrincipal.getContentPane().removeAll();
		frmPrincipal.getContentPane().add(panelNuevo, BorderLayout.CENTER);
		frmPrincipal.revalidate();
		frmPrincipal.repaint();
	}

	private void mostrarAcercaDe(){
		JDialog acercaDeDialog = new JDialog((JFrame) null, "Acerca de", true);
		acercaDeDialog.setSize(400, 300);
		acercaDeDialog.setLocationRelativeTo(null); // Centrar en la pantalla
		acercaDeDialog.setLayout(new BorderLayout());

		// Panel para mostrar la información
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		JLabel titleLabel = new JLabel("Aplicacion para gestion de alumnos.");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel authorsLabel = new JLabel("Autores: Cristina, Julian");
		authorsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel versionLabel = new JLabel("Versión: 1.0");
		versionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel dateLabel = new JLabel("Fecha: Enero 2025");
		dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		infoPanel.add(Box.createVerticalStrut(20)); // Espaciado
		infoPanel.add(titleLabel);
		infoPanel.add(Box.createVerticalStrut(10));
		infoPanel.add(authorsLabel);
		infoPanel.add(Box.createVerticalStrut(10));
		infoPanel.add(versionLabel);
		infoPanel.add(Box.createVerticalStrut(10));
		infoPanel.add(dateLabel);
		infoPanel.add(Box.createVerticalGlue()); // Ajustar espacio

		acercaDeDialog.add(infoPanel, BorderLayout.CENTER);

		// Panel para el botón "Aceptar"
		JPanel buttonPanel = new JPanel();
		JButton aceptarButton = new JButton("Aceptar");

		aceptarButton.addActionListener(e -> acercaDeDialog.dispose()); // Cierra el diálogo

		buttonPanel.add(aceptarButton);
		acercaDeDialog.add(buttonPanel, BorderLayout.SOUTH);

		// Hacer visible el JDialog
		acercaDeDialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == itmEntrar) {
			cambiarPanel(panEntrar);
			frmPrincipal.setTitle("Entrar");
		} else if (e.getSource() == itmAcerdaDe) {
			mostrarAcercaDe();
		} else if (e.getSource() == itmDetalle) {
            cambiarPanel(panDetalle);
            frmPrincipal.setTitle("Detalle");
        } else if (e.getSource() == itmResumen) {
        	cambiarPanel(panResumen);
        	frmPrincipal.setTitle("Resumen");
        } else if (e.getSource() == itmSalir) {
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
			if(respuesta == JOptionPane.YES_OPTION) {
				con.desconectar();
				System.exit(0);
			}
		}
	}
}