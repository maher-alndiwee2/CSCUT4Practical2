import java.io.*;
import java.util.Scanner;

public class FormatNamesm {

    public static void main(String[] args) {
        boolean allUpperCase = false; // flag for uppercase formatting
        String inputFileName = ""; // name of input file
        String outputFileName = ""; // name of output file

        // parse command line arguments
        if (args.length >= 2) {
            if (args[0].equals("-u")) {
                allUpperCase = true;
                inputFileName = args[1];
                outputFileName = args[2];
            } else {
                inputFileName = args[0];
                outputFileName = args[1];
            }
        } else {
            System.out.println("Usage: java FilesInOut [-u] input_file output_file");
            System.exit(1);
        }

        try {
            Scanner scanner = new Scanner(new File(inputFileName));
            PrintWriter writer = new PrintWriter(new File(outputFileName));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");

                // format first and last names
                StringBuilder firstName = new StringBuilder(words[0]);
                StringBuilder lastName = new StringBuilder(words[words.length - 2]);
                firstName.setCharAt(0, Character.toUpperCase(firstName.charAt(0)));
                lastName.setCharAt(0, Character.toUpperCase(lastName.charAt(0)));
                firstName.replace(1, firstName.length(), firstName.substring(1).toLowerCase());
                lastName.replace(1, lastName.length(), lastName.substring(1).toLowerCase());
                String fullName = firstName.toString();
                if (words.length == 4) {
                    String middleInitial = words[1];
                    fullName += " " + middleInitial.toUpperCase() + ". " + lastName.toString();
                } else {
                    fullName += " " + lastName.toString();
                }

                // format date
                String date = words[words.length - 1];
                String formattedDate = date.substring(0, 2) + "/" + date.substring(2, 4) + "/" + date.substring(4);

                // write output to file
                if (allUpperCase) {
                    writer.println(fullName.toUpperCase() + "     " + formattedDate);
                } else {
                    writer.println(fullName + "     " + formattedDate);
                }
            }

            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(1);
        }
    }

}
