package Visualisation.View;

import Visualisation.Visualisation_geometry;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

public class HN_View implements Neuron_view {
    private Line[] lines;
    private Ellipse2D external_circle;
    private Ellipse2D inner_circle;
    private Color color = new Color(89, 148, 14);
    private Color border_color = new Color(246, 8, 22);
    private double X;
    private double Y;

    public HN_View(double x, double y) {
        this.X = x ;
        this.Y = y ;
        this.external_circle = new Ellipse2D.Double(
                this.X - Visualisation_geometry.neuron_radius ,
                this.Y - Visualisation_geometry.neuron_radius,
                Visualisation_geometry.neuron_radius + 5,
                Visualisation_geometry.neuron_radius + 5);

        this.inner_circle = new Ellipse2D.Double(
                this.external_circle.getCenterX() - Visualisation_geometry.neuron_radius/2,
                this.external_circle.getCenterY() - Visualisation_geometry.neuron_radius/2,
                Visualisation_geometry.neuron_radius,
                Visualisation_geometry.neuron_radius);
    }


    public double[] getCenter(){
        return new double[]{this.external_circle.getCenterX(), this.external_circle.getCenterY()};
    }

    public void setNeuron(){
        this.inner_circle = new Double.Double(this.X,this.Y,
                Visualisation_geometry.neuron_radius,
                Visualisation_geometry.neuron_radius);
    }

    public void draw(Graphics g) {
//        if (this.lines!=null) {
//            Arrays.stream(this.lines).filter(Objects::nonNull).forEach(line -> line.draw(g));
//        }

        Graphics2D border = (Graphics2D)g;
        border.setPaint(border_color);
        border.fill(this.external_circle);
        border.draw(this.external_circle);

        Graphics2D circle = (Graphics2D)g;
        circle.setPaint(color);
        circle.draw(this.inner_circle);
        circle.fill(this.inner_circle);




    }

    public Line[] getLines() {
        return lines;
    }

    @Override
    public void setLines(Neuron_view[] layer) {
        this.lines = new Line[(int) Arrays.stream(layer).filter(neuron_view -> neuron_view instanceof HN_View).count()];
        for (int i = 0; i < this.lines.length; i++) {
                lines[i] = new Line(this.inner_circle.getCenterX(),
                        this.inner_circle.getCenterY(),
                        layer[i].getCenter()[0],
                        layer[i].getCenter()[1]);
        }

    }


}
