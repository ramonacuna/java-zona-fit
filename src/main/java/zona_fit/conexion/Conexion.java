package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion() {

        var baseDatos = "zona_fit_db";
        var url = "jdbc:mysql://127.0.0.1:3306/" + baseDatos;
        var usuario = "root";
        var password = "admin";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            throw new RuntimeException("Fallo critico en la conexion a la base de datos", e);
        }
    }

}
