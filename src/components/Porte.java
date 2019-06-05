package components;

import javafx.scene.image.ImageView;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Porte extends Equipement {


    public Porte(ImageView image, Etat etat) {
        super(image, etat);
    }

    public Porte(ImageView image) {
        super(image);
    }

    @Override
    public void ouvrir() {

    }

    @Override
    public void fermer() {

    }
}
