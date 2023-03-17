package pl.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ElglamalController extends Controller {

    @FXML
    void switchTo3Des(ActionEvent event) {
        switchPanelTo(event,"des.fxml");
    }

}
