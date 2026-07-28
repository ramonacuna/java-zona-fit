package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp() {
        Scanner sc = new Scanner(System.in);
        IClienteDAO clienteDao = new ClienteDAO();
        var exitApp = false;
        while (!exitApp) {
            var option = mostrarMenu(sc);
            switch (option) {
                case 1 -> listarClientes(clienteDao);
                case 2 -> buscarCliente(clienteDao, sc);
                case 3 -> agregarCliente(clienteDao, sc);
                case 4 -> modificarCliente(clienteDao, sc);
                case 5 -> eliminarCliente(clienteDao, sc);
                case 6 -> {
                    System.out.println("Hasta Pronto!...");
                    exitApp = true;
                }
                default -> System.out.println("Opcion no reconocida: " + option);
            }
        }
    }

    private static int mostrarMenu(Scanner sc) {
        var menu = """
                *** Zona Fit (GYM) ***
                1. Listar Clientes
                2. Buscar Cliente
                3. Agregar Cliente
                4. Modificar Cliente
                5. Eliminar Cliente
                6. Salir
                Elija una Opcion:\s""";
        return leerEntero(sc, menu);
    }

    private static void listarClientes(IClienteDAO clienteDao) {
        List<Cliente> clienteList = clienteDao.listarClientes();
        System.out.println("\nListado Clientes:");
        clienteList.forEach(System.out::println);
        System.out.println();
    }

    private static void buscarCliente(IClienteDAO clienteDao, Scanner sc) {
        int idCliente = leerEntero(sc, "Ingrese el Id del Usuario:\s");
        var resultadoBusqueda = clienteDao.buscarClienteById(new Cliente(idCliente));
        if (resultadoBusqueda == null) {
            System.out.println("\nEl Usuario no existe");
        } else {
            System.out.println("\nEl Usuario que desea buscar:\s");
            System.out.println(resultadoBusqueda);
            System.out.println();
        }
    }

    private static void agregarCliente(IClienteDAO clienteDao, Scanner sc) {
        Cliente cliente = new Cliente();
        System.out.print("\nIngrese el nombre del Usuario:\s");
        cliente.setNombre(sc.nextLine());
        System.out.print("Ingrese el apellido del Usuario:\s");
        cliente.setApellido(sc.nextLine());
        cliente.setMembresia(leerEntero(sc, "Ingrese la membresía del Usuario:\s"));
        if (clienteDao.agregarCliente(cliente)) {
            System.out.println("Cliente agregado correctamente\n");
        } else {
            System.out.println("El cliente no ha sido agregado\n");
        }

    }

    private static void modificarCliente(IClienteDAO clienteDao, Scanner sc) {
        Cliente cliente = new Cliente();
        cliente.setId(leerEntero(sc, "\nIngrese el id del Usuario:\s"));
        System.out.print("Ingrese el nombre del Usuario:\s");
        cliente.setNombre(sc.nextLine());
        System.out.print("Ingrese el apellido del Usuario:\s");
        cliente.setApellido(sc.nextLine());
        cliente.setMembresia(leerEntero(sc, "Ingrese la membresía del Usuario:\s"));
        if (clienteDao.modificarCliente(cliente)) {
            System.out.println("Cliente modificado correctamente\n");
        } else {
            System.out.println("El cliente no ha sido actualizado\n");
        }
    }

    private static void eliminarCliente(IClienteDAO clienteDao, Scanner sc) {
        Cliente cliente = new Cliente();
        cliente.setId(leerEntero(sc, "\nIngrese el Id del Usuario:\s"));
        if (clienteDao.eliminarCliente(cliente)) {
            System.out.println("Cliente eliminado correctamente\n");
        } else {
            System.out.println("El cliente no ha sido eliminado\n");
        }
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número entero válido.\n");
            }
        }
    }

}
