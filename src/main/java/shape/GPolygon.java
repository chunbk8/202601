package shape;

import java.awt.*;

public class GPolygon extends GShape {
    private Polygon polygon;

    public GPolygon() {
        this.polygon = new Polygon();
        this.shape = this.polygon;
    }

    @Override
    public void setLocation0(int x, int y) {
        polygon.addPoint(x, y);
        polygon.addPoint(x, y);
    }

    @Override
    public void setLocation1(int x, int y) {
        polygon.xpoints[polygon.npoints - 1] = x;
        polygon.ypoints[polygon.npoints - 1] = y;
    }

    @Override
    public void addPoint(int x, int y) {
        polygon.addPoint(x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        //g.setColor(Color.BLACK);
        g.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
    }

    @Override
    public void translate(int dx, int dy) {
        for (int i = 0; i < polygon.npoints; i++) {
            polygon.xpoints[i] = polygon.xpoints[i] + dx;
            polygon.ypoints[i] = polygon.ypoints[i] + dy;
        }
        polygon.invalidate();
    }

    @Override
    public GShape clone() {
        GPolygon clone = (GPolygon) super.clone();
        clone.polygon = new Polygon(this.polygon.xpoints, this.polygon.ypoints, this.polygon.npoints);
        clone.shape = clone.polygon;
        return clone;
    }

    @Override
    public void resetTo(GShape original) {
        Polygon origPoly = (Polygon) original.shape;
        this.polygon = new Polygon(origPoly.xpoints, origPoly.ypoints, origPoly.npoints);
        this.shape = this.polygon;
    }

    @Override
    public void scale(double sx, double sy, double cx, double cy) {
        for (int i = 0; i < this.polygon.npoints; i++) {
            this.polygon.xpoints[i] = (int) Math.round((this.polygon.xpoints[i] - cx) * sx + cx);
            this.polygon.ypoints[i] = (int) Math.round((this.polygon.ypoints[i] - cy) * sy + cy);
        }
        this.polygon.invalidate();
    }
}