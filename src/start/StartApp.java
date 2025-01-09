package start;

import controller.Conexion;

public class StartApp {
	public static void main(String[] args) {
		Conexion conn = new Conexion();

		conn.conectar();

		conn.desconectar();
	}

}
