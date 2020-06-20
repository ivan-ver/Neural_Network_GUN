package Visualisation.View;

import java.awt.*;

public interface Neuron_view {
    void draw(Graphics g);
    void setLines(Neuron_view[] layer);
    Line[] getLines();
    double[] getCenter();

}
