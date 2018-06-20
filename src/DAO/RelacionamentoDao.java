package DAO;

import Conexao.ConexaoBD;
import Controller.PrincipalController;
import Model.Relacionamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;


public class RelacionamentoDao {
    private Connection con;
    private ComboBox<Relacionamento> cbparentesco;
    public static int IDParente;
    
    public static int getIDParente(){
        return IDParente;
    }
    
    public static void setIDParente(int IDParente){
         RelacionamentoDao.IDParente = IDParente;
    }
    /*Aqui ele guarda o id do usuario*/
    public static int ID;
    
    public static int getID(){
        return ID;
    }
    
    public static void setID(int ID){
         RelacionamentoDao.ID = ID;
    }
    
    
    public void setConnection(Connection con) {
       this.con = con;
    }
    public RelacionamentoDao(){
        this.con = new ConexaoBD().estabelecerConexao();
    }   
    
    public boolean inserir(Relacionamento r) throws SQLException{
        
    
      String sql = "INSERT INTO relacionamento(nomeparente, tiporelacao,usuario_idusuario) VALUES (?,?,?);"; 
           
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, r.getNomeparente());
            stmt.setString(2, r.getTiporelacao());
            /*Aqui ele inseri o id na tabela relação, quando ele faz o relacionamento*/
            stmt.setInt(3, ID);
            stmt.executeUpdate();
            System.out.println(ID); 
            return true;
    }
    
     public List<Relacionamento> getList() {
        List<Relacionamento> rela = new ArrayList<>();
        String sql = "select idrelacionamento, nomeparente, tiporelacao from relacionamento where usuario_idusuario = "+ID+";";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Relacionamento p = new Relacionamento();
                p.setIdrelacionamento(rs.getString("idrelacionamento"));
                p.setNomeparente(rs.getString("nomeparente"));
                p.setTiporelacao(rs.getString("tiporelacao"));
                
                rela.add(p);
            }
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro, Lista não retornada");
            return null;
        }
        return rela;
                
    }
        
   public boolean update(Relacionamento p){
       String sql = "UPDATE relacionamento SET nomeparente = ? where idrelacionamento = ?;"; 
    
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNomeparente());
             stmt.setString(2, p.getIdrelacionamento());
            stmt.executeUpdate();
            con.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(RelacionamentoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(Relacionamento p){
        String sql = "DELETE FROM relacionamento WHERE idrelacionamento = ?"; 

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getIdrelacionamento());
            stmt.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(RelacionamentoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
}

}