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


    // Variables de controle de l'etat ecluse

    // Mode de fonctionnement de l'ecluse
    public short modeFonctionnement = Constantes.MODE_MANUEL;

    // Position actuelle ou le bateau se trouve
    public short positionActuelleBateau = Constantes.NIVEAU1;

    // Sens de circulation du bateau
    public short sensCirculation = Constantes.AMONT_VERS_AVAL;

    //



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
        addToWorld(ressources.backgroundView, ressources.sasView, ressources.bateauView, ressources.porteAmontView, ressources.porteAvalView);
    }

    private void initResourses(short sens) {
        ressources = new Resources();
        // Definition du sens de circulation du bateau
        this.sensCirculation = sens;
        // Definition du niveau du bateau
        this.positionActuelleBateau = (sens == Constantes.AMONT_VERS_AVAL) ? Constantes.NIVEAU1 : Constantes.NIVEAU3;
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
                    modeFonctionnement = Constantes.MODE_MANUEL;
                    // Initialisation avec les éléments par défaut
                    initResourses(sensCirculation);
                    intialiserEnvironement();
                }
                // Si le mode automatique est activé
                else if (StringConstants.TEXTE_MODE_AUTO.equalsIgnoreCase(texteItemMenu)) {
                    System.out.println("Mode automatique");
                    // On reinitialise les composants
                    initResourses(sensCirculation);
                    modeFonctionnement = Constantes.MODE_AUTO;
                    intialiserEnvironement();


                    // Lancement du mode automatique


                }
            }
        };
    }

    /***
     *  Lancement du mode automatique ... Approche recurssive
     */
    private void lancerModeAutomatique(){

        //
        if(modeFonctionnement != Constantes.MODE_AUTO )
            return;

        // Bateau est au niveau 1
        if (positionActuelleBateau == Constantes.NIVEAU1) {

            // Si le bateau se dirige vers l'aval
            if(sensCirculation == Constantes.AMONT_VERS_AVAL) {

                positionActuelleBateau = Constantes.NIVEAU2;
            }
            // Sinon il est à la fin du parcours
            else {
                return;
            }
        }

        // Bateau est au niveau 2 / SAS
        if (positionActuelleBateau == Constantes.NIVEAU2) {

            // Si le bateau se dirige vers l'aval
            if(sensCirculation == Constantes.AMONT_VERS_AVAL) {

                positionActuelleBateau = Constantes.NIVEAU3;
            }
            // Sinon il se dirige vers l'amont
            else {
                positionActuelleBateau = Constantes.NIVEAU1;
            }
        }

        // Bateau est au niveau 3 / AVAL
        if (positionActuelleBateau == Constantes.NIVEAU3) {

            // Si le bateau se dirige vers l'aval
            if(sensCirculation == Constantes.AVAL_VERS_AMONT) {

                positionActuelleBateau = Constantes.NIVEAU2;
            }
            // Sinon fin parcours
            else {
                // Reinitialiser / ...
                return;
            }
        }

        // Recursivité
        lancerModeAutomatique();
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
