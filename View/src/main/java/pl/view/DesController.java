package pl.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import pl.model.OperacjePlikowe;
import pl.model.PotrojnyDES;

import java.io.*;


public class DesController extends Controller {


    PotrojnyDES desAlgorithm;

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
        try {
            desAlgorithm.ustawKlucze(key1.getText().getBytes(charset),
                    key2.getText().getBytes(charset), key3.getText().getBytes(charset));
            if (textChoice.isDisabled()) {

                tekstJawny = desAlgorithm.deszyfrujWiadomosc(tekstZaszyfrowany);
                plainText.setText(new String(tekstJawny,charset));
            } else {

                byte[] file = OperacjePlikowe.wczytajZpliku(fileToOperateOn.getAbsolutePath());
                plikPoOperacji = desAlgorithm.deszyfrujWiadomosc(file);

                saveFileBtn.setVisible(true);
            }

        }catch (IOException e) {
            exceptionWindow(e);
        }

    }

    @FXML
    void encrypt(ActionEvent event) {
        try {
            desAlgorithm.ustawKlucze(key1.getText().getBytes(charset),
                    key2.getText().getBytes(charset), key3.getText().getBytes(charset));
            if (textChoice.isDisabled()) {

                tekstJawny = plainText.getText().getBytes(charset);
                tekstZaszyfrowany = desAlgorithm.szyfrujWiadomosc(tekstJawny);
                cipherText.setText(new String(tekstZaszyfrowany,charset));
            } else {

                byte[] file = OperacjePlikowe.wczytajZpliku(fileToOperateOn.getAbsolutePath());
                plikPoOperacji = desAlgorithm.szyfrujWiadomosc(file);

                saveFileBtn.setVisible(true);
            }

        }catch (IOException e) {
            exceptionWindow(e);
        }


    }


    File OpenFile() {
        chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        return chooser.showOpenDialog(stage);
    }


    File saveFile() {
        chooser = new FileChooser();
        chooser.setTitle("Save File in resources");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));

        return chooser.showSaveDialog(stage);
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
    void switchToElglamal(ActionEvent event) {
        switchPanelTo(event,"elglamal.fxml");

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
        desAlgorithm = new PotrojnyDES();
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
