import java.util.ArrayList;

import javafx.application.Application;
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


		//pick language

		Label pickTitle = new Label("Pick a language");
		pickTitle.setTextFill(Color.BLUEVIOLET);
		pickTitle.setFont(new Font("Cambria", 32));

		frenchbtn = new Button("French to English");
		frenchbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sceneLang = new Scene(master);
				stage.setScene(sceneLang);
				stage.show();

			}

		});

		spanishbtn = new Button("Spanish to English");
		spanishbtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				sceneLang = new Scene(master);
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
		ArrayList<Deck> decksS = new ArrayList<Deck>();

		TextField deckName = new TextField();
		Button savebtn = new Button("Save");
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
				System.out.println(decksS);
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

							if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
								int cardNum = 0;
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
							for (int i = 0; i < decksS.size(); i++) {
								if (b.getText().equals(decksS.get(i).getName())) {
									v = i;
								}
							}

							if (decksS.size() > 0 && decksS.get(v).getNumCards() > 0) {
								int cardNum = 0;
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

				});
				master.getChildren().add(b);

				stage.setScene(sceneLang);
				stage.show();
			}

		});

		cards.getChildren().addAll(deckName, savebtn);

		sceneRun = new Scene(run);

		Button runCards = new Button("Run Through Cards");
		runCards.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.setScene(sceneRun);
				stage.show();

			}

		});

		if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
			
			
			int cardNum = 0;
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
		
		System.out.println("outside");
		
		sceneTest = new Scene(testpage);

		Button test = new Button("Test");
		runCards.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.setScene(sceneTest);
				stage.show();

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
				decksF.get(v).addCard(c);
				
				run.getChildren().clear();
				
				if (decksF.size() > 0 && decksF.get(v).getNumCards() > 0) {
					System.out.println("num cards is not 0");
					int cardNum = 0;
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
			}
		});

		play.getChildren().addAll(runCards,test, addCards, cardFront, cardBack);


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

		for(int i = 0; i<decksF.size(); i++) {
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