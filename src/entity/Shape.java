package entity;


public abstract class Shape {
    protected final int SIZE = 4;
    protected Point[] points;

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public void rotate() {
        int xRotate = points[0].getPosX();
        int yRotate = points[0].getPosY();

        for (int pointIndex = 1; pointIndex < points.length; pointIndex++) {
            int xCurrent = points[pointIndex].getPosX();
            int yCurrent = points[pointIndex].getPosY();
            int diffX = xRotate - xCurrent;
            int diffY = yRotate - yCurrent;
            points[pointIndex].setPosX(xRotate + diffY);
            points[pointIndex].setPosY(yRotate - diffX);
        }
    }


    public Shape() {
        this.points = new Point[SIZE];
    }

    public void moveLeft() {
        for (int pointIndex = 0; pointIndex < points.length; pointIndex++) {
            int y = points[pointIndex].getPosY();
            points[pointIndex].setPosY(y - 1);

        }
    }


    public void moveDown() {
        for (Point point : points) {
            point.setPosX(point.getPosX() + 1);
        }
    }


    public void moveRight() {

        for (int pointIndex = 0; pointIndex < points.length; pointIndex++) {
            int y = points[pointIndex].getPosY();
            points[pointIndex].setPosY(y + 1);

        }

    }
}
