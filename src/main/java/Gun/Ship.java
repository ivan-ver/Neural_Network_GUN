package Gun;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ship {
    private Ellipse2D ellipse2D;
    private double X0;
    private double Y0;
    private boolean isLive;
    private Color color = new Color(33, 149, 149);

    public Ship() {
        this.X0 = 50 + Math.random()*(1100-82);
        this.Y0 = 50 + Math.random()*(736-50);
        this.isLive = true;
        setRectangle(this.X0, this.Y0);
    }

    public Ship(double x, double y){
        this.X0 = x;
        this.Y0 = y;
        this.isLive = true;
        setRectangle(this.X0, this.Y0);
    }

    private void setRectangle(double x, double y){
        this.ellipse2D = new Ellipse2D.Double(x, y , 160, 80);
    }

    public void move(){
        this.X0 += 1;
        setRectangle(this.X0,this.Y0);
    }

    public boolean isLive(){
        return this.isLive;
    }

    public void destroy() {
        isLive = false;
    }

    public Ellipse2D getShape(){
        return this.ellipse2D;
    }

    protected void draw(Graphics g) {
        Graphics2D body = (Graphics2D) g;
        Graphics2D line = (Graphics2D) g;
        line.setColor(this.color);
        body.draw(this.ellipse2D);
        body.fill(this.ellipse2D);

        Graphics2D text = (Graphics2D) g;
        text.setColor(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 22));
        text.drawString(String.valueOf((int)getShape().getCenterX()), 1220, 289);
        text.drawString(String.valueOf((int)getShape().getCenterY()), 1220, 556);

    }
}
