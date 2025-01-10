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
    private CtrlPanDetalle ctrlPanDetalle;


    public CtrlPanResumen(PanResumen panResumen, Conexion conexion, int id_alumn) {
        this.conn = conexion;
        this.panResumen = panResumen;
        this.id_alumn = id_alumn;
        this.asignaturas = getAsignaturas();
        this.index = 0;
    }

    // Función que cambia la nota de la asignatura mediante un statement
    public void cambiarNotaAsignatura() throws SQLException {
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
        ctrlPanDetalle = new CtrlPanDetalle();
        ctrlPanDetalle.actualizarDatos(id_alumn);
    }

    // Función que pone los datos en el panel
    public void setData() {
        if (index >= 0 && index < asignaturas.size()) {
            Asignatura asignatura = asignaturas.get(index);
            panResumen.lblAsignatura.setText(asignatura.getName());
            panResumen.txtNota.setText(String.valueOf(asignatura.getScore()));
        }
        bloquearBotones();
    }

    // Bloquea los botones dependiendo de si está en primer o ultimo lugar
    private void bloquearBotones() {
        panResumen.btnAnterior.setEnabled(index > 0);
        panResumen.btnSiguiente.setEnabled(index < asignaturas.size() - 1);
        panResumen.btnPrimero.setEnabled(index > 0);
        panResumen.btnUltimo.setEnabled(index < asignaturas.size() - 1);
    }

    // Obtiene la lista de asignaturas del usuario que ha iniciado la sesion
    // lo guarda en una lista
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

    // Obtener la siguiente asignatura añadiendo +1 al indice
    public void siguienteAsignatura() {
        if (index < asignaturas.size() - 1) {
            index++;
            setData();
        }
    }

    // Obtener la anterior asignatura quitandole -1 al indice
    public void anteriorAsignatura() {
        if (index > 0) {
            index--;
            setData();
        }
    }

    // Poner el indice en 0 para ir a la primera asignatura
    public void primeraAsignatura() {
        index = 0;
        setData();
    }

    // Poner el indice como el numero total de asignaturas que hay en la lista de asignaturas
    public void ultimaAsignatura() {
        index = asignaturas.size() - 1;
        setData();
    }

    // Recoge la nota de la asignatura actual que aun no ha cambiado el usuario en caso de que quiera
    // cancelar la operación
    public void recogerNota() {
        Asignatura actual = asignaturas.get(index);
        panResumen.txtNota.setText(String.valueOf(actual.getScore()));
    }
}
