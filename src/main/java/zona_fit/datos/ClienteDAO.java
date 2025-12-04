package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO{
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM Cliente ORDER BY id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("menbresia"));
                clientes.add(cliente);
            }
        }catch(Exception e){
            System.out.println("Error al obtener los registros de cliente "+e.getMessage());
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion "+e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarClientePorID(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM Cliente WHERE id = ?";
        try{
            ps = con.prepareStatement(sql); //preparar la sentencia
            ps.setInt(1, cliente.getId()); //para determinar el valor de ?
            rs = ps.executeQuery(); //ejecuta la sentencia
            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("menbresia"));
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println("Error al obtener el cliente por id "+e.getMessage());
        }finally{
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al Error al cerrar conexion "+e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente (nombre,apellido,menbresia) VALUES (?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al agregar el cliente "+e.getMessage());
        }finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion "+e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE cliente SET nombre=?,apellido=?,menbresia=? WHERE id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al modificar el cliente "+e.getMessage());
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion "+e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM cliente WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al eliminar el cliente "+e.getMessage());
        }finally {
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion "+e.getMessage());
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IClienteDAO clienteDAO = new ClienteDAO();

        //listar clientes

//        System.out.println("Listar clientes");
//        IClienteDAO clienteDAO = new ClienteDAO();
//        var clientes = clienteDAO.listarClientes();
//        clientes.forEach(System.out::println);

        //buscar por id

//        var cliente1 = new Cliente(2);
//        System.out.println("cliente antes de la busqueda: "+cliente1);
//        var encontrado = clienteDAO.buscarClientePorID(cliente1);
//        if(encontrado){
//            System.out.println("cliente encontrado " +cliente1);
//        }else{
//            System.out.println("cliente no encontrado " +cliente1.getId());
//        }

        //Agregar cliente
//        var nuevoCliente = new Cliente("Daniel","Ortiz",300);
//        var agregado = clienteDAO.agregarCliente(nuevoCliente);
//        if(agregado){
//            System.out.println("Cliente agregado correctamente "+nuevoCliente);
//        }else {
//            System.out.println("No existe el cliente con el id "+nuevoCliente);
//        }

        //modificar cliente
//        var modificarCliente = new Cliente(5,"Paco", "Ortiz",300);
//        var modificado = clienteDAO.modificarCliente(modificarCliente);
//        if(modificado){
//            System.out.println("cliente modificado correctamente "+modificarCliente);
//        }else{
//            System.out.println("cliente modificado incorrecto" + modificarCliente);
//        }

        //elminar cliente

        var clienteEliminar = new Cliente(5);
        var eliminado = clienteDAO.eliminarCliente(clienteEliminar);
        if(eliminado){
            System.out.println("El cliente se elimino correctamente "+clienteEliminar);
        }else{
            System.out.println("El cliente no se puede eliminar "+clienteEliminar);
        }
        //listar
        System.out.println("Listar clientes");
        var clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);
    }
}
