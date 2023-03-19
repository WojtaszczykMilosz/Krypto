package pl.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.model.DES;
import pl.model.INPUT;
import pl.model.PotrojnyDES;

import java.io.*;


public class DesController extends Controller {


    PotrojnyDES desAlgorithm;


    File fileToOperateOn;


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
    }

    @FXML
    void decrypt(ActionEvent event) {

//            desAlgorithm = new PotrojnyDES(key1.getText().getBytes("UTF-8"),
//                    key2.getText().getBytes("UTF-8"),key3.getText().getBytes("UTF-8"));
//            System.out.println(new String(cipherText.getText().getBytes("UTF-8")));
//        try {
//            plainText.setText(new String(desAlgorithm.deszyfrujWiadomosc(desAlgorithm.getBufor()), "UTF-8"));
//        } catch (UnsupportedEncodingException e){
//
//        }

    }

    @FXML
    void encrypt(ActionEvent event) {
        if (textChoice.isDisabled()) {
            try {
                desAlgorithm = new PotrojnyDES(key1.getText().getBytes("UTF-8"),
                        key2.getText().getBytes("UTF-8"), key3.getText().getBytes("UTF-8"));
                cipherText.setText(new String(desAlgorithm.szyfrujWiadomosc(plainText.getText().getBytes("UTF-8"))));
            } catch (UnsupportedEncodingException e) {

            }
        } else {
            try {
                desAlgorithm = new PotrojnyDES(key1.getText().getBytes("UTF-8"),
                        key2.getText().getBytes("UTF-8"), key3.getText().getBytes("UTF-8"));

                byte[] file = INPUT.wczytajZpliku(fileToOperateOn.getAbsolutePath());
//                cipherText.setText(new String(desAlgorithm.szyfrujWiadomosc(plainText.getText().getBytes("UTF-8"))));
            } catch (UnsupportedEncodingException e) {

            }


        }
    }

    File OpenFile() {
        chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
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

                byte[] byteTab = INPUT.wczytajZpliku(selectedFile.getAbsolutePath());
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
            INPUT.zapiszDoPliku(plik.getAbsolutePath(),str.getBytes());

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

    }

    @FXML
    void saveFile(ActionEvent event) {
        saveFile();
    }

    @FXML
    void initialize(){
        fileReadBtn.setVisible(false);
        saveFileBtn.setVisible(false);
        fileNameTextField.setVisible(false);
        fileNameTextField.setDisable(true);
    }



}
