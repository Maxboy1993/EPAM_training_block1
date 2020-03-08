package by.nareiko.scanner;
import java.util.Scanner;

public class DataScanner {
    public static int enterInt() {
        Scanner input = new Scanner(System.in);
        int number = 0;
        while (!input.hasNextInt()){
            input.next();
        }
        number = input.nextInt();
        return number;
    }

    public static double enterDouble() {
        Scanner input = new Scanner(System.in);
        double number = 0.0;
        while (!input.hasNextDouble()){
            input.next();
        }
        number = input.nextDouble();
        return number;
    }
    public static String enterString() {
        Scanner input = new Scanner(System.in);
        String string = "";
        while (!input.hasNextLine()){
            input.next();
        }
        string = input.nextLine();
        return string;
    }

}
