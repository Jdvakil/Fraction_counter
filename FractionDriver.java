
/**
 * This is the driver class for fractions. It reads from a file a list of fractions and puts them in a arrayList
 * of Fractions and then sends it to be counted how many times does a fraction occur.
 * @author Jay Darshan Vakil
 * @version 01/21/2020
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FractionDriver {
    public static void main(String[] args) {
        File file = new File("fractions.txt");
        ArrayList<Fraction> fractions = new ArrayList<Fraction>();
        Scanner readFile;
        int numerator, denominator;
        String[] split = new String[2];
        Fraction addFraction;
        try {
            readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                split = readFile.nextLine().split("/");
                numerator = Integer.parseInt(split[0]);
                denominator = Integer.parseInt(split[1]);
                addFraction = new Fraction(numerator, denominator);
                fractions.add(addFraction);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found...");
        }
        FractionCounter countFractions = new FractionCounter(fractions);
        System.out.println(countFractions);
    }
}