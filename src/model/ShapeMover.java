package model;

import controller.Controller;
import entity.Shape;

import java.util.concurrent.locks.Lock;

public class ShapeMover extends Thread {
    private Shape shape;
    private ModelField field;
    private Lock lock;
    private Controller controller;

    public ShapeMover(ModelField field, Lock lock, Controller controller) {
        this.controller = controller;
        this.field = field;
        this.lock = lock;
    }

    public void setShape(Shape shape) {
        this.lock.lock();
        try {
            this.shape = shape;
        } finally {
            lock.unlock();
            controller.updateField();
        }
    }

    @Override
    public void run() {
        try {
            while (!this.isInterrupted()) {
                Thread.sleep(200);
                this.lock.lock();
                try {
                    shape.moveDown();
                } finally {
                    lock.unlock();
                    controller.updateField();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
