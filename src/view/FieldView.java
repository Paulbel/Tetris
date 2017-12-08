package view;

import controller.Controller;
import entity.CellState;
import javax.swing.*;
import java.awt.*;


public class FieldView {
    private JPanel gameGreed;
    private Controller controller;

    public void updateColor(CellState[][] states) {
        gameGreed.removeAll();
        for (int xIndex = 0; xIndex < states.length; xIndex++) {
            for (int yIndex = 0; yIndex < states[xIndex].length; yIndex++) {
                CellState state = states[xIndex][yIndex];
                gameGreed.add(CellFabric.createCell(state));
            }
        }
        gameGreed.revalidate();
    }

    public FieldView(int x, int y, Controller controller) {
        this.controller = controller;
        this.gameGreed = new JPanel();
        this.gameGreed.setLayout(new GridLayout(x, y));
        this.gameGreed.addKeyListener(new KeyboardListener(this.controller));
        this.gameGreed.setFocusable(true);

    }

    public JPanel getGameGreed() {
        return gameGreed;
    }




}
