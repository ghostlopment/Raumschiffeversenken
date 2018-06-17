package RaumSchiffeVersenken.Core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FeldFX {

    public int[][] mapGroesse;

    /**
     * <p>Erstellung des Loggers.</p>
     */
    private static final Logger log = LogManager.getLogger(FeldFX.class);

    /**
     * <p>10x10 Matrix 100 Feldern wird mit Zahlen von 0-99 mit "0" gefüllt.</p>
     *
     * @param xAchse Länge
     * @param yAchse Länge
     */
    FeldFX(int xAchse, int yAchse) {
        this.mapGroesse = new int[xAchse][yAchse];

        for (int y = 0; y < yAchse; y++) {
            for (int x = 0; x < xAchse; x++) {
                mapGroesse[x][y] = 0;
            }
        }
    }

    /**
     * @param yAchseBeschuss  :)
     * @param xAchseBeschuss  :)
     * @param schiffsRichtung :)
     * @param schiffsLaenge   :)
     * @return :)
     */
    boolean schiffSetzenAutomatisch(int yAchseBeschuss, int xAchseBeschuss, int schiffsRichtung, int schiffsLaenge) {
        boolean schiffErfolgreichSetzen = false;
        int schiffsLaengeCounter = 0;

        try {
            if (schiffsRichtung == 1) {
                for (int i = 0; i < schiffsLaenge; i++) {
                    if (mapGroesse[xAchseBeschuss + i][yAchseBeschuss] == 0 && mapGroesse[xAchseBeschuss + i][yAchseBeschuss] != 5) {
                        schiffsLaengeCounter++;
                    }
                }
            } else if (schiffsRichtung == 2) {
                for (int i = 0; i < schiffsLaenge; i++) {
                    if (mapGroesse[xAchseBeschuss][yAchseBeschuss + i] == 0 && mapGroesse[xAchseBeschuss][yAchseBeschuss + i] != 5) {
                        schiffsLaengeCounter++;
                    }
                }
            }

            if (schiffsLaengeCounter == schiffsLaenge) {
                schiffErfolgreichSetzen = true;
            }

            if (schiffErfolgreichSetzen) {
                if (schiffsRichtung == 1) {
                    for (int i = 0; i < schiffsLaenge; i++) {
                        mapGroesse[xAchseBeschuss + i][yAchseBeschuss] += 5;
                        log.info("Schiff erfolgreich horizontal gesetzt.\n");
                    }
                } else if (schiffsRichtung == 2) {
                    for (int i = 0; i < schiffsLaenge; i++) {
                        mapGroesse[xAchseBeschuss][yAchseBeschuss + i] += 5;
                        log.info("Schiff erfolgreich vertikal gesetzt.\n");
                    }
                }
            }
        } catch (Exception ex4) {
            log.error("Schiff konnte nicht gesetzt werden (außerhalb des Feldes).\n", ex4);
        }
        return schiffErfolgreichSetzen;
    }

    /**
     * Für Test relevant
     *
     * @return mapGroesse
     */
    int[][] getMapGroesse() {
        return mapGroesse;
    }
}