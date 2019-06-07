package components;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import utils.Constantes;

/**
 *
 * @author Yassine
 */
public class Bateau {
    private ImageView image;
    
    private TranslateTransition transitionX; 

    public Bateau(ImageView img){
        image = img;
    }
    
    public ImageView getImage(){
        return image;
    }
    
    public void setImage(ImageView img){
        this.image = img;
    }
    
    public void bougerX(int depart, int fin){ 
        transitionX = new TranslateTransition();
        transitionX.setNode(getImage());
        transitionX.setByX(fin - depart);
        transitionX.setDuration(Constantes.DUREE);
        transitionX.setCycleCount(1);
        transitionX.setAutoReverse(false);
        transitionX.play();
    }
    
}
