package controller;

import entity.CellState;
import entity.Line;
import entity.Shape;
import entity.ZLeft;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ListFigures;
import model.ModelField;
import view.FieldView;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private int activState;
    boolean canAroundShape;
    private FieldView fieldView;
    private ModelField modelField;
    private MenuItem newGameItem;
    private int NUMBER_VERTICAL = 15;
    private int NUMBER_HORIZONTAL = 8;
    private Scene scene;
    private int score;
    private Shape shapeActiv;
    private Stage stage;
    private int startTime;
    //private List<Stat>
    private Timer timer;
    private TimerTask timerTask;
    private JFrame gameFrame;
    private CellState[][] states;
    private ListFigures figures;



    public void startGame() {
        this.fieldView = new FieldView(NUMBER_VERTICAL, NUMBER_HORIZONTAL, this);
        shapeActiv = new ZLeft(2, 2);
        this.modelField = new ModelField(NUMBER_VERTICAL, NUMBER_HORIZONTAL, this);
        modelField.setShape(shapeActiv);
        gameFrame = new JFrame();
        this.gameFrame.setLayout(new FlowLayout());
        this.gameFrame.setSize(250, 400);
        this.gameFrame.add(fieldView.getGameField());
        figures = new ListFigures(NUMBER_HORIZONTAL);
        this.gameFrame.setVisible(true);
    }


    public void updateField() {
        modelField.refreshField();
        fieldView.updateColor(modelField.getFieldMatrix());
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

    public void addScore(){
        score++;
    }

    public void nextShape(){
        Shape shape = figures.getRandomShape();
        modelField.setShape(shape);
    }

}
