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

import java.io.*;


public class DesController extends Controller {


    DES desAlgorithm;

    @FXML
    private Button ElglamalSwitch;

    @FXML
    private TextArea cipherText;

    @FXML
    private Button decryptButton;

    @FXML
    private Button encryptButton;

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


        textChoice.setFocusTraversable(false);

        plainText.setVisible(showText);
        cipherText.setVisible(showText);
    }

    @FXML
    void decrypt(ActionEvent event) {
        //desAlgorithm.SetKeys
        //
        //desAlgorithm.encrypt
        //naodwrut
    }

    @FXML
    void encrypt(ActionEvent event) {
        //desAlgorithm.SetKeys
        //
        //desAlgorithm.encrypt
    }



    @FXML
    void readKeys(ActionEvent event) {
        INPUT input = new INPUT();
        chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = chooser.showOpenDialog(stage);

        try {
            InputStream sin = new FileInputStream(selectedFile);
            key1.setText(new String(sin.readAllBytes(),"UTF-8"));

        } catch (IOException e) {

        }


//            key1.setText(input.)

    }

    @FXML
    void saveKeys(ActionEvent event) {
        chooser = new FileChooser();
        chooser.setTitle("Open Resource File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = chooser.showSaveDialog(stage);
        // i tu by trzeba metode napisac ktora zapisuje do pliku ale mi sie nie chce teraz

    }

    @FXML
    void switchToElglamal(ActionEvent event) {


        switchPanelTo(event,"elglamal.fxml");

    }

    @FXML
    void textClick(ActionEvent event) {
        switchShown(true);
    }





    //tu na razie nie wpadlem jeszcze na pomysl jak to prrzedstawic wizualnie
    //ale pewnie jakis przycisk z lewej do wczytania pliku i tylko moze jego nazwa

    // i po zaszyfrowaniu cos ze plik zaszyfrowany jest gotowy do zapisania i tez plik
    // i na odwrot po obu stronach wsm
    @FXML
    void fileClick(ActionEvent event) {
        switchShown(false);
    }





}
