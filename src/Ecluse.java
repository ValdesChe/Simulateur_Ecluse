import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Constantes;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Ecluse extends Application {

    Resources ressources;
    Pane root;
    Scene scene;


    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new Pane();
        scene = new Scene(root, Constantes.WINDOWS_WIDTH, Constantes.WINDOWS_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
        this.initResourses(Constantes.AMONT_VERS_AVAL);
        this.intialiserEnvironement();
    }

    public void intialiserEnvironement() {
        addToWorld(ressources.backgroundView, ressources.porteAmontView, ressources.porteAvalView, ressources.sasView, ressources.bateauView);
    }

    private void initResourses(int sens) {
        ressources = new Resources();
        ressources.chargerRessources(sens);
    }

    private void addToWorld(Node... n) {
        root.getChildren().addAll(n);
    }

    private void addToWorld(Node n) {
        root.getChildren().add(0, n);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
