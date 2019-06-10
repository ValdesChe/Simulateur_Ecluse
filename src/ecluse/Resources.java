package ecluse;

import components.Bateau;
import components.Etat;
import components.Feu;
import components.Porte;
import components.Sas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Constantes;


import ressources.ClassHelper;
import utils.StringConstants;
public class Resources {

    Image porteAmontImage;
    Image porteAvalImage;

    Image imageBackground;
    
    public static Image imageFeuAmont;
    public static Image imageFeuAval;
    public Image imageFeuVanneAmont;
    public Image imageFeuVanneAval;

    ImageView porteAmontView;
    ImageView porteAvalView;
    ImageView backgroundView;
    
    public ImageView feuAmontView;
    public ImageView feuAvalView;
    public ImageView feuVanneAmontView;
    public ImageView feuVanneAvalView;
    
    Porte porteAmont;
    Porte porteAval;
    
    Feu feuAmont;
    Feu feuAval;
    Feu feuVanneAmont;
    Feu feuVanneAval;
    // Sas (Modelisee comme un rectangle)
    Sas sas;
    Image sasImage;
    ImageView sasView;

    // Bateau
    Bateau bateau;
    Image bateauImage;
    ImageView bateauView;
    /**
     *  Charger les ressources du Jeu
     */
    public void chargerRessources(short sens) {
        try {
            imageBackground = new Image(ClassHelper.class.getResourceAsStream("background.jpg"));
            porteAmontImage = new Image(ClassHelper.class.getResourceAsStream("porte.png"));
            porteAvalImage = new Image(ClassHelper.class.getResourceAsStream("porte.png"));
            sasImage = new Image(ClassHelper.class.getResourceAsStream("sas.png"));
            imageFeuAmont = new Image(ClassHelper.class.getResourceAsStream("feuxrouge.png"));
            imageFeuAval = new Image(ClassHelper.class.getResourceAsStream("feuxrouge.png"));
            imageFeuVanneAmont = new Image(ClassHelper.class.getResourceAsStream("vannefeuxrouge.png"));
            imageFeuVanneAval = new Image(ClassHelper.class.getResourceAsStream("vannefeuxrouge.png"));
            
            
            if(sens == Constantes.AMONT_VERS_AVAL)
                bateauImage = new Image(ClassHelper.class.getResourceAsStream("BateauSens1.gif"));
            else
                bateauImage = new Image(ClassHelper.class.getResourceAsStream("BateauSens-1.gif"));

            // attching
            attachDefaultImages(sens);
        } catch (Exception e) {
            System.out.println("error loading one of the resourses");
        }
    }

    private void attachDefaultImages(short sens) {
        try {
            //
            backgroundView = new ImageView(imageBackground);
            
            // Attachement des images de feux
            
            feuAmontView = new ImageView(imageFeuAmont);
            feuAmontView.setTranslateX(Constantes.FEU_AMONT_X);
            feuAmontView.setTranslateY(Constantes.FEU_AMONT_Y);
            
            feuAvalView = new ImageView(imageFeuAval);
            feuAvalView.setTranslateX(Constantes.FEU_AVAL_X);
            feuAvalView.setTranslateY(Constantes.FEU_AVAL_Y);
            
            feuVanneAmontView = new ImageView(imageFeuVanneAmont);
            feuVanneAmontView.setTranslateX(Constantes.FEU_VANNE_AMONT_X);
            feuVanneAmontView.setTranslateY(Constantes.FEU_VANNE_AMONT_Y);
            
            feuVanneAvalView = new ImageView(imageFeuVanneAval);
            feuVanneAvalView.setTranslateX(Constantes.FEU_VANNE_AVAL_X);
            feuVanneAvalView.setTranslateY(Constantes.FEU_VANNE_AVAL_Y);
                        
            // Sas et Bateau si on est dans le sens inverse
            sasView = new ImageView(sasImage);
            if(sens == Constantes.AVAL_VERS_AMONT){
                // Sas dans le sens inverse: Aval Vers Amont
                sasView.setPreserveRatio(false);
                sasView.setFitWidth(Constantes.SAS_IMAGE_WIDTH);
                sasView.setFitHeight(Constantes.SAS_IMAGE_MAX_HEIGHT);
                sasView.setTranslateX(Constantes.SAS_X);
                sasView.setTranslateY(Constantes.SAS_Y_SENS_INVERSE);
                sas = new Sas(sasView);
                sas.setEtat(Constantes.SAS_NIVEAU_MAX);
                
                // Bateau dans le sens inverse
                bateauView = new ImageView(bateauImage);
                bateauView.setTranslateX(Constantes.BATEAU_X_ETAPE_3_ETAT_2);
                bateauView.setTranslateY(Constantes.BATEAU_Y_ETAPE_3);
                bateau = new Bateau(bateauView);
            }
            else{
                // Sas et Bateau dans le sens Direct
                sasView.setPreserveRatio(false);
                sasView.setFitWidth(Constantes.SAS_IMAGE_WIDTH);
                sasView.setFitHeight(Constantes.SAS_IMAGE_MIN_HEIGHT);
                sasView.setTranslateX(Constantes.SAS_X);
                sasView.setTranslateY(Constantes.SAS_Y_SENS_DIRECT);
                sas = new Sas(sasView);
                sas.setEtat(Constantes.SAS_NIVEAU_MIN);
                
                // 
                bateauView = new ImageView(bateauImage);
                bateauView.setTranslateX(Constantes.BATEAU_X_ETAPE_1_ETAT_1);
                bateauView.setTranslateY(Constantes.BATEAU_Y_ETAPE_1);
                bateau = new Bateau(bateauView);
            }
            
            
                
            
            //
            porteAmontView = new ImageView(porteAmontImage);
            porteAmontView.setTranslateX(Constantes.PORTE_AMONT_X);
            porteAmontView.setTranslateY(Constantes.PORTE_AMONT_Y);
            porteAmont = new Porte(porteAmontView);
             
            //
            porteAvalView = new ImageView(porteAvalImage);
            porteAvalView.setTranslateX(Constantes.PORTE_AVAL_X);
            porteAvalView.setTranslateY(Constantes.PORTE_AVAL_Y);
            porteAval = new Porte(porteAvalView);
            
            
            feuAmont = new Feu(feuAmontView, StringConstants.FEU_TYPE_FEU);
            feuAval = new Feu(feuAvalView, StringConstants.FEU_TYPE_FEU);
            
            feuVanneAmont = new Feu(feuVanneAmontView, StringConstants.FEU_TYPE_VANNE_AMONT);
            feuVanneAval = new Feu(feuVanneAvalView, StringConstants.FEU_TYPE_VANNE_AVAL);
            
        } catch (Exception e) {
            System.out.println("error attaching imgs");
        }
    }
}
