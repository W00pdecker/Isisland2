package com.company.GameUnits;

import com.company.Abstracts.Animal;
import com.company.GameUnits.Cell;

public class Island {
    private final int HEIGHT;
    private final int WIDTH;

    public static Cell[][] cells;

    public Island(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.cells = new Cell[height][width];       //creating cells of the map
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].populate();             //creating animals in each cell
            }
        }

        System.out.println("Island ready");
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }


}
