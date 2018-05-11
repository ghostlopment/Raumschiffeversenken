package RaumSchiffeVersenken.Interface;

public interface RaumSchiff {

    static enum RaumschiffsArten {
        JAEGER,
        BOMBER,
        FREGATTE,
        ZERSTOERER;

        RaumschiffsArten() {
        }
    }

    int getLaenge();

    void setLaenge(int laenge);

    int getLebensPunkte();

    void setLebensPunkte(int lebensPunkte);

    int getxKoordinaten();

    void setxKoordinaten(int xKoordinaten);

    int getyKoordinaten();

    void setyKoordinaten(int yKoordinaten);

    void zielenZumSchiffeSetzen();

    void schiffSetzen();
}
