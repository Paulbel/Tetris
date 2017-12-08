import javax.swing.*;
import java.awt.*;

public class ScorePanel {
    private JLabel score;
    private JPanel scorePanel;
    private final int INITIAL_SCORE = 0;

    public ScorePanel(){
        this.scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout());
        score.setText(String.valueOf(INITIAL_SCORE));
        scorePanel.add(score);
    }

    public void setScore(int currentScore){
        score.setText(String.valueOf(currentScore));
    }
}
