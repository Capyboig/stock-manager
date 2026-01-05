import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    public static Connection conectar() {
        Properties props = new Properties();
        Connection con = null;

        try {

            props.load(new FileInputStream("db.properties"));


            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String pass = props.getProperty("db.password");

            con = DriverManager.getConnection(url, user, pass);

        } catch (IOException e) {
            System.out.println(" [ERROR] No se pudo leer el archivo de configuración 'db.properties'");
        } catch (SQLException e) {
            System.out.println("[ERROR] de conexión SQL");
            e.printStackTrace();
        }
        return con;
    }
}