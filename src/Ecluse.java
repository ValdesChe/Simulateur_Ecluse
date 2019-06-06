import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Constantes;
import utils.StringConstants;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Ecluse extends Application {

    Resources ressources;
    Pane root;
    Scene scene;
    BorderPane borderPane;
    Pane gameContainer;
    Pane ecluseControlsContainer;



    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new Pane();

        MenuBar menuBar = new MenuBar();
        // Menu List
        Menu menuModeFonctionnement = new Menu(StringConstants.MODE_MENU_TEXTE);
        Menu menuApropos = new Menu(StringConstants.ABOUT_MENU_TEXTE);

        EventHandler<ActionEvent> clicSurMenuHandler = clicSurMenu();
        // Items du menu menuModeFonctionnement
        MenuItem modeManuel__MenuItem = new MenuItem(StringConstants.TEXTE_MODE_MANUEL);
        MenuItem modeAutomatique__MenuItem = new MenuItem(StringConstants.TEXTE_MODE_AUTO);


        // Ajout des ecouteurs d'evenements sur les items du menu
        modeAutomatique__MenuItem.setOnAction(clicSurMenuHandler);
        modeManuel__MenuItem.setOnAction(clicSurMenuHandler);
        menuApropos.setOnAction(clicSurMenuHandler);

        // Ajout des items au niveau du menu Mode de fonctionnement
        menuModeFonctionnement.getItems().add(modeAutomatique__MenuItem);
        menuModeFonctionnement.getItems().add(modeManuel__MenuItem);


        menuBar.getMenus().add(menuModeFonctionnement);
        menuBar.getMenus().add(menuApropos);

        scene = new Scene(root, Constantes.WINDOWS_WIDTH, Constantes.WINDOWS_HEIGHT + Constantes.MENU_OFFSET );

        //
        borderPane = new BorderPane();
        gameContainer = new Pane();
        ecluseControlsContainer = new Pane();

        borderPane.setTop(menuBar);

        borderPane.prefHeight(Constantes.WINDOWS_HEIGHT);
        borderPane.prefWidthProperty().bind(scene.widthProperty());

        borderPane.setCenter(gameContainer);

        root.getChildren().add(borderPane);
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


    private EventHandler<ActionEvent> clicSurMenu() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MenuItem mItem = (MenuItem) event.getSource();
                String texteItemMenu = mItem.getText();

                // Si le mode manuel est activé
                if (StringConstants.TEXTE_MODE_MANUEL.equalsIgnoreCase(texteItemMenu)) {
                    System.out.println("Mode manuel");
                }
                // Si le mode automatique est activé
                else if (StringConstants.TEXTE_MODE_AUTO.equalsIgnoreCase(texteItemMenu)) {
                    System.out.println("Mode automatique");
                }
            }
        };
    }

    private void addToWorld(Node... n) {
        gameContainer.getChildren().addAll(n);
    }

    private void addToWorld(Node n) {
        gameContainer.getChildren().add(0, n);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
