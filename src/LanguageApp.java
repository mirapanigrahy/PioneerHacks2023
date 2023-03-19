import java.util.ArrayList;
import java.util.HashSet;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent; 
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
	Scene sceneC;
	Scene sceneRun;
	Scene sceneTest;

	Scene cardScene;


	VBox pick;
	VBox master;
	VBox cards;
	VBox play;
	VBox run;
	VBox testpage;
	VBox cardHolder;

	Button frenchbtn;
	Button spanishbtn;
	Button englishbtn;

	Button createDeck;
	HashSet<KeyCode> keycodeDown;
	int cardNum = 0;
	int v = 0;
	boolean french = true;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Language Flashcards"); 
		stage.setWidth(400);
		stage.setHeight(400);
		stage.setMinWidth(400);
		stage.setMinHeight(400);

		//pages
		pick = new VBox();
		master = new VBox();
		cards = new VBox();
		play = new VBox();
		run = new VBox();
		testpage = new VBox();
		pick.setStyle("-fx-background-color: #ffd7b5");
		master.setStyle("-fx-background-color: #ffd7b5");
		cards.setStyle("-fx-background-color: #ffd7b5");
		play.setStyle("-fx-background-color: #ffd7b5");
		run.setStyle("-fx-background-color: #ffd7b5");
		testpage.setStyle("-fx-background-color: #ffd7b5");


		//pick language

		Label pickTitle = new Label("Pick a language");
		Color c = new Color((double)25/255,(double)95/255,(double)109/255,1);
		pickTitle.setTextFill(c);
		pickTitle.setFont(new Font("Cambria", 32));

		Color btnColor = new Color((double)13/255,(double)89/255,(double)86/255,1);

		frenchbtn = new Button("French to English");
		frenchbtn.setStyle("-fx-background-color: #F8E8E8FF");
		frenchbtn.setTextFill(btnColor);

		sceneLang = new Scene(master);
		frenchbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				stage.setScene(sceneLang);
				stage.show();
			}

		});

		spanishbtn = new Button("Spanish to English");
		spanishbtn.setStyle("-fx-background-color: #F8E8E8FF");
		spanishbtn.setTextFill(btnColor);
		spanishbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.setScene(sceneLang);
				stage.show();
			}
		});

		pick.setPadding(new Insets(50));
		pick.setSpacing(20);
		pick.getChildren().addAll(pickTitle, frenchbtn, spanishbtn);


		//decks
		scenePlay = new Scene(play);
		//cardScene = new Scene(cardHolder);

		Label frenchTitle = new Label("French to English");
		frenchTitle.setTextFill(c);
		frenchTitle.setFont(new Font("Cambria", 32));

		Label spanishTitle = new Label("Spanish to English");
		spanishTitle.setTextFill(c);
		spanishTitle.setFont(new Font("Cambria", 32));

		Label MyDecksTitleF = new Label("My Decks");
		MyDecksTitleF.setTextFill(c);
		MyDecksTitleF.setFont(new Font("Cambria", 22));

		Label MyDecksTitleS = new Label("My Decks");
		MyDecksTitleS.setTextFill(c);
		MyDecksTitleS.setFont(new Font("Cambria", 22));


		ArrayList<Deck> decksF = new ArrayList<Deck>();
		ArrayList<Deck> decksS = new ArrayList<Deck>();

		TextField deckName = new TextField();
		Button savebtn = new Button("Save");
		savebtn.setStyle("-fx-background-color: #F8E8E8FF");
		savebtn.setTextFill(btnColor);
		savebtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String a = deckName.getText();
				deckName.clear();
				Deck newDeck = new Deck(a);
				if (french) {
					decksF.add(newDeck);
				} else {
					decksS.add(newDeck);
				}
				
				System.out.println(decksF);
				System.out.println("f deck size " + decksF.size());
				System.out.println(decksS);
				System.out.println("s deck size " + decksS.size());
				Button b = new Button(newDeck.getName());
				b.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						stage.setScene(scenePlay);
						stage.show();

						if (french) {
							for (int i = 0; i < decksF.size(); i++) {
								if (b.getText().equals(decksF.get(i).getName())) {
									v = i;
								}
							}

						} else {
							for (int i = 0; i < decksS.size(); i++) {
								if (b.getText().equals(decksS.get(i).getName())) {
									v = i;
								}
							}
						}	
					}
				});
				master.getChildren().add(b);

				stage.setScene(sceneLang);
				stage.show();
			}

		});

		cards.getChildren().addAll(deckName, savebtn);

		sceneRun = new Scene(run);

		Button runCards = new Button("Run Through Cards");
		runCards.setStyle("-fx-background-color: #F8E8E8FF");
		runCards.setTextFill(btnColor);
		System.out.println(runCards);
		runCards.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cardNum = 0;
				
				stage.setScene(sceneRun);
				stage.show();
				
				if (french) {
					if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
						HBox card = new HBox();
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
				
				sceneRun.setOnKeyPressed(new EventHandler<KeyEvent>() {
					
					@Override
					public void handle (KeyEvent e) {
						if (e.getCode().equals(KeyCode.LEFT)) {	
							
							if (cardNum > 0) cardNum--;
							run.getChildren().clear();
							
							if (french) {
								if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
									HBox card = new HBox();
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
						
						if (e.getCode().equals(KeyCode.SPACE)) {
							
							run.getChildren().clear();
							decksF.get(v).getCard(cardNum).flip();
							
							if (french) {
								if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
									HBox card = new HBox();
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
					}
				});
			}
		});
				
		sceneTest = new Scene(testpage);

		Button test = new Button("Test");
		test.setStyle("-fx-background-color: #F8E8E8FF");
		test.setTextFill(btnColor);

		test.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.setScene(sceneTest);
				stage.show();
				
				if (french) {
					
				} else {
					
				}

			}

		});

		TextField cardFront = new TextField();
		TextField cardBack = new TextField();
		Button addCards = new Button("Add Card");
		addCards.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String cf = cardFront.getText();
				String cb = cardBack.getText();
				Card c = new Card(cf, cb, true);
				
				if (french) {
					decksF.get(v).addCard(c);
				} else {
					decksS.get(v).addCard(c);
				}
				
				run.getChildren().clear();
				
			}
		});

		play.getChildren().addAll(runCards, test, addCards, cardFront, cardBack);


		createDeck = new Button("Create new deck");
		createDeck.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				stage.setScene(sceneC);
				stage.show();

			}

		});

		sceneC = new Scene(cards);

		master.setPadding(new Insets(50));
		master.setSpacing(20);
		
		if (french) {
			master.getChildren().addAll(frenchTitle, createDeck, MyDecksTitleF);
		} else {
			master.getChildren().addAll(frenchTitle, createDeck, MyDecksTitleS);
		}

		
		//FIXXX
		
		for(int i = 0; i < decksF.size(); i++) {
			Button b = new Button(decksF.get(i).getName());
			b.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					//open up play page

				}

			});
			master.getChildren().add(b);
		}	

		scene = new Scene(pick);

		stage.setScene(scene);
		stage.show();
		
	}
	
}
