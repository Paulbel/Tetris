package entity;

public class Line extends Shape {
    public Line(int x, int y){

        this.points = new Point[SIZE];
        for (int index = 0; index < points.length; index++ ){
            points[index] = new Point();
        }
        this.points[0].setPosX(x);
        this.points[0].setPosY(y);

        this.points[1].setPosX(x-1);
        this.points[1].setPosY(y);

        this.points[2].setPosX(x+1);
        this.points[2].setPosY(y);

        this.points[3].setPosX(x+2);
        this.points[3].setPosY(y);
    }
}
