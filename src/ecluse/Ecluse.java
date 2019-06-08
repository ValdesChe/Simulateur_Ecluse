package ecluse;

import components.Etat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Constantes;
import utils.StringConstants;

/**
 * Created by ValdoR on 2019-06-05.
 */
public class Ecluse extends Application {

    static Resources ressources;
    Pane root;
    Scene scene;
    BorderPane borderPane;
    Pane gameContainer;
    GridPane ecluseControlsContainer;


    // Variables de controle de l'etat ecluse

    // Mode de fonctionnement de l'ecluse
    public short modeFonctionnement = Constantes.MODE_MANUEL;

    // Position actuelle ou le bateau se trouve
    public short positionActuelleBateau = Constantes.NIVEAU1;

    // Sens de circulation du bateau
    public short sensCirculation = Constantes.AMONT_VERS_AVAL;

    //
    public short niveauSas = Constantes.SAS_NIVEAU_MIN;


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
        ecluseControlsContainer = boutonsPane();

        // Faire un Render des boutons de controle)
        borderPane.setLeft(ecluseControlsContainer);
        // Fin boutons de controle
        
        
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
        addToWorld(ressources.backgroundView, 
                   ressources.sasView,  
                   ressources.feuAmontView,  
                   ressources.feuAvalView, 
                   ressources.bateauView, 
                   ressources.porteAmontView, 
                   ressources.porteAvalView,
                   ressources.feuVanneAmontView,
                   ressources.feuVanneAvalView);
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
    
