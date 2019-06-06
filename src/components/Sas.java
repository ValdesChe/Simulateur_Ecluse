package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Mohammed Yassine Chraibi 
 */
public class Sas {
    private ImageView image;

    public Sas(ImageView img) {
        image = img;
    }

    public ImageView getImage(){
        return image;
    }
    
    public void setImage(ImageView img){
        this.image = img;
    }
}