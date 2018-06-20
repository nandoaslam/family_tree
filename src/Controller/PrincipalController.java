package Controller;

import Conexao.ConexaoBD;
import DAO.RelacionamentoDao;
import Main.Arvoregenea;
import Main.CadastrarPessoa;
import Main.Editarconta;
import Main.Login;
import Main.Principal;
import Model.Relacionamento;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author E
 */
public class PrincipalController implements Initializable {
    
    
    @FXML private TableView<Relacionamento> Tabela;
    
    @FXML private TableColumn<Relacionamento, String> clnNome;
    @FXML private TableColumn<Relacionamento, String> clnParentesco;
    
    @FXML private Button btnAtualizar;
    @FXML private Button btnDeletar;
    @FXML private Button btnPesquisar;
    @FXML private TextField txtPesquisar;
    
    @FXML private Button btCadFamiliar;
    
    @FXML private Button btVisualizar;
    
    @FXML private Button btSair;
    
    @FXML
    private Label idconta;
    
    @FXML
    private TextField txtNomeParente;

    @FXML
    private Button btSalvarParente;

    @FXML
    private MaterialDesignIconView btclose;
    @FXML
    private Button btatualizar;
    @FXML
    private Label idparent;
    
     private Connection con;
    
    private ObservableList<Relacionamento> relacionamentos = FXCollections.observableArrayList();
    
     public void setConnection(Connection con) {
       this.con = con;
    }
    public PrincipalController(){
        this.con = new ConexaoBD().estabelecerConexao();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       initTable();       
     
       btclose.setOnMouseClicked((MouseEvent e)-> Platform.exit());
       
     btVisualizar.setOnMouseClicked((MouseEvent e) -> {
        Arvoregenea ar = new Arvoregenea();
        fecha();
        try {
            ar.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
       btSair.setOnMouseClicked((MouseEvent e)->{
          login();
     });
       
    
    btCadFamiliar.setOnMouseClicked((MouseEvent e) -> {
        CadastrarPessoa pessoa = new CadastrarPessoa();
        fecha();
        try {
            pessoa.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastrarLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });
        btSalvarParente.setOnMouseClicked((MouseEvent e)->{
           try {
               alterar();
           } catch (SQLException ex) {
               Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
           }
         });
        
        idparent.setOnMouseClicked((MouseEvent e)->{
           try {
               alterar();
           } catch (SQLException ex) {
               Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
           }
         });
        idconta.setOnMouseClicked((MouseEvent e)->{
            editar();
         });
        btatualizar.setOnMouseClicked((MouseEvent e)->{
            fecha();
            abrePrincipal();
         });
        txtPesquisar.setOnKeyReleased((KeyEvent e)->{
            Tabela.setItems(busca());
        });
        
        btnDeletar.setOnMouseClicked((MouseEvent e)->{
            deleta();
        });
        
        Tabela.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> MostrarParente(newValue)); 
              
    }
    public void initTable(){
      
       clnNome.setCellValueFactory(new PropertyValueFactory<>("nomeparente"));
       clnParentesco.setCellValueFactory(new PropertyValueFactory<>("tiporelacao"));
       Tabela.setItems(atualizaTabela());
   }
   
   public ObservableList<Relacionamento> atualizaTabela(){
       RelacionamentoDao dao = new RelacionamentoDao();
       relacionamentos = FXCollections.observableArrayList(dao.getList());
       return relacionamentos;
   }
   private void fecha() {
        Principal.getStage().close();
    }
   private void fechalogin() {
        Login.getStage().close();
    }
   private void editar() {
        Editarconta a = new Editarconta();
        fecha();
        try {
            a.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public void abrePrincipal(){
        Principal p = new Principal();
        fecha();
        try {
            p.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
    
        }    }
   private void login() {
        Login a = new Login();
        fecha();
        try {
            a.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public void deleta(){
       String idparente = idparent.getText();  
    
              Relacionamento r = new Relacionamento(idparente, idparente);
              RelacionamentoDao dao = new RelacionamentoDao();
        
       if (dao.delete(r)){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Parente deletado com sucesso!");
            a.show();
            atualizaTabela();
       }else{
           Alert a = new Alert(Alert.AlertType.WARNING);
           a.setHeaderText("Parente não deletado");
           a.show();
       }
   }
    public void MostrarParente(Relacionamento relacionamento) {
       txtNomeParente.setText(relacionamento.getNomeparente());
        idparent.setText(relacionamento.getIdrelacionamento());
        
        
    }private boolean alterar() throws SQLException{
      String nomeparente = txtNomeParente.getText().trim();
      String idparente = idparent.getText();  
         
      if ( btSalvarParente == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Selecione um parente");
                alert.showAndWait();
                return false;
                 }
                 if (nomeparente.equals("")){
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setHeaderText("Nome parente vazio!");
                  alert.show();
                  return false;
    }
              Relacionamento r = new Relacionamento(idparente, nomeparente);
              RelacionamentoDao dao = new RelacionamentoDao();
        
       if (dao.update(r)){
                Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Dados alterados com sucesso!");
            a.show();
           
       }else{
           Alert a = new Alert(Alert.AlertType.WARNING);
           a.setHeaderText("Dados não alterados");
           a.show();
       }
        return true;
    }

   private ObservableList<Relacionamento> busca(){
       ObservableList<Relacionamento> pessoaPesquisa = FXCollections.observableArrayList();
       for(int x = 0;x < relacionamentos.size();x++){
       if(relacionamentos.get(x).getNomeparente().toLowerCase().contains(txtPesquisar.getText().toLowerCase())){
           pessoaPesquisa.add(relacionamentos.get(x));
       }
   }
       return pessoaPesquisa; 
  
   }
}
