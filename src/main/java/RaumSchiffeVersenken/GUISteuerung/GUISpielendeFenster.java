package RaumSchiffeVersenken.GUISteuerung;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static RaumSchiffeVersenken.GUISteuerung.GUIStartFenster.spieler1Leben;
import static RaumSchiffeVersenken.GUISteuerung.GUIStartFenster.spieler2Leben;


public class GUISpielendeFenster implements Initializable {

    GUIKlangSteuerung guiKlangSteuerung = new GUIKlangSteuerung();

    @FXML
    private VBox vbox;

    @FXML
    private Label siegesNachricht;

    /**
     *
     * @throws IOException
     */
    @FXML
    private void spielEnde() throws IOException {
        Stage spielefenster = (Stage) vbox.getScene().getWindow();
        spielefenster.close();
    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (spieler1Leben == 0) {
            siegesNachricht.setText("SPIELER 2 HAT GEWONNEN!");
        } else if (spieler2Leben == 0) {
            siegesNachricht.setText("SPIELER 1 HAT GEWONNEN!");
        }
    }

    /**
     *
     */
    @FXML
    private void knopfZielen() {
        guiKlangSteuerung.knopfZielen();
    }
}
