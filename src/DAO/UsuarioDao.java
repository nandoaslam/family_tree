/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexao.ConexaoBD;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao {
    private Connection con;
    
    public UsuarioDao(){
        this.con = new ConexaoBD().estabelecerConexao();
    } 
    
    public boolean add(Usuario p){
    
       String sql = "INSERT INTO usuario(idusuario, nome, email, senha) VALUES (?,?,?,?);"; 
    
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getIdusuario());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getSenha());
        
            stmt.execute();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }     
    
    public boolean update(Usuario p){
       RelacionamentoDao rd = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
        
       String sql = "UPDATE usuario SET nome=?, email = ?, senha=? WHERE idusuario ="+ID+";"; 
    
        try {
           try (PreparedStatement stmt = con.prepareStatement(sql)) {
               stmt.setString(1, p.getNome());
               stmt.setString(2, p.getEmail());
               stmt.setString(3, p.getSenha());
               stmt.executeUpdate();
           }
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
    public boolean delete(Usuario p){
        String sql = "DELETE FROM cadastro WHERE id = ?;"; 

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, p.getIdusuario());
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
}
    
    
}