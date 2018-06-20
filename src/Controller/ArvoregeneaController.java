package Controller;

import Conexao.ConexaoBD;
import DAO.RelacionamentoDao;
import Main.Arvoregenea;
import Main.Principal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ArvoregeneaController implements Initializable {
@FXML
    private Separator lacirmaos;

    @FXML
    private Separator lcpaiavos2;

    @FXML
    private Separator lcpaiavos;

    @FXML
    private Button IDusu;

    @FXML
    private Button IDmae;

    @FXML
    private Button IDpai;

    @FXML
    private Button IDavomaterno;

    @FXML
    private Button idvoltar;

    @FXML
    private Separator lacfilho;

    @FXML
    private Button IDconjugue;

    @FXML
    private Button IDfilho;

    @FXML
    private Separator lacconjugue;

    @FXML
    private Button IDfilhoA;

    @FXML
    private Button IDaavomaterno;

    @FXML
    private Separator lcavosmaternos2;

    @FXML
    private Separator lcavosmaternos;

    @FXML
    private Button IDaavopaterno;

    @FXML
    private Button IDavopaterno;

    @FXML
    private Button IDirmaoA;

    @FXML
    private Button IDirmao;

    @FXML
    private Separator lcirmaos2;

    @FXML
    private Separator lcirmaoss;

    @FXML
    private Button IDtio;

    @FXML
    private Separator lctio;
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       IDusu();
       IDmae();
       IDpai();
       AvoPat();
       Conjugue();
       Irmao();
       IrmaoA();
       filho();
       filhoA();
       AvooPat();
       AvooMat();
       AvoMat();
       TioPat();
       
       
       idvoltar.setOnMouseClicked((MouseEvent e)->{
            abrePrincipal();
            fecha();
         });
    }  
    private void TioPat() {
        IDtio.setVisible(false);
        lctio.setVisible(false);
    RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'tio(a) paterno' and usuario_idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDtio.setText(resultSet.getString("nomeparente"));
                IDtio.setVisible(true);
                lctio.setVisible(true);
            }
        }
        catch (SQLException e) {
        }
    }
    private void IDmae() {
    RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'Mãe' and usuario_idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDmae.setText(resultSet.getString("nomeparente"));
            }
        }
        catch (SQLException e) {
        }
    }
    
    private void IDpai() {     
    RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'Pai' and usuario_idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDpai.setText(resultSet.getString("nomeparente"));
            }
        }
        catch (SQLException e) {
        }
    }

    private void IDusu() {
    RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nome from usuario where idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDusu.setText(resultSet.getString("nome"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
     private void AvooMat() {
        IDaavomaterno.setVisible(false);
        lcpaiavos.setVisible(false);
        lcpaiavos2.setVisible(false);
        
        RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'avó materna (F)' and usuario_idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDaavomaterno.setText(resultSet.getString("nomeparente"));                
                IDaavomaterno.setVisible(true);
                lcpaiavos.setVisible(true);
                lcpaiavos2.setVisible(true);
                
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
     private void AvoMat() {
        IDavomaterno.setVisible(false);
        RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'avô materno (M)' and usuario_idusuario = "+ID+" and sexo = 'M';"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDavomaterno.setText(resultSet.getString("nomeparente"));                
                IDavomaterno.setVisible(true);
              }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void AvooPat() {
        IDaavopaterno.setVisible(false);
        lcavosmaternos2.setVisible(false);
        lcavosmaternos.setVisible(false);
        RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'avó paterna (F)' and usuario_idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDaavopaterno.setText(resultSet.getString("nomeparente"));                
                IDaavopaterno.setVisible(true);
                lcavosmaternos2.setVisible(true);
                lcavosmaternos.setVisible(true);
                
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void AvoPat() {
        IDavopaterno.setVisible(false);
        RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'avô paterno (M)' and usuario_idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDavopaterno.setText(resultSet.getString("nomeparente"));                
                IDavopaterno.setVisible(true);
                
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void Irmao() {
        IDirmao.setVisible(false);
        lcirmaoss.setVisible(false);
        RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'irmão(a)' and usuario_idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
           if (resultSet.next()){
                IDirmao.setText(resultSet.getString("nomeparente"));
                IDirmao.setVisible(true);
                lcirmaoss.setVisible(true);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void IrmaoA() {
        IDirmaoA.setVisible(false);
        lcirmaos2.setVisible(false);
        RelacionamentoDao a = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select (nomeparente) from relacionamento where tiporelacao = 'irmão(a)' and usuario_idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDirmaoA.setText(resultSet.getString("nomeparente"));
                IDirmaoA.setVisible(true);
                lcirmaos2.setVisible(true);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void Conjugue() {
        IDconjugue.setVisible(false);
        lacconjugue.setVisible(false);
        RelacionamentoDao a = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente,last_insert_id(idrelacionamento) from relacionamento where tiporelacao = 'esposo(a)' and usuario_idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDconjugue.setText(resultSet.getString("nomeparente"));
                IDconjugue.setVisible(true);
                lacconjugue.setVisible(true);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void filho() {
        IDfilho.setVisible(false);
        lacfilho.setVisible(false);
        RelacionamentoDao a = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'filho(a)' and usuario_idusuario = "+ID+";";
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDfilho.setText(resultSet.getString("nomeparente"));
                IDfilho.setVisible(true);
                lacfilho.setVisible(true);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
      private void filhoA() {
        IDfilhoA.setVisible(false);
        lacirmaos.setVisible(false);
        RelacionamentoDao a = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nomeparente from relacionamento where tiporelacao = 'filho(a)' and usuario_idusuario = "+ID+";";
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                IDfilhoA.setText(resultSet.getString("nomeparente"));
                IDfilhoA.setVisible(true);
                lacirmaos.setVisible(true);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void abrePrincipal(){
        Principal p = new Principal();
        fecha();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarPessoaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fecha() {
        Arvoregenea.getStage().close();
    }
}

