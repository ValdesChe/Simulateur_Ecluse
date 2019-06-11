package components;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import utils.Constantes;

/**
 *
 * @author Yassine
 */
public class Bateau {
    private ImageView image;
    private Duration vitesseDeplacement = Constantes.DUREE;
    private TranslateTransition transition; 

    public Bateau(ImageView img){
        image = img;
    }
    
    public ImageView getImage(){
        return image;
    }
    
    public void setImage(ImageView img){
        this.image = img;
    }

    public void setVitesseDeplacement(Duration duration){
        this.vitesseDeplacement = duration;
    }

    public void bougerX(int depart, int fin){ 
        transition = new TranslateTransition();
        transition.setNode(getImage());
        transition.setByX(fin - depart);
        transition.setDuration(vitesseDeplacement);
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.play();
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
