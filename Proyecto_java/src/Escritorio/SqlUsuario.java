/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escritorio;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Diego
 */


public class SqlUsuario extends Database{
     public boolean registrar(usuario usr)
    {
        PreparedStatement ps = null;
        Connection conexion = getConectar();
        String sql = "INSERT INTO usuario (username,password,tipo,rfc_e) VALUES (?,?,?,?)";
        try
        {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usr.getUsername());
            ps.setString(2, usr.getPass());
            ps.setString(3, usr.getTipo());
            ps.setString(4, usr.getRfc());
            ps.execute();
            return true;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
            return false;
        }
      }
   
public int existeUsuario(String usuario)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conexion = getConectar();
        String sql = "SELECT count(id) FROM usuario where username= ?";
        try
        {
            ps = conexion.prepareStatement(sql);
            ps.setString(1,usuario);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getInt(1);
            }
            return 1;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error 001" + ex.getMessage());
            return 1;
        }
      }

    
    
}