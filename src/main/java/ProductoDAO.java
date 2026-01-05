import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;

public class ProductoDAO {
    private static final String INSERT_SQL = "INSERT INTO productos (nombre, cantidad, precio) VALUES (?, ?, ?)";



    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filasEliminar = ps.executeUpdate();

            if (filasEliminar > 0) {
                System.out.println("[EXITO] Producto eliminado");
            } else {
                System.out.println("[ERROR] No se encontro el producto para eliminar");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Error al borrar");
            e.printStackTrace();
        }
    }




    public void actualizarStock(int id, int nuevaCantidad) {
        String sql = "UPDATE productos SET cantidad = ? WHERE id = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nuevaCantidad);
            ps.setInt(2, id);

            int filas = ps.executeUpdate();
            if (filas > 0 ) {
                System.out.println("[EXITO] Stock actualizado");
            } else {
                System.out.println("[ERROR] No se encontro un producto con esa ID");
            }


        } catch (SQLException e) {
            System.out.println("[ERROR] Error al actualizar");
            e.printStackTrace();
        }





    }



    public List<Producto> listarProductos() {

        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {


            while (rs.next()) {


                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");


                Producto p = new Producto(id, nombre, cantidad, precio);


                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] al listar productos");
            e.printStackTrace();
        }

        return lista;
    }



    public void guardar(Producto p) {


        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {


            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getCantidad());
            ps.setDouble(3, p.getPrecio());

            int filasAfectadas = ps.executeUpdate();


            if (filasAfectadas > 0) {
                System.out.println("[EXITO]Producto guardado " + p.getNombre());
            }

        } catch (SQLException e) {
            System.out.println("[ERROR] Al guardar el producto");
            e.printStackTrace();
        }
    }
}
