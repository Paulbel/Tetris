package view;

import controller.Controller;
import entity.CellState;
import javax.swing.*;
import java.awt.*;


public class FieldView {
    private JPanel gameField;
    private int numberGUIX;
    private int numberGUIY;
    private int numberX;
    private int numberY;
    private Controller controller;



    public void updateColor(CellState[][] states) {
        gameField.removeAll();
        for (int xIndex = 0; xIndex < states.length; xIndex++) {
            for (int yIndex = 0; yIndex < states[xIndex].length; yIndex++) {
                CellState state = states[xIndex][yIndex];
                gameField.add(CellFabric.createCell(state));
            }
        }
        gameField.revalidate();
    }

    public FieldView(int x, int y, Controller controller) {
        this.controller = controller;
        this.numberGUIX = x;
        this.numberGUIY = y;
        this.gameField = new JPanel();
        this.gameField.setDoubleBuffered(true);
        this.gameField.setLayout(new GridLayout(x, y));
        this.gameField.addKeyListener(new KeyboardListener(this.controller));
        this.gameField.setFocusable(true);

    }

    public JPanel getGameField() {
        return gameField;
    }

    public void setGameField(JPanel gameField) {
        this.gameField = gameField;
    }

    public int getNumberGUIX() {
        return numberGUIX;
    }

    public void setNumberGUIX(int numberGUIX) {
        this.numberGUIX = numberGUIX;
    }

    public int getNumberGUIY() {
        return numberGUIY;
    }

    public void setNumberGUIY(int numberGUIY) {
        this.numberGUIY = numberGUIY;
    }

    public int getNumberX() {
        return numberX;
    }

    public void setNumberX(int numberX) {
        this.numberX = numberX;
    }

    public int getNumberY() {
        return numberY;
    }

    public void setNumberY(int numberY) {
        this.numberY = numberY;
    }


}
