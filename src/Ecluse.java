import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
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
        this.initResourses();
        this.intialiserEnvironement();
    }

    public void intialiserEnvironement() {
        addToWorld(ressources.backgroundView, ressources.porteAmontView, ressources.porteAvalView, ressources.sasView);
    }

    private void initResourses() {
        ressources = new Resources();
        ressources.chargerRessources();
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
