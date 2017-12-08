package model;

import controller.Controller;
import entity.CellState;
import entity.Point;
import entity.Shape;

public class ModelField {
    private CellState[][] fieldMatrix;
    private int numberX;
    private int numberY;
    private Shape activeShape;
    private ShapeMover shapeMover;
    private Controller controller;
    private Point[] pointsCopy;
    private boolean gameStopped;
    private final int CELL_POSITION_FINAL = 2;

    public ModelField(int numberX, int numberY, Controller controller, Shape shape) {
        this.controller = controller;
        this.numberX = numberX;
        this.numberY = numberY;
        fieldMatrix = new CellState[this.numberX][this.numberY];
        for (int xIndex = 0; xIndex < fieldMatrix.length; xIndex++) {
            for (int yIndex = 0; yIndex < fieldMatrix[xIndex].length; yIndex++) {
                fieldMatrix[xIndex][yIndex] = CellState.FREE;
            }
        }

        shapeMover = new ShapeMover(controller);
        this.setShape(shape);
        this.gameStopped = false;
        shapeMover.start();
    }


    public void stopGame() {
        this.gameStopped = true;
        shapeMover.interrupt();
    }

    public CellState[][] getFieldMatrix() {
        return fieldMatrix;
    }


    public void setShape(Shape shape) {
        this.activeShape = shape;
        shapeMover.setShape(shape);
    }


    public boolean refreshField() {
        if (!gameStopped) {
            Point[] points = this.activeShape.getPoints();
            boolean canBePainted = true;

            int position = points[0].getPosX();
            for (Point point : points) {
                int posX = point.getPosX();
                int posY = point.getPosY();

                if (posY < 0 || posY > numberY - 1) {
                    activeShape.setPoints(pointsCopy);
                    return true;
                }
                if (posX > fieldMatrix.length - 1 || fieldMatrix[posX][posY] == CellState.BUSY) {
                    canBePainted = false;
                    break;
                }
            }
            if (canBePainted) {
                freeField();
                for (Point point : points) {
                    int posX = point.getPosX();
                    int posY = point.getPosY();
                    fieldMatrix[posX][posY] = CellState.FIGURE;
                }
            } else {
                if (position == CELL_POSITION_FINAL) {
                    controller.stopGame();
                    return false;
                }
                for (int xIndex = 0; xIndex < fieldMatrix.length; xIndex++) {
                    for (int yIndex = 0; yIndex < fieldMatrix[xIndex].length; yIndex++) {
                        if (fieldMatrix[xIndex][yIndex] == CellState.FIGURE) {
                            fieldMatrix[xIndex][yIndex] = CellState.BUSY;
                        }
                    }
                }
                freeFullRows();
                controller.nextShape();
                return false;
            }
            return true;
        }
        return false;
    }

    private void freeLine(int lineIndex) {
        for (int index = 0; index < fieldMatrix[lineIndex].length; index++) {
            fieldMatrix[lineIndex][index] = CellState.FREE;
        }
        shapeMover.decreaseTime();
    }

    private void freeFullRows() {
        for (int xIndex = 0; xIndex < fieldMatrix.length; xIndex++) {
            boolean canFree = true;
            for (int yIndex = 0; yIndex < fieldMatrix[xIndex].length; yIndex++) {
                if (fieldMatrix[xIndex][yIndex] != CellState.BUSY) {
                    canFree = false;
                    break;
                }
            }
            if (canFree) {
                controller.addScore();
                freeLine(xIndex);
                shiftAllColumns();
            }
        }
    }


    private void shiftAllColumns() {
        for (int yIndex = 0; yIndex < numberY; yIndex++) {
            for (int xIndex = numberX - 1; xIndex >= 0; xIndex--) {
                if (fieldMatrix[xIndex][yIndex] == CellState.FREE) {
                    shiftColumn(xIndex, yIndex);
                }
            }
        }
    }

    private void shiftColumn(int columnIndex, int startRowIndex) {
        for (int index = columnIndex; index >= 0; index--) {
            if (fieldMatrix[index][startRowIndex] == CellState.BUSY) {
                fieldMatrix[columnIndex][startRowIndex] = CellState.BUSY;
                fieldMatrix[index][startRowIndex] = CellState.FREE;
                break;
            }
        }
    }


    private void freeField() {
        for (int xIndex = 0; xIndex < fieldMatrix.length; xIndex++) {
            for (int yIndex = 0; yIndex < fieldMatrix[xIndex].length; yIndex++) {
                if (fieldMatrix[xIndex][yIndex] == CellState.FIGURE) {
                    fieldMatrix[xIndex][yIndex] = CellState.FREE;
                }
            }
        }
    }

    public void rotateShape() {
        int pointNumber = activeShape.getPoints().length;
        Point[] currentPoints = activeShape.getPoints();
        pointsCopy = new Point[pointNumber];
        for (int pointIndex = 0; pointIndex < pointNumber; pointIndex++) {
            pointsCopy[pointIndex] = new Point();
            pointsCopy[pointIndex].setPosX(currentPoints[pointIndex].getPosX());
            pointsCopy[pointIndex].setPosY(currentPoints[pointIndex].getPosY());
        }
        activeShape.rotate();
    }


    public void moveRight() {
        boolean canMove = true;
        for (Point point : activeShape.getPoints()) {
            int posY = point.getPosY();
            int posX = point.getPosX();
            if (posY >= numberY - 1 || fieldMatrix[posX][posY + 1] == CellState.BUSY) {
                canMove = false;
                break;
            }
        }
        if (canMove) {
            activeShape.moveRight();
        }

    }

    public void moveLeft() {
        boolean canMove = true;

        for (Point point : activeShape.getPoints()) {
            int posY = point.getPosY();
            int posX = point.getPosX();
            if (posY <= 0 || fieldMatrix[posX][posY - 1] == CellState.BUSY) {
                canMove = false;
                break;
            }
        }
        if (canMove) {
            activeShape.moveLeft();
        }
    }


    public void moveDownFast() {
        do {
            activeShape.moveDown();
        }
        while (refreshField());
    }
}
