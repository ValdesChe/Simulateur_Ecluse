package components;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.Constantes;
import utils.Etat;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Porte extends Thread {
    private TranslateTransition transition;
    private ImageView image;
    private Etat etat = Etat.FERME;
    private Duration vitesseDeplacement = Constantes.DUREE;

    public Porte(ImageView image, Etat etat) {
        this.image = image;
        this.etat = etat;
    }

    public Porte(ImageView image) {
        this.image = image;
    }


    public void setVitesseDeplacement(Duration duration){
        this.vitesseDeplacement = duration;
    }

    synchronized public void ouvrir() {
        synchronized (etat) {
            this.setEtat(Etat.OUVERTURE);
            bougerY(Constantes.PORTE_AMONT_Y, Constantes.PORTE_AMONT_MIN_Y);
            this.setEtat(Etat.OUVERT);
        }
    }

    synchronized public void fermer() {
        synchronized (etat) {
            this.setEtat(Etat.FERMETURE);
            bougerY(Constantes.PORTE_AMONT_MIN_Y, Constantes.PORTE_AMONT_Y);
            this.setEtat(Etat.FERME);
        }

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

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public ImageView getImage() {
        return image;
    }

}
