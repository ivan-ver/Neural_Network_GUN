package Gun;

import Neural_Network.Neural_Network;
import Neural_Network.Neuron.Neuron_Factory;
import Visualisation.Visualisation_geometry;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Gun {
    private Rectangle2D rectangle;
    private Ellipse2D circle;
    private Line2D line;
    private Color color = new Color(88, 128, 9);
    private double radius = 150.0;
    private double length = 100;
    //////////////
    private Neural_Network brain;
    //////////////
    private double realAngle;
    public double relativeAngle;
    private double X0 = Visualisation_geometry.workSpaceWidth/2.0-this.radius + Visualisation_geometry.border;
    private double Y0 = Visualisation_geometry.windowHeight - this.radius;
    private Shell shell;
    private Ship ship;
    public List<double[]> trainData;
    public List<double[]> targetData;

    public Gun() {
        this.brain = new Neural_Network(2,1,
                Neuron_Factory.get_new_layer(10),
                Neuron_Factory.get_new_layer(10),
                Neuron_Factory.get_new_layer(5))
                .compile();

//            this.brain = loadBrain();

        this.circle = new Ellipse2D.Double(X0, Y0, this.radius, this.radius);
        this.rectangle = new Rectangle2D.Double(X0 - 75, Y0 + this.radius / 2, 300, 300);
        this.line = new Line2D.Double(
                X0 + 150.0 / 2,
                Y0 + 150.0 / 2,
                X0 - this.length * Math.cos(0 * Math.PI / 2) + this.radius / 2,
                Y0 - this.length * Math.sin(0 * Math.PI / 2) + this.radius / 2);

        trainData = new ArrayList<>();
        targetData = new ArrayList<>();
    }

    public Ship getShip() {
        return ship;
    }

    public void arm(Ship ship){
        this.ship = ship;
        setAngle(this.brain.query(ship.getShape().getCenterX()/1100,ship.getShape().getCenterY()/736)[0]);
    }

    public void setAngle(double angle) {
        this.realAngle = angle * 150 + 15;
        this.relativeAngle = angle;
        double x2 = X0 - this.length * Math.cos(Math.toRadians(180 - this.realAngle)) + this.radius / 2;
        double y2 = Y0 - this.length * Math.sin(Math.toRadians(180 - this.realAngle)) + this.radius / 2;
        this.line = new Line2D.Double(
                X0 + this.radius / 2,
                Y0 + this.radius / 2,
                x2, y2);
        this.shell = new Shell(this.line.getX2(),this.line.getY2(),this.realAngle,this);
    }

    protected void draw(Graphics g) {
        Graphics2D body = (Graphics2D) g;
        Graphics2D line = (Graphics2D) g;
        line.setColor(Color.BLACK);
        line.setStroke(new BasicStroke(22.0f));
        line.draw(this.line);
        body.setPaint(color);
        body.draw(this.circle);
        body.fill(this.circle);
        body.draw(this.rectangle);
        body.fill(this.rectangle);

        Graphics2D text = (Graphics2D) g;
        text.setColor(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 22));
        text.drawString(String.valueOf(((int)this.realAngle*10)/10), 1755, 422);
    }

    public void correctAngel(double delta){
        double rAngle = this.relativeAngle-delta/3000;
        setAngle(rAngle);
    }

    public void train(){
        double[][] trainMatrix = new double[this.targetData.size()][];
        double[][] targetMatrix = new double[this.targetData.size()][];
        for (int i = 0; i < trainMatrix.length; i++) {
            trainMatrix[i] = trainData.get(i);
            targetMatrix[i] = targetData.get(i);
        }
        this.brain.trainBackPropagation(0.05,trainMatrix, targetMatrix);
        this.brain.save("src\\main\\java\\Gun\\GunBrain.txt");
    }

    public Neural_Network getBrain() {
        return this.brain;
    }

    private Neural_Network loadBrain() throws IOException, ClassNotFoundException {
        Neural_Network nn = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("725281062.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            nn = (Neural_Network) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nn;
    }

    public Shell getShell() {
        return this.shell;
    }
}
