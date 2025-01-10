package start;

import controller.Conexion;
import view.FrmPrincipal;

import java.sql.SQLException;

public class StartApp {
	public static void main(String[] args) throws SQLException {
		new FrmPrincipal();
	}

}
