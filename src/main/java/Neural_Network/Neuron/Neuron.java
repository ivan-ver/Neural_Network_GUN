package Neural_Network.Neuron;

public interface Neuron {
    double get_Signal();
    void set_Signal(double signal);

    double calculate_Error(int n);
    void  set_Error(double error);

    double get_Weight(int n);

    void set_Layer(Neuron[] layer);
    void correct_Weights(double learning_Rate);
    double getError();
}
