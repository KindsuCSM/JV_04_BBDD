package view;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class PanAcercaDe extends JPanel {


	@Serial
    private static final long serialVersionUID = 1L;


	public PanAcercaDe() {
		setLayout(new BorderLayout(1,1));
		addComponents();
	}

	private void addComponents() {


		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel titleLabel = new JLabel("Acerca de la Aplicacion");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JTextArea descriptionLabel = new JTextArea( "Aplicacion realizada y producida por Cristina y Julian S.L. V0.01");
		descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		descriptionLabel.setLineWrap(true);
		descriptionLabel.setWrapStyleWord(true);
		descriptionLabel.setEditable(false);

		infoPanel.add(titleLabel);
		infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		infoPanel.add(descriptionLabel);

		JButton donateBotton = new JButton("Donar Abrazo!");
		donateBotton.setAlignmentX(Component.CENTER_ALIGNMENT);
		donateBotton.addActionListener(e -> JOptionPane.showMessageDialog(
				this,
				"HEMOS RECIBIDO TU ABRAZO, GRACIAS!",
				"GRACIAS",
				JOptionPane.INFORMATION_MESSAGE
		));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(donateBotton);

		add(infoPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

}
