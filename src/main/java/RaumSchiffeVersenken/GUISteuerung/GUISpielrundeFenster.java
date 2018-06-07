package RaumSchiffeVersenken.GUISteuerung;

import RaumSchiffeVersenken.Core.Feld;
import RaumSchiffeVersenken.Core.SpielablaufFX;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static RaumSchiffeVersenken.Core.SpielablaufFX.Feld_Spieler1;
import static RaumSchiffeVersenken.Core.SpielablaufFX.Feld_Spieler2;
import static RaumSchiffeVersenken.GUISteuerung.GUIStartFenster.spielerAktiv;
import static RaumSchiffeVersenken.GUISteuerung.GUIStartFenster.spieler1Leben;
import static RaumSchiffeVersenken.GUISteuerung.GUIStartFenster.spieler2Leben;

public class GUISpielrundeFenster implements Initializable {

    private boolean spielfeldAktiv = true;

    //referentziert GUI-Elemente der FXML-Datei
    @FXML
    private GridPane spielfeld1;

    @FXML
    private GridPane spielfeld2;

    @FXML
    private Label textAusgabe;

    @FXML
    private VBox spielefensterVBox;

    @FXML
    private void szeneWechsel() throws IOException {
        try {
            if (!spielfeldAktiv) {
                if (spielerAktiv == "1") {
                    spielerAktiv = "2";
                } else {
                    spielerAktiv = "1";
                }

                if (spieler1Leben == 0 | spieler2Leben == 0) {
                    Stage spielefenster = (Stage) spielefensterVBox.getScene().getWindow();
                    Parent quelle = FXMLLoader.load(getClass().getResource("/fxml/spielendeFenster.fxml"));
                    Scene szene2 = new Scene(quelle);
                    spielefenster.setScene(szene2);
                    szene2.getStylesheets().add("/gestaltung.css");
                    spielefenster.setFullScreen(false);
                } else {
                    Stage spielefenster = (Stage) spielefensterVBox.getScene().getWindow();
                    Parent quelle = FXMLLoader.load(getClass().getResource("/fxml/spielerwechselFenster.fxml"));
                    Scene szene2 = new Scene(quelle);
                    spielefenster.setScene(szene2);
                    szene2.getStylesheets().add("/gestaltung.css");
                    spielefenster.setFullScreen(false);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Die initialisierung des Fensters direkt vor dessen Aufrufen, verknüpft das GUI mit der Logik.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int feldSpalte = 10;
        int feldReihe = 10;

        SpielablaufFX sfx = new SpielablaufFX();


        //führt die Methode zum Bau des Spielfeldes auf je nachdem welcher Spieler gerade dran ist
        if (spielerAktiv == "1") {
            feldgrafikAktualisieren(feldSpalte, feldReihe, Feld_Spieler2, Feld_Spieler1);
        } else {
            feldgrafikAktualisieren(feldSpalte, feldReihe, Feld_Spieler1, Feld_Spieler2);
        }

        //starte die Spielablauf-Klasse im Hintergrund
        sfx.start(textAusgabe);
    }

    /**
     * Die Spielfelder werden mit der Feld-Klasse verknüpft und im Spielefenster grafisch dargestellt
     *
     * @param feldSpalte
     * @param feldReihe
     */
    public void feldgrafikAktualisieren(int feldSpalte, int feldReihe, Feld großesFeld, Feld kleinesFeld) {
        Image feldGrafik = new Image("images/weltraum.png");
        Image raumschiffGrafik = new Image("images/raumschiff.png");
        Image danebenGrafik = new Image("images/daneben.png");
        Image trefferGrafik = new Image("images/treffer.png");


        //die for-Schleifen befüllen beide Spielfelder mit Grafiken
        for (int y = 0; y < feldSpalte; y++) {
            for (int x = 0; x < feldReihe; x++) {
                ImageView grafikFeld1 = new ImageView();
                grafikFeld1.setFitWidth(50);
                grafikFeld1.setFitHeight(50);
                grafikFeld1.setId(""+(x)+(y));

                if (spielfeldAktiv) {
                    klickevent(großesFeld, grafikFeld1, danebenGrafik, trefferGrafik, feldSpalte, feldReihe);
                }

                if (großesFeld.mapGroesse[x][y] == 0) {
                    grafikFeld1.setImage(feldGrafik);
                    spielfeld1.add(grafikFeld1, x, y);
                } else if (großesFeld.mapGroesse[x][y] == 1) {
                    grafikFeld1.setImage(danebenGrafik);
                    spielfeld1.add(grafikFeld1, x, y);
                } else if (großesFeld.mapGroesse[x][y] == 5) {
                    grafikFeld1.setImage(feldGrafik);
                    spielfeld1.add(grafikFeld1, x, y);
                } else if (großesFeld.mapGroesse[x][y] == 6) {
                    grafikFeld1.setImage(trefferGrafik);
                    spielfeld1.add(grafikFeld1, x, y);
                }
            }
        }

        for (int y = 0; y < feldSpalte; y++) {
            for (int x = 0; x < feldReihe; x++) {
                ImageView grafikFeld2 = new ImageView();
                //grafikFeld2.setFitWidth(15);
                //grafikFeld2.setFitHeight(15);
                grafikFeld2.setId(""+x+"|"+y);

                if (kleinesFeld.mapGroesse[x][y] == 0) {
                    grafikFeld2.setImage(feldGrafik);
                    spielfeld2.add(grafikFeld2, x, y);
                } else if (kleinesFeld.mapGroesse[x][y] == 1) {
                    grafikFeld2.setImage(danebenGrafik);
                    spielfeld2.add(grafikFeld2, x, y);
                } else if (kleinesFeld.mapGroesse[x][y] == 5) {
                    grafikFeld2.setImage(raumschiffGrafik);
                    spielfeld2.add(grafikFeld2, x, y);
                } else if (kleinesFeld.mapGroesse[x][y] == 6) {
                    grafikFeld2.setImage(trefferGrafik);
                    spielfeld2.add(grafikFeld2, x, y);
                }
            }
        }
    }

    /**
     * Die Methode steuert die Textausgabe über das TextField-Feld unten im Fenster. Ihr kann Text als String übergeben
     * werden.
     *
     * @param textAusgabeText
     * @param textAusgabe
     */
    public static void textAusgabeSteuerung(String textAusgabeText, Label textAusgabe) {

        //erstellt ein Textelement für das Label
        textAusgabe.setText(textAusgabeText);
    }

    public void klickevent(Feld feld, ImageView grafikFeld1, Image danebenGrafik, Image trefferGrafik, int feldSpalte, int feldReihe) {
        grafikFeld1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (feld.mapGroesse[Character.getNumericValue(grafikFeld1.getId().charAt(0))][Character.getNumericValue(grafikFeld1.getId().charAt(1))] == 0) {
                    grafikFeld1.setImage(danebenGrafik);
                    feld.mapGroesse[Character.getNumericValue(grafikFeld1.getId().charAt(0))][Character.getNumericValue(grafikFeld1.getId().charAt(1))] = 1;

                    GUISpielrundeFenster.textAusgabeSteuerung("Daneben!", textAusgabe);
                    spielfeldAktiv = false;

                    //Button zum Weiterspielen muss jetzt hier erscheinen (vorher deaktivieren)

                    if (spielerAktiv == "1") {
                        feldgrafikAktualisieren(feldSpalte, feldReihe, Feld_Spieler2, Feld_Spieler1);
                    } else {
                        feldgrafikAktualisieren(feldSpalte, feldReihe, Feld_Spieler1, Feld_Spieler2);
                    }
                } else if (feld.mapGroesse[Character.getNumericValue(grafikFeld1.getId().charAt(0))][Character.getNumericValue(grafikFeld1.getId().charAt(1))] == 5) {
                    grafikFeld1.setImage(trefferGrafik);
                    feld.mapGroesse[Character.getNumericValue(grafikFeld1.getId().charAt(0))][Character.getNumericValue(grafikFeld1.getId().charAt(1))] = 6;
                    GUISpielrundeFenster.textAusgabeSteuerung("Treffer!", textAusgabe);

                    if (spielerAktiv == "1") {
                        feldgrafikAktualisieren(feldSpalte, feldReihe, Feld_Spieler2, Feld_Spieler1);
                        spieler2Leben -= 1;
                    } else {
                        feldgrafikAktualisieren(feldSpalte, feldReihe, Feld_Spieler1, Feld_Spieler2);
                        spieler1Leben -= 1;
                    }
                } else {
                    GUISpielrundeFenster.textAusgabeSteuerung("Nicht möglich!", textAusgabe);
                }
            }
        });
    }
}