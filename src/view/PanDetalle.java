package view;

import controller.CtrlPanDetalle;
import model.Alumno;
import model.Asignatura;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.IconView;
import java.awt.*;
import java.io.Serial;
import java.sql.SQLException;

public class PanDetalle extends JPanel {

	private JTextField numeroField;
	private JTextField usuarioField;
	private JPasswordField contraseñaField;
	private JTextField fechaNacimientoField;
	private JTextField notaMediaField;
	private JLabel imagenLabel;
	private JList<String> asignaturasList;
	private JButton calcularMediaButton;
	private CtrlPanDetalle ctrlPanDetalle;

	@Serial
    private static final long serialVersionUID = 1L;
	
	public PanDetalle(int alumn_id) throws SQLException {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
				"Información del Alumno", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14)));

		// Panel principal para dividir datos y lista
		JPanel mainPanel = new JPanel(new GridLayout(1, 2));

		// Panel izquierdo para los datos del alumno
		JPanel datosPanel = new JPanel();
		datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.Y_AXIS));
		datosPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Imagen del Alumno
		imagenLabel = new JLabel();
		imagenLabel.setPreferredSize(new Dimension(100, 100));
		//imagenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		datosPanel.add(imagenLabel);

		mainPanel.add(datosPanel);

		// Campo Número
		numeroField = new JTextField(20);
		numeroField.setEditable(false);
		datosPanel.add(numeroField);
		datosPanel.add(Box.createVerticalStrut(10)); // Espaciado

		// Campo Usuario
		usuarioField = new JTextField(20);
		usuarioField.setEditable(false);
		datosPanel.add(usuarioField);
		datosPanel.add(Box.createVerticalStrut(10));

		// Campo Contraseña
		contraseñaField = new JPasswordField(20);
		contraseñaField.setEditable(false);
		datosPanel.add(contraseñaField);
		datosPanel.add(Box.createVerticalStrut(10));

		// Campo Fecha de Nacimiento
		fechaNacimientoField = new JTextField(20);
		fechaNacimientoField.setEditable(false);
		datosPanel.add(fechaNacimientoField);
		datosPanel.add(Box.createVerticalStrut(10));

		// Campo Nota Media
		notaMediaField = new JTextField(20);
		notaMediaField.setEditable(false);
		datosPanel.add(notaMediaField);
		datosPanel.add(Box.createVerticalStrut(10));


		// JList para las asignaturas
		JPanel asignaturasPanel = new JPanel(new BorderLayout());
		asignaturasPanel.setBorder(BorderFactory.createTitledBorder("Asignaturas"));

		asignaturasList = new JList<>();
		JScrollPane scrollPane = new JScrollPane(asignaturasList);
		asignaturasPanel.add(scrollPane, BorderLayout.CENTER);

		mainPanel.add(asignaturasPanel);

		add(mainPanel, BorderLayout.CENTER);

		//Boton
		calcularMediaButton = new JButton("Calcular Media");
		calcularMediaButton.addActionListener(e -> calcularMedia());
		asignaturasPanel.add(calcularMediaButton, BorderLayout.SOUTH);

		mainPanel.add(asignaturasPanel);

		add(mainPanel, BorderLayout.CENTER);

		ctrlPanDetalle = new CtrlPanDetalle();
		setAlumnoData(ctrlPanDetalle.actualizarDatos(alumn_id));
	}

	// Métodos para actualizar la información del panel
	public void setAlumnoData(Alumno alumno) {
		numeroField.setText(String.valueOf(alumno.getAlumn_id()));
		usuarioField.setText(alumno.getUser());
		contraseñaField.setText(alumno.getPassword());
		fechaNacimientoField.setText(alumno.getBirthday_date().toString());
		notaMediaField.setText(String.valueOf(alumno.getAverage_score()));
		imagenLabel.setIcon((Icon)alumno.getPhoto());
	}

//	public void setAsignaturasData(List asignaturas) {
//		DefaultListModel<String> listModel = new DefaultListModel<>();
//		for (String asignatura : asignaturas) {
//			listModel.addElement(asignatura);
//		}
//		asignaturasList.setModel(listModel);
//	}

	private void calcularMedia() {
		DefaultListModel<String> model = (DefaultListModel<String>) asignaturasList.getModel();
		if (model.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No hay asignaturas para calcular la media.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		double total = 0;
		int count = 0;
		for (int i = 0; i < model.size(); i++) {
			String asignatura = model.getElementAt(i);
			String[] parts = asignatura.split(":");
			if (parts.length == 2) {
				try {
					total += Double.parseDouble(parts[1].trim());
					count++;
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Error al procesar las notas.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}

		double media = total / count;
		JOptionPane.showMessageDialog(this, "La nota media es: " + String.format("%.2f", media), "Media Calculada", JOptionPane.INFORMATION_MESSAGE);
	}




}
