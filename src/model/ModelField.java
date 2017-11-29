package model;

import controller.Controller;
import entity.CellState;
import entity.Point;
import entity.Shape;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ModelField {
    private CellState[][] fieldMatrix;
    private int numberX;
    private int numberY;
    private Shape activeShape;
    private ShapeMover shapeMover;
    private Lock lock;
    private Controller controller;

    public ModelField(int numberX, int numberY, Controller controller) {
        this.controller = controller;
        this.numberX = numberX;
        this.numberY = numberY;
        fieldMatrix = new CellState[this.numberX][this.numberY];
        for (int xIndex = 0; xIndex < fieldMatrix.length; xIndex++) {
            for (int yIndex = 0; yIndex < fieldMatrix[xIndex].length; yIndex++) {
                fieldMatrix[xIndex][yIndex] = CellState.FREE;
            }
        }
        this.lock = new ReentrantLock();
        shapeMover = new ShapeMover(this, this.lock, controller);
        shapeMover.start();
    }


    public CellState[][] getFieldMatrix() {
        return fieldMatrix;
    }


    public void setShape(Shape shape) {
        this.activeShape = shape;
        shapeMover.setShape(shape);
    }


    public void refreshField() {
        Point[] points = this.activeShape.getPoints();
        boolean canMoveDown = true;

        for (Point point : points) {
            int posX = point.getPosX();
            int posY = point.getPosY();
            System.out.println("X: " + posX + " Y: " + posY);
            if (posX > fieldMatrix.length - 1 || fieldMatrix[posX][posY] == CellState.BUSY) {
                canMoveDown = false;
                break;
            }
        }

        if (canMoveDown) {
            freeField();
            for (Point point : points) {
                int posX = point.getPosX();
                int posY = point.getPosY();
                fieldMatrix[posX][posY] = CellState.FIGURE;
            }
        } else {
            for (int xIndex = 0; xIndex < fieldMatrix.length; xIndex++) {
                for (int yIndex = 0; yIndex < fieldMatrix[xIndex].length; yIndex++) {
                    if (fieldMatrix[xIndex][yIndex] == CellState.FIGURE) {
                        fieldMatrix[xIndex][yIndex] = CellState.BUSY;
                    }
                }
            }
            freeFullRows();
            controller.nextShape();
            //setShape(shape);
        }

    }

    private void freeLine(int lineIndex) {
        for (int index = 0; index < fieldMatrix[lineIndex].length; index++) {
            fieldMatrix[lineIndex][index] = CellState.FREE;
        }
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
                shiftColumns();
            }
        }
    }


    private void shiftColumns() {
        for (int yIndex = 0; yIndex < numberY; yIndex++) {
            for (int xIndex = numberX - 1; xIndex >= 0; xIndex--) {
                if (fieldMatrix[xIndex][yIndex] == CellState.FREE) {
                    for (int index = xIndex - 1; index >= 0; index--) {
                        if (fieldMatrix[index][yIndex] == CellState.BUSY) {
                            fieldMatrix[xIndex][yIndex] = CellState.BUSY;
                            fieldMatrix[index][yIndex] = CellState.FREE;
                            break;
                        }
                    }
                }
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
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    activeShape.rotate();
                } finally {
                    lock.unlock();
                }
            }
        });
        thread.start();
        controller.updateField();
    }


    public void moveRight() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                boolean canMove = true;
                try {
                    for (Point point:activeShape.getPoints()){
                        int posY = point.getPosY();
                        if(posY>=numberY-1){
                            canMove = false;
                            break;
                        }
                    }
                    if(canMove){
                        activeShape.moveRight();
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        thread.start();
        controller.updateField();
    }

    public void moveLeft() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                boolean canMove = true;
                try {
                    for (Point point:activeShape.getPoints()){
                        int posY = point.getPosY();
                        if(posY<=0){
                            canMove = false;
                            break;
                        }
                    }
                    if(canMove){
                        activeShape.moveLeft();
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        thread.start();
        controller.updateField();
    }
}
