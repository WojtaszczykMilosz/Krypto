package pl.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.model.OperacjePlikowe;
import pl.model.ElGamal;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

public class ElgamalController extends Controller {

    @FXML
    void switchTo3Des(ActionEvent event) {
        switchPanelTo(event,"des.fxml");
    }
    String charset = "UTF-8";

    ElGamal gamal;

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
    private TextField x;

    @FXML
    private TextField g;

    @FXML
    private TextField y;

    @FXML
    private TextField p;

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
            if (textChoice.isDisabled()) {

                tekstJawny = gamal.deszyfrujWiadomosc(tekstZaszyfrowany);
                plainText.setText(new String(tekstJawny,charset));
            } else {

                byte[] file = OperacjePlikowe.wczytajZpliku(fileToOperateOn.getAbsolutePath());
                plikPoOperacji = gamal.deszyfrujWiadomosc(file);

                saveFileBtn.setVisible(true);
            }

        }catch (IOException e) {
            exceptionWindow(e);
        }


    }

    @FXML
    void encrypt(ActionEvent event) {
        try {
            if (textChoice.isDisabled()) {

                tekstJawny = plainText.getText().getBytes(charset);
                tekstZaszyfrowany = gamal.szyfrujWiadomosc(tekstJawny);
                cipherText.setText(new String(tekstZaszyfrowany,charset));
            } else {

                byte[] file = OperacjePlikowe.wczytajZpliku(fileToOperateOn.getAbsolutePath());
                plikPoOperacji = gamal.szyfrujWiadomosc(file);
                saveFileBtn.setVisible(true);
            }

        }catch (IOException e) {
            exceptionWindow(e);
        }


    }




    @FXML
    void readKeys(ActionEvent event) {
        File selectedFile = OpenFile();
        if (selectedFile != null) {
            try {

                byte[] byteTab = OperacjePlikowe.wczytajZpliku(selectedFile.getAbsolutePath());
                String text = new String(byteTab,charset);
                String[] tab=text.split("\n");
                x.setText(tab[0]);
                y.setText(tab[1]);
                g.setText(tab[2]);
                p.setText(tab[3]);
                gamal.ustawKlucze(new BigInteger(p.getText(),16),new BigInteger(g.getText(),16)
                        ,new BigInteger(x.getText(),16),new BigInteger(y.getText(),16));
            } catch (IOException e) {
                exceptionWindow(e);
            }
        }

    }

    @FXML
    void saveKeys(ActionEvent event) {
        File plik = saveFile();

        String str = String.join("\n",x.getText(),y.getText(),g.getText(),p.getText());

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
    void confirmKeys(){
        try {
            gamal.ustawKlucze(new BigInteger(p.getText(),16),new BigInteger(g.getText(),16)
                    ,new BigInteger(x.getText(),16),new BigInteger(y.getText(),16));
        }catch (IOException e){
            exceptionWindow(e);
        }

    }

    @FXML
    void generateKeys(){
        gamal.generujKlucze();
        p.setText(gamal.getP());
        g.setText(gamal.getG());
        x.setText(gamal.getX());
        y.setText(gamal.getY());
    }

    @FXML
    void initialize(){
        fileReadBtn.setVisible(false);
        saveFileBtn.setVisible(false);
        fileNameTextField.setVisible(false);
        fileNameTextField.setDisable(true);

        gamal = new ElGamal();
        p.setText(gamal.getP());
        g.setText(gamal.getG());
        x.setText(gamal.getX());
        y.setText(gamal.getY());
    }


    public void exceptionWindow(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setContentText(e.getMessage());
        alert.setHeaderText("Wystąpił błąd");
        alert.showAndWait();
    }


}
