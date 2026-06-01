package shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GOval extends GShape {

    public GOval() {
        this.shape = new Ellipse2D.Double();
    }

    @Override
    public void setLocation0(int x, int y) {
        Ellipse2D r = (Ellipse2D) shape;
        r.setFrame(x, y, 0, 0);
    }

    @Override
    public void setLocation1(int x, int y) {
        Ellipse2D r = (Ellipse2D) shape;
        double w = x - r.getX();
        double h = y - r.getY();
        r.setFrame(r.getX(), r.getY(), w, h);
    }

    @Override
    public void translate(int dx, int dy) {
        Ellipse2D r = (Ellipse2D) shape;
        r.setFrame(r.getX() + dx, r.getY() + dy, r.getWidth(), r.getHeight());
    }

    @Override
    public GShape clone() {
        GOval clone = (GOval) super.clone();
        Ellipse2D.Double currentOval = (Ellipse2D.Double) this.shape;
        clone.shape = new Ellipse2D.Double(currentOval.getX(), currentOval.getY(), currentOval.getWidth(), currentOval.getHeight());
        return clone;
    }

    @Override
    public void resetTo(GShape original) {
        Ellipse2D origOval = (Ellipse2D) original.shape;
        this.shape = new Ellipse2D.Double(origOval.getX(), origOval.getY(), origOval.getWidth(), origOval.getHeight());
    }

    @Override
    public void scale(double sx, double sy, double cx, double cy) {
        Ellipse2D r = (Ellipse2D) this.shape;

        double x1 = r.getX();
        double y1 = r.getY();
        double x2 = x1 + r.getWidth();
        double y2 = y1 + r.getHeight();

        double newX1 = (x1 - cx) * sx + cx;
        double newY1 = (y1 - cy) * sy + cy;
        double newX2 = (x2 - cx) * sx + cx;
        double newY2 = (y2 - cy) * sy + cy;

        r.setFrame(
                Math.min(newX1, newX2),
                Math.min(newY1, newY2),
                Math.abs(newX2 - newX1),
                Math.abs(newY2 - newY1)
        );
    }
}