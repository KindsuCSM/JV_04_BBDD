package view;

import controller.Conexion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuBar implements ActionListener {
	private JMenuBar menuBar;
	private JMenu mnValidar, mnVisualizar, mnAcercaDe;
	private JMenuItem itmEntrar, itmSalir, itmDetalle, itmResumen, itmAcerdaDe;
	private FrmPrincipal frmPrincipal;
<<<<<<< Updated upstream
	private JPanel panDetalle, panEntrar, panResumen;
	private Conexion con;

	public MenuBar(FrmPrincipal frmPrincipal, PanDetalle panDetalle, PanEntrar panEntrar, PanResumen panResumen, Conexion con) {
=======
	private JPanel panDetalle, panEntrar, panResumen, panAcercaDe;

	public MenuBar(FrmPrincipal frmPrincipal, PanDetalle panDetalle, PanEntrar panEntrar, PanResumen panResumen, PanAcercaDe panAcercaDe) {
>>>>>>> Stashed changes
		this.frmPrincipal = frmPrincipal;
		this.panDetalle = panDetalle;
		this.panEntrar = panEntrar;
		this.panResumen = panResumen;
<<<<<<< Updated upstream
		this.con = con;
=======
		this.panAcercaDe = panAcercaDe;
>>>>>>> Stashed changes
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


		return menuBar;
	}

	private void cambiarPanel(JPanel panelNuevo) {
		frmPrincipal.getContentPane().removeAll();
		frmPrincipal.getContentPane().add(panelNuevo, BorderLayout.CENTER);
		frmPrincipal.revalidate();
		frmPrincipal.repaint();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == itmEntrar) {
			cambiarPanel(panEntrar);
			frmPrincipal.setTitle("Entrar");
		} else if (e.getSource() == itmAcerdaDe) {
			cambiarPanel(panAcercaDe);
			frmPrincipal.setTitle("Detalle");
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