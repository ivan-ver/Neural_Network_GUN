package Neural_Network.Neuron;


import java.io.Serializable;

public class Displacement_Neuron implements Neuron, Serializable {
    private double SIGNAL;

    public Displacement_Neuron() {
        this.SIGNAL = 1.0;
    }

    public Neuron[] getLayer() {
        return null;
    }

    public void set_Layer(Neuron[] layer) {

    }

    public double get_Signal() {
        return SIGNAL;
    }

    public void set_Signal(double signal) {

    }

    public double calculate_Error(int n) {
        return 0;
    }

    public void set_Error(double error) {

    }

    @Override
    public double get_Weight(int n) {
        return 0;
    }

    public void correct_Weights(double learning_Rate) {

    }

    @Override
    public double getError() {
        return 0;
    }


}
