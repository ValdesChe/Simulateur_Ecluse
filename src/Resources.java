import components.Porte;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Resources {

    Image porteAmontImage;
    Image porteAvalImage;

    Image imageBackground;

    ImageView porteAmontView;
    ImageView porteAvalView;
    ImageView backgroundView;

    Porte porteAmont;
    Porte porteAval;
    public void loadResourses() {
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
            porteAmontView = new ImageView(porteAmontImage);
            backgroundView = new ImageView(imageBackground);
            porteAmontView.setTranslateX(280);
            porteAmontView.setTranslateY(100);
            porteAmont = new Porte(porteAmontView);

            //
            porteAvalView = new ImageView(porteAvalImage);
            porteAvalView.setTranslateX(350);
            porteAvalView.setTranslateY(100);
            porteAval = new Porte(porteAvalView);

        } catch (Exception e) {
            System.out.println("error attaching imgs");
        }
    }
}
