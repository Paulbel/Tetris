package entity;

public class Square extends Shape {
    public static final int OFFSET_LEFT = 1;

    public Square(int x, int y){
        this.points = new Point[SIZE];
        for (int index = 0; index < points.length; index++ ){
            points[index] = new Point();
        }
        this.points[0].setPosX(x);
        this.points[0].setPosY(y);

        this.points[1].setPosX(x+1);
        this.points[1].setPosY(y);

        this.points[2].setPosX(x);
        this.points[2].setPosY(y-1);

        this.points[3].setPosX(x+1);
        this.points[3].setPosY(y-1);
    }

    @Override
    public void rotate() {
    }
}