    /**
     * Retourne un GridPane contenant les boutons et le label des messages
     * 
     * @return GridPane : Le pane qui va etre ajoute a gauche du Pane principal root 
     * 
     */
    private GridPane boutonsPane(){
        // GridPane contenant tous Boutons de controle + Messages label
        GridPane boutonsPane = new GridPane();
        boutonsPane.setMinWidth(Constantes.WINDOWS_WIDTH / 5);
        
        /**
         * Amont de type GridPane contenant tous les boutons
         */
        GridPane amontPane = new GridPane();
        Label labelAmont = new Label("Amont");
        amontPane.setHgap(10);
        amontPane.setVgap(5);
        amontPane.add(labelAmont, 0, 0);
        
        // Vanne
        Label labelVanneAmont = new Label("Vanne");
        Button boutonOuvrirVanneAmont = new Button("Ouvrir");
        EventHandler<ActionEvent> ouvrirVanneAmont = ouvrirVanneAmont();
        boutonOuvrirVanneAmont.setOnAction(ouvrirVanneAmont);
        Button boutonFermerVanneAmont = new Button("Fermer");
        EventHandler<ActionEvent> fermerVanneAmont = fermerVanneAmont();
        boutonFermerVanneAmont.setOnAction(fermerVanneAmont);
        amontPane.add(labelVanneAmont, 0, 1);
        amontPane.add(boutonOuvrirVanneAmont, 2, 1);
        amontPane.add(boutonFermerVanneAmont, 3, 1);
        
        // Amont Porte
        Label labelPorteAmont = new Label("Porte");
        Button boutonOuvrirPorteAmont = new Button("Ouvrir");
        EventHandler<ActionEvent> ouvrirPorteAmont = ouvrirPorteAmont();
        boutonOuvrirPorteAmont.setOnAction(ouvrirPorteAmont);
        Button boutonFermerPorteAmont = new Button("Fermer");
        EventHandler<ActionEvent> fermerPorteAmont = fermerPorteAmont();
        boutonFermerPorteAmont.setOnAction(fermerPorteAmont);
        amontPane.add(labelPorteAmont, 0,2);
        amontPane.add(boutonOuvrirPorteAmont, 2, 2);
        amontPane.add(boutonFermerPorteAmont, 3, 2);
        
        // Amont Feu
        Label labelFeuAmont = new Label("Feu");
        Button boutonAllumerFeuAmont = new Button("Allumer");
        EventHandler<ActionEvent> allumerFeuAmont = allumerFeuAmont();
        boutonAllumerFeuAmont.setOnAction(allumerFeuAmont);
        Button boutonEteindreFeuAmont = new Button("Eteindre");
        EventHandler<ActionEvent> eteindreFeuAmont = eteindreFeuAmont();
        boutonEteindreFeuAmont.setOnAction(eteindreFeuAmont);
        amontPane.add(labelFeuAmont, 0,3);
        amontPane.add(boutonAllumerFeuAmont, 2, 3);
        amontPane.add(boutonEteindreFeuAmont, 3, 3);

        
        /**
         * GridPane imbrique contenant Aval et ses controles
         */
        GridPane avalPane = new GridPane();
        Label labelAval = new Label("Aval");
        avalPane.add(labelAval, 0,0);
        avalPane.setHgap(10);
        avalPane.setVgap(5);

        
        // Aval Vanne
        Label labelVanneAval = new Label("Vanne");
        Button boutonOuvrirVanneAval = new Button("Ouvrir");
        EventHandler<ActionEvent> ouvrirVanneAval = ouvrirVanneAval();
        boutonOuvrirVanneAval.setOnAction(ouvrirVanneAval);
        Button boutonFermerVanneAval = new Button("Fermer");
        EventHandler<ActionEvent> fermerVanneAval = fermerVanneAval();
        boutonFermerVanneAval.setOnAction(fermerVanneAval);
        avalPane.add(labelVanneAval, 0, 1);
        avalPane.add(boutonOuvrirVanneAval, 2, 1);
        avalPane.add(boutonFermerVanneAval, 3, 1);
        
        // Aval Porte
        Label labelPorteAval = new Label("Porte");
        Button boutonOuvrirPorteAval = new Button("Ouvrir");
        EventHandler<ActionEvent> ouvrirPorteAval = ouvrirPorteAval();
        boutonOuvrirPorteAval.setOnAction(ouvrirPorteAval);
        Button boutonFermerPorteAval = new Button("Fermer");
        EventHandler<ActionEvent> fermerPorteAval = fermerPorteAval();
        boutonFermerPorteAval.setOnAction(fermerPorteAval);
        avalPane.add(labelPorteAval, 0,2);
        avalPane.add(boutonOuvrirPorteAval, 2, 2);
        avalPane.add(boutonFermerPorteAval, 3, 2);
        
        // Aval Feu
        Label labelFeuAval = new Label("Feu");
        Button boutonAllumerFeuAval = new Button("Allumer");
        EventHandler<ActionEvent> allumerFeuAval = allumerFeuAval();
        boutonAllumerFeuAval.setOnAction(allumerFeuAval);
        Button boutonEteindreFeuAval = new Button("Eteindre");
        EventHandler<ActionEvent> eteindreFeuAval = eteindreFeuAval();
        boutonEteindreFeuAval.setOnAction(eteindreFeuAval);
        avalPane.add(labelFeuAval, 0,3);
        avalPane.add(boutonAllumerFeuAval, 2, 3);
        avalPane.add(boutonEteindreFeuAval, 3, 3);
        
        
        // Label pour afficher les messages d'erreur eventuels
        Label messagesLabel = new Label("Messages: ");
        
        // Attachement des Pane Amont et Aval au Pane des Boutons
        boutonsPane.setHgap(20);
        boutonsPane.setVgap(10);
        boutonsPane.add(amontPane,0,4);
        boutonsPane.add(avalPane, 0,7);
        boutonsPane.add(messagesLabel, 0,10);
        
        return boutonsPane;
    }
    
    // Amont
    
