package controller;

import entity.Shape;
import model.ListFigures;
import model.ModelField;
import view.FieldView;
import view.LoseMsgBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private FieldView fieldView;
    private ModelField modelField;
    private int NUMBER_VERTICAL = 25;
    private int NUMBER_HORIZONTAL = 8;
    private int score;
    private JFrame gameFrame;
    private ListFigures figures;
    private JPanel menu;
    private JLabel scoreLabel;


    public Controller() {
        this.score = 0;
        this.scoreLabel = new JLabel("Score: " + score);
    }

    public void showMenu() {
        gameFrame = new JFrame();
        this.gameFrame.setLayout(new FlowLayout());
        this.gameFrame.setSize(250, 100);
        menu = new JPanel();
        JButton exitButton = new JButton("Exit");
        JButton startGameButton = new JButton("Start");
        menu.add(exitButton);
        menu.add(startGameButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.remove(menu);
                startGame();
            }
        });
        gameFrame.add(menu);
        gameFrame.setVisible(true);
    }

    public void startGame() {
        System.out.println("START");
        gameFrame.dispose();
        gameFrame = new JFrame();
        this.gameFrame.setLayout(new FlowLayout());
        this.gameFrame.setSize(250, 400);
        figures = new ListFigures(NUMBER_HORIZONTAL);
        this.fieldView = new FieldView(NUMBER_VERTICAL, NUMBER_HORIZONTAL, this);
        Shape shape = figures.getRandomShape();
        this.modelField = new ModelField(NUMBER_VERTICAL, NUMBER_HORIZONTAL, this, shape);
        this.gameFrame.add(fieldView.getGameGreed());
        this.gameFrame.add(scoreLabel);
        this.gameFrame.setVisible(true);

    }


    public void /*boolean*/ updateField() {
        modelField.refreshField();
        fieldView.updateColor(modelField.getFieldMatrix());
        scoreLabel.setText(String.valueOf(score));
/*        if (!canMove){
            modelField.stopGame();
        }
        return canMove;*/
    }

    public void rotateShape() {
        modelField.rotateShape();
    }

    public void moveRight() {
        modelField.moveRight();
    }

    public void moveLeft() {
        modelField.moveLeft();

    }

    public void moveDownFast() {
        modelField.moveDownFast();
    }


    public void addScore() {
        score++;
    }

    public void nextShape() {
        Shape shape = figures.getRandomShape();
        modelField.setShape(shape);
    }

    public void stopGame() {
        modelField.stopGame();
        gameFrame.dispose();
        System.out.println("Stop");
        LoseMsgBox loseMsgBox = new LoseMsgBox(this);
        this.gameFrame = loseMsgBox.getFrame();
        this.gameFrame.setVisible(true);
    }

}
