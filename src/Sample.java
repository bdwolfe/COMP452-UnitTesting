import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Sample {

    /////////////////
    // START of code for exercise 1

    // the values in the sample
    private ArrayList<Double> values;

    // if true, use the population standard deviation formula
    // instead of the sample standard deviation formula
    private boolean usePopStdDev;

    // Date when the last value was added
    private LocalDateTime lastEdited;


    public Sample(){
        this(false);
    }

    public Sample(boolean usePopulationStandardDeviation){
        values = new ArrayList<>();
        usePopStdDev = usePopulationStandardDeviation;
    }


    /**
     * @param val - to be added to this sample
     */
    public void addValue(double val){
        values.add(val);
        lastEdited = LocalDateTime.now();
    }

    /**
     * @return the number of items in the sample
     */
    public double size(){
        return values.size();
    }

    /**
     * @return sample mean
     */
    public double mean(){
        double sum = 0.0;
        for(Double val : values){
            sum += val;
        }
        return sum / size();
    }


    /**
     * @param low - lower bound
     * @param high - upper bound
     * @return number of values between low and high (inclusive)
     */
    public int numInRange(double low, double high){
        int count = 0;
        for(Double val : values){
            if(val < low && val > high){
                count++;
            }
        }
        return count;
    }

    ////////////////////////////////////////////
    // END of exercise 1 code
    //
    // START of exercise 2 code

    /**
     * @return maximum value in the sample
     */
    public double max(){
        // NOTE: this is stub, to let the unit tests compile
        // In a real project, we would need to complete this
        // method after writing the unit tests
        return 0.0;
    }

    /**
     * @return minimum value in the sample
     */
    public double min(){
        // NOTE: this is stub, to let the unit tests compile
        // In a real project, we would need to complete this
        // method after writing the unit tests
        return 0.0;
    }

    ////////////////////////////////////////////
    // END of exercise 2 code
    //
    // START of exercise 3 code

    /**
     * @param filename - file to which the method will write the sample data
     */
    public void writeToFile(String filename) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filename));

        out.println(lastEdited.toString());

        out.println(size());

        for(Double val : values){
            out.println(val);
        }

        out.close();
    }

    /**
     * @return standard deviation (population or sample, determined by class variable)
     */
    public double standardDev(){
        double avg = mean();

        double sumOfSquares = 0.0;
        for(Double val : values){
            sumOfSquares += (val - avg) * (val - avg);
        }

        double denominator = (usePopStdDev ? size() : size()-1);
        return Math.sqrt( sumOfSquares / denominator );
    }
}
