package shape;

import java.awt.*;

public class GRectangle extends GShape {

    public GRectangle() {
        this.shape = new Rectangle();
    }

    @Override
    public void setLocation0(int x, int y) {
        Rectangle r = (Rectangle) shape;
        r.setFrame(x, y, 0, 0);
    }

    @Override
    public void setLocation1(int x, int y) {
        Rectangle r = (Rectangle) shape;
        double w = x - r.getX();
        double h = y - r.getY();
        r.setFrame(r.getX(), r.getY(), w, h);
    }

    @Override
    public void translate(int dx, int dy) {
        Rectangle r = (Rectangle) shape;
        r.setFrame(r.getX() + dx, r.getY() + dy, r.getWidth(), r.getHeight());
    }

    @Override
    public GShape clone() {
        GRectangle clone = (GRectangle) super.clone();
        Rectangle currentRect = (Rectangle) this.shape;
        // 기존 메모리 주소를 공유하지 않고 완전히 새로운 AWT 사각형을 생성합니다.
        clone.shape = new Rectangle(currentRect.x, currentRect.y, currentRect.width, currentRect.height);
        return clone;
    }

    @Override
    public void resetTo(GShape original) {
        Rectangle origRect = (Rectangle) original.shape;
        this.shape = new Rectangle(origRect.x, origRect.y, origRect.width, origRect.height);
    }

    @Override
    public void scale(double sx, double sy, double cx, double cy) {
        Rectangle r = (Rectangle) this.shape;

        double x1 = r.getX();
        double y1 = r.getY();
        double x2 = x1 + r.getWidth();
        double y2 = y1 + r.getHeight();

        double newX1 = (x1 - cx) * sx + cx;
        double newY1 = (y1 - cy) * sy + cy;
        double newX2 = (x2 - cx) * sx + cx;
        double newY2 = (y2 - cy) * sy + cy;

        double newX = Math.min(newX1, newX2);
        double newY = Math.min(newY1, newY2);
        double newW = Math.abs(newX2 - newX1);
        double newH = Math.abs(newY2 - newY1);

        r.setFrame(newX, newY, newW, newH);
    }
}