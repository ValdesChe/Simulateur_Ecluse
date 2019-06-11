package components;

import ecluse.Resources;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Constantes;
import ressources.ClassHelper;
import utils.Etat;
import utils.StringConstants;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Feu extends Thread {
    private TranslateTransition transition;
    private String type;
    private ImageView image;
    private Etat etat = Etat.FERME;

    public Feu(ImageView image, Etat etat) {
        this.image = image;
        this.etat = etat;
    }

    public Feu(ImageView image, String type) {
        this.image = image;
        setType(type);
    }
    
    
    public void allumer(Resources res) {
        if(getType().equalsIgnoreCase(StringConstants.FEU_TYPE_FEU)){
            res.feuAmontView.setImage(new Image(ClassHelper.class.getResourceAsStream("feuxvert.png")));
            res.feuAmontView.setTranslateX(Constantes.FEU_AMONT_X);
            res.feuAmontView.setTranslateY(Constantes.FEU_AMONT_Y);
            this.setImage(res.feuAmontView);
        }
        else if(getType().equalsIgnoreCase(StringConstants.FEU_TYPE_VANNE_AMONT)){
            res.feuVanneAmontView.setImage(new Image(ClassHelper.class.getResourceAsStream("vannefeuxvert.png")));
            res.feuVanneAmontView.setTranslateX(Constantes.FEU_VANNE_AMONT_X);
            res.feuVanneAmontView.setTranslateY(Constantes.FEU_VANNE_AMONT_Y);
            this.setImage(res.feuVanneAmontView);
        }
        else{
            res.feuVanneAvalView.setImage(new Image(ClassHelper.class.getResourceAsStream("vannefeuxvert.png")));
            res.feuVanneAvalView.setTranslateX(Constantes.FEU_VANNE_AVAL_X);
            res.feuVanneAvalView.setTranslateY(Constantes.FEU_VANNE_AVAL_Y);
            this.setImage(res.feuVanneAvalView);
        }   
    }

    public void eteindre(Resources res){
        if(getType().equalsIgnoreCase(StringConstants.FEU_TYPE_FEU)){
            res.feuAmontView.setImage(new Image(ClassHelper.class.getResourceAsStream("feuxrouge.png")));
            res.feuAmontView.setTranslateX(Constantes.FEU_AMONT_X);
            res.feuAmontView.setTranslateY(Constantes.FEU_AMONT_Y);
            this.setImage(res.feuAmontView);
        }
        else if(getType().equalsIgnoreCase(StringConstants.FEU_TYPE_VANNE_AMONT)){
            res.feuVanneAmontView.setImage(new Image(ClassHelper.class.getResourceAsStream("vannefeuxrouge.png")));
            res.feuVanneAmontView.setTranslateX(Constantes.FEU_VANNE_AMONT_X);
            res.feuVanneAmontView.setTranslateY(Constantes.FEU_VANNE_AMONT_Y);
            this.setImage(res.feuVanneAmontView);
        }
        else{
            res.feuVanneAvalView.setImage(new Image(ClassHelper.class.getResourceAsStream("vannefeuxrouge.png")));
            res.feuVanneAvalView.setTranslateX(Constantes.FEU_VANNE_AVAL_X);
            res.feuVanneAvalView.setTranslateY(Constantes.FEU_VANNE_AVAL_Y);
            this.setImage(res.feuVanneAvalView);
        }  
    }

    
    public String getType(){
        return type;
    }
    
    public void setType(String t){
        type = t;
    }

    public ImageView getImage() {
        return image;
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
}
