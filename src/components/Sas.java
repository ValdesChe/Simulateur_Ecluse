package components;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import utils.Constantes;

/**
 * @author Mohammed Yassine Chraibi 
 */
public class Sas {
    private ImageView image;
    private TranslateTransition transition;
    private short niveau = Constantes.SAS_NIVEAU_MAX;
    public Sas(ImageView img) {
        image = img;
    }

    public ImageView getImage(){
        return image;
    }
    
    public void setImage(ImageView img){
        this.image = img;
    }
    
    public short getEtat(){
        return niveau;
    }
    
    public void setEtat(short n){
        niveau = n;
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
    
    public void passerNiveauHaut(){
        getImage().setFitHeight(Constantes.SAS_IMAGE_MAX_HEIGHT);
        getImage().setTranslateX(Constantes.SAS_X);
        getImage().setTranslateY(Constantes.SAS_Y_SENS_INVERSE);
    }
    
    public void passerNiveauBas(){
        getImage().setFitHeight(Constantes.SAS_IMAGE_MIN_HEIGHT);
        getImage().setTranslateX(Constantes.SAS_X);
        getImage().setTranslateY(Constantes.SAS_Y_SENS_DIRECT);
    }
}