    /**
     * Ouvrir Vanne Amont
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> ouvrirVanneAmont(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                // Si la vanne Aval est ouverte, erreur
                if(ressources.porteAval.getEtat() == Etat.OUVERT){
                    System.out.println("La vanne ne peut pas s'ouvrir car la porte Aval est ouverte.");
                    return;          
                }
                
                // La vanne aval doit s'ouvrir si la porte amont est fermee
                // Et le niveau d'eau dans le sas est stable (Max ou Min)
                if(ressources.sas.getEtat() == Constantes.SAS_NIVEAU_MIN){
                    ressources.feuVanneAmont.allumer(ressources);
                    ressources.feuVanneAmont.setEtat(Etat.OUVERT);
                    // Le sas est au minimum, le faire monter
                    ressources.sas.passerNiveauHaut();
                    
                    if(positionActuelleBateau == Constantes.NIVEAU2 && ressources.sas.getEtat() == Constantes.SAS_NIVEAU_MIN){
                        // Faire monter le bateau si il est sur le Sas
                        ressources.bateau.bougerY(Constantes.BATEAU_Y_ETAPE_2_ETAT_1, Constantes.BATEAU_Y_ETAPE_2_ETAT_2);
                    }
                    ressources.sas.setEtat(Constantes.SAS_NIVEAU_MAX);
                }
            }
        };
    }
    /**
     * Fermer Vanne Amont
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> fermerVanneAmont(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                ressources.feuVanneAmont.eteindre(ressources);
                ressources.feuVanneAmont.setEtat(Etat.FERME);
            }
        };
    }
    /**
     * Ouvrir Porte Amont
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> ouvrirPorteAmont(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(ressources.sas.getEtat() == Constantes.SAS_NIVEAU_MIN){
                    System.out.println("La porte amont ne peut pas s'ouvrir a cause de la pression. ");
                }
                else{
                    if(ressources.porteAmont.getEtat() != Etat.OUVERT){
                        ressources.porteAmont.ouvrir();
                        ressources.porteAmont.setEtat(Etat.OUVERT);
                    }
                }  
            }
        };
    }
    /**
     * Fermer Porte Amont
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> fermerPorteAmont(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(ressources.feuAmont.getEtat() == Etat.OUVERT){
                    System.out.println("La porte amont ne peut pas se fermer car un bateau peut passer . ");
                }
                else{
                    if(ressources.porteAmont.getEtat() == Etat.FERME){
                        System.out.println("La porte est deja fermee");
                        return;
                    }
                        
                   ressources.porteAmont.fermer();
                   ressources.porteAmont.setEtat(Etat.FERME);
                }
            }
        };
    }
    /**
     * Allumer Feu (Vert) Amont
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> allumerFeuAmont(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(ressources.feuAmont.getEtat() == Etat.OUVERT) {
                    System.out.println("Le feu est deja ouvert");
                    return;
                }
                if(ressources.porteAmont.getEtat() == Etat.OUVERT) {
                    ressources.feuAmont.allumer(ressources);
                    ressources.feuAmont.setEtat(Etat.OUVERT);
                    // Si le bateau est dans le premier niveau, le faire passer vers le deuxieme
                    if(positionActuelleBateau == Constantes.NIVEAU1  && sensCirculation == Constantes.AMONT_VERS_AVAL){
                        ressources.bateau.bougerX(Constantes.BATEAU_X_ETAPE_1_ETAT_1,Constantes.BATEAU_X_ETAPE_2_ETAT_1);
                        positionActuelleBateau = Constantes.NIVEAU2;
                    }
                } else{
                    System.out.println("Le feu ne peut etre allume car la porte amont est fermee ");
                }
            }
        };
    }
    /**
     * Eteindre Feu (Vert) Amont (Le feu devient rouge)
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> eteindreFeuAmont(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
               if(ressources.feuAmont.getEtat() == Etat.FERME) {
                    System.out.println("Le feu est deja ferme");
                    return;
                }
                if(ressources.feuAmont.getEtat() == Etat.OUVERT) {
                    ressources.feuAmont.eteindre(ressources);
                    ressources.feuAmont.setEtat(Etat.FERME);
                }
            }
        };
    }
    
    // Aval
    
    /**
     * Ouvrir Vanne Aval
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> ouvrirVanneAval(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(ressources.sas.getEtat() == Constantes.SAS_NIVEAU_MAX){
                    // Le sas est au minimum, le faire monter
                    ressources.sas.passerNiveauBas();
                    ressources.sas.setEtat(Constantes.SAS_NIVEAU_MIN);
                    
                    if(positionActuelleBateau == Constantes.NIVEAU2){
                        ressources.bateau.bougerY(Constantes.BATEAU_Y_ETAPE_2_ETAT_2, Constantes.BATEAU_Y_ETAPE_2_ETAT_1);
                    }

                }
            }
        };
    }
    /**
     * Fermer Vanne Aval
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> fermerVanneAval(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(ressources.sas.getEtat() == Constantes.SAS_NIVEAU_MAX){
                    // Le sas est au minimum, le faire monter
                    ressources.sas.passerNiveauBas();
                    ressources.sas.setEtat(Constantes.SAS_NIVEAU_MIN);
                }
            }
        };
    }
    /**
     * Ouvrir Porte Aval
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> ouvrirPorteAval(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(ressources.sas.getEtat() == Constantes.SAS_NIVEAU_MAX){
                    System.out.println("La porte aval ne peut pas s'ouvrir a cause de la pression. ");
                }
                else{
                    if(ressources.porteAval.getEtat() != Etat.OUVERT){
                        ressources.porteAval.ouvrir();
                        ressources.porteAval.setEtat(Etat.OUVERT);
                    }
                }
            }
        };
    }
    /**
     * Fermer Porte Aval
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> fermerPorteAval(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(ressources.feuAval.getEtat() == Etat.OUVERT){
                    System.out.println("La porte aval ne peut pas se fermer car un bateau peut passer . ");
                }
                else{
                    if(ressources.porteAval.getEtat() == Etat.FERME){
                        System.out.println("La porte est deja fermee");
                        return;
                    }
                        
                   ressources.porteAval.fermer();
                   ressources.porteAval.setEtat(Etat.FERME);
                }
            }
        };
    }
    /**
     * Allumer Feu (Vert) Aval
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> allumerFeuAval(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(ressources.feuAval.getEtat() == Etat.OUVERT) {
                    System.out.println("Le feu est deja ouvert");
                    return;
                }
                if(ressources.porteAval.getEtat() == Etat.OUVERT) {
                    ressources.feuAval.allumer(ressources);
                    ressources.feuAval.setEtat(Etat.OUVERT);
                    // Si le bateau est dans le premier niveau, le faire passer vers le deuxieme
                    if(positionActuelleBateau == Constantes.NIVEAU2 && sensCirculation == Constantes.AMONT_VERS_AVAL){
                        ressources.bateau.bougerX(Constantes.BATEAU_X_ETAPE_2_ETAT_1,Constantes.BATEAU_X_ETAPE_3_ETAT_2);
                        positionActuelleBateau = Constantes.NIVEAU3;
                        // Reinitialiser
                    }
                    if(positionActuelleBateau == Constantes.NIVEAU3 && sensCirculation == Constantes.AVAL_VERS_AMONT){
                        ressources.bateau.bougerX(Constantes.BATEAU_X_ETAPE_3_ETAT_2, Constantes.BATEAU_X_ETAPE_2_ETAT_1);
                        positionActuelleBateau = Constantes.NIVEAU2;
                        // Reinitialiser
                    }
                } else{
                    System.out.println("Le feu ne peut etre allume car la porte amont est fermee ");
                }
            }
        };
    }
    /**
     * Eteindre Feu (Vert) Aval (Le feu devient rouge)
     * @return EventHandler<ActionEvent>
     */
    public EventHandler<ActionEvent> eteindreFeuAval(){
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Eteindre Feu Aval");
            }
        };
    }
}
