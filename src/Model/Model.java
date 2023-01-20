package Model;
import View.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    int width, height, isolation, overcrowding, revive;
    Point[][] activeGrid, nextGrid;
    List<Point> cellsToDraw = new ArrayList<>();

    public Model(int width, int height) {
        this.width = width;
        this.height = height;
        isolation = 1;
        overcrowding = 4;
        revive = 3;

        activeGrid = createGrid();
        nextGrid = activeGrid;

        //Cellgrupp 1
        createCells(15, 15);
        createCells(16, 15);
        createCells(17, 15);
        createCells(15, 16);
        createCells(16, 17);

        //Cellgrupp 2
        createCells(20, 20);
        createCells(21, 20);
        createCells(22, 20);
    }

    public void update() {
        nextGrid = createGrid();
        cellsToDraw.clear();
        checkGrid();
        activeGrid = nextGrid;
    }

    public void checkGrid() {
        for (int Y = 0; Y < height; Y++) {
            for (int X = 0; X < width; X++) {
                int neighbours = countAliveNeighbours(X, Y);
                Point point = activeGrid[X][Y];

                if (point.isAlive()) {
                    cellsToDraw.add(point);
                    nextGrid[X][Y].setAlive(neighbours > isolation && neighbours < overcrowding);
                } else {
                    nextGrid[X][Y].setAlive(neighbours == revive);
                }
            }
        }
    }

    private int countAliveNeighbours(int x, int y) {
        int count = 0;
        for (int checkX = -1; checkX <= 1; checkX++) {
            for (int checkY = -1; checkY <= 1; checkY++) {
                if (x + checkX >= 0 && x + checkX < width && y + checkY >= 0 && y + checkY < height) {
                    if (activeGrid[x + checkX][y + checkY].isAlive()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private Point[][] createGrid() {
        Point[][] point = new Point[width][height];
        for (int Y = 0; Y < height; Y++) {
            for (int X = 0; X < width; X++) {
                point[X][Y] = new Point(X, Y);
            }
        }
        return point;
    }

    private void createCells(int x, int y) {
        nextGrid[x][y].setAlive(true);
        cellsToDraw.add(nextGrid[x][y]);
    }

    public Point[] getShapes() {
        return cellsToDraw.toArray(new Point[0]);
    }
}
