package controller;

import model.Asignatura;
import view.PanResumen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CtrlPanResumen {
	private ArrayList<Asignatura> asignaturas;
	private Conexion conn;
	private int index;
	private PanResumen panResumen;
    private int id_alumn;


	public CtrlPanResumen(PanResumen panResumen, Conexion conexion, int id_alumn) {
		this.conn = conexion;
		this.panResumen = panResumen;
        this.id_alumn = id_alumn;
		this.asignaturas = getAsignaturas();
		this.index = 0;
	}

	public void cambiarNotaAsignatura() {
        if (index >= 0 && index < asignaturas.size()) {
            Asignatura asignatura = asignaturas.get(index);
            double nuevaNota = Double.parseDouble(panResumen.txtNota.getText());

            String sql = "UPDATE subject SET score = ? WHERE code_subject = ? AND alumn_id = ?";
            try {
                PreparedStatement ps = conn.getConnection().prepareStatement(sql);
                ps.setDouble(1, nuevaNota);
                ps.setString(2, asignatura.getCode_subject());
                ps.setInt(3, asignatura.getAlumn_id());
                int rowsUpdated = ps.executeUpdate();

                if (rowsUpdated > 0) {
                    asignatura.setScore(nuevaNota);
                    System.out.println("Nota actualizada");
                } else {
                    System.out.println("No se pudo actualizar la nota");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar la nota: " + e.getMessage());
            }
        }
	}

	public void setData(){
        if(index >= 0 && index < asignaturas.size()) {
			Asignatura asignatura = asignaturas.get(index);
            panResumen.lblAsignatura.setText(asignatura.getName());
            panResumen.txtNota.setText(String.valueOf(asignatura.getScore()));
		}
        bloquearBotones();
	}

    private void bloquearBotones() {
        panResumen.btnAnterior.setEnabled(index > 0);
        panResumen.btnSiguiente.setEnabled(index < asignaturas.size() - 1);
        panResumen.btnPrimero.setEnabled(index > 0);
        panResumen.btnUltimo.setEnabled(index < asignaturas.size() - 1);
    }

	private ArrayList<Asignatura> getAsignaturas() {
		ArrayList<Asignatura> lstAsignaturas = new ArrayList<>();
        String sql = "SELECT * FROM subject WHERE alumn_id = ?";
        try {
            PreparedStatement ps = conn.getConnection().prepareStatement(sql);
            ps.setInt(1, id_alumn);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("code_subject");
                String nombre = rs.getString("name");
                double nota = rs.getDouble("score");
                int alumn_id = rs.getInt("alumn_id");

                lstAsignaturas.add(new Asignatura(codigo, nombre, nota, alumn_id));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener asignaturas: " + e.getMessage());
        }
        System.out.println(lstAsignaturas.size());
        return lstAsignaturas;
	}
	public void siguienteAsignatura() {
        if (index < asignaturas.size() - 1) {
            index++;
            setData();
        }
    }
    public void anteriorAsignatura() {
        if (index > 0) {
            index--;
            setData();
        }
    }

    public void primeraAsignatura() {
        index = 1;
        setData();
    }
    public void ultimaAsignatura() {
        index = asignaturas.size() - 1;
        setData();
    }
}
