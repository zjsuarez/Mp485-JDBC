package com.mycompany.jdb_justin.libraries;

import Model.Employee;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;


public class GestorBBD {
    private static String datosconexion = "jdbc:mysql://127.0.0.1:3306/";
    private static String baseDatos = "mp485";
    private static String usuario = "root";
    private static String password = "";
    
    private Connection con;
    
    static Statement stmt = null;
    ArrayList<Employee> empleados = new ArrayList();
    
    public GestorBBD(){
    
        try {
            con = DriverManager.getConnection(datosconexion,usuario,password);
            
            try {
                
                crearBBD();
                crearTablaEmployee();
                
                
            } catch (Exception e) { }
            
        } catch (Exception e) {  }
        updatearray();
    }
    
    
    private void crearBBD() throws Exception{
        String query = "create database if not exists " + baseDatos + ";";
        
        try {
            stmt =  con.createStatement();
            stmt.executeUpdate(query);
            con = DriverManager.getConnection(datosconexion+baseDatos,usuario,password);
            

        } catch (Exception e) { 
        } finally{
            stmt.close();
        }
        
    }
    
    private void crearTablaEmployee() throws Exception{
        String query = "CREATE TABLE Empleado ("
             + "id INT PRIMARY KEY AUTO_INCREMENT, "
             + "nombre VARCHAR(100), "
             + "edad INT, "
             + "departamento VARCHAR(100), "
             + "salario DOUBLE"
             + ");";

        

        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            
        } catch (Exception e) {  }
    
    }
    
    public void ingresarempleado(String nombre, int edad, String departamento, double salario){
        String query = "INSERT INTO Empleado (nombre, edad, departamento, salario) VALUES ("
                + "'" + nombre + "', "
                + edad + ", "
                + "'" + departamento + "', "
                + salario + ");";

        

        try {
            System.out.println("--INSERTANDO EMPLEADO--");
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("LISTO");
            
        } catch (Exception e) { System.out.println("ERROR"); }
    }

    public ArrayList<Employee> getEmpleados() {
        return empleados;
    }
    
    
    public void eliminarempleado(int id){
         String query = "DELETE FROM Empleado WHERE id = " + id;  // Consulta para eliminar el empleado por su ID

    try {
        System.out.println("eliminando empleado");
        stmt = con.createStatement();

     
        stmt.executeUpdate(query);

        System.out.println("ELIMINADO");
    } catch (Exception e) {
        System.out.println("Error al eliminar el empleado: " + e.getMessage());
    }
    }
    
    public void actualizarEmpleado(int id, String nombre, int edad, String departamento, double salario) {
    String query = "UPDATE Empleado SET "
            + "nombre = '" + nombre + "', "
            + "edad = " + edad + ", "
            + "departamento = '" + departamento + "', "
            + "salario = " + salario + " "
            + "WHERE id = " + id;

    try {
        System.out.println("Actualizando");
        stmt = con.createStatement();

        stmt.executeUpdate(query);

        System.out.println("Actualizado!");
    } catch (Exception e) {
        System.out.println("Error al actualizar el empleado: " + e.getMessage());
    }
    }
    
    private void updatearray(){
        String query = "SELECT * FROM empleado";
        
        try {
            System.out.println("Actualizando tabla");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                String departamento = rs.getString("departamento");
                double salario = rs.getDouble("salario");
                
                // Agregar el empleado a la lista
                empleados.add(new Employee(id, nombre, edad, departamento, salario));
                System.out.println("Empleado insertado");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("ERROR al obtener empleados: " + e.getMessage());
        }

    }
}

