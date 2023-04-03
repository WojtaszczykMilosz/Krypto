package pl.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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

    File OpenFile() {
        chooser = new FileChooser();
        chooser.setInitialDirectory(new File("."));
        chooser.setTitle("Open Resource File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        return chooser.showOpenDialog(stage);
    }


    File saveFile() {
        chooser = new FileChooser();
        chooser.setInitialDirectory(new File("."));
        chooser.setTitle("Save File in resources");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));

        return chooser.showSaveDialog(stage);
    }
}
