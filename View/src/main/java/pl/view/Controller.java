package pl.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    protected Stage stage;

    private Scene scene;

    private Parent parent;

    protected FileChooser chooser;

    void switchPanelTo(ActionEvent event, String fileName){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource(fileName));
        try {
            parent = loader.load();
        } catch (IOException e) {

        }
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}
