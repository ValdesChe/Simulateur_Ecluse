package components;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.Constantes;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Porte extends Equipement {
    private TranslateTransition transition;

    private Duration vitesseDeplacement = Constantes.DUREE;
    public Porte(ImageView image, Etat etat) {
        super(image, etat);
    }

    public Porte(ImageView image) {
        super(image);
    }

    public void setVitesseDeplacement(Duration duration){
        this.vitesseDeplacement = duration;
    }

    @Override
    public void ouvrir() {
        bougerY(Constantes.PORTE_AMONT_Y, Constantes.PORTE_AMONT_MIN_Y);
    }

    @Override
    public void fermer() {
        bougerY(Constantes.PORTE_AMONT_MIN_Y, Constantes.PORTE_AMONT_Y);
    }
    
    public void bougerY(int depart, int fin){ 
        transition = new TranslateTransition();
        transition.setNode(getImage());
        transition.setByY(fin - depart);
        transition.setDuration(vitesseDeplacement);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.play();
    }
}
