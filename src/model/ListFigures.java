package model;

import entity.Line;
import entity.Shape;
import entity.ZLeft;

import java.util.Random;


public class ListFigures {
    private int width;
    private Random random = new Random(0);
    private FigureType[] shapeTypes = FigureType.values();

    public Shape getRandomShape() {
        int typeIndex = random.nextInt(shapeTypes.length);
        int yPos = random.nextInt(width - 2);
        Shape shape = null;
        FigureType type = shapeTypes[typeIndex];
        switch (type) {
            case LINE: {
                shape = new Line(1, yPos);
                break;
            }
            case ZLEFT: {
                shape = new ZLeft(1, yPos);
                break;
            }
        }
        return shape;
    }

    public ListFigures(int width) {
        this.width = width;
    }
}
