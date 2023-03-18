//import java.util.ArrayList;
//
//import engine.World;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
//import javafx.stage.Stage;
//
//
//public class GameWorld extends World {
//
//	private Game game;
//	private boolean gameLost;
//	private boolean gameWon; 
//	private Image happyMcLeod;
//	private Image sadMcLeod;
// 	private ArrayList<Tile> tiles = new ArrayList<Tile>();
// 	private int LENGTH = 4; 
//	private int IMAGE_WIDTH = 90; 
//	private int DIST = IMAGE_WIDTH + 10;  		
//	private int WIN_VAL = 2048;  
//	private int SPEED = 25;
//	private int SHIFT = 10;
//	
//	public GameWorld(Game g) {
//		game = g;
//		happyMcLeod = new Image("file:images/happy_mcleod.png");
//		sadMcLeod = new Image("file:images/sad_mcleod.png");
//		setPrefSize(3 * DIST + IMAGE_WIDTH + 2*SHIFT, 3 * DIST + IMAGE_WIDTH + 2*SHIFT);
//	}
//	
//	@Override
//	public void act (long now) {
//		
//	}
//	
//	public Tile getTile(int r, int c) {
//		for (int i = 0; i < tiles.size(); i++) {
//			if (tiles.get(i).getRow() == r && tiles.get(i).getCol() == c) {
//				return tiles.get(i);
//			}
//		}
//		return null;
//	}
//	
//	public void removeTile(int r, int c) {
//		for (int i = 0; i < tiles.size(); i++) {
//			if (tiles.get(i).getRow() == r && tiles.get(i).getCol() == c) {
//				tiles.remove(i);
//				break; 
//			}
//		}
//	}
//
//	@Override
//	public void onDimensionsInitialized() {	
//		ImageView background = new ImageView(new Image("file:images/background.png"));
//		getChildren().addAll(background);
//		background.setX(0);
//		background.setX(0);
//		background.setFitWidth(3 * DIST + IMAGE_WIDTH + SHIFT*2);
//		background.setFitHeight(3 * DIST + IMAGE_WIDTH + SHIFT*2);
//		
//		for (int i = 0; i < LENGTH; i++) {
//			for (int j = 0; j < LENGTH; j++) {	
//				Tile t = new Tile(0, i, j);
//				t.setFitWidth(IMAGE_WIDTH);
//				t.setPreserveRatio(true);
//				t.setX(SHIFT + DIST * i);
//				t.setY(SHIFT + DIST * j); 
//				add(t);
//			}
//		}
//		
//		Tile r1; 
//		Tile r2; 
//		int randR = (int)(Math.random() * 4); 
//		int randC = (int)(Math.random() * 4);
//		int randNum = (int)(Math.random() * 10);
//		
//		if (randNum == 0) r1 = new Tile(4, randR, randC); 
//		else r1 = new Tile(2, randR, randC); 
//		
//		r1.setFitWidth(IMAGE_WIDTH);
//		r1.setPreserveRatio(true);
//		r1.setX(SHIFT + DIST * randR);
//		r1.setY(SHIFT + DIST * randC); 
//		add(r1);
//		tiles.add(r1);
//		
//		int randR2 = (int)(Math.random() * 4);
//		int randC2 = (int)(Math.random() * 4);
//		while (randR2 == randR && randC2 == randC) {
//			randR2 = (int)(Math.random() * 4);
//			randC2 = (int)(Math.random() * 4);
//		}
//		randNum = (int)(Math.random() * 10);
//		
//		if (randNum == 0) r2 = new Tile(4, randR2, randC2); 
//		else r2 = new Tile(2, randR2, randC2); 
//		
//		r2.setFitWidth(IMAGE_WIDTH);
//		r2.setPreserveRatio(true);
//		r2.setX(SHIFT + DIST * randR2);
//		r2.setY(SHIFT + DIST * randC2); 
//		add(r2);
//		tiles.add(r2);
//		
//		setOnKeyPressed(new keyPressed());
//		start();
//	}
//	
//	private class keyPressed implements EventHandler<KeyEvent> {
//		@Override
//		public void handle(KeyEvent e) {
//		
//			if (!gameLost && !gameWon) {
//				
//				boolean moved = false;
//				
//				if (e.getCode().equals(KeyCode.LEFT)) {	
//					for (int i = 1; i < LENGTH; i++) {
//						for (int j = 0; j < LENGTH; j++) {	
//
//							if (getTile(i, j) != null) {
//								
//								boolean combine = false;
//								int num = 0;
//								for (int k = i; k > 0; k--) {
//									if (getTile(k - 1, j) == null) {
//										num++;
//										moved = true;
//									} else if (getTile(k - 1, j).getVal() == getTile(i, j).getVal() && !getTile(k - 1, j).getNewTile()) {
//										num = k - 1;
//										combine = true;
//										moved = true;
//										break;
//									} else {
//										break;
//									}
//								}
//								
//								Tile moving = getTile(i, j);
//								
//								if (combine) {
//									Tile t = moving.combine(true, getTile(num, j));
//									tiles.add(t);
//									game.getScore().setChange(t.getVal());
//									if (t.getVal() == WIN_VAL) gameWon = true;
//									moving.setMovement(-SPEED, 0);
//									moving.setDistance((i - num) * DIST);
//									removeTile(num, j);
//									removeTile(i, j);
//									combine = false;
//								} else {									
//									moving.setPosition(i - num, j);
//									moving.setMovement(-SPEED, 0);
//									moving.setDistance(num * DIST);
//								}
//							}
//						}
//					}
//				}
//				
//				
//				if (e.getCode().equals(KeyCode.RIGHT)) {
//					for (int i = LENGTH - 2; i >= 0; i--) {
//						for (int j = 0; j < LENGTH; j++) {
//							
//							if (getTile(i, j) != null) {
//								
//								boolean combine = false;
//								int num = 0;
//								for (int k = i; k < LENGTH - 1; k++) {
//									if (getTile(k + 1, j) == null) {
//										num++;
//										moved = true;
//									} else if (getTile(k + 1, j).getVal() == getTile(i, j).getVal() && !getTile(k + 1, j).getNewTile()) {
//										num = k + 1;
//										combine = true;
//										moved = true;
//										break;
//									} else {
//										break;
//									}
//								}
//								
//								Tile moving = getTile(i, j);
//								
//								if (combine) {
//									Tile t = moving.combine(true, getTile(num, j));
//									game.getScore().setChange(t.getVal());
//									tiles.add(t);
//									if (t.getVal() == WIN_VAL) gameWon = true;
//									moving.setMovement(SPEED, 0);
//									moving.setDistance((num - i) * DIST);	
//									removeTile(num, j);
//									removeTile(i, j);
//									combine = false;
//								} else {									
//									moving.setPosition(i + num, j);
//									moving.setMovement(SPEED, 0);
//									moving.setDistance(num * DIST);
//								}
//							}
//						}
//					}
//				}
//				
//				
//				if (e.getCode().equals(KeyCode.UP)) {	
//					for (int i = 1; i < LENGTH; i++) {
//						for (int j = 0; j < LENGTH; j++) {
//							
//							if (getTile(j, i) != null) {
//								
//								boolean combine = false;
//								int num = 0;
//								for (int k = i; k > 0; k--) {
//									if (getTile(j, k - 1) == null) {
//										num++;
//										moved = true;
//									} else if (getTile(j, k - 1).getVal() == getTile(j, i).getVal() && !getTile(j, k - 1).getNewTile()) {
//										num = k - 1;
//										combine = true;
//										moved = true;
//										break;
//									} else {
//										break;
//									}
//								}
//							
//								Tile moving = getTile(j, i);
//								
//								if (combine) {
//									Tile t = moving.combine(true, getTile(j, num));
//									game.getScore().setChange(t.getVal());
//									tiles.add(t);
//									if (t.getVal() == WIN_VAL) gameWon = true;
//									moving.setMovement(0, -SPEED);
//									moving.setDistance((i - num) * DIST);	
//									removeTile(j, num);
//									removeTile(j, i);
//									combine = false;
//								} else {									
//									moving.setPosition(j, i - num);
//									moving.setMovement(0, -SPEED);
//									moving.setDistance(num * DIST);
//								}
//							}
//						}
//					}
//				}
//				
//				if (e.getCode().equals(KeyCode.DOWN)) {	
//					for (int i = LENGTH - 2; i >= 0; i--) {
//						for (int j = 0; j < LENGTH; j++) {
//						
//							if (getTile(j, i) != null) {
//								
//								boolean combine = false;
//								int num = 0;
//								for (int k = i; k < LENGTH - 1; k++) {
//									if (getTile(j, k + 1) == null) {
//										num++;
//										moved = true;
//									} else if (getTile(j, k + 1).getVal() == getTile(j, i).getVal() && !getTile(j, k + 1).getNewTile()) {
//										num = k + 1;
//										combine = true;
//										moved = true;
//										break;
//									} else {
//										break;
//									}
//								}
//							
//								Tile moving = getTile(j, i);
//								
//								if (combine) {
//									Tile t = moving.combine(true, getTile(j, num));
//									game.getScore().setChange(t.getVal());
//									tiles.add(t);
//									if (t.getVal() == WIN_VAL) gameWon = true;
//									moving.setMovement(0, SPEED);
//									moving.setDistance((num - i) * DIST);	
//									removeTile(j, num);
//									removeTile(j, i);
//									combine = false;
//								} else {									
//									moving.setPosition(j, i + num);
//									moving.setMovement(0, SPEED);
//									moving.setDistance(num * DIST);
//								}
//							}
//						}
//					}
//				}
//				
//				for (int i = 0; i < tiles.size(); i++) {
//					tiles.get(i).setNewTile(false);
//				}
//				
//				if (moved && !gameWon && !gameLost) {
//					int randR = (int)(Math.random() * 4);
//					int randC = (int)(Math.random() * 4);
//					int randNum = (int)(Math.random() * 10);
//					
//					while (true) {
//						boolean works = true;
//						for (int i = 0; i < tiles.size(); i++) {
//							if (tiles.get(i).getRow() == randR && tiles.get(i).getCol() == randC) {
//								works = false;
//								break;
//							}						
//						}
//
//						if (works) break;
//						randR = (int)(Math.random() * 4);
//						randC = (int)(Math.random() * 4);
//					}
//					
//					Tile r; 
//					if (randNum == 0) r = new Tile(4, randR, randC); 
//					else r = new Tile(2, randR, randC); 
//					
//					r.setFitWidth(IMAGE_WIDTH);
//					r.setPreserveRatio(true);
//					r.setX(SHIFT + DIST * randR);
//					r.setY(SHIFT + DIST * randC); 
//					add(r);
//					tiles.add(r);
//				}
//			
//				
//				gameLost = (tiles.size() == LENGTH * LENGTH);
//				if (gameLost) {
//					for (int i = 0; i < LENGTH; i++) {
//						for (int j = 0; j < LENGTH - 1; j++) {
//							if (getTile(j + 1, i).getVal() == getTile(j, i).getVal() || getTile(i, j + 1).getVal() == getTile(i, j).getVal()) {
//								gameLost = false;
//								break;
//							}	
//						}
//					}
//				}
//				
//			} else if (gameLost) {
//				System.out.println("GAME OVER");
//				Stage popupStage = new Stage();
//				VBox infoCol = new VBox(10);
//				Button done = new Button("Done");
//				Text txt = new Text("GAME OVER");
//	
//				Scene scene = new Scene(infoCol, 500, 400);
//				
//				infoCol.setPadding(new Insets(50));
//				
//				txt.setTextAlignment(TextAlignment.CENTER);
//				infoCol.setAlignment(Pos.CENTER);
//				
//				txt.setFill(Color.rgb(99, 71, 53));
//				infoCol.setStyle("-fx-background-color: #f5ecdf");
//				done.setStyle("-fx-background-color: #a3865b");
//				done.setTextFill(Color.rgb(242, 235, 223));
//				
//				txt.setFont(Font.font("Impact", 50));
//				txt.setWrappingWidth(400);
//				
//				ImageView sad = new ImageView(sadMcLeod);
//				sad.setFitWidth(250);
//				sad.setFitHeight(230);
//				
//				infoCol.getChildren().addAll(txt,sad,done);
//				
//				done.setOnAction(new EventHandler<ActionEvent>( ) {
//					@Override
//					public void handle(ActionEvent e) {
//						popupStage.close();							
//					}
//				});
//				
//				popupStage.setScene(scene);
//				popupStage.showAndWait(); 
//			} else {
//				Stage popupStage = new Stage();
//				VBox infoCol = new VBox(10);
//				Button done = new Button("Done");
//				Text txt = new Text("YOU WIN!");
//	
//				Scene scene = new Scene(infoCol, 500, 400);
//				
//				infoCol.setPadding(new Insets(50));
//				
//				txt.setTextAlignment(TextAlignment.CENTER);
//				infoCol.setAlignment(Pos.CENTER);
//				
//				txt.setFill(Color.rgb(99, 71, 53));
//				infoCol.setStyle("-fx-background-color: #f5ecdf");
//				done.setStyle("-fx-background-color: #a3865b");
//				done.setTextFill(Color.rgb(242, 235, 223));
//				
//				txt.setFont(Font.font("Impact", 50));
//				txt.setWrappingWidth(400);
//				
//				ImageView happy = new ImageView(happyMcLeod);
//				happy.setFitWidth(250);
//				happy.setFitHeight(230);
//				
//				infoCol.getChildren().addAll(txt,happy,done);
//				
//				done.setOnAction(new EventHandler<ActionEvent>( ) {
//					@Override
//					public void handle(ActionEvent e) {
//						popupStage.close();							
//					}
//				});
//				
//				popupStage.setScene(scene);
//				popupStage.showAndWait(); 
//			}
//		}
//	}
//}
