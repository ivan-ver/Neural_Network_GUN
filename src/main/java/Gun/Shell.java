package Gun;

import Visualisation.Visualisation_geometry;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Shell{
    private Ellipse2D shellCircle;
    private double diam = 20.0;

    private double X0;
    private double Y0;
    private double angle;
    private double V0 = 15.0;
    private int iteration = 0;
    private boolean isLive;
    private Gun gun;

    Shell(double x, double y, double angle, Gun gun) {
        setMainCoordinates(x-this.diam/2,y-this.diam/2);
        this.angle = angle;
        this.isLive = true;
        this.gun = gun;
    }
    boolean isLive(){
        return this.isLive;
    }

    Ellipse2D getShape(){
        return this.shellCircle;
    }

    private void setMainCoordinates(double x, double y){
        this.X0 = x;
        this.Y0 = y;
        this.shellCircle = new Ellipse2D.Double(this.X0,this.Y0,diam,diam);

    }

    protected void draw(Graphics g) {
        Graphics2D ball = (Graphics2D)g;
        ball.setPaint(Color.RED);
        ball.draw(this.shellCircle);
        ball.fill(this.shellCircle);
    }

    void move(){

        double x = this.X0 + this.V0*(this.iteration/100.0)*Math.cos(Math.toRadians(this.angle));
        double y = this.Y0 - this.V0*(this.iteration/100.0)*Math.sin(Math.toRadians(this.angle))+
                (9.81*(this.iteration/100.0)*(this.iteration/100.0))/2;
        setMainCoordinates(x,y);
        this.iteration++;
    }

    public void check() {
        if (this.gun.getShip().getShape().contains(this.getShape().getCenterX(), this.getShape().getCenterY())) {
            this.gun.getShip().destroy();
            this.isLive = false;
        }else {
            if (this.X0 >= Visualisation_geometry.windowWidth - 50) {
                this.isLive = false;
            }
            if (this.X0 <= 50) {
                this.isLive = false;
            }
        }
    }

    public void trainCheck() {
        if (this.gun.getShip().getShape().contains(this.getShape().getCenterX(), this.getShape().getCenterY())) {
            this.gun.getShip().destroy();
            this.isLive = false;
            this.gun.trainData.add(new double[]{
                    this.gun.getShip().getShape().getCenterX()/1100,
                    this.gun.getShip().getShape().getCenterY()/736});
            this.gun.targetData.add(new double[]{
                    this.gun.relativeAngle});

        } else {
            if (this.X0 >= Visualisation_geometry.windowWidth - 50) {
                this.isLive = false;
                this.gun.correctAngel(this.gun.getShip().getShape().getCenterY() - this.getShape().getCenterY());
            }
            if (this.X0 <= 50) {
                this.isLive = false;
                this.gun.correctAngel(-(this.gun.getShip().getShape().getCenterY() - this.getShape().getCenterY()));
            }
            if (this.getShape().contains(this.getShape().getCenterX(), this.gun.getShip().getShape().getCenterY())) {
                this.isLive = false;
                this.gun.correctAngel( this.gun.getShip().getShape().getCenterX() - this.getShape().getCenterX());
            }
        }
    }
}
