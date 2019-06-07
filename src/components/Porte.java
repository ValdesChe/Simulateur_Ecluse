package components;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import utils.Constantes;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Porte extends Equipement {
    private TranslateTransition transition;

    public Porte(ImageView image, Etat etat) {
        super(image, etat);
    }

    public Porte(ImageView image) {
        super(image);
    }

    @Override
    public void ouvrir() {

    }

    @Override
    public void fermer() {

    }
    
    public void bougerY(int depart, int fin){ 
        transition = new TranslateTransition();
        transition.setNode(getImage());
        transition.setByY(fin - depart);
        transition.setDuration(Constantes.DUREE);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.play();
    }
}
