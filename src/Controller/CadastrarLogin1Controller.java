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
public class CadastrarLogin1Controller implements Initializable {

    @FXML
    private Button btCancelar;
    @FXML
    private Button btProximo;
    @FXML
    private TextField txtPai;
       @FXML
    private TextField txtAvoPaterno;

    @FXML
    private TextField txtAvooPaterno;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btProximo.setOnMouseClicked((MouseEvent e)->{
            try {
                cadastrarParenteLogin();
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarLogin1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        btProximo.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                try {
                    cadastrarParenteLogin();
                                    
                } catch (SQLException ex) {
                    Logger.getLogger(CadastrarLogin1Controller.class.getName()).log(Level.SEVERE, null, ex);
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
    public void abreLogin(){
         Login l = new Login();
         fecha();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
    public void abreLogin2(){
         CadastrarLogin2 clc = new CadastrarLogin2();
         fecha();
        try {
            clc.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarLogin1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void fecha(){
        CadastrarLogin1.getStage().close();
    }
    
    private boolean cadastrarParenteLogin() throws SQLException{
         /*Aqui ele não recebe valores nulos*/
         String tiporelacao = "Pai";
         String nomeparente = txtPai.getText().trim();
         
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
                alert.setHeaderText("Agora cadastre o nome da sua mãe");
                alert.showAndWait();
                fecha();
                abreLogin2();
       }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Parente não cadastrado!");
                alert.show();
            }
            l.setNomeparente(nomeparente);
            l.setTiporelacao(tiporelacao);
           
        return true;
     }
    
}
    