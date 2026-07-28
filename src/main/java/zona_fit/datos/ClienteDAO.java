package zona_fit.datos;

import java.sql.Connection;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        var sql = "SELECT * FROM clientes ORDER BY id";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery())
        {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("ID"));
                    cliente.setNombre(rs.getString("NOMBRE"));
                    cliente.setApellido(rs.getString("APELLIDO"));
                    cliente.setMembresia(rs.getInt("MEMBRESIA"));
                    clientes.add(cliente);
                }
        } catch (Exception e) {
            System.out.println("Error al obtener los datos: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public boolean buscarClienteById(Cliente cliente) {

        var sql = "SELECT * FROM clientes WHERE ID = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, cliente.getId());
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Cliente clientert = new Cliente();
                    clientert.setId(rs.getInt("ID"));
                    clientert.setNombre(rs.getString("NOMBRE"));
                    clientert.setApellido(rs.getString("APELLIDO"));
                    clientert.setMembresia(rs.getInt("MEMBRESIA"));
                    System.out.println("\n"+clientert);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por id: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        var sql = "INSERT INTO clientes (NOMBRE, APELLIDO, MEMBRESIA) VALUES (?, ?, ?)";
        try(Connection con = Conexion.getConexion();
        PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            int filas = ps.executeUpdate();
            return (filas > 0);
        } catch (Exception e) {
            System.out.println("Error al insertar el registro: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        var sql = """
                UPDATE clientes
                SET NOMBRE = ?
                , APELLIDO = ?
                , MEMBRESIA = ?
                WHERE ID = ?
                """;
        try(Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            int filas = ps.executeUpdate();
            return (filas > 0);
        }catch (Exception e){
            System.out.println("Error al modificar el registro: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        var sql = """
                DELETE FROM clientes WHERE ID = ?
                """;
        try(Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, cliente.getId());
            int filas = ps.executeUpdate();
            return (filas > 0);
        }catch (Exception e){
            System.out.println("Error al eliminar el registro: " + e.getMessage());
        }
        return false;
    }


    static void main(String[] args) {
        IClienteDAO clienteDao = new ClienteDAO();
//        var listaClientes =  clienteDao.listarClientes();
//        listaClientes.forEach(System.out::println);
//        var exite = clienteDao.buscarClienteById(new Cliente(6));
//        System.out.println(exite?"Si":"No");
//        var creacionCliente = clienteDao.agregarCliente(new Cliente("Carlos","Acuña",400));
//        System.out.println(creacionCliente?"Si, se creo el cliente":"No, no se creo el cliente");
//        var eliminarCliente = clienteDao.eliminarCliente(new Cliente(10));
//        System.out.println(eliminarCliente?"Se elimino el cliente":"No, no se elimino el cliente");
        var seModifico = clienteDao.modificarCliente(new Cliente(7, "Pedro", "Castillo", 200));
        System.out.println(seModifico?"Si":"No");
    }
}
