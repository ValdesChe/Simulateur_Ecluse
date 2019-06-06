import components.Porte;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    /**
     *  Charger les ressources du Jeu
     */
    public void chargerRessources() {
        try {
            imageBackground = new Image(Resources.class.getResourceAsStream("ressources/background.jpg"));
            porteAmontImage = new Image(Resources.class.getResourceAsStream("ressources/porte.png"));
            porteAvalImage = new Image(Resources.class.getResourceAsStream("ressources/porte.png"));
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

        } catch (Exception e) {
            System.out.println("error attaching imgs");
        }
    }
}
