package and;

import java.util.Arrays;

/**
 *
 * @author root
 */
public class And {

    double[] inputs;
    double output;
    double[] weights;
    
    double[][] patterns = {
        {0, 0, 0},
        {0, 1, 0},
        {1, 0, 0},
        {1, 1, 1},
    };
    
    public And() {
        inputs = new double[2];
        weights = new double[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            weights[i] = Math.random() * 0.2 + 0.1; //0.1--0.3
        }
    }
    
    public void countOutput() {
        output = 0;
        for (int i = 0; i < inputs.length; i++) {
            output += inputs[i] * weights[i];
        }
        //output > 0.5 ? output = 1 : output = 0;
        if (output > 0.5) output = 1; else output = 0;
    }
    
    public void study() {
        double globalError = 0;
        do {
            globalError = 0;
            for (int p = 0; p < patterns.length; p++) {
                inputs = Arrays.copyOf(patterns[p], patterns[p].length - 1);
                countOutput();
                double error = patterns[p][2] - output;
                globalError += Math.abs(error);
                for (int i = 0; i < inputs.length; i++) {
                    weights[i] += 0.1 * error * inputs[i];
                }    
            }
        } while (globalError != 0);
    }
    
    public void test() {
        study();
        for (int p = 0; p < patterns.length; p++) {
            inputs = Arrays.copyOf(patterns[p], patterns[p].length - 1);
            countOutput();
            System.out.println(output);
        }
    }
    
    public static void main(String[] args) {
        new And().test();
    }
    
}
