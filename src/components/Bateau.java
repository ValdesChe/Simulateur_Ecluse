package components;

import javafx.scene.image.ImageView;

/**
 *
 * @author Yassine
 */
public class Bateau {
    private ImageView image;
    
    public Bateau(ImageView img){
        image = img;
    }
    
    public ImageView getImage(){
        return image;
    }
    
    public void setImage(ImageView img){
        this.image = img;
    }
    
}
