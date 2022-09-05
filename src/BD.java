/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mario
 */

import java.awt.List;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BD {
    private Connection con;
    public BD(){
        con = null;
    }
    
    private Connection conectar(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_empresa","root","root34");
            if (con != null) {
                System.out.println("Conectado");
            } 
        }
        catch(SQLException e)
        {
            con = null;
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException e)
        {
            con = null;
            System.out.println(e.getMessage());
        }
        
        return con;
    }
    
    private void desconectar() throws SQLException{
        if(con != null)
        {
            con.close();
            con = null;
        }
    }
    
    public boolean InsertarCliente(Clientes cli){
        boolean resp = false;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        try{
            if(conectar() != null){
                String query = "INSERT INTO clientes (nit, nombres, apellidos, direccion, telefono) " +
                        "VALUES (?,?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, cli.getNit());
                statement.setString(2, cli.getNombres());
                statement.setString(3, cli.getApellidos());
                statement.setString(4, cli.getDireccion());
                statement.setString(5, cli.getTelefono());
                
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    resp = true;
                }else{
                    resp = false;
                }
            }
        }catch(Exception ex){
            resp = false;
            System.out.println(ex.getMessage());
        }
        return resp;
    }
    
    public ArrayList<Clientes> GetClientes(){
        ArrayList<Clientes> lst_Clientes = new ArrayList<Clientes>();
        try{
            if(conectar() != null){
                String sql = "SELECT * FROM clientes";
 
                Statement statement = con.createStatement();
                ResultSet result = statement.executeQuery(sql);

                int count = 0;

                while (result.next()){
                    Clientes cli = new Clientes();
                    cli.setId_cliente(result.getInt(1));
                    cli.setNit(result.getString(2));
                    cli.setNombres(result.getString(3));
                    cli.setApellidos(result.getString(4));
                    cli.setDireccion(result.getString(5));
                    cli.setTelefono(result.getString(6));
                    lst_Clientes.add(cli);
                }
            }
        }catch(Exception ex){
            lst_Clientes = null;
            System.out.println(ex.getMessage());
        }
        return lst_Clientes;
    }
    
    public boolean UpdateCliente(Clientes cli){
        boolean resp = false;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        try{
            if(conectar() != null){
                String query = "UPDATE clientes SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ? " +
                        "WHERE id_cliente = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, cli.getNit());
                statement.setString(2, cli.getNombres());
                statement.setString(3, cli.getApellidos());
                statement.setString(4, cli.getDireccion());
                statement.setString(5, cli.getTelefono());
                statement.setInt(6, cli.getId_cliente());
                
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    resp = true;
                }else{
                    resp = false;
                }
            }
        }catch(Exception ex){
            resp = false;
            System.out.println(ex.getMessage());
        }
        return resp;
    }
    
    public boolean DeleteCliente(int id_cli){
        boolean resp = false;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        try{
            if(conectar() != null){
                String query = "DELETE FROM clientes WHERE id_cliente = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setInt(1, id_cli);
                
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    resp = true;
                }else{
                    resp = false;
                }
            }
        }catch(Exception ex){
            resp = false;
            System.out.println(ex.getMessage());
        }
        return resp;
    }
}
