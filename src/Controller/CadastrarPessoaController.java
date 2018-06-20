/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RelacionamentoDao;
import Main.CadastrarPessoa;
import Main.Principal;
import Model.Relacionamento;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class CadastrarPessoaController implements Initializable {
    
  
     @FXML
    private TextField txtNome;
    @FXML
    private Button btCadastrar;
    @FXML
    private Button btCancelar;
    @FXML
    private ComboBox<Relacionamento> cbparentesco;
    @FXML
    
    private List<Relacionamento> categoriaparentesco = new ArrayList<>();

    private ObservableList<Relacionamento>list;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriaparentesco();
       
        btCadastrar.setOnMouseClicked((MouseEvent e)->{
            try {
                cadastrarParente();
            } catch (SQLException ex) {
                Logger.getLogger(CadastrarPessoaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        btCadastrar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                try {
                    cadastrarParente();
                } catch (SQLException ex) {
                    Logger.getLogger(CadastrarPessoaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btCancelar.setOnMouseClicked((MouseEvent e)->{
          abrePrincipal();
        });
        
        btCancelar.setOnKeyPressed((KeyEvent e)->{
            if(e.getCode() == KeyCode.ENTER){
                abrePrincipal();
            }
        });
        
              
    }
       public void categoriaparentesco() {
           
           Relacionamento tipo1 = new Relacionamento("avó materna (F)");
           Relacionamento tipo2 = new Relacionamento("avô materno (M)");
           Relacionamento tipo3 = new Relacionamento("irmão(a)");
           Relacionamento tipo4 = new Relacionamento("avó paterna (F)");
           Relacionamento tipo5 = new Relacionamento("avô paterno (M)");
           Relacionamento tipo6 = new Relacionamento("tio(a) paternos");
           Relacionamento tipo7 = new Relacionamento("tio(a) maternos");
           Relacionamento tipo8 = new Relacionamento("primo(a)");
           Relacionamento tipo9 = new Relacionamento("esposo(a)");
           Relacionamento tipo10 = new Relacionamento("filho(a)");
           
           
           
           categoriaparentesco.add(tipo1);
           categoriaparentesco.add(tipo2);
           categoriaparentesco.add(tipo3);
           categoriaparentesco.add(tipo4);
           categoriaparentesco.add(tipo5);
           categoriaparentesco.add(tipo6);
           categoriaparentesco.add(tipo7);
           categoriaparentesco.add(tipo8);
           categoriaparentesco.add(tipo9);
           categoriaparentesco.add(tipo10);
           
      
           list = FXCollections.observableArrayList(categoriaparentesco);               
           
           cbparentesco.setItems(list);
           cbparentesco.setPromptText("Selecione o Parentesco");
           
    }
    public void fecha(){
        CadastrarPessoa.getStage().close();
    }
   
     private boolean cadastrarParente() throws SQLException{
         String nomeparente = txtNome.getText().trim();
         
     if (nomeparente.equals("")){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText("Nome parente vazio!");
         alert.show();
         return false;
    }     
   
   if (cbparentesco.getValue() == null){
        Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setHeaderText("Parentesco vazio!");
         alert.show();
         return false;
   } 
 
  
   String tiporelacao = cbparentesco.getSelectionModel().getSelectedItem().toString().trim(); 
        Relacionamento p = new Relacionamento(nomeparente, tiporelacao, 0);
        RelacionamentoDao dao = new RelacionamentoDao();
        
       if (dao.inserir(p)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Parente cadastrado!");
                alert.showAndWait();
                fecha();
               abrePrincipal();
                
       }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Parente não cadastrado!");
                alert.show();
            }
        return true;
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

    
   

