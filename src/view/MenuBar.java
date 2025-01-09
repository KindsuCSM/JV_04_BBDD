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
		/*if (e.getSource() == itemPrincipal) {
            cambiarPanel(panPrincipal);
            frmPrincipal.setTitle("Menu principal");
        } else if (e.getSource() == itemAgregarCuentaAhorro) {
            cambiarPanel(panAddCA);
            frmPrincipal.setTitle("Agregar cuenta de ahorros");*/

	}
}