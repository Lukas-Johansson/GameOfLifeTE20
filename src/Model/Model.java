package Model;
import View.*;
import java.util.ArrayList;

public class Model {
    int width, height, isolation, overcrowding, revive;
    Point[][] activeGrid, nextGrid;
    ArrayList<Point> cellsToDraw = new ArrayList<>();

    public Model(int width, int height) {
        this.width = width;
        this.height = height;
        activeGrid = new Point[width][height];
        isolation = 1;
        overcrowding = 4;
        revive = 3;

        activeGrid = Grid();
        nextGrid = activeGrid;

        //Cellgrupp 1

        Cells(15, 15);
        Cells(16, 15);
        Cells(17, 15);
        Cells(15, 16);
        Cells(16, 17);

        //Cellgrupp 2

        Cells(20, 20);
        Cells(21, 20);
        Cells(22, 20);
    }

    public void update() {
        nextGrid = Grid();
        cellsToDraw.clear();
        checkGrid();
        activeGrid = nextGrid;
    }

    public void checkGrid() {
        for (int Y = 0; Y < height; Y++) {
            for (int X = 0; X < width; X++) {
                int neighbours = 0;

                if(activeGrid[X][Y].isAlive()) { cellsToDraw.add(activeGrid[X][Y]); }

                for (int checkX = -1; checkX <= 1; checkX++) {
                    for (int checkY = -1; checkY <= 1; checkY++) {
                        if(X + checkX >= 0 && X + checkX < width && Y + checkY >= 0 && Y + checkY < height) {
                            if(activeGrid[X + checkX][Y + checkY].isAlive()) { neighbours++; }
                        }
                    }
                }

                if(activeGrid[X][Y].isAlive()) {
                    neighbours--;
                    nextGrid[X][Y].setAlive(neighbours > isolation && neighbours < overcrowding);
                }

                else {
                    nextGrid[X][Y].setAlive(neighbours == revive);
                }
            }
        }
    }

    public Point[][] Grid() {
        Point[][] point = new Point[width][height];
        for (int Y = 0; Y < height; Y++) {
            for (int X = 0; X < width; X++) {
                point[X][Y] = new Point(X, Y);
            }
        }
        return point;
    }

    public void Cells(int x, int y) {
        nextGrid[x][y].setAlive(true);
        cellsToDraw.add(nextGrid[x][y]);
    }

    public Shape[] getShapes() {
        Point[] shape = new Point[cellsToDraw.size()];
        cellsToDraw.toArray(shape);
        return shape;
    }
}