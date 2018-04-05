package RaumSchiffeVersenken.Core;


public class RaumSchiffFactory {
    public int laenge;
    public int Model;
    public int xKoordinaten;
    public int yKoordinaten;

    /**
     * @param laenge       Das beschreibt mit dem Model die Größe und dementsprechend dann
     *                     auch das Model.
     * @param model        1 = Jäger 4x
     *                     2 = Frigatte 3x
     *                     4 = Zerstörer 2x
     *                     5 = Kreuzer 1x
     * @param xKoordinaten
     * @param yKoordinaten
     */
    public RaumSchiffFactory(int laenge, int model, int xKoordinaten, int yKoordinaten) {
        this.laenge = laenge;
        this.Model = model;
        this.xKoordinaten = xKoordinaten;
        this.yKoordinaten = yKoordinaten;
    }
/*
   Beispiel wie man ein Schiff erstellt mit dem Constructor
    RaumSchiffFactory zerstörer = new RaumSchiffFactory(4,4, 3, 10);
     */
public int indexAuswählen() {
    return 2;
}

    //-------------------------------------------------------------------------------
    public int getLaenge() {
        return laenge;
    }

    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public int getModel() {
        return Model;
    }

    public void setModel(int model) {
        Model = model;
    }

    public int getxKoordinaten() {
        return xKoordinaten;
    }

    public void setxKoordinaten(int xKoordinaten) {
        this.xKoordinaten = xKoordinaten;
    }

    public int getyKoordinaten() {
        return yKoordinaten;
    }

    public void setyKoordinaten(int yKoordinaten) {
        this.yKoordinaten = yKoordinaten;
    }
}