package pl.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.model.OperacjePlikowe;
import pl.model.PotrojnyDES;

import java.io.File;
import java.io.IOException;

public class ElglamalController extends Controller {

    @FXML
    void switchTo3Des(ActionEvent event) {
        switchPanelTo(event,"des.fxml");
    }
    String charset = "UTF-8";

    File fileToOperateOn;

    byte[] tekstZaszyfrowany;

    byte[] tekstJawny;

    byte[] plikPoOperacji;



    @FXML
    private Button fileReadBtn;

    @FXML
    private Button saveFileBtn;

    @FXML
    private TextField fileNameTextField;

    @FXML
    private TextArea cipherText;


    @FXML
    private Button fileChoice;

    @FXML
    private TextField key1;

    @FXML
    private TextField key2;

    @FXML
    private TextField key3;

    @FXML
    private TextArea plainText;

    @FXML
    private Button textChoice;

    void switchShown(boolean showText) {
        fileChoice.setDisable(!showText);
        textChoice.setDisable(showText);

        fileChoice.requestFocus();
        textChoice.requestFocus();


        plainText.setVisible(showText);
        cipherText.setVisible(showText);

        fileReadBtn.setVisible(!showText);
        saveFileBtn.setVisible(!showText);
        fileNameTextField.setVisible(!showText);

        saveFileBtn.setVisible(false);
        fileNameTextField.setText("");
        fileToOperateOn = null;
        fileNameTextField.setDisable(true);

    }

    @FXML
    void decrypt(ActionEvent event) {


    }

    @FXML
    void encrypt(ActionEvent event) {



    }




    @FXML
    void readKeys(ActionEvent event) {
        File selectedFile = OpenFile();
        if (selectedFile != null) {
            try {

                byte[] byteTab = OperacjePlikowe.wczytajZpliku(selectedFile.getAbsolutePath());
                String text = new String(byteTab,"UTF-8");
                String[] tab=text.split("\n");
                key1.setText(tab[0]);
                key2.setText(tab[1]);
                key3.setText(tab[2]);
            } catch (IOException e) {

            }
        }

    }

    @FXML
    void saveKeys(ActionEvent event) {
        File plik = saveFile();

        String str = String.join("\n",key1.getText(),key2.getText(),key3.getText());

        if (plik != null) {
            OperacjePlikowe.zapiszDoPliku(plik.getAbsolutePath(),str.getBytes());

        }

    }



    @FXML
    void textClick(ActionEvent event) {
        switchShown(true);
    }

    @FXML
    void fileClick(ActionEvent event) {
        switchShown(false);
    }

    @FXML
    void fileRead(ActionEvent event) {
        fileToOperateOn = OpenFile();
        if (fileToOperateOn != null)
            fileNameTextField.setText(fileToOperateOn.getName());
        saveFileBtn.setVisible(false);
        fileNameTextField.setDisable(false);
    }

    @FXML
    void saveFile(ActionEvent event) {
        File plik = saveFile();
        if (plik != null) {
            OperacjePlikowe.zapiszDoPliku(plik.getAbsolutePath(),plikPoOperacji);

        }
    }

    @FXML
    void initialize(){
        fileReadBtn.setVisible(false);
        saveFileBtn.setVisible(false);
        fileNameTextField.setVisible(false);
        fileNameTextField.setDisable(true);
    }


    public void exceptionWindow(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setContentText(e.getMessage());
        alert.setHeaderText("Wystąpił błąd");
        alert.showAndWait();
    }


}
