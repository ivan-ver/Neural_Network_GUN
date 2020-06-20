package Neural_Network.Neuron;

import java.io.Serializable;


public class Hidden_Neuron implements Neuron, Serializable {

    private double signal;
    private double error;
    private double[] weights;

    private Neuron[] previous_Layer;

    public Hidden_Neuron() {}

    public void set_Layer(Neuron[] layer) {
        this.previous_Layer = layer;
        this.weights = new double[layer.length];
        for (int i = 0; i < this.weights.length; i++) {
            weights[i] = 2 * Math.random() - 1;
        }
    }


    public void set_Signal(double signal) {
        double result = 0;
        for (int i = 0; i < previous_Layer.length; i++) {
            result += previous_Layer[i].get_Signal()*weights[i];
        }
        this.signal = Sigmoid(result);
    }


    public Neuron[] getLayer() {
        return this.previous_Layer;
    }

    public double get_Signal() {
        return this.signal;
    }


    public double calculate_Error(int n) {
        return this.error*weights[n];
    }


    public void set_Error(double error) {
        this.error = error;
    }

    @Override
    public double get_Weight(int n) {
        return weights[n];
    }

    public void correct_Weights(double learning_Rate){
        double deltaW = 0;
        for (int i = 0; i < previous_Layer.length; i++) {
            deltaW += weights[i]*previous_Layer[i].get_Signal();
        }
        deltaW = DerivativeSigmoid(deltaW)*this.error;

        for (int i = 0; i < weights.length; i++) {
            weights[i] -= deltaW*learning_Rate*previous_Layer[i].get_Signal();
        }

    }

    @Override
    public double getError() {
        return this.error;
    }


    private double Sigmoid(double arg){
        return 1/(1+ Math.exp(-1.0*arg));
    }
    private double DerivativeSigmoid(double arg){
        return Sigmoid(arg)*(1-Sigmoid(arg));
    }
}
