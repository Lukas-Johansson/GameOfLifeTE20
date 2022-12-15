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
    }

    public Shape[] getShapes() {
        Point[] points = {new Point(5,5)};
        return (Shape[])points;
    }
}
