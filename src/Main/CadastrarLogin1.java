/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author E
 */
public class CadastrarLogin1 extends Application{
     private static Stage stage;//Abre a janela 1

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View/CadastrarLogin1.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Cadastro de Parentes");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();//Abre a janela 2
        setStage(stage);
        
        
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        CadastrarLogin1.stage = stage;
    }
}
