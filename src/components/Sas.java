package components;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import utils.Constantes;

/**
 * @author Mohammed Yassine Chraibi 
 */
public class Sas {
    private ImageView image;
    private TranslateTransition transition;
    private ScaleTransition scaleTransition;
    private short niveau;
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
        scaleTransition = new ScaleTransition(Constantes.DUREE, this.getImage());
        scaleTransition.setToX(1);
	scaleTransition.setToY(2.6);
        transition = new TranslateTransition(Constantes.DUREE, this.getImage());
        transition.setByY(Constantes.TRANSITION_OFFSET);
        
        scaleTransition.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                getImage().setFitHeight(Constantes.SAS_IMAGE_MAX_HEIGHT / 2.6);
            }
        });
        transition.play();
        scaleTransition.play();
    }
    
    public void passerNiveauBas(){
        scaleTransition = new ScaleTransition(Constantes.DUREE, this.getImage());
	scaleTransition.setFromY(Constantes.SAS_IMAGE_MAX_HEIGHT / this.getImage().getFitHeight());
        scaleTransition.setToY(1);
        transition = new TranslateTransition(Constantes.DUREE, this.getImage());
        transition.setByY(-Constantes.TRANSITION_OFFSET);
        
        scaleTransition.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
                getImage().setFitHeight(Constantes.SAS_IMAGE_MIN_HEIGHT);
            }
        });
        transition.play();
        scaleTransition.play();
    }
    
    
}
