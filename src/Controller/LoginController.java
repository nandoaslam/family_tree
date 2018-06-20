package Controller;

import Conexao.ConexaoBD;
import Main.CadastrarLogin;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Main.Login;
import Main.Principal;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.glass.events.WindowEvent;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.event.EventHandler;

public class LoginController implements Initializable {
    @FXML private Button btEntrar;
     @FXML
    private JFXTextField textEmail;

    @FXML
    private JFXPasswordField textPassword;
    @FXML private Label label;
    @FXML private Button btCadastrar;
    @FXML
    private MaterialDesignIconView btclose;

 
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
        btEntrar.setOnMouseClicked((MouseEvent e)->{
            logar();
        });
        btEntrar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                logar();
            }
        });
        
            
        
        btclose.setOnMouseClicked((MouseEvent e)-> Platform.exit());
       
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            CadastrarLogin login = new CadastrarLogin();
            fecha();
            try {
                login.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
      
        
    }
    private void fecha() {
        Login.getStage().close();
    }
    private void logar() {
    
        ConexaoBD conexao=new ConexaoBD();
        Connection connection=conexao.estabelecerConexao();

        try {
            Statement statement=connection.createStatement();
            String sql="SELECT * FROM usuario WHERE email= '"+textEmail.getText()+"' "
                    + "AND senha = '"+textPassword.getText()+"';"; 
            ResultSet resultSet=statement.executeQuery(sql);       
            /*Aqui ele pega o id do usuario*/
            if (resultSet.next()){
                int id = resultSet.getInt("idusuario");
                DAO.RelacionamentoDao.setID(id);
            Principal p = new Principal();
            fecha();
               try {
                p.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
                }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Erro");
            alert.setTitle("Login Inválido");
            alert.setContentText("Usuário ou senha não cadastrado!");
            alert.show();
        }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

    

    }
  
}

