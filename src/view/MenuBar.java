package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuBar implements ActionListener {
	private JMenuBar menuBar;
	private JMenu mnValidar, mnVisualizar, mnAcercaDe;
	private JMenuItem itmEntrar, itmSalir, itmDetalle, itmResumen;
	private FrmPrincipal frmPrincipal;
	private JPanel panDetalle, panEntrar, panResumen;

	public MenuBar(FrmPrincipal frmPrincipal, PanDetalle panDetalle, PanEntrar panEntrar, PanResumen panResumen) {
		this.frmPrincipal = frmPrincipal;
		this.panDetalle = panDetalle;
		this.panEntrar = panEntrar;
		this.panResumen = panResumen;
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

		mnValidar.add(itmEntrar);
		mnValidar.add(itmSalir);

		mnVisualizar.add(itmDetalle);
		mnVisualizar.add(itmResumen);

		itmEntrar.addActionListener(this);
		itmSalir.addActionListener(this);
		itmDetalle.addActionListener(this);
		itmResumen.addActionListener(this);

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
        } else if (e.getSource() == itmDetalle) {
            cambiarPanel(panDetalle);
            frmPrincipal.setTitle("Detalle");
        } else if (e.getSource() == itmResumen) {
        	cambiarPanel(panResumen);
        	frmPrincipal.setTitle("Resumen");
        }

	}
}