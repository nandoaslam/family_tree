/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexao.ConexaoBD;
import DAO.UsuarioDao;
import Main.CadastrarLogin;
import Main.CadastrarLogin1;
import Main.Login;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.input.KeyEvent;
/**
 * FXML Controller class
 *
 * @author E
 */
public class CadastrarLoginController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btCancelar;
 
    @FXML
    private Button btProximo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btProximo.setOnMouseClicked((MouseEvent e)->{
            cadastrarLogin();
            try {
                buscarid();
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        btProximo.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                cadastrarLogin();
                try {
                    buscarid();
                } catch (SQLException ex) {
                    Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btCancelar.setOnMouseClicked((MouseEvent e)->{
          abreLogin();
        });
        
        btCancelar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abreLogin();
            }
        });
               
    }
    public void fecha(){
        CadastrarLogin.getStage().close();
    }
    
     private boolean cadastrarLogin(){
         /*Aqui ele não recebe valores nulos*/
         String nome = txtNome.getText().trim();
         
     if (nome.equals("")){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText("Campo nome vazio!");
         alert.show();
         return false;
    }     
        String email = txtEmail.getText(); 
        if (email.equals("")){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText("Campo email vazio!");
         alert.show();
         return false;
    }    
        String senha = txtSenha.getText();
        if (senha.equals("")){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText("Campo senha vazio!");
         alert.show();
         return false;
      }
      
       Usuario l = new Usuario(nome, email, senha);
       UsuarioDao dao = new UsuarioDao();
        
       if (dao.add(l)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Agora cadastre o nome do seu pai");
                alert.showAndWait();
                fecha();
                abreLogin1();
       }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Usuário não cadastrado!");
                alert.show();
            }
            l.setNome(nome);
            l.setEmail(email);
            l.setSenha(senha);
            
        return true;
     }
     private void buscarid() throws SQLException {
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();

        try{
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM usuario WHERE email= '"+txtEmail.getText()+"' "
                    + "AND senha = '"+txtSenha.getText()+"';"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            /*Aqui ele pega o id do usuario*/
            if (resultSet.next()){
                int id = resultSet.getInt("idusuario");
                DAO.RelacionamentoDao.setID(id);
            fecha();
           }
         } 
        catch (SQLException e) {
        }
     }
     public void abreLogin(){
         Login l = new Login();
         fecha();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
        public void abreLogin1(){
         CadastrarLogin1 clc = new CadastrarLogin1();
         fecha();
        try {
            clc.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      }
    
    

    

