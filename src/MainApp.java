import processing.core.PApplet;
import processing.core.PVector;

public class MainApp extends PApplet {

    private PVector[][] mainGrid;
    private PerlinGrid field;
    private int locSpacing;
    private boolean drawGridPoints;
    private boolean drawInputField;
    public static void main(String[] args) {
        PApplet.main("MainApp", args);
    }

    public void settings() {
        size(800,800);
    }

    public void setup() {
        drawInputField = true;
        drawGridPoints = false;

        locSpacing = 80;
        mainGrid = new PVector[width / locSpacing][height / locSpacing];

        for (int i = 0; i < (width / locSpacing); i++) {
            float xLoc = (i + 1) * locSpacing;
            for (int j = 0; j < (height / locSpacing); j++) {
                float yLoc = (j+1) * locSpacing;
                mainGrid[i][j] = new PVector(xLoc, yLoc);
            }
        }
        field = new PerlinGrid(this, mainGrid, locSpacing, width, height);

    }

    public void draw() {
        background(255);
        drawAll();
        field.display();

    }

    private void drawAll() {
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
        if (drawInputField) {
            field.display();
        }
//      if (drawGridLines) {

//      }


    }



}
