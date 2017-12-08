package model;

import entity.*;

import java.util.Random;


public class ListFigures {
    private int width;
    private Random randomFigure = new Random(0);
    private ShapeType[] shapeTypes = ShapeType.values();
    private final int START_POSITION_X = 1;

    public Shape getRandomShape() {
        int typeIndex = randomFigure.nextInt(shapeTypes.length);
        ShapeType type = shapeTypes[typeIndex];
        int yPos = (int) (Math.random() * (width - 1));
        switch (type) {
            case LINE: {
                return new Line(START_POSITION_X, yPos);
            }
            case ZLEFT: {
                return new ZLeft(START_POSITION_X, yPos);
            }
            case SQUARE: {
                return new Square(START_POSITION_X, yPos);
            }
            case TSHAPE:
                return new TShape(START_POSITION_X, yPos);
            case GSHAPE:
                return new GShape(START_POSITION_X, yPos);
        }
        return null;
    }

    public ListFigures(int width) {
        this.width = width;
    }
}
