package controller;

import model.Alumno;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

public class CtrlPanDetalle {


    public Alumno actualizarDatos(Integer alumnId) throws SQLException {

        int id = alumnId;
        String sql = "SELECT alumn_id, user, password, birthday_date, average_score, photo FROM alumn WHERE alumn_id = " + id;
        try (Statement st = Conexion.obtenerStatementResumen();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                // Crear y llenar el objeto Alumno con los datos obtenidos
                Alumno alumno = new Alumno();
                alumno.setAlumn_id(rs.getInt("alumn_id"));
                alumno.setUser(rs.getString("user"));
                alumno.setPassword(rs.getString("password"));
                alumno.setBirthday_date(rs.getDate("birthday_date"));
                alumno.setAverage_score(rs.getDouble("average_score"));

                System.out.println("Alumno cargado: " + alumno.getUser());
                return alumno;
                // Ahora tienes el objeto Alumno con los datos cargados

            } else {
                System.out.println("No se encontró ningún alumno con el ID: " + id);
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al cargar los datos del alumno", e);
        }

    }
}
