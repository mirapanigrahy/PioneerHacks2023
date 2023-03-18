import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Score extends Text {
	private Font f; 	
	private int score; 
	
	public void updateDisplay() {
		setText("" + score);
		setFont(Font.font("Impact", FontWeight.THIN, 25));
		setFill(Color.WHITE);
	}
	
	public void reset() {
		score = 0; 
		updateDisplay();
	}
	
	public Score() {
		score = 0; 
		updateDisplay();
	}
	
	public int getVal() {
		return score; 
	}
	
	public void setChange(int change) {
		score += change; 
		updateDisplay();
	}
}