package ecluse;

import utils.Etat;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    Label messagesLabel = new Label("");

    // Variables de controle de l'etat ecluse

    // Mode de fonctionnement de l'ecluse
    public short modeFonctionnement = Constantes.MODE_MANUEL;

    // Position actuelle ou le bateau se trouve
    public short positionActuelleBateau = Constantes.NIVEAU1;

    // Sens de circulation du bateau
    public short sensCirculation = Constantes.AVAL_VERS_AMONT;


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
        
        this.initResourses(sensCirculation);
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
    
    
    public BorderPane centralPane(){
        BorderPane bp = new BorderPane();
        gameContainer = new Pane();
        ecluseControlsContainer = boutonsPane();

        // Faire un Render des boutons de controle)
        bp.setLeft(ecluseControlsContainer);
        // Fin boutons de controle

        bp.prefHeight(Constantes.WINDOWS_HEIGHT);
        bp.prefWidthProperty().bind(scene.widthProperty());

        bp.setCenter(gameContainer);
        
        return bp;
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
        boutonsPane.setPadding(new Insets(10,10,10,20));

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
        
        
        
        // GridPane pour les boutons des sens
        GridPane boutonsSensPane = new GridPane();
        
        Button amontVersAval = new Button("Amont Vers Aval");
        amontVersAval.setOnAction(e -> remiseAZero(Constantes.AMONT_VERS_AVAL));
        
        Button avalVersAmont = new Button("Aval Vers Amont");
        avalVersAmont.setOnAction(e -> remiseAZero(Constantes.AVAL_VERS_AMONT));
        
        boutonsSensPane.add(amontVersAval, 0, 0);
        boutonsSensPane.add(avalVersAmont, 0, 1);
        

        // Label pour afficher les messages d'erreur eventuels
        
        
        // Attachement des Pane Amont et Aval au Pane des Boutons
        boutonsPane.setHgap(20);
        boutonsPane.setVgap(10);
        boutonsPane.add(amontPane,0,4);
        boutonsPane.add(avalPane, 0,7);
        boutonsPane.add(boutonsSensPane, 0,9);
        boutonsPane.add(messagesLabel, 0,11);


        /*
            Pane des vitesses d'objets
         */


        final Slider vitesseBateauSlider = new Slider(500, 3000, 1);
        final Slider vitessePortesSlider = new Slider(500, 3000, 1);
        final Slider tempsAttenteSlider = new Slider(500, 3000, 1);


        final Label vitesseBateauLabel = new Label("Vitesse Bateau:");
        final Label vitessePortesLabel = new Label("Vitesse des portes :");
        final Label tempsAttenteLabel = new Label("Vitesse attente");

        final Label vitesseBateauValue = new Label(
                Double.toString(vitesseBateauSlider.getValue()));
        final Label vitessePortesValue = new Label(
                Double.toString(vitessePortesSlider.getValue()));
        final Label tempsAttenteValue = new Label(
                Double.toString(tempsAttenteSlider.getValue()));

        final  Color textColor = Color.BLACK;

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(10);

        /*
            Vitesse bateau composants
         */

        vitesseBateauLabel.setTextFill(textColor);
        GridPane.setConstraints(vitesseBateauLabel, 0, 1);
        grid.getChildren().add(vitesseBateauLabel);
        vitesseBateauSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                ressources.bateau.setVitesseDeplacement(Duration.millis( vitesseBateauSlider.getMax() - (Double)new_val));
                vitesseBateauValue.setText(String.format("%.2f", new_val));
            }
        });

        GridPane.setConstraints(vitesseBateauSlider, 0, 2);
        grid.getChildren().add(vitesseBateauSlider);

        vitesseBateauValue.setTextFill(textColor);
        GridPane.setConstraints(vitesseBateauValue, 1, 2);
        grid.getChildren().add(vitesseBateauValue);

        /*
            Vitesse ouverture et fermture de Portes
         */
        vitessePortesLabel.setTextFill(textColor);
        GridPane.setConstraints(vitessePortesLabel, 0, 3);
        grid.getChildren().add(vitessePortesLabel);
        vitessePortesSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                ressources.porteAval.setVitesseDeplacement(Duration.millis( vitessePortesSlider.getMax() - (Double)new_val));
                ressources.porteAmont.setVitesseDeplacement(Duration.millis( vitessePortesSlider.getMax() - (Double)new_val));
                vitessePortesValue.setText(String.format("%.2f", new_val));
            }
        });

        GridPane.setConstraints(vitessePortesSlider, 0, 4);
        grid.getChildren().add(vitessePortesSlider);

        vitessePortesValue.setTextFill(textColor);
        GridPane.setConstraints(vitessePortesValue, 1, 4);
        grid.getChildren().add(vitessePortesValue);



        /*
            Temps attente
         */
        tempsAttenteLabel.setTextFill(textColor);
        GridPane.setConstraints(tempsAttenteLabel, 0, 5);
        grid.getChildren().add(tempsAttenteLabel);

        tempsAttenteSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                tempsAttenteValue.setText(String.format("%.2f", new_val));
            }
        });
        GridPane.setConstraints(tempsAttenteSlider, 0, 6);
        grid.getChildren().add(tempsAttenteSlider);

        tempsAttenteValue.setTextFill(textColor);
        GridPane.setConstraints(tempsAttenteValue, 1, 6);
        grid.getChildren().add(tempsAttenteValue);
        boutonsPane.add(grid, 0,12);
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
                    messagesLabel.setText("La vanne ne peut pas s'ouvrir car\n la porte Aval est ouverte");
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
                    messagesLabel.setText("La porte amont ne peut pas \ns'ouvrir a cause de la pression.");
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
                    messagesLabel.setText("La porte amont ne peut pas se fermer\ncar un bateau peut passer . ");
                }
                else{
                    if(ressources.porteAmont.getEtat() == Etat.FERME){
                        System.out.println("La porte est deja fermee");
                        messagesLabel.setText("La porte est deja fermee");
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
                    messagesLabel.setText("Le feu est deja ouvert");
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
                    else if(positionActuelleBateau == Constantes.NIVEAU2 && sensCirculation == Constantes.AVAL_VERS_AMONT){
                        ressources.bateau.bougerX(Constantes.BATEAU_X_ETAPE_2_ETAT_1,Constantes.BATEAU_X_ETAPE_1_ETAT_1);
                        positionActuelleBateau = Constantes.NIVEAU1;
                        // Reinitialiser le monde
                        
                    }
                } else{
                    System.out.println("Le feu ne peut etre allume car la porte amont est fermee ");
                    messagesLabel.setText("Le feu ne peut etre allume \ncar la porte amont est fermee ");
                    
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
                    System.out.println("Le feu est deja eteint !");
                    messagesLabel.setText("Le feu est deja eteint !");
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
                // Si la porte Amont est ouverte, erreur
                if(ressources.porteAmont.getEtat() == Etat.OUVERT){
                    System.out.println("La vanne ne peut pas s'ouvrir car la porte Amont est ouverte.");
                    messagesLabel.setText("La vanne ne peut pas s'ouvrir car\n la porte Amont est ouverte.");
                    return;          
                }

                if(ressources.sas.getEtat() == Constantes.SAS_NIVEAU_MAX){
                    // Le sas est au minimum, le faire monter
                    ressources.sas.passerNiveauBas();
                    ressources.sas.setEtat(Constantes.SAS_NIVEAU_MIN);
                    ressources.sasView.setFitHeight(Constantes.SAS_IMAGE_MIN_HEIGHT);
                    ressources.sasView.setTranslateY(Constantes.SAS_Y_SENS_DIRECT + Constantes.TRANSITION_OFFSET);

                    // Tricher Un Peu
                    if(sensCirculation == Constantes.AVAL_VERS_AMONT){
                        TranslateTransition tr = new TranslateTransition(Constantes.DUREE, ressources.sas.getImage());
                        tr.setByY(-Constantes.TRANSITION_OFFSET);
                        tr.play();
                    }
                    
                    //
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
                // Si la vanne est deja ferme eviter de descendre le niveau d'eau
                if(ressources.porteAmont.getEtat() == Etat.OUVERT){
                    System.out.println("La vanne ne peut pas se fermer car deja fermée.");
                    messagesLabel.setText("La vanne ne peut pas se fermer car\n deja fermée.");
                    return;
                }

                // Si la porte aval est ouverte, erreur
                if(ressources.porteAmont.getEtat() == Etat.OUVERT){
                    System.out.println("La vanne ne peut pas s'ouvrir car la porte Amont est ouverte.");
                    messagesLabel.setText("La vanne ne peut pas s'ouvrir car\n la porte Amont est ouverte.");
                    return;
                }

                if(ressources.sas.getEtat() == Constantes.SAS_NIVEAU_MAX){
                    // Le sas est au maximum, le faire descendre
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
                    System.out.println("La porte aval ne peut pas s'ouvrir a cause de la pression.");
                    messagesLabel.setText("La porte aval ne peut pas s'ouvrir \na cause de la pression.");
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
                    System.out.println("La porte aval ne peut pas se fermer car un bateau peut passer.");
                    messagesLabel.setText("(\"La porte aval ne peut pas se \nfermer car un bateau peut passer");
                }
                else{
                    if(ressources.porteAval.getEtat() == Etat.FERME){
                        System.out.println("La porte est deja fermee");
                        messagesLabel.setText("La porte est deja fermee");
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
                    System.out.println("Le feu ne peut etre allume car la porte amont est fermee");
                    messagesLabel.setText("Le feu ne peut etre allume \ncar la porte amont est fermee");
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
                ressources.feuAval.setEtat(Etat.FERME);
            }
        };
    }
    
      
    /**
     * Remettre a 0 les proprietes de l'Ecluse
     */
    public void remiseAZero(short sens){
        sensCirculation = sens;
        modeFonctionnement = Constantes.MODE_MANUEL;

        if(sens == Constantes.AMONT_VERS_AVAL){
            positionActuelleBateau = Constantes.NIVEAU1;
            ressources.sas.setEtat(Constantes.SAS_NIVEAU_MIN);
        }
        else{
            positionActuelleBateau = Constantes.NIVEAU3;
            ressources.sas.setEtat(Constantes.SAS_NIVEAU_MAX);
        }
        initResourses(sensCirculation); 
        intialiserEnvironement();
    }

    public static Resources getRessources() {
        return ressources;
    }
}
