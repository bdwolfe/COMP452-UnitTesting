import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SampleTest {
    // tolerance used for floating point equality checks
    final double tol = 1E-9;

    @Test
    void sizeZero() {
        Sample sample = new Sample();
        assertEquals(0, sample.size());
    }

    @Test
    void sizeOne() {
        Sample sample = new Sample();
        sample.addValue(7.0);
        assertEquals(1, sample.size());
    }

    @Test
    void sizeFive() {
        Sample sample = new Sample();
        final int n = 5;
        for(int i=0; i<n; i++){
            sample.addValue(7.0);
        }
        assertEquals(n, sample.size());
    }

    @Test
    void meanNegativeValue(){
        Sample sample = new Sample();
        sample.addValue(-20.0);
        sample.addValue(50.0);
        sample.addValue(0.0);

        // use the third argument to allow for
        // floating-point precision limitations
        assertEquals(10.0, sample.mean(), tol);
    }

    @Test
    void meanPositiveValues(){
        Sample sample = new Sample();
        sample.addValue(25.3);
        sample.addValue(4.8);
        sample.addValue(2.93);
        sample.addValue(100.0);
        assertEquals(33.2575, sample.mean(), tol);
    }
}