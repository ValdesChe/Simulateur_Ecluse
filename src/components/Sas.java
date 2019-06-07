package components;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Constantes;

/**
 * @author Mohammed Yassine Chraibi 
 */
public class Sas {
    private ImageView image;
    private TranslateTransition transition;
    
    public Sas(ImageView img) {
        image = img;
    }

    public ImageView getImage(){
        return image;
    }
    
    public void setImage(ImageView img){
        this.image = img;
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
