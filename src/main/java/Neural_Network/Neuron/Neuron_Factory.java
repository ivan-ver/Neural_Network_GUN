package Neural_Network.Neuron;

public class Neuron_Factory {
    public static Neuron[] get_new_layer(int neurons_in_layer){
        Neuron[] layer = new Neuron[neurons_in_layer + 1];
        for (int i = 0; i < neurons_in_layer; i++) {
            layer[i] = new Hidden_Neuron();
        }
        layer[neurons_in_layer] = new Displacement_Neuron();
        return layer;
    }
}
