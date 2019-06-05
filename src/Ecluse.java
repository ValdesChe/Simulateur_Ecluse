import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

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
        scene = new Scene(root, 1100, 650);

        primaryStage.setScene(scene);
        primaryStage.show();
        this.initResourses();
        this.intialiserEnvironement();
    }

    public void intialiserEnvironement() {
        addToWorld(ressources.backgroundView, ressources.porteAmontView, ressources.porteAvalView);
    }

    private void initResourses() {
        ressources = new Resources();
        ressources.loadResourses();
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
