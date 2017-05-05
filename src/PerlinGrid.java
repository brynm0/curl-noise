import processing.core.PApplet;
import processing.core.PVector;

import static jdk.nashorn.internal.objects.Global.println;


public class PerlinGrid {
    PVector[][] baseGrid;

    PVector[][] field;
    PVector[][] curlField;

    int locSpacing;

    PApplet app;

    float noiseSpacing;
    int width, height;

    PerlinGrid (PApplet _app, PVector[][] _baseGrid, int _locSpacing, int _width, int _height) {;
        baseGrid = _baseGrid;
        width = _width;
        height = _height;
        field = new PVector[width / _locSpacing][height / _locSpacing];
        curlField = new PVector[width / _locSpacing][height / _locSpacing];
        app = _app;
        noiseSpacing = 0.5f;
        locSpacing = _locSpacing;
        init();
        getCurlField();
    }

    private void init() {
        for (int i = 0; i <  width / locSpacing; i++ ) {
            for (int j = 0; j < 10; j++) {
                float current = app.noise(i * noiseSpacing, j * noiseSpacing);
                PVector tmp = new PVector(1,0,0);
                tmp.setMag(current);
                tmp.mult(10);
                field[i][j] = new PVector();
                field[i][j] = tmp;
            }
        }
    }


    void getCurlField() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                PVector curl = computeCurl(field[i][j].x, field[i][j].y);
                curlField[i][j] = field[i][j].add(curl);
            }
        }
    }

    PVector computeCurl(float x, float y) {
        float eps = 1f;
        float n1, n2, a, b;

        n1 = app.noise(x,y + eps);
        n2 = app.noise(x, y - eps);
        a = (n1 - n2) / (2 * eps);

        n1 = app.noise(x + eps, y);
        n2 = app.noise(x - eps, y);
        b = (n1 - n2) / (2 * eps);

        PVector curl = new PVector(a, -b);
        curl.mult(50);
        return curl;
    }

    void display() {
        for (int i = 0; i <  10; i++ ) {
            for (int j = 0; j < 10; j++) {
                app.noFill();
                app.stroke(0);
                app.strokeWeight(1);
                //app.line(baseGrid[i][j].x, baseGrid[i][j].y, baseGrid[i][j].x + field[i][j].x, baseGrid[i][j].y + field[i][j].y);
                //app.line(baseGrid[i][j].x, baseGrid[i][j].y, baseGrid[i][j].x + 20, baseGrid[i][j].y + 20);
                app.line(baseGrid[i][j].x, baseGrid[i][j].y, baseGrid[i][j].x + curlField[i][j].x, baseGrid[i][j].y + curlField[i][j].y);
            }
        }

    }

 }
