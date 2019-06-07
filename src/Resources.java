import components.Bateau;
import components.Porte;
import components.Sas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import utils.Constantes;

public class Resources {

    Image porteAmontImage;
    Image porteAvalImage;

    Image imageBackground;

    ImageView porteAmontView;
    ImageView porteAvalView;
    ImageView backgroundView;

    Porte porteAmont;
    Porte porteAval;
    
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
    public void chargerRessources(int sens) {
        try {
            imageBackground = new Image(Resources.class.getResourceAsStream("ressources/background.jpg"));
            porteAmontImage = new Image(Resources.class.getResourceAsStream("ressources/porte.png"));
            porteAvalImage = new Image(Resources.class.getResourceAsStream("ressources/porte.png"));
            sasImage = new Image(Resources.class.getResourceAsStream("ressources/sas.png"));
            if(sens == Constantes.AMONT_VERS_AVAL)
                bateauImage = new Image(Resources.class.getResourceAsStream("ressources/BateauSens1.gif"));
            else
                bateauImage = new Image(Resources.class.getResourceAsStream("ressources/BateauSens-1.gif"));

            // attching
            attachDefaultImages(sens);
        } catch (Exception e) {
            System.out.println("error loading one of the resourses");
        }
    }

    private void attachDefaultImages(int sens) {
        try {
            //
            backgroundView = new ImageView(imageBackground);
            
            // Sas et Bateau dans le sens Direct
                        
            sasView = new ImageView(sasImage);
            sasView.setPreserveRatio(false);
            sasView.setFitWidth(Constantes.SAS_IMAGE_WIDTH);
            sasView.setFitHeight(Constantes.SAS_IMAGE_MAX_HEIGHT);
            sasView.setTranslateX(Constantes.SAS_X);
            sasView.setTranslateY(Constantes.SAS_Y_SENS_DIRECT);

            // 
            bateauView = new ImageView(bateauImage);
            bateauView.setTranslateX(Constantes.BATEAU_X_ETAPE_1_ETAT_1);
            bateauView.setTranslateY(Constantes.BATEAU_Y_ETAPE_1);
            
            // Sas et Bateau si on est dans le sens inverse
            if(sens == Constantes.AVAL_VERS_AMONT){
                // Sas dans le sens inverse: Aval Vers Amont
                sasView.setPreserveRatio(false);
                sasView.setFitWidth(Constantes.SAS_IMAGE_WIDTH);
                sasView.setFitHeight(Constantes.SAS_IMAGE_MIN_HEIGHT);
                sasView.setTranslateX(Constantes.SAS_X);
                sasView.setTranslateY(Constantes.SAS_Y_SENS_INVERSE);
                
                // Bateau dans le sens inverse
                bateauView = new ImageView(bateauImage);
                bateauView.setTranslateX(Constantes.BATEAU_X_ETAPE_1_ETAT_1);
                bateauView.setTranslateY(Constantes.BATEAU_Y_ETAPE_1);
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
            
            // Initialisation des objets
            sas = new Sas(sasView);
            bateau = new Bateau(bateauView);
            
        } catch (Exception e) {
            System.out.println("error attaching imgs");
        }
    }
}
