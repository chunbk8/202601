package shape;

import java.awt.*;
public abstract class GShape implements Cloneable{

    public enum EAnchor {
        eRotate,
        eMove,
        eResize
    }
    protected int x0, y0, x1, y1;

    protected Shape shape;
    public GShape() {

    }
    public GShape clone() {
        try {
            return (GShape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Rectangle getBounds() {
        return this.shape.getBounds();
    }

    public void drawAnchors(Graphics2D g) {
        Rectangle b = this.shape.getBounds();

        int[] xCoords = {b.x, b.x + b.width / 2, b.x + b.width};
        int[] yCoords = {b.y, b.y + b.height / 2, b.y + b.height};

        g.setColor(Color.BLACK); //Anchors와 도형의 색 구분하기
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue;

                g.fillRect(xCoords[i] - 3, yCoords[j] - 3, 6, 6);
            }
        }
    }

    public EAnchor onShape(int x, int y) {

        Rectangle b = this.shape.getBounds();

        int[] xCoords = {b.x, b.x + b.width / 2, b.x + b.width};
        int[] yCoords = {b.y, b.y + b.height / 2, b.y + b.height};

        //마우스 -- 앵커 위치 확인
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) continue;

                Rectangle anchorArea = new Rectangle(xCoords[i] - 3, yCoords[j] - 3, 6, 6);

                if (anchorArea.contains(x, y)) {
                    return EAnchor.eResize; //앵커 클릭
                }
            }
        }

        if (this.shape.contains(x, y)) {
            return EAnchor.eMove;
        } else {
            return null;
        }
    }
    public void draw (Graphics2D g) {
        g.draw(shape);
    }
    public abstract void resetTo(GShape original);

    public void resize(int x, int y) {
    }

    public void rotate(int x, int y) {
    }

    public void addPoint(int x, int y ) {}






    public void setLocation0(int x, int y) {}

    public void setLocation1(int x, int y) { }
    public void translate(int dx, int dy){}
    public void scale(double sx, double sy, double cx, double cy) {}

    public void setSize(int width, int height){
        this.x1=x0+width;
        this.y1=y0+height;
    }




}