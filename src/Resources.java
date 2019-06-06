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

    /**
     *  Charger les ressources du Jeu
     */
    public void chargerRessources() {
        try {
            imageBackground = new Image(Resources.class.getResourceAsStream("ressources/background.jpg"));
            porteAmontImage = new Image(Resources.class.getResourceAsStream("ressources/porte.png"));
            porteAvalImage = new Image(Resources.class.getResourceAsStream("ressources/porte.png"));
            sasImage = new Image(Resources.class.getResourceAsStream("ressources/sas.png"));
            // attching
            attachDefaultImages();
        } catch (Exception e) {
            System.out.println("error loading one of the resourses");
        }
    }

    private void attachDefaultImages() {
        try {
            //
            backgroundView = new ImageView(imageBackground);

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
            
            //
            sasView = new ImageView(sasImage);
            sasView.setPreserveRatio(false);
            sasView.setFitWidth(Constantes.SAS_IMAGE_WIDTH);
            sasView.setFitHeight(Constantes.SAS_IMAGE_HEIGHT);
            sasView.setTranslateX(Constantes.SAS_X);
            sasView.setTranslateY(Constantes.SAS_Y);
            sas = new Sas(sasView);
        } catch (Exception e) {
            System.out.println("error attaching imgs");
        }
    }
}
