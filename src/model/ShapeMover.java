package model;

import controller.Controller;
import entity.Shape;


public class ShapeMover extends Thread {
    private Shape shape;
    private long time;
    private Controller controller;

    public ShapeMover(Controller controller) {
        this.time = 400;
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
                Thread.sleep(time);
            }
        } catch (InterruptedException ignored) {
        }
    }

    public void decreaseTime(){
        long nextTime = (long) (time-time*0.04);
        if(nextTime>100){
            time = nextTime;
        }
    }
}
