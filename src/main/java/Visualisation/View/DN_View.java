package Visualisation.View;

import Visualisation.Visualisation_geometry;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

public class DN_View implements Neuron_view{
    private Line[] lines;
    private Ellipse2D circle;
    private Color color = Color.yellow;
    private double X;
    private double Y;

    public Line[] getLines() {
        return this.lines;
    }

    public DN_View(double x, double y) {
        this.X = x;
        this.Y = y;
        this.circle = new Ellipse2D.Double(
                this.X + Visualisation_geometry.neuron_radius/2,
                this.Y + Visualisation_geometry.neuron_radius/2,
                Visualisation_geometry.neuron_radius ,
                Visualisation_geometry.neuron_radius );


    }

    public double[] getCenter(){
        return new double[]{circle.getCenterX(), circle.getCenterY()};
    }

    public void setNeuron(){
        this.circle = new Double.Double(this.X,this.Y,
                Visualisation_geometry.neuron_radius,
                Visualisation_geometry.neuron_radius);
    }

    public void draw(Graphics g) {
//        Arrays.stream(this.lines).filter(Objects::nonNull).forEach(line -> line.draw(g));

        Graphics2D border = (Graphics2D)g;
        border.setPaint(color);
        border.draw(this.circle);
        border.fill(this.circle);
    }



    @Override
    public void setLines(Neuron_view[] layer) {
        this.lines = new Line[(int) Arrays.stream(layer).filter(neuron_view -> neuron_view instanceof HN_View).count()];
        for (int i = 0; i < this.lines.length; i++) {
            lines[i] = new Line(this.circle.getCenterX(),
                    this.circle.getCenterY(),
                    layer[i].getCenter()[0],
                    layer[i].getCenter()[1]);
        }
    }
}
