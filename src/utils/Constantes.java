package utils;

import javafx.util.Duration;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Constantes {

    // Dimensions de la fenetre
    public static final int WINDOWS_WIDTH = 1320;
    public static final int WINDOWS_HEIGHT = 650;


    // Position de certains objets graphiques
    public static final short PORTE_AMONT_X = 280; // Amont X
    public static final short PORTE_AMONT_Y = 320; // Amont Y (Fermee)
    public static final short PORTE_AMONT_MIN_Y = 50; // Position Y de la porte Amont (Ouverte)
    

    public static final short PORTE_AVAL_X = 710; // Aval X
    public static final short PORTE_AVAL_Y = PORTE_AMONT_Y; // Aval Y (Fermee)
    public static final short PORTE_AVAL_MIN_Y = PORTE_AMONT_MIN_Y; // Aval Y minimum (Ouverte)

    // Sas 
    public static final short SAS_IMAGE_WIDTH = 450;
    public static final short SAS_X = PORTE_AMONT_X;   

    // Sas sens Direct
    public static final short SAS_IMAGE_MIN_HEIGHT = 81;
    public static final short SAS_Y_SENS_DIRECT = 475;
    
    // Sas Sens Inverse
    public static final short SAS_IMAGE_MAX_HEIGHT = 210;
    public static final short SAS_Y_SENS_INVERSE = PORTE_AMONT_Y + 26;

    /**
     * Positions du Bateau
     */
    // Bateau dans l'Amont
    public static final short BATEAU_X_ETAPE_1_ETAT_1 = 5;
    public static final short BATEAU_X_ETAPE_1_ETAT_2 = BATEAU_X_ETAPE_1_ETAT_1 + 100;
    public static final short BATEAU_Y_ETAPE_1 = PORTE_AMONT_Y - 25;

    // Bateau arrive au Sas venant de l'Amont
    public static final short BATEAU_X_ETAPE_2_ETAT_1 = 315;
    public static final short BATEAU_X_ETAPE_2_ETAT_2 = 315 + 200; 
    public static final short BATEAU_Y_ETAPE_2_ETAT_1 = 476 - 52;
    public static final short BATEAU_Y_ETAPE_2_ETAT_2 = PORTE_AMONT_Y - 25;

    // Bateau dans l'aval
    public static final short BATEAU_X_ETAPE_3_ETAT_1 = 740;
    public static final short BATEAU_X_ETAPE_3_ETAT_2 = 740 + 150;
    public static final short BATEAU_Y_ETAPE_3 = 476 - 52;

    
    // Position des feux
    public static final short FEU_AMONT_X = 177;
    public static final short FEU_AMONT_Y = 287;
    
    public static final short FEU_AVAL_X = 834;
    public static final short FEU_AVAL_Y = 418;
    
    // Position des feux de Vanne
    public static final short FEU_VANNE_AMONT_X = 288;
    public static final short FEU_VANNE_AMONT_Y = 598;
    
    public static final short FEU_VANNE_AVAL_X = 730;
    public static final short FEU_VANNE_AVAL_Y = 616;
     
    // Differentes etapes du parcours de l'ecluse
    public static final short NIVEAU1 = 1; // Amont
    public static final short NIVEAU2 = 2; // Sas
    public static final short NIVEAU3 = 3; // Aval

    // Niveau d'eau dans le Sas
    public static final short SAS_EN_MOUVEMENT = 2;
    public static final short SAS_NIVEAU_MAX = 1;
    public static final short SAS_NIVEAU_MIN = 0;
    
    // Constantes sens de parcours
    public static final short AMONT_VERS_AVAL = 1;
    public static final short AVAL_VERS_AMONT = -1;

    // Simulation modes
    public static final short MODE_MANUEL = 1;
    public static final short MODE_AUTO = -1;

    // OffSet for menu
    public static final short MENU_OFFSET = 15;
    
    // Offset pour la transition
    public static final short TRANSITION_OFFSET = -64;

    // Durations des transitions
    public static final Duration DUREE = Duration.millis(2000);
    
    // Listes des messages à afficher
    public static final String MESSAGE_ERREUR_ = "Impossible de faire cette action";
}
