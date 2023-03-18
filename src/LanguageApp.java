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
    Scene sceneF;
    Scene sceneS;
    Scene scenePlay;
    Scene sceneCF;
    Scene sceneCS;
    Scene sceneRun;
    
    VBox pick;
    VBox frenchmaster;
    VBox spanishmaster;
    VBox cardsS;
    VBox cardsF;
    VBox play;
    VBox run;

    Button frenchbtn;
    Button spanishbtn;
    Button englishbtn;

    Button createDeckF;
    Button createDeckS;
    
    int v = 0;

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
        cardsS = new VBox();
        cardsF = new VBox();


        //pick language

        Label pickTitle = new Label("Pick a language");
        pickTitle.setTextFill(Color.BLUEVIOLET);
        pickTitle.setFont(new Font("Cambria", 32));

        frenchbtn = new Button("French to English");
        frenchbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                sceneF = new Scene(frenchmaster);
                stage.setScene(sceneF);
                stage.show();

            }

        });

        spanishbtn = new Button("Spanish to English");
        spanishbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                sceneS = new Scene(spanishmaster);
                stage.setScene(sceneS);
                stage.show();

            }

        });
        
        pick.setPadding(new Insets(50));
        pick.setSpacing(20);
        pick.getChildren().addAll(pickTitle, frenchbtn, spanishbtn);
        
        

        //decks
        scenePlay = new Scene(play);

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
        	
        
        ArrayList<Deck> decksF = new ArrayList<Deck>();
                
        TextField deckNameF = new TextField();
        Button savebtnF = new Button("Save");
        savebtnF.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String a = deckNameF.getText();
				deckNameF.clear();
				Deck newDeck = new Deck(a);
				decksF.add(newDeck);
				Button b = new Button(newDeck.getName());
	        	b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						stage.setScene(scenePlay);
		                stage.show();
						
		                for (int i = 0; i < decksF.size(); i++) {
		                	if (b.getText().equals(decksF.get(i).getName())) {
		                		v = i;
		                	}
		                }
					}
	        		
	        	});
	        	frenchmaster.getChildren().add(b);
	        	
	        	stage.setScene(sceneF);
                stage.show();
			}
        	
        });
        cardsF.getChildren().addAll(deckNameF, savebtnF);
        
        sceneRun = new Scene(run);
        
        Button runCards = new Button("Run Through Cards");
        runCards.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.setScene(sceneRun);
                stage.show();
				
			}
        	
        });
        
        Button test = new Button("Test");
        
        
        
        ArrayList<Deck> decksS = new ArrayList<Deck>();
        
        TextField deckNameS = new TextField();
        Button savebtnS = new Button("Save");
        savebtnS.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String a = deckNameS.getText();
				deckNameS.clear();
				Deck newDeck = new Deck(a);
				decksS.add(newDeck);
	        	Button b = new Button(newDeck.getName());
	        	b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						stage.setScene(scenePlay);
		                stage.show();
						
		                for (int i = 0; i < decksS.size(); i++) {
		                	if (b.getText().equals(decksS.get(i).getName())) {
		                		v = i;
		                	}
		                }
						
					}
	        		
	        	});
	        	spanishmaster.getChildren().add(b);
	        	
	        	stage.setScene(sceneS);
                stage.show();
				
				
			}
        	
        });
        cardsS.getChildren().addAll(deckNameS, savebtnS);
        

        createDeckF = new Button("Create new deck");
        createDeckF.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
     
                stage.setScene(sceneCF);
                stage.show();

            }

        });
        
        sceneCF = new Scene(cardsF);
        sceneCS = new Scene(cardsS);

        createDeckS = new Button("Create new deck");
        createDeckS.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                stage.setScene(sceneCS);
                stage.show();

            }

        });
        
        frenchmaster.setPadding(new Insets(50));
        frenchmaster.setSpacing(20);
        frenchmaster.getChildren().addAll(frenchTitle, createDeckF, MyDecksTitleF);

        spanishmaster.setPadding(new Insets(50));
        spanishmaster.setSpacing(20);
        spanishmaster.getChildren().addAll(spanishTitle, createDeckS, MyDecksTitleS);
        
        for(int i = 0; i<decksF.size(); i++) {
        	Button b = new Button(decksF.get(i).getName());
        	b.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					//open up play page
					
				}
        		
        	});
        	frenchmaster.getChildren().add(b);
        }

        scene = new Scene(pick);
        stage.setScene(scene);
        stage.show();
    }
}