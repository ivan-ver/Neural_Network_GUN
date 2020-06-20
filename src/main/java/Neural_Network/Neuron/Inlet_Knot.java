package Neural_Network.Neuron;



import java.io.Serializable;

public class Inlet_Knot implements Neuron, Serializable {
    private double signal;

    public Inlet_Knot(){ }

    //**********************************************************************//

    public void set_Layer(Neuron[] layer) { }

    public Neuron[] getLayer() {
        return null;
    }

    public double get_Signal() {
        return this.signal;
    }

    public void set_Signal(double signal) {
        this.signal = signal;
    }

    public double calculate_Error(int n) {
        return 0;
    }

    public void set_Error(double error) { }

    @Override
    public double get_Weight(int n) {
        return 0;
    }

    public void correct_Weights(double learning_Rate) { }

    @Override
    public double getError() {
        return 0;
    }


}
