package controller;

import model.Alumno;
import model.Asignatura;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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



    public void calcularMedia(Integer alumnId) throws SQLException {
        double totalNotas = 0;
        int numAsignaturas = 0;

        // Consulta para obtener las notas de las asignaturas del alumno
        String sql = "SELECT score FROM subject WHERE alumn_id = " + alumnId;

        try (Statement st = Conexion.obtenerStatementResumen();
             ResultSet rs = st.executeQuery(sql)) {

            // Sumar las notas de todas las asignaturas
            while (rs.next()) {
                totalNotas += rs.getDouble("score");
                numAsignaturas++;
            }

            // Si hay asignaturas, calcular la media
            if (numAsignaturas > 0) {
                double media = totalNotas / numAsignaturas;
                System.out.println("La media del alumno es: " + media);

                String sqlConsultaNota = "SELECT average_score from alumn where alumn_id = " + alumnId;
                Double notaGuardada = 0.0;
                try (Statement st2 = Conexion.obtenerStatementResumen();
                     ResultSet rs2 = st.executeQuery(sqlConsultaNota)) {

                    // Sumar las notas de todas las asignaturas
                    while (rs2.next()) {
                        notaGuardada = rs2.getDouble("average_score");
                    }
                }

                if(media == notaGuardada){
                    return;
                }

                // Crear un diálogo para confirmar la acción
                int respuesta = JOptionPane.showConfirmDialog(
                        null,
                        "La nota media calculada es: " + media + ". ¿Desea actualizar la nota media en la base de datos?",
                        "Confirmar actualización",
                        JOptionPane.YES_NO_OPTION
                );

                // Actualizar la nota media en la base de datos
                    if (respuesta == JOptionPane.YES_OPTION) {

                        String updateSql = "UPDATE alumn SET average_score = ? WHERE alumn_id = ?";
                        try (PreparedStatement pst = Conexion.obtenerStatementResumen().getConnection().prepareStatement(updateSql)) {
                            pst.setDouble(1, media);
                            pst.setInt(2, alumnId);
                            pst.executeUpdate();
                        }

                    } else {
                        System.out.println("No se encontraron asignaturas para el alumno.");
                    }
            }else {
                JOptionPane.showMessageDialog(null, "La nota media no fue actualizada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al calcular la media del alumno", e);
        }
    }

    public List<Asignatura> setAsignaturasData(Integer alumnId) throws SQLException {
        List<Asignatura> asignaturasList = new ArrayList<>();

        // Consulta para obtener los datos de las asignaturas del alumno
        String sql = "SELECT code_subject, name, score FROM subject WHERE alumn_id = " + alumnId;

        try (Statement st = Conexion.obtenerStatementResumen();
             ResultSet rs = st.executeQuery(sql)) {

            // Crear y llenar la lista de asignaturas
            while (rs.next()) {
                Asignatura asignatura = new Asignatura();
                asignatura.setCode_subject(rs.getString("code_subject"));
                asignatura.setName(rs.getString("name"));
                asignatura.setScore(rs.getDouble("score"));
                asignaturasList.add(asignatura);
            }

            System.out.println("Asignaturas cargadas: " + asignaturasList.size() + " asignaturas encontradas.");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al cargar las asignaturas del alumno", e);
        }

        return asignaturasList;
    }
}
