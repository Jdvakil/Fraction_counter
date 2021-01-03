/*
1 .Can you complete this without using arrays? What is the least number of variables you can use to solve this problem?
=> Yes, this can be solved by using other methods other than arrays, such as arraylists. Minimum 10 variables are required. 
2. Can you use just one array to solve this? What would the data type be of that array?
=> No, you need an initial array and then another array to store the split values and the reduced values. 
3. What does it mean to make a class so another class is inside (or part of) the first class,so that it is composed of other data types?  What does "composition" mean in that case?  How is it done?
=> Composition allows us to reuse the class over and over again.
4. What are some solutions to the reduction problem other than Euclid's GCD algorithm?
=> We could use the greatest common multiple method. 
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class reads from a file full of fractions, and outputs the number of
 * times a particular fraction is repeated. A fraction will be reduced to its
 * simplest form and then be counted for the occurences.
 * 
 * @author Jay Darshan Vakil
 * @version 01/14/2020
 */
public class FractionsV1{
    private static int lines = 0;// Number of lines, made a class variable so lesser variables need to be created.
    /**
     * This is the main method, it reads from the file, gets the number of lines,
     * and then puts it into an array and then sends the array for further
     * calculations.
     * 
     * @param args
     */
    public static void main(String[] args) {
        /*
         * I was running into this issue where just "fractions.txt" wasnt working so i
         * used the directory address which worked and i used it for testing.Maybe it 
         * couldve been my IDE that was getting confused. 
         */
        File file = new File("fractions.txt");// reads from the file fractions.txt.
        // try-catch to retrieve the number of fractions in the file.
        try {
            Scanner getLines = new Scanner(file);
            while (getLines.hasNextLine()) {
                lines++;
                getLines.nextLine();// Reading whole lines from the text file.
            }
            getLines.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
        }
        // try-catch to put all the fractions from the file into an array
        String[] allFractions = new String[lines];// array that contains all the fractions as strings.
        try {
            Scanner readFile = new Scanner(file);
            for (int i = 0; i < lines; i++) {
                allFractions[i] = readFile.nextLine();
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
        }

        countFractions(reduceFractions(
                allFractions));/*
                                * this sends the original fraction array to be reduced into its simplest form
                                */
    }

    /**
     * This method takes the original fraction and reduces it to its simplest form
     * using the gcd method. once it gets in the simplest form, it returns an array
     * with the reduced fractions to be counted for their occurences.
     * 
     * @param allFractions The original fraction array.
     * @return reducedfractions The reduced version of fractions.
     */
    public static String[] reduceFractions(String[] allFractions) {
        String[] reducedFractions = new String[lines];
        String[] split = new String[2];
        int getGcd, tempNum, tempDen;
        for (int i = 0; i < lines; i++) {
            split = allFractions[i].split("/");
            tempNum = Integer.parseInt(split[0]);
            tempDen = Integer.parseInt(split[1]);
            getGcd = getGcd(tempNum, tempDen);
            tempNum /= getGcd;
            tempDen /= getGcd;
            reducedFractions[i] = tempNum + "/" + tempDen;
        }
        return reducedFractions;
    }

    /**
     * By using recursive technique, this method calculates the greatest common
     * divisor of the numerator and the denominator. this method helps reduce the
     * fraction so that the simplest form can be counted.
     * 
     * @param numerator   the numerator of the fraction
     * @param denominator the denominator of the fraction
     * @return the gcd of the fraction
     */
    public static int getGcd(int numerator, int denominator) {
        // as the denominator of a fraction cant be 0, this if statement prevents it
        // from happening
        if (denominator == 0)
            return numerator;
        return getGcd(denominator, numerator % denominator);
    }

    /**
     * This method takes an input an array of strings of fractions reduced. Then it
     * separates the fractions into two int arrays, one for numerators and one for
     * denominators. Then using a nested for loop, it checks for the occurences of
     * the fracions.
     * 
     * @param allFractions The reduced form of fractions.
     */
    public static void countFractions(String[] allFractions) {
        int[] numerator = new int[lines];// the numerator array
        int[] denominator = new int[lines];// denominator array
        String[] split = new String[2];// temporary array to store the numerator and denominator after splitting the
                                       // "/"

        for (int i = 0; i < lines; i++) {
            split = allFractions[i].split("/");
            numerator[i] = Integer.parseInt(split[0]);
            denominator[i] = Integer.parseInt(split[1]);
        }
        int tempNum, tempDen;
        int count = 0;// number of occurences
        boolean[] repeat = new boolean[lines];// This array checks if the fraction has been repeated and prevents it
        // from reprinting
        for (int i = 0; i < lines; i++) {
            tempNum = numerator[i];
            tempDen = denominator[i];
            for (int j = 0; j < lines; j++) {
                if (!repeat[j]) {
                    if (tempDen == denominator[j]) {
                        if (tempNum == numerator[j]) {
                            count++;
                            repeat[j] = true;
                        }
                    }
                }
            }
            if (count != 0) {
                System.out.println(tempNum + "/" + tempDen + " \thas a count of: " + count);
            }
            count = 0;
        }
    }
}
