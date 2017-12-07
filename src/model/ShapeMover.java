package model;

import controller.Controller;
import entity.Shape;


public class ShapeMover extends Thread {
    private Shape shape;

    private Controller controller;

    public ShapeMover(Controller controller) {
        this.controller = controller;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {

                shape.moveDown();

                controller.updateField();
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
        }
    }
}
