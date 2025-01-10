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
import java.util.List;

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
		imagenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
		calcularMediaButton.addActionListener(e -> {
            try {
                ctrlPanDetalle.calcularMedia(alumn_id);
				setAlumnoData(ctrlPanDetalle.actualizarDatos(alumn_id));

            } catch (SQLException ex) {
				System.out.println("Problemas en nota media.");
                throw new RuntimeException(ex);
            }
        });
		asignaturasPanel.add(calcularMediaButton, BorderLayout.SOUTH);

		mainPanel.add(asignaturasPanel);

		add(mainPanel, BorderLayout.CENTER);

		ctrlPanDetalle = new CtrlPanDetalle();
		setAlumnoData(ctrlPanDetalle.actualizarDatos(alumn_id));
	}

	// Métodos para actualizar la información del panel
	public void setAlumnoData(Alumno alumno) throws SQLException {
		numeroField.setText(String.valueOf(alumno.getAlumn_id()));
		usuarioField.setText(alumno.getUser());
		contraseñaField.setText(alumno.getPassword());
		fechaNacimientoField.setText(alumno.getBirthday_date().toString());
		notaMediaField.setText(String.valueOf(alumno.getAverage_score()));
		imagenLabel.setIcon((Icon)alumno.getPhoto());
		ctrlPanDetalle.setAsignaturasData(alumno.getAlumn_id());

		List<Asignatura> asignaturas = ctrlPanDetalle.setAsignaturasData(alumno.getAlumn_id());

		// Crear el DefaultListModel para las asignaturas
		DefaultListModel<String> asignaturasModel = new DefaultListModel<>();
		asignaturasList.setModel(asignaturasModel);

		// Limpiar el modelo antes de agregar los nuevos elementos
		asignaturasModel.clear();

		// Agregar las asignaturas al modelo
		for (Asignatura asignatura : asignaturas) {
			asignaturasModel.addElement(asignatura.getName() + ": " + asignatura.getScore());
		}
	}

}
