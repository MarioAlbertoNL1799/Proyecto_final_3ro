/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Database {
    public static Connection getConectar(){
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/pdesign","root","");
        }catch(Exception ex){
            System.out.println(String.valueOf(ex));
        }
        return conexion;
    }
    public static ResultSet getTabla(String consulta){
        Connection conexion = getConectar();
        Statement st;
        ResultSet datos=null;
        try{
            st=conexion.createStatement();
            datos = st.executeQuery(consulta);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error C1" + ex.getMessage());
        }
        return datos;
    }
}
