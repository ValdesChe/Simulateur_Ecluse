package utils;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Constantes {

    // Dimensions de la fenetre
    public static final int WINDOWS_WIDTH = 1100;
    public static final int WINDOWS_HEIGHT = 650;


    // Position de certains objets graphiques
    public static final short PORTE_AMONT_X = 280; // Amont X
    public static final short PORTE_AMONT_Y = 320; // Amont Y (Fermee)
    public static final short PORTE_AMONT_MIN_Y = 50; // Position Y de la porte Amont (Ouverte)
    

    public static final short PORTE_AVAL_X = 710; // Aval X
    public static final short PORTE_AVAL_Y = PORTE_AMONT_Y; // Aval Y (Fermee)
    public static final short PORTE_AVAL_MIN_Y = PORTE_AMONT_MIN_Y; // Aval Y minimum (Ouverte)

    // Sas 
    public static final short SAS_IMAGE_WIDTH = 453;
    public static final short SAS_X = PORTE_AMONT_X;   

    // Sas sens Direct
    public static final short SAS_IMAGE_MAX_HEIGHT = 81;
    public static final short SAS_Y_SENS_DIRECT = 475;
    
    // Sas Sens Inverse
    public static final short SAS_IMAGE_MIN_HEIGHT = 210;
    public static final short SAS_Y_SENS_INVERSE = PORTE_AMONT_Y + 26;

    // Bateau Sens Direct
    public static final short BATEAU_X_SENS_DIRECT = 5;
    public static final short BATEAU_Y_SENS_DIRECT = PORTE_AMONT_Y - 25;
    
    // Bateau Sens Inverse
    public static final short BATEAU_X_SENS_INVERSE = WINDOWS_WIDTH - 200;
    public static final short BATEAU_Y_SENS_INVERSE = 480 - 56;

    // Differentes etapes du parcours de l'ecluse
    public static final short NIVEAU1 = 1; // Amont
    public static final short NIVEAU2 = 2; // Sas
    public static final short NIVEAU3 = 3; // Aval

    // Constantes sens de parcours
    public static final short AMONT_VERS_AVAL = 1;
    public static final short AVAL_VERS_AMONT = -1;

    // Simulation modes
    public static final short MODE_MANUEL = 1;
    public static final short MODE_AUTO = -1;

    // OffSet for menu
    public static final short MENU_OFFSET = 15;

    // Listes des messages à afficher
    public static final String MESSAGE_ERREUR_ = "Impossible de faire cette action";
}
