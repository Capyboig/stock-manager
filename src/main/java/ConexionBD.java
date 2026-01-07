import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class ConexionBD {
    private static HikariDataSource dataSource;

    static {
        try {
        Properties props = new Properties();
        props.load(new FileInputStream("db.properties"));

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(props.getProperty("db.url"));
        config.setUsername(props.getProperty("db.user"));
        config.setPassword(props.getProperty("db.password"));

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(2000);
            dataSource = new HikariDataSource(config);
            System.out.println("[EXITO] Pool inicializada correctamente");






        } catch (IOException e) {
            System.out.println("[ERROR GRAVE] No se encontro el archivo con las claves para entrar ");
            throw new RuntimeException("[ERROR] No se pudo cargar la configuracion, e");
        }
    }


    public static Connection conectar() throws SQLException {
        return dataSource.getConnection();
    }
}