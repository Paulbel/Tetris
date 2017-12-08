package entity;

public class TShape extends Shape {

    public TShape(int x, int y) {
        y = y + 1;
        this.points = new Point[SIZE];
        for (int index = 0; index < points.length; index++) {
            points[index] = new Point();
        }
        this.points[0].setPosX(x);
        this.points[0].setPosY(y);

        this.points[1].setPosX(x + 1);
        this.points[1].setPosY(y);

        this.points[2].setPosX(x - 1);
        this.points[2].setPosY(y);

        this.points[3].setPosX(x);
        this.points[3].setPosY(y - 1);
    }


}
