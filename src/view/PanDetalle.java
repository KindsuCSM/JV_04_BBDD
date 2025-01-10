package view;

import controller.CtrlPanDetalle;
import model.Alumno;
import model.Asignatura;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.io.Serial;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PanDetalle extends JPanel {

	private JTextField numeroField;
	private JTextField usuarioField;
	private JPasswordField contraseniaField;
	private JTextField fechaNacimientoField;
	private JTextField notaMediaField;
	private JLabel imagenLabel;
	private JList<String> asignaturasList;
	private JButton calcularMediaButton;
	private CtrlPanDetalle ctrlPanDetalle;

	private JSpinner fechaNacimientoSpinner;

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
		imagenLabel.setPreferredSize(new Dimension(80, 100));
		imagenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		datosPanel.add(imagenLabel);

		mainPanel.add(datosPanel);

		// Campo Número
		JPanel numeroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel numeroLabel = new JLabel("Número: ");
		numeroLabel.setPreferredSize(new Dimension(80, 20));  // Hacer el label pequeño
		numeroField = new JTextField(20);
		numeroField.setEditable(false);
		numeroPanel.add(numeroLabel);
		numeroPanel.add(numeroField);
		datosPanel.add(numeroPanel);
		datosPanel.add(Box.createVerticalStrut(10));

		// Campo Usuario
		JPanel usuarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel usuarioLabel = new JLabel("Usuario: ");
		usuarioLabel.setPreferredSize(new Dimension(80, 20));  // Hacer el label pequeño
		usuarioField = new JTextField(20);
		usuarioField.setEditable(false);
		usuarioPanel.add(usuarioLabel);
		usuarioPanel.add(usuarioField);
		datosPanel.add(usuarioPanel);
		datosPanel.add(Box.createVerticalStrut(10));

		// Campo Contraseña
		JPanel contraseniaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel contraseniaLabel = new JLabel("Contraseña: ");
		contraseniaLabel.setPreferredSize(new Dimension(80, 20));  // Hacer el label pequeño
		contraseniaField = new JPasswordField(20);
		contraseniaField.setEditable(false);
		contraseniaPanel.add(contraseniaLabel);
		contraseniaPanel.add(contraseniaField);
		datosPanel.add(contraseniaPanel);
		datosPanel.add(Box.createVerticalStrut(10));

		// Campo Fecha de Nacimiento
		JPanel fechaNacimientoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel fechaNacimientoLabel = new JLabel("Fecha Nac.: ");
		fechaNacimientoLabel.setPreferredSize(new Dimension(80, 20));  // Hacer el label pequeño
		fechaNacimientoField = new JTextField(20);
		fechaNacimientoField.setEditable(false);

		// Configuración de JSpinner para fechas
		SpinnerDateModel dateModel = new SpinnerDateModel();
		fechaNacimientoSpinner = new JSpinner(dateModel);
		fechaNacimientoSpinner.setEditor(new JSpinner.DateEditor(fechaNacimientoSpinner, "dd/MM/yyyy"));
		fechaNacimientoSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				actualizarFechaNacimiento((Date) fechaNacimientoSpinner.getValue());  // Al cambiar la fecha
			}
		});

		fechaNacimientoPanel.add(fechaNacimientoLabel);
		fechaNacimientoPanel.add(fechaNacimientoSpinner);
		datosPanel.add(fechaNacimientoPanel);
		datosPanel.add(Box.createVerticalStrut(10));

		// Campo Nota Media
		JPanel notaMediaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel notaMediaLabel = new JLabel("Nota Media: ");
		notaMediaLabel.setPreferredSize(new Dimension(80, 20));  // Hacer el label pequeño
		notaMediaField = new JTextField(20);
		notaMediaField.setEditable(false);
		notaMediaPanel.add(notaMediaLabel);
		notaMediaPanel.add(notaMediaField);
		datosPanel.add(notaMediaPanel);
		datosPanel.add(Box.createVerticalStrut(10));

		// JList para las asignaturas
		JPanel asignaturasPanel = new JPanel(new BorderLayout());
		asignaturasPanel.setBorder(BorderFactory.createTitledBorder("Asignaturas"));

		asignaturasList = new JList<>();
		JScrollPane scrollPane = new JScrollPane(asignaturasList);
		asignaturasPanel.add(scrollPane, BorderLayout.CENTER);

		mainPanel.add(asignaturasPanel);

		add(mainPanel, BorderLayout.CENTER);

		// Botón Calcular Media
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

	private void actualizarFechaNacimiento(Date nuevaFecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		fechaNacimientoField.setText(sdf.format(nuevaFecha));

		// Aquí podrías actualizar el objeto Alumno con la nueva fecha
	}

	// Métodos para actualizar la información del panel
	public void setAlumnoData(Alumno alumno) throws SQLException {
		numeroField.setText(String.valueOf(alumno.getAlumn_id()));
		usuarioField.setText(alumno.getUser());
		contraseniaField.setText(alumno.getPassword());

		// Formatear la fecha
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		fechaNacimientoField.setText(sdf.format(alumno.getBirthday_date().getTime()));

		// Establecer la fecha en el JSpinner
		fechaNacimientoSpinner.setValue(alumno.getBirthday_date().getTime());

		// Actualizar la nota media
		double notaMedia = alumno.getAverage_score();
		notaMediaField.setText(String.valueOf(notaMedia));

		// Establecer foto
		imagenLabel.setIcon((Icon)alumno.getPhoto());

		// Asignaturas
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

