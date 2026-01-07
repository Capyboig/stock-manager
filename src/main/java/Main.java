import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Instanciamos el DAO una sola vez fuera para reutilizarlo (buena práctica)
        ProductoDAO dao = new ProductoDAO();

        try (Scanner sc = new Scanner(System.in)) {
            boolean salir = false;

            do {

                try {
                    System.out.println();
                    System.out.println("|-------------------------- MENU DE STOCK ----------------------------|");
                    System.out.println("|                      1- Añadir productos                            |");
                    System.out.println("|                      2- Listar productos                            |");
                    System.out.println("|                      3- Actualizar stock                            |");
                    System.out.println("|                      4- Eliminar Producto                           |");
                    System.out.println("|                      5- Buscar Producto                             |");
                    System.out.println("|                      6- Salir                                       |");
                    System.out.println("|---------------------------------------------------------------------|");

                    System.out.println("Escriba la opcion del menu a la que quiere acceder:");


                    int opcionMenu = sc.nextInt();
                    sc.nextLine();

                    switch (opcionMenu) {

                        case 1:
                            System.out.println(" ------ AÑADIR STOCK ------");
                            System.out.println(" REQUISITOS:");
                            System.out.println("- Nombre del producto");
                            System.out.println("- Cantidad de stock");
                            System.out.println("- Precio por unidad");


                            System.out.println("Introduzca el nombre del producto a stockear");
                            String nombre = sc.nextLine();

                            System.out.println("Introduce la cantidad de stock");
                            int cantidad = sc.nextInt();

                            System.out.println("Introduce el precio");
                            double precio = sc.nextDouble();

                            sc.nextLine();

                            Producto producto = new Producto(nombre, cantidad, precio);
                            dao.guardar(producto);
                            break;

                        case 2:
                            List<Producto> misProductos = dao.listarProductos();

                            System.out.println(" ---- LISTA DE INVENTARIO ----");
                            System.out.println();
                            for (Producto p : misProductos) {
                                System.out.println(p);
                            }
                            break;

                        case 3:
                            System.out.println(" ----- ACTUALIZAR STOCK -----");

                            System.out.println("Introduce el ID del producto a buscar");
                            int idParaActualizar = sc.nextInt();

                            System.out.println("A cuanto quiere cambiar el stock");
                            int cambiarStock = sc.nextInt();
                            sc.nextLine();

                            dao.actualizarStock(idParaActualizar, cambiarStock);
                            break;

                        case 4:
                            System.out.println(" ----- ELIMINAR PRODUCTO -----");
                            System.out.println();
                            System.out.println("[ADVERTENCIA] Esto eliminará el producto de la base de datos, ¿seguro? (Si/No)");
                            String continuar = sc.nextLine();

                            if (continuar.equalsIgnoreCase("si")) {
                                System.out.println("Introduzca el ID del producto a eliminar");
                                int id = sc.nextInt();
                                sc.nextLine();
                                dao.eliminarProducto(id);
                            } else {
                                System.out.println("Operación cancelada.");
                            }
                            break;

                        case 5:
                            System.out.println(" ----- Busqueda de producto -----");

                            System.out.printf("Introduzca el nombre del producto a encontrar");
                            String busqueda = sc.nextLine();

                            dao.buscarProductoPorNombre(busqueda);

                            break;
                        case 6:
                            salir = true;
                            System.out.println("Cerrando sistema...");
                            break;

                        default:
                            System.out.println("[ERROR] Opción no válida (1-5)");
                    }


                } catch (InputMismatchException e) {
                    System.out.println("[ERROR] Entrada inválida: Has introducido letras en un campo numérico.");
                    System.out.println("Volviendo al menú principal...");


                    sc.nextLine();
                } catch (Exception e) {
                    System.out.println("[ERROR] Ocurrió un error inesperado: " + e.getMessage());
                    sc.nextLine();
                }

            } while (!salir);
        }
    }
}