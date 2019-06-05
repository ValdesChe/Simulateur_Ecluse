package components;

import javafx.scene.image.ImageView;

/**
 * Created by ValdoR on 2019-06-05.
 */
public abstract  class Equipement {
    private ImageView image;
    private Etat etat = Etat.FERME;

    public Equipement(ImageView image, Etat etat) {
        this.image = image;
        this.etat = etat;
    }

    public Equipement(ImageView image) {
        this.image = image;
    }


    abstract public void ouvrir();
    abstract public void fermer();

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
