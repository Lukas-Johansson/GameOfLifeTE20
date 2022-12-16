package Model;
import View.Point;
import View.Shape;

public class Model {
    int width;
    int height;
    int x;
    int y;

    public Model(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void update() {
        x++;
        y++;
        getRules();
    }

    public Shape[] getShapes() {
        Point[] points = {
                new Point(5,5),
                new Point(6,6),
                new Point(6,7),
                new Point(5,7),
                new Point(4,7)
        };
        return (Shape[])points;
    }

    public void getRules() {
        for() {

        }
    }
}
