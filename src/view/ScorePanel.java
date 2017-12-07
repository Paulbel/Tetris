package view;

import javax.swing.*;
import java.awt.*;

public class ScorePanel {
    private JLabel scoreCurrent;
    private JPanel panel;

    public ScorePanel(int currentScore) {
        this.panel = new JPanel();
        scoreCurrent.setText(String.valueOf(currentScore));
        this.panel.add(scoreCurrent);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setScore(int score){
        this.scoreCurrent.setText(String.valueOf(score));
    }
}
