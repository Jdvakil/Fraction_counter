
/**
 * This is the FractionCounter class. It takes an arraylist as an argument in the constructor and seperates the 
 * numerator and denominator into two separate arrays. 
 * I didnt use the parameters given because the way i programmed simplified my code. 
 * @author Jay Darshan Vakil
 * @version 01/21/2020
 */
import java.util.ArrayList;

public class FractionCounter {
    ArrayList<Fraction> fractions;
    int[] numerator;
    int[] denominator;
    int count;
    int numLines = 0;

    /**
     * This is the only constructor of the class, it takes an argument an array list
     * that contains all the fractions and then puts them in the two separate arrays
     * 
     * @param arr the arraylist full of fractions
     */
    public FractionCounter(ArrayList<Fraction> arr) {
        this.numLines = arr.size();
        this.fractions = arr;
        this.numerator = new int[numLines];
        this.denominator = new int[numLines];

        for (int i = 0; i < numLines; i++) {
            numerator[i] = fractions.get(i).getNumerator();
            denominator[i] = fractions.get(i).getDenominator();
        }
    }

    /**
     * This is the compare and increment method, it checks for how many times does a
     * fraction occurs in the text file and returns the statement that is the output
     * that tells how many times does a fraction occur. This method is private
     * because the function of this method has nothing to do with the driver and the
     * output job is done by the toString method.
     * 
     * @return
     */
    private String compareAndIncrement() {
        boolean[] check = new boolean[numLines];
        int tempNumerator, tempDenominator;
        String retVal = "";// this returns the final product
        for (int i = 0; i < numLines - 1; i++) {
            tempNumerator = numerator[i];
            tempDenominator = denominator[i];
            for (int j = 0; j < numLines; j++) {
                if (!check[j]) {
                    if (tempDenominator == denominator[j] && tempNumerator == numerator[j]) {
                        count++;
                        check[j] = true;
                    }
                }
            }
            if (count != 0) {
                retVal += tempNumerator + "/" + tempDenominator + " Repeats: " + count + " times. \n";
            }
            count = 0;
        }
        return retVal;
    }

    /**
     * This is the output toString method that tells the user how many times does a
     * fraction occur.
     */
    @Override
    public String toString() {
        return compareAndIncrement();
    }
}