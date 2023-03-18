import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Game extends Application {

	GameWorld world; 
	private static Color TEXT_C;
	private static Color RECT_C;
	private Button instructions;
	private Button newGame;
	private Score scoreVal;
	private VBox root;
	
	public Score getScore() {
		return scoreVal;
	}

	public static void main(String[] args) {
		TEXT_C = Color.rgb(99, 71, 53);
		RECT_C = Color.rgb(191, 177, 161);
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Game 2048");
		stage.setResizable(false); 
		
		root = new VBox();
		root.setPadding(new Insets(10));
		root.setStyle("-fx-background-color: #f5efe4");
		
		HBox header = new HBox(30);
		
		//2048 title
		Text title = new Text("  2 0 4 8");
		title.setFont(Font.font("Impact", FontWeight.THIN, 70));	
		title.setFill(TEXT_C);
		
		//score rectangle
		Rectangle rect = new Rectangle(150,60);
		rect.setFill(RECT_C);
		rect.setArcHeight(15);
		rect.setArcWidth(15);
		
		StackPane scorePane = new StackPane();
		VBox scoreCol = new VBox(2);
		Label score = new Label("SCORE");
		scoreVal = new Score();
		
		scoreCol.setPadding(new Insets(20));
		scoreCol.setAlignment(Pos.CENTER);
		
		score.setFont(Font.font("Impact", FontWeight.THIN, 15));
		scoreVal.setFont(Font.font("Impact", FontWeight.THIN, 25));
		
		score.setTextFill(Color.rgb(232, 222, 211));		
		scoreVal.setFill(Color.WHITE);
		
		scoreCol.getChildren().addAll(score,scoreVal);
		scorePane.getChildren().addAll(rect, scoreCol);
		
		//instructions button & new game button
		ButtonHandler bHandler = new ButtonHandler();
		bHandler.setGame(this);
		HBox playRow = new HBox(60);
		instructions = new Button("how to play -->");
		newGame = new Button("New Game");
		instructions.setOnAction(bHandler);
		newGame.setOnAction(bHandler);
		
		playRow.setPadding(new Insets(10));
		playRow.setAlignment(Pos.CENTER);
		
		instructions.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, 20));
		newGame.setFont(Font.font("Impact", FontWeight.THIN, 20));
		
		instructions.setUnderline(true);
		instructions.setBackground(null);
		newGame.setTextFill(Color.WHITE);
		newGame.setStyle("-fx-background-color: #8f7657");
		
		playRow.getChildren().addAll(newGame, instructions);
		header.getChildren().addAll(title, scorePane);
		
		world = new GameWorld(this);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		root.getChildren().addAll(header, playRow, world);
		root.setAlignment(Pos.CENTER);
		stage.show();
	}
	
	private class ButtonHandler implements EventHandler<ActionEvent> {
		
		Game g = null;
		public void setGame(Game g) {
			this.g = g;
		}

		@Override
		public void handle(ActionEvent e) {
			Button src = (Button) e.getSource();
			if (src == instructions) {
				Stage popupStage = new Stage();
				VBox infoCol = new VBox(10);
				Button done = new Button("Done");
				Text title = new Text("G A M E   I N S T R U C T I O N S");
				Text txt = new Text("Using the arrow keys, you can move tiles around and combine tiles of the same number to reach higher ranking tiles. Every time you make a move, a new tile will generate in a random unoccupied position. Your score will increase each time you make a new tile.\n\nOnce you reach the 2048 tile, you win! If there are no more possible moves you can make, you lose.\n");
	
				Scene scene = new Scene(infoCol, 500, 400);
				
				infoCol.setPadding(new Insets(50));
				
				txt.setTextAlignment(TextAlignment.CENTER);
				title.setTextAlignment(TextAlignment.CENTER);
				infoCol.setAlignment(Pos.CENTER);
				
				title.setFill(TEXT_C);
				txt.setFill(TEXT_C);
				infoCol.setStyle("-fx-background-color: #f5ecdf");
				done.setStyle("-fx-background-color: #a3865b");
				done.setTextFill(Color.rgb(242, 235, 223));
				
				txt.setFont(Font.font("Verdana", 15));
				title.setFont(Font.font("Impact", 25));
				txt.setWrappingWidth(400);
				
				infoCol.getChildren().addAll(title, txt, done);
				
				done.setOnAction(new EventHandler<ActionEvent>( ) {
					@Override
					public void handle(ActionEvent e) {
						popupStage.close();							
					}
				});
				
				popupStage.setScene(scene);
				popupStage.showAndWait();
			} else if (src == newGame) {
				root.getChildren().remove(world);
				world = new GameWorld(g);
				g.getScore().reset();
				root.getChildren().addAll(world);
			} 
		} 
	}
}
