import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Sample {

    // the values in the sample
    private ArrayList<Double> values;

    // if true, use the population standard deviation formula
    // instead of the sample standard deviation formula
    private boolean usePopStdDev;

    // Date when the last value was added
    private Date lastEdited;


    public boolean isUsingPopStdDev() {
        return usePopStdDev;
    }

    public Date getLastEdited() {
        return lastEdited;
    }


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
        lastEdited = new Date();
    }

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

    /**
     * @return maximum value in the sample
     */
    public double max(){
        //TODO: complete this method
        return 0.0;
    }

    /**
     * @return minimum value in the sample
     */
    public double min(){
        //TODO: complete this method
        return 0.0;
    }

    /**
     * @param filename - file to which the method will write the sample data
     * @throws IOException
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
