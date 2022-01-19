import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;


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
        if(size() == 0){
            throw new ArithmeticException("Cannot calculate mean of empty Sample");
        }

        double sum = 0.0;
        for(Double val : values){
            sum += val;
        }
        return sum / size();
    }


    /**
     * @param low - lower bound
     * @param high - upper bound
     * @param inclusive - if true, values equal to low or high will be counted
     * @return number of values between low and high (or equal to low or high, if inclusive is true)
     */
    public int numInRange(double low, double high, boolean inclusive){
        int count = 0;
        for(Double val : values){
            if((low < val && high > val) || (inclusive && low == val || high == val)){
                count++;
            }
        }
        return count;
    }

    ////////////////////////////////////////////
    // END of exercise 1 code
    //
    // START of exercise 2 code

    public boolean getUsePopulationStandardDev(){
        return usePopStdDev;
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


    ////////////////////////////////////////////
    // END of exercise 3 code
    //
    // START of exercise 5 code


    /**
     * @param prob - the probability of an item being included in the returned subsample
     * @return a random sample of values from this; each item is included with probability prob
     */
    public Sample subsample(double prob){
        Random random = new Random();
        Sample result = new Sample(this.usePopStdDev);

        for(Double val : values){
            if(random.nextDouble() <= prob){
                result.addValue(val);
            }
        }

        return result;
    }

}
