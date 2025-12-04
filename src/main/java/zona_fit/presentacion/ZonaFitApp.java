package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        IClienteDAO clienteDAO = new ClienteDAO();
        zonaFitApp(clienteDAO);
    }

    private static void zonaFitApp(IClienteDAO clienteDAO) {
        var opcion = 0;
        do {
            mostrarMenu();
            opcion = leerNumero();
            try {
                if (opcion < 1 || opcion > 6) {
                    System.out.println("Opcion no valida");
                } else {
                    switch (opcion) {
                        case 1:
                            listarCliente(clienteDAO);
                            break;
                        case 2:
                            buscarCliente(clienteDAO);
                            break;
                        case 3:
                            agregarCliente(clienteDAO);
                            break;
                        case 4:
                            modificarCliente(clienteDAO);
                            break;
                        case 5:
                            eliminarCliente(clienteDAO);
                            break;
                        case 6:
                            System.out.println("Fin del programa");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Opcion invalida");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error opcion no valida " + e.getMessage());
            }

        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("Elija la opcion deseada");
        System.out.println("1. Listar Clientes");
        System.out.println("2. Buscar Cliente");
        System.out.println("3. Agregar Cliente");
        System.out.println("4. Modificar Cliente");
        System.out.println("5. Eliminar Cliente");
        System.out.println("6. Salir");
    }

    private static int leerNumero() {
        Scanner sc = new Scanner(System.in);
        int numero = 0;
        try {
            numero = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Error opcion no valida " + e.getMessage());
        }
        return numero;
    }
    private static String leerCadena() {
        Scanner sc = new Scanner(System.in);
        String cadena = "";
        try {
            cadena = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error caracter no valido " + e.getMessage());
        }
        return cadena;
    }

    private static void listarCliente(IClienteDAO  clienteDAO) {
        System.out.println("Listado de Clientes:");
        var clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);
    }
    private static void buscarCliente(IClienteDAO  clienteDAO) {
        System.out.println("Introduce el id del cliente:");
        var id = leerNumero();
        var cliente = new Cliente(id);
        var encontrado = clienteDAO.buscarClientePorID(cliente);
        if(encontrado){
            System.out.println("Cliente encontrado "+ cliente);
        }else{
            System.out.println("Cliente no encontrado "+ cliente);
        }
    }
    private static void agregarCliente(IClienteDAO  clienteDAO) {
        System.out.println("Introduce el nombre del cliente:");
        String nombre = leerCadena();
        System.out.println("Introduce el apellido del cliente:");
        String apellido = leerCadena();
        System.out.println("Introduce el membresia del cliente:");
        int membresia = leerNumero();
        var cliente = new Cliente(nombre, apellido, membresia);
        var agregado = clienteDAO.agregarCliente(cliente);
        if(agregado){
            System.out.println("Cliente agregado "+ cliente);
        }else {
            System.out.println("Cliente no agregado "+ cliente);
        }
    }
    private static void modificarCliente(IClienteDAO  clienteDAO) {
        System.out.println("Modificar cliente");
        System.out.println("Introduce el id del cliente:");
        var id = leerNumero();
        System.out.println("Introduce el nombre del cliente:");
        String nombre = leerCadena();
        System.out.println("Introduce el apellido del cliente:");
        String apellido = leerCadena();
        System.out.println("Introduce el membresia del cliente:");
        int membresia = leerNumero();
        var cliente = new Cliente(id, nombre, apellido, membresia);
        var modificado = clienteDAO.modificarCliente(cliente);
        if(modificado){
            System.out.println("Cliente modificado "+ cliente);
        } else {
            System.out.println("Cliente no modificado "+ cliente);
        }
    }
    private static void eliminarCliente(IClienteDAO  clienteDAO) {
        System.out.println("Eliminar cliente");
        System.out.println("Introduce el id del cliente:");
        var id = leerNumero();
        var cliente = new Cliente(id);
        var eliminado = clienteDAO.eliminarCliente(cliente);
        if(eliminado){
            System.out.println("Cliente eliminado "+ cliente);
        }  else {
            System.out.println("Cliente no eliminado "+ cliente);
        }
    }
}
