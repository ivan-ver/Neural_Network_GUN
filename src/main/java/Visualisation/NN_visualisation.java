package Visualisation;


import Neural_Network.Neural_Network;
import Neural_Network.Neuron.Hidden_Neuron;
import Neural_Network.Neuron.Inlet_Knot;
import Visualisation.View.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NN_visualisation {
    private Neural_Network neural_network;
    private List<Neuron_view[]> neurons = new ArrayList<>();



    public NN_visualisation(Neural_Network neural_network){
        this.neural_network = neural_network;
        SetNeuronsView();
    }


    private void SetNeuronsView(){
        double stepX = 110;
        for (int i = 0; i < neural_network.getLayers().size(); i++) {
            double stepY = Visualisation_geometry.workSpaceHeight/
                    ((Arrays.stream(neural_network.getLayers().get(i))
                            .filter(x -> x instanceof Hidden_Neuron | x instanceof Inlet_Knot)).count()+1.0);

            Neuron_view[] layer = new Neuron_view[neural_network.getLayers().get(i).length];
            for (int j = 0; j < neural_network.getLayers().get(i).length; j++) {
                switch (neural_network.getLayers().get(i)[j].getClass().getSimpleName()){
                    case "Inlet_Knot":
                        layer[j] = new IK_View((Visualisation_geometry.workSpaceWidth-3)+stepX*(i+1),stepY*(j+1)+30);
                        break;
                    case "Displacement_Neuron":
                        layer[j] = new DN_View((Visualisation_geometry.workSpaceWidth+stepX*(i+1)),750);
                        break;
                    case "Hidden_Neuron":
                        layer[j] = new HN_View((Visualisation_geometry.workSpaceWidth-3)+stepX*(i+1),stepY*(j+1)+30);
                        break;
                    default:
                        layer[j] = null;
                        break;
                }
            }
            neurons.add(layer);
        }

        for (int i = 0; i < neurons.size()-1; i++) {
            for (int j = 0; j < neurons.get(i).length; j++) {
                neurons.get(i)[j].setLines(neurons.get(i+1));
            }
        }
        for (int i = 0; i < neurons.size()-1; i++) {
            for (int j = 0; j < neurons.get(i).length; j++) {
                for (int k = 0; k < neurons.get(i)[j].getLines().length; k++) {
                    double s = this.neural_network.getLayers().get(i)[j].get_Signal();
                    neurons.get(i)[j].getLines()[k].setColorAndThickness(
                        this.neural_network.getLayers().get(i+1)[k].get_Weight(j)*s

                    );
                }
            }
        }
    }

    public void draw(Graphics g) {

        for (Neuron_view[] neuron : neurons) {
            for (Neuron_view neuron_view : neuron) {
                if (neuron_view.getLines()!=null){
                    for (Line line : neuron_view.getLines()) {
                        line.draw(g);
                    }
                }
            }
        }

        neurons.forEach(layer -> Arrays.stream(layer)
                .forEach(neuron_view -> neuron_view.draw(g)));
    }

}
