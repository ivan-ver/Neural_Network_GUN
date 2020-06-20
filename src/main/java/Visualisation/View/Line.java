package Visualisation.View;


import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Line extends Line2D {
    private Point2D p1;
    private Point2D p2;
    private Color color;
    private float thickness;


    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point2D.Double(x1,y1);
        this.p2 = new Point2D.Double(x2,y2);
    }

    public void setColorAndThickness(double weight){
        if (weight > 0){
            this.color = Color.BLUE;
            this.thickness = (float) (Math.atan(weight)*3);
        } else {
            this.color = Color.RED;
            this.thickness = (float) (Math.atan(Math.abs(weight))*3);
        }
    }

    public void draw(Graphics g) {
        Graphics2D border = (Graphics2D)g;
        border.setStroke(new BasicStroke(this.thickness));
        border.setPaint(color);
        border.draw(this);
        border.fill(this);
    }


    @Override
    public double getX1() {
        return p1.getX();
    }

    @Override
    public double getY1() {
        return p1.getY();
    }

    @Override
    public Point2D getP1() {
        return this.p1;
    }

    @Override
    public double getX2() {
        return p2.getX();
    }

    @Override
    public double getY2() {
        return p2.getY();
    }

    @Override
    public Point2D getP2() {
        return this.p2;
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {
        p1.setLocation(x1,y1);
        p2.setLocation(x2,y2);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }







}
