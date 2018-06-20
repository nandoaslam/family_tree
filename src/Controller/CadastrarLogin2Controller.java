/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RelacionamentoDao;
import Main.CadastrarLogin1;
import Main.CadastrarLogin2;
import Main.Login;
import Model.Relacionamento;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author E
 */
public class CadastrarLogin2Controller implements Initializable {

    @FXML
    private Button btCancelar;
    @FXML
    private Button btSalvar;
    @FXML
    private TextField txtMae;
    @FXML
    private TextField txtAvoM;
    @FXML
    private TextField txtAvooM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       btSalvar.setOnMouseClicked((MouseEvent e)->{
            try {
                cadastrarParenteLogin();
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        btSalvar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                try {
                    cadastrarParenteLogin();
                } catch (SQLException ex) {
                    Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
   public void fecha(){
        CadastrarLogin2.getStage().close();
    }
   private boolean cadastrarParenteLogin() throws SQLException{
         /*Aqui ele não recebe valores nulos*/
         String tiporelacao = "Mãe";
         String nomeparente = txtMae.getText().trim();
         
     if (nomeparente.equals("")){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText("Campo pai vazio!");
         alert.show();
         return false;
    }         
       Relacionamento l = new Relacionamento(nomeparente, tiporelacao, 0);
       RelacionamentoDao dao = new RelacionamentoDao();
       if (dao.inserir(l)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Dados Cadastrados com sucesso! Agora faça o login");
                alert.showAndWait();
                fecha();
                abreLogin();
       }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Parentes não cadastrado!");
                alert.show();
            }
            l.setNomeparente(nomeparente);
            l.setTiporelacao(tiporelacao);
           
        return true;
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
}
