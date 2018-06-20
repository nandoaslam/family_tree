/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexao.ConexaoBD;
import DAO.RelacionamentoDao;
import DAO.UsuarioDao;
import Main.Editarconta;
import Main.Principal;
import Model.Usuario;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class EditarcontaController implements Initializable {

    @FXML
    private Label idconta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSenha;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logar();
        
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            abrePrincipal();
            fecha();
         });
        
        btSalvar.setOnMouseClicked((MouseEvent e)->{
            
            try {
                alterar();
                fecha();
            } catch (SQLException ex) {
                Logger.getLogger(EditarcontaController.class.getName()).log(Level.SEVERE, null, ex);
            }
         });  
    }
    private void fecha() {
        Editarconta.getStage().close();
    }
    private boolean alterar() throws SQLException{
        
        String nome = txtNome.getText().trim();
         
     if (nome.equals("")){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText("Nome parente vazio!");
         alert.show();
         return false;
    }   
     String email = txtEmail.getText().trim();
        
   if (email == null){
        Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText("Nome parente vazio!");
         alert.show();
         return false;
   } 
        String senha = txtSenha.getText().trim();
        
   if (senha == null){
        Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText("Nome parente vazio!");
         alert.show();
         return false;
   }   
        Usuario u = new Usuario(nome, email, senha);
        UsuarioDao dao = new UsuarioDao();
        
       if (dao.update(u)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Dados Alterados");
                alert.showAndWait();
                fecha();
               abrePrincipal();
                
       }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Dados não cadastrado!");
                alert.show();
            }
        return true;
    }
    
    private void logar() {
    RelacionamentoDao d = new RelacionamentoDao();
        int ID = RelacionamentoDao.ID;
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();
        
        try {
            Statement statement=connection.createStatement();
            String sql="select nome, email, senha from usuario where idusuario = "+ID+";"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            
            if (resultSet.next()){
                txtNome.setText(resultSet.getString("nome"));
                txtEmail.setText(resultSet.getString("email"));
                txtSenha.setText(resultSet.getString("senha"));
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
         
    }}
   

