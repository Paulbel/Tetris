package model;

import entity.Line;
import entity.Shape;
import entity.Square;
import entity.ZLeft;

import java.util.Random;


public class ListFigures {
    private int width;
    private Random randomFigure = new Random(0);
    private FigureType[] shapeTypes = FigureType.values();

    public Shape getRandomShape() {
        int typeIndex = randomFigure.nextInt(shapeTypes.length);


        FigureType type = shapeTypes[typeIndex];
        int yPos = (int) (Math.random()*(width-1));
        switch (type) {
            case LINE: {
                return new Line(1, yPos+Line.OFFSET_LEFT);
            }
            case ZLEFT: {
                return  new ZLeft(1, yPos+ZLeft.OFFSET_LEFT);
            }
            case SQUARE: {
                return  new Square(1, yPos+Square.OFFSET_LEFT);
            }
        }
        return null;
    }

    public ListFigures(int width) {
        this.width = width;


    }
}
