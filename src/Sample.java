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
     * @return standard deviation
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

    /**
     * @param low - lower bound
     * @param high - upper bound
     * @return number of values between low and high (inclusive)
     */
    public int numInRange(double low, double high){
        int count = 0;
        for(Double val : values){
            if(val <= low && val >= high){
                count++;
            }
        }
        return count;
    }

}
