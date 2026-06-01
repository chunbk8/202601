package transformer;

import shape.GShape;
import java.awt.Rectangle;

public class GScaler extends GTransformer {

    private int startX, startY;
    private double cx, cy;
    private int resizeMode;
    private GShape originalShape;

    public GScaler(GShape shape){
        super(shape);
    }

    @Override
    public void start(int x, int y) {
        this.startX = x;
        this.startY = y;


        this.originalShape = this.shape.clone();

        Rectangle b = this.shape.getBounds();
        this.cx = b.getCenterX();
        this.cy = b.getCenterY();


        int[] xCoords = {b.x, b.x + b.width / 2, b.x + b.width};
        int[] yCoords = {b.y, b.y + b.height / 2, b.y + b.height};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue;

                Rectangle anchorArea = new Rectangle(xCoords[i] - 3, yCoords[j] - 3, 6, 6);
                if (anchorArea.contains(x, y)) {
                    if (i == 1) {
                        this.resizeMode = 2;
                    } else if (j == 1) {
                        this.resizeMode = 1;
                    } else {
                        this.resizeMode = 0;
                    }
                }
            }
        }
    }

    @Override
    public void keep(int x, int y) {
        double sx = 1.0;
        double sy = 1.0;


        this.shape.resetTo(this.originalShape);

        if (this.resizeMode == 0 || this.resizeMode == 1) {
            double oldDistX = this.startX - this.cx;
            double newDistX = x - this.cx;
            sx = (oldDistX == 0) ? 1.0 : newDistX / oldDistX;
        }
        if (this.resizeMode == 0 || this.resizeMode == 2) {
            double oldDistY = this.startY - this.cy;
            double newDistY = y - this.cy;
            sy = (oldDistY == 0) ? 1.0 : newDistY / oldDistY;
        }

        this.shape.scale(sx, sy, this.cx, this.cy);
    }

    @Override
    public void finish(int x, int y) {
        this.originalShape = null;
    }

    @Override
    public void cont(int x, int y) {
    }
}