package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    static void main(String[] args) {
        ZonaFitApp();
    }

    private static void ZonaFitApp() {
        Scanner sc = new Scanner(System.in);
        ClienteDAO clienteDao = new ClienteDAO();
        var on = false;
        while (!on) {
            var option = mostrarMenu(sc);
            switch (option) {
                case 1 -> listarClientes(clienteDao);
                case 2 -> buscarCliente(clienteDao, sc);
                case 3 -> agregarCliente(clienteDao, sc);
                case 4 -> modificarCliente(clienteDao, sc);
                case 5 -> eliminarCliente(clienteDao, sc);
                case 6 -> on = true;
                default -> System.out.println("Opcion no reconocida: " + option);
            }
        }
    }

    private static int mostrarMenu(Scanner sc) {
        System.out.print("""
                    *** Zona Fit (GYM) ***
                    1. Listar Clientes
                    2. Buscar Cliente
                    3. Agregar Cliente
                    4. Modificar Cliente
                    5. Eliminar Cliente
                    6. Salir
                    Elija una Opcion:\s""");
        return Integer.parseInt(sc.nextLine());
    }

    private static void listarClientes(ClienteDAO clienteDao) {
        List<Cliente> clienteList = clienteDao.listarClientes();
        System.out.println("\nListado Clientes:");
        clienteList.forEach(System.out::println);
        System.out.println();
    }
    private static void buscarCliente(ClienteDAO clienteDao, Scanner sc) {
        System.out.print("Ingrese el Id del Usuario:\s");
        int idCliente = Integer.parseInt(sc.nextLine());
        System.out.print("\nEl Usuario que desea buscar:\s");
        System.out.println(
                clienteDao.buscarClienteById(new Cliente(idCliente)) ?
                        "Se ecuentre regitrado en el sistema\n"
                        : "No se encuntra registrado en el sistema\n"
        );
    }
    private static void agregarCliente(ClienteDAO clienteDao, Scanner sc) {
        Cliente cliente = new Cliente();
        System.out.print("\nIngrese el nombre del Usuario:\s");
        cliente.setNombre(sc.nextLine());
        System.out.print("Ingrese el apellido del Usuario:\s");
        cliente.setApellido(sc.nextLine());
        System.out.print("Ingrese el membresia del Usuario:\s");
        cliente.setMembresia(Integer.parseInt(sc.nextLine()));
        if(clienteDao.agregarCliente(cliente)){
            System.out.println("Cliente agregado correctamente\n");
        }else{
            System.out.println("El cliente no ha sido agregado\n");
        }

    }
    private static void modificarCliente(ClienteDAO clienteDao, Scanner sc) {
        Cliente cliente = new Cliente();
        System.out.print("\nIngrese el id del Usuario:\s");
        cliente.setId(Integer.parseInt(sc.nextLine()));
        System.out.print("Ingrese el nombre del Usuario:\s");
        cliente.setNombre(sc.nextLine());
        System.out.print("Ingrese el apellido del Usuario:\s");
        cliente.setApellido(sc.nextLine());
        System.out.print("Ingrese el membresia del Usuario:\s");
        cliente.setMembresia(Integer.parseInt(sc.nextLine()));
        if(clienteDao.modificarCliente(cliente)){
            System.out.println("Cliente modificado correctamente\n");
        }else {
            System.out.println("El cliente no ha sido actualizado\n");
        }
    }
    private static void eliminarCliente(ClienteDAO clienteDao, Scanner sc) {
        Cliente cliente = new Cliente();
        System.out.print("\nIngrese el Id del Usuario:\s");
        cliente.setId(Integer.parseInt(sc.nextLine()));
        if(clienteDao.eliminarCliente(cliente)){
            System.out.println("Cliente eliminado correctamente\n");
        }else  {
            System.out.println("El cliente no ha sido eliminado\n");
        }
    }

}

