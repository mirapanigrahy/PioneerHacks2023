import java.util.ArrayList;
import java.util.HashSet;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LanguageApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	Scene scene;
	Scene sceneLang;
	Scene scenePlay;
	Scene sceneCD;
	Scene sceneRun;
	Scene sceneTest;	
	
	VBox pick = new VBox();
	VBox master = new VBox();
	VBox cards = new VBox();
	VBox play = new VBox();
	VBox run = new VBox();
	VBox testpage = new VBox();

	Button frenchbtn = new Button("French to English");
	Button spanishbtn = new Button("Spanish to English");
	Button runCards = new Button("Run Through Cards");
	Button test = new Button("Test");
	Button savebtn = new Button("Save");
	Button addCards = new Button("Add Card");
	Button backbtn = new Button("Back");
	Button createDeck = new Button("Create new deck");
	
	Label frenchTitle = new Label("French to English");
	Label spanishTitle = new Label("Spanish to English");
	Label MyDecksTitle = new Label("My Decks");
	Label pickTitle = new Label("Pick a Language");
	
	TextField deckName = new TextField();
	TextField cardFront = new TextField();
	TextField cardBack = new TextField();
	
	int cardNum = 0;
	int v = 0;
	boolean french = true;
	
	Color btnColor = new Color((double)13/255,(double)89/255,(double)86/255,1);
	Color c = new Color((double)25/255,(double)95/255,(double)109/255,1);
	Font f = new Font("Impact", 32);
	Insets p = new Insets(20, 20, 20, 20);
	
	ArrayList<Deck> decksF = new ArrayList<Deck>();
	ArrayList<Deck> decksS = new ArrayList<Deck>();
	

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Language Flashcards"); 
		stage.setWidth(400);
		stage.setHeight(400);
		stage.setMinWidth(400);
		stage.setMinHeight(400);
		
		scene = new Scene(pick);
		scenePlay = new Scene(play);
		sceneCD = new Scene(cards);
		sceneTest = new Scene(testpage);
		sceneRun = new Scene(run);
		sceneLang = new Scene(master);


		pick.setStyle("-fx-background-color: #ffd7b5");
		master.setStyle("-fx-background-color: #ffd7b5");
		cards.setStyle("-fx-background-color: #ffd7b5");
		play.setStyle("-fx-background-color: #ffd7b5");
		run.setStyle("-fx-background-color: #ffd7b5");
		testpage.setStyle("-fx-background-color: #ffd7b5");
		
		// Labels
		
		frenchTitle.setTextFill(c);
		frenchTitle.setFont(f);

		spanishTitle.setTextFill(c);
		spanishTitle.setFont(f);

		MyDecksTitle.setTextFill(c);
		MyDecksTitle.setFont(f);

		// VBoxes

		pick.setPadding(new Insets(50));
		pick.setSpacing(20);
		
		master.setPadding(new Insets(50));
		master.setSpacing(20);
		
		startSceneInit(stage);

		stage.setScene(scene);
		stage.show();
		
	}
	
	public void startSceneInit(Stage stage) {
		pick.getChildren().clear();
		pick.setPadding(p);
		
		frenchbtn.setStyle("-fx-background-color: #F8E8E8FF");
		frenchbtn.setTextFill(btnColor);
		spanishbtn.setStyle("-fx-background-color: #F8E8E8FF");
		spanishbtn.setTextFill(btnColor);
		pickTitle.setTextFill(c);
		pickTitle.setFont(f);

		frenchbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				french = true;
				langSceneInit(stage);
				stage.setScene(sceneLang);
				stage.show();
			}

		});

		spanishbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				french = false;
				langSceneInit(stage);
				stage.setScene(sceneLang);
				stage.show();
			}
		});
		
		pick.getChildren().addAll(pickTitle, frenchbtn, spanishbtn);
	}
	
	public void langSceneInit(Stage stage) {
		master.getChildren().clear();
		master.setPadding(p);
		
		backbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				startSceneInit(stage);
				stage.setScene(scene);
				stage.show();	
			}
			
		});
		
		createDeck.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				newDeckSceneInit(stage);
				stage.setScene(sceneCD);
				stage.show();

			}

		});

		if (french) {
			master.getChildren().addAll(backbtn, frenchTitle, createDeck, MyDecksTitle);
		} else {
			master.getChildren().addAll(backbtn, spanishTitle, createDeck, MyDecksTitle);
		}
		
		if (french) {
			for (int i = 0; i < decksF.size(); i++) {
				Button b = new Button(decksF.get(i).getName());
				b.setOnMouseClicked(new EventHandler<MouseEvent>() {
		
					@Override
					public void handle(MouseEvent event) {
						playInit(stage);
						stage.setScene(scenePlay);
						stage.show();
		
						for (int i = 0; i < decksF.size(); i++) {
							if (b.getText().equals(decksF.get(i).getName())) {
								v = i;
							}
						}
		
					}
				});
				master.getChildren().add(b);
			}
		} else {
		
			for (int i = 0; i < decksS.size(); i++) {
				Button b = new Button(decksS.get(i).getName());
				b.setOnMouseClicked(new EventHandler<MouseEvent>() {
		
					@Override
					public void handle(MouseEvent event) {
						playInit(stage);
						stage.setScene(scenePlay);
						stage.show();
		
						for (int i = 0; i < decksS.size(); i++) {
							if (b.getText().equals(decksS.get(i).getName())) {
								v = i;
							}
						}
					}
				});
				master.getChildren().add(b);
			}
		}
		
	}

	public void newDeckSceneInit(Stage stage) {
		cards.getChildren().clear();
		cards.setPadding(p);
		cards.setSpacing(10);
		
		savebtn.setStyle("-fx-background-color: #F8E8E8FF");
		savebtn.setTextFill(btnColor);
		
		savebtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String a = deckName.getText();
				deckName.clear();
				Deck newDeck = new Deck(a);
				if (french) {
					decksF.add(newDeck);
				} else {
					decksS.add(newDeck);
				}

				langSceneInit(stage);
				stage.setScene(sceneLang);
				stage.show();
			}
		});
		
		cards.getChildren().addAll(deckName, savebtn);
	}
	
	public void playInit(Stage stage) {
		play.getChildren().clear();
		play.setPadding(p);
		play.setSpacing(20);
		
		HBox scene3bx = new HBox();
        scene3bx.setSpacing(10);

        HBox term = new HBox();
        HBox translation = new HBox();
        Label termLabel = new Label("Term:           ");
        Label translationLabel = new Label("Translation: ");
        term.getChildren().addAll(termLabel, cardFront);
        term.setSpacing(10);
        translation.getChildren().addAll(translationLabel, cardBack);
        translation.setSpacing(10);

        Color termColor = new Color((double)25/255,(double)95/255,(double)109/255,1);
        termLabel.setTextFill(termColor);
        translationLabel.setTextFill(termColor);
		
		test.setStyle("-fx-background-color: #F8E8E8FF");
		test.setTextFill(btnColor);
		runCards.setStyle("-fx-background-color: #F8E8E8FF");
		runCards.setTextFill(btnColor);	

		runCards.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				cardNum = 0;
				runSceneInit(stage);
				stage.setScene(sceneRun);
				stage.show();
			}
		});
		
		test.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				testSceneInit(stage);
				stage.setScene(sceneTest);
				stage.show();
			}
		});
		
		addCards.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String cf = cardFront.getText();
				String cb = cardBack.getText();
				cardFront.clear();
				cardBack.clear();
				Card c = new Card(cf, cb, true);
				
				if (french) {
					decksF.get(v).addCard(c);
				} else {
					decksS.get(v).addCard(c);
				}
				
				run.getChildren().clear();	
			}
		});
		
		backbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				langSceneInit(stage);
				stage.setScene(sceneLang);
				stage.show();	
			}
			
		});
		
		scene3bx.getChildren().addAll(runCards, test);
		play.getChildren().addAll(backbtn, scene3bx, term, translation, addCards);
	}
	
	public void testSceneInit(Stage stage) {
		
		testpage.getChildren().clear();
		testpage.setPadding(p);
		testpage.setSpacing(10);
		
		backbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				playInit(stage);
				stage.setScene(scenePlay);
				stage.show();	
			}
			
		});
		
		testpage.getChildren().add(backbtn);
		
		if (french) {
			for (int i = 0; i < decksF.get(v).getNumCards(); i++) {
				HBox card = new HBox();
				card.setStyle("-fx-background-color: #FFFFFFFF");
				card.setPadding(p);
				Label phrase = new Label(decksF.get(v).getCard(i).getPhrase());
				card.getChildren().add(phrase);
				TextField ans = new TextField();
				testpage.getChildren().addAll(card, ans);
			}
		} else {
			for (int i = 0; i < decksS.get(v).getNumCards(); i++) {
				HBox card = new HBox();
				card.setStyle("-fx-background-color: #FFFFFFFF");
				card.setPadding(p);
				Label phrase = new Label(decksS.get(v).getCard(i).getPhrase());
				card.getChildren().add(phrase);
				TextField ans = new TextField();
				testpage.getChildren().addAll(card, ans);
			}
		}	
	}
	
	public void runSceneInit(Stage stage) {
		
		run.getChildren().clear();
		run.setPadding(p);
		run.setSpacing(10);

		
		backbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				playInit(stage);
				stage.setScene(scenePlay);
				stage.show();	
			}
			
		});
				
		if (french) {
			if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
				HBox card = new HBox();
				card.setStyle("-fx-background-color: #FFFFFFFF");
				card.setPadding(p);
				Label phrase = new Label(decksF.get(v).getCard(cardNum).getPhrase());
				Label translation = new Label(decksF.get(v).getCard(cardNum).getTranslation());
				if (decksF.get(v).getCard(cardNum).isFront()) {
					card.getChildren().add(phrase);
				} else {
					card.getChildren().add(translation);
				}
				run.getChildren().add(card);
			}
		} else {
			if (decksS.size() > 0 && decksS.get(v).getNumCards() > 0) {
				HBox card = new HBox();
				card.setStyle("-fx-background-color: #FFFFFFFF");
				card.setPadding(p);
				Label phrase = new Label(decksS.get(v).getCard(cardNum).getPhrase());
				Label translation = new Label(decksS.get(v).getCard(cardNum).getTranslation());
				if (decksS.get(v).getCard(cardNum).isFront()) {
					card.getChildren().add(phrase);
				} else {
					card.getChildren().add(translation);
				}
				run.getChildren().add(card);
			}
		}
		
		//run.getChildren().add(backbtn);	
		sceneRun.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle (KeyEvent e) {
				
				if ((french && decksF.get(v).getNumCards() == 0) || (!french && decksS.get(v).getNumCards() == 0)) {
					return;
				}
				
				if (e.getCode().equals(KeyCode.LEFT)) {	
										
					if (cardNum > 0) cardNum--;
					run.getChildren().clear();
					
					if (french) {
						if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
							HBox card = new HBox();
							card.setStyle("-fx-background-color: #FFFFFFFF");
							card.setPadding(p);
							Label phrase = new Label(decksF.get(v).getCard(cardNum).getPhrase());
							Label translation = new Label(decksF.get(v).getCard(cardNum).getTranslation());
							if (decksF.get(v).getCard(cardNum).isFront()) {
								card.getChildren().add(phrase);
							} else {
								card.getChildren().add(translation);
							}
							run.getChildren().addAll(card);
						}
					} else {
						if (decksS.size() > 0 && decksS.get(v).getNumCards() > 0) {
							HBox card = new HBox();
							card.setStyle("-fx-background-color: #FFFFFFFF");
							card.setPadding(p);
							Label phrase = new Label(decksS.get(v).getCard(cardNum).getPhrase());
							Label translation = new Label(decksS.get(v).getCard(cardNum).getTranslation());
							if (decksS.get(v).getCard(cardNum).isFront()) {
								card.getChildren().add(phrase);
							} else {
								card.getChildren().add(translation);
							}
							run.getChildren().addAll(card);
						}
					}
				}
				
				if (e.getCode().equals(KeyCode.RIGHT)) {	
										
					if (french) {
						if (cardNum < decksF.get(v).getNumCards() - 1) cardNum++;
					} else {
						if (cardNum < decksS.get(v).getNumCards() - 1) cardNum++;
					}

					run.getChildren().clear();
					
					if (french) {
						if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
							HBox card = new HBox();
							card.setStyle("-fx-background-color: #FFFFFFFF");
							card.setPadding(p);
							Label phrase = new Label(decksF.get(v).getCard(cardNum).getPhrase());
							Label translation = new Label(decksF.get(v).getCard(cardNum).getTranslation());
							if (decksF.get(v).getCard(cardNum).isFront()) {
								card.getChildren().add(phrase);
							} else {
								card.getChildren().add(translation);
							}
							run.getChildren().addAll(card);
						}
					} else {
						if (decksS.size() > 0 && decksS.get(v).getNumCards() > 0) {
							HBox card = new HBox();
							card.setStyle("-fx-background-color: #FFFFFFFF");
							card.setPadding(p);
							Label phrase = new Label(decksS.get(v).getCard(cardNum).getPhrase());
							Label translation = new Label(decksS.get(v).getCard(cardNum).getTranslation());
							if (decksS.get(v).getCard(cardNum).isFront()) {
								card.getChildren().add(phrase);
							} else {
								card.getChildren().add(translation);
							}
							run.getChildren().addAll(card);
						}
					}
				}
				
				if (e.getCode().equals(KeyCode.SPACE)) {
										
					run.getChildren().clear();
					
					if (french) {
						decksF.get(v).getCard(cardNum).flip();
						if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
							HBox card = new HBox();
							card.setStyle("-fx-background-color: #FFFFFFFF");
							card.setPadding(p);
							Label phrase = new Label(decksF.get(v).getCard(cardNum).getPhrase());
							Label translation = new Label(decksF.get(v).getCard(cardNum).getTranslation());
							if (decksF.get(v).getCard(cardNum).isFront()) {
								card.getChildren().add(phrase);
							} else {
								card.getChildren().add(translation);
							}
							run.getChildren().add(card);
						}
					} else {
						decksS.get(v).getCard(cardNum).flip();
						if (decksS.size() > 0 && decksS.get(v).getNumCards() > 0) {
							HBox card = new HBox();
							card.setStyle("-fx-background-color: #FFFFFFFF");
							card.setPadding(p);
							Label phrase = new Label(decksS.get(v).getCard(cardNum).getPhrase());
							Label translation = new Label(decksS.get(v).getCard(cardNum).getTranslation());
							if (decksS.get(v).getCard(cardNum).isFront()) {
								card.getChildren().add(phrase);
							} else {
								card.getChildren().add(translation);
							}
							run.getChildren().add(card);
						}
					}
				}
				
				//run.getChildren().add(backbtn);
				
			}
		});	
	}	
}
