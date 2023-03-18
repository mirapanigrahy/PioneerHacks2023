import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LanguageApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Scene scene;
    VBox pick;
    VBox frenchmaster;
    VBox spanishmaster;
    VBox cards;

    Button frenchbtn;
    Button spanishbtn;
    Button englishbtn;

    Button createDeckF;
    Button createDeckS;
@Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Language Flashcards"); 
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setMinWidth(400);
        stage.setMinHeight(400);

        //pages
        pick = new VBox();
        frenchmaster = new VBox();
        spanishmaster = new VBox();
        cards = new VBox();


        //pick language

        Label pickTitle = new Label("Pick a language");
        pickTitle.setTextFill(Color.BLUEVIOLET);
        pickTitle.setFont(new Font("Cambria", 32));

        frenchbtn = new Button("French to English");
        frenchbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene = new Scene(frenchmaster);
                stage.setScene(scene);
                stage.show();

            }

        });

        spanishbtn = new Button("Spanish to English");
        spanishbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene = new Scene(spanishmaster);
                stage.setScene(scene);
                stage.show();

            }

        });
        
        pick.setPadding(new Insets(50));
        pick.setSpacing(20);
        pick.getChildren().addAll(pickTitle, frenchbtn, spanishbtn);

        //decks

        Label frenchTitle = new Label("French to English");
        frenchTitle.setTextFill(Color.BLUEVIOLET);
        frenchTitle.setFont(new Font("Cambria", 32));

        Label spanishTitle = new Label("Spanish to English");
        spanishTitle.setTextFill(Color.BLUEVIOLET);
        spanishTitle.setFont(new Font("Cambria", 32));

        Label MyDecksTitleF = new Label("My Decks");
        MyDecksTitleF.setTextFill(Color.BLUEVIOLET);
        MyDecksTitleF.setFont(new Font("Cambria", 22));

        Label MyDecksTitleS = new Label("My Decks");
        MyDecksTitleS.setTextFill(Color.BLUEVIOLET);
        MyDecksTitleS.setFont(new Font("Cambria", 22));
        
        ArrayList<Deck> decks = new ArrayList<Deck>();
        
        TextField deckName = new TextField();
        Button savebtn = new Button("Save");
        savebtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String a = deckName.getText();
				Deck newDeck = new Deck(a);
				decks.add(newDeck);
				
			}
        	
        });
        cards.getChildren().addAll(deckName, savebtn);

        createDeckF = new Button("Create new deck");
        createDeckF.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene = new Scene(cards);
                stage.setScene(scene);
                stage.show();

            }

        });

        createDeckS = new Button("Create new deck");
        createDeckS.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scene = new Scene(cards);
                stage.setScene(scene);
                stage.show();

            }

        });
        
        frenchmaster.setPadding(new Insets(50));
        frenchmaster.setSpacing(20);
        frenchmaster.getChildren().addAll(frenchTitle, createDeckF, MyDecksTitleF);

        spanishmaster.setPadding(new Insets(50));
        spanishmaster.setSpacing(20);
        spanishmaster.getChildren().addAll(spanishTitle, createDeckS, MyDecksTitleS);


        scene = new Scene(pick);
        stage.setScene(scene);
        stage.show();
    }
}