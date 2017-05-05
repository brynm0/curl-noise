/**
 * Created by Bryn on 5/5/2017.
 */

import processing.core.PApplet;
import processing.core.PVector;

public class MainApp extends PApplet {

    private PVector[][] mainGrid;
    private int locSpacing;
    private boolean drawGridPoints;

    public static void main(String[] args) {
        PApplet.main("MainApp", args);
    }

    public void settings() {
        size(800,800);
    }

    public void setup() {
        locSpacing = 80;
        drawGridPoints = true;

        mainGrid = new PVector[width / locSpacing][height / locSpacing];
        for (int i = 0; i < (width / locSpacing); i++) {
            float xLoc = (i + 1) * locSpacing;
            for (int j = 0; j < (height / locSpacing); j++) {
                float yLoc = (j+1) * locSpacing;
                mainGrid[i][j] = new PVector(xLoc, yLoc);
            }
        }
    }

    public void draw() {
        background(255);
        drawGrid();
    }

    private void drawGrid() {
        if (drawGridPoints) {
            for (int i = 0; i < width / locSpacing; i++) {
                for (int j = 0; j < height / locSpacing; j++) {
                    PVector current;
                    current = mainGrid[i][j];
                    noStroke();
                    fill(0);
                    ellipse(current.x, current.y, 5,5);
                }
            }
        }
//        else if (drawGridLines) {

//        }


    }

}
