import java.io.*;
import java.util.*;

public class SentenceCase {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java SentenceCase input_file output_file");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try (Scanner scanner = new Scanner(new File(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            boolean newSentence = true;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    if (word.length() == 0) {
                        continue;
                    }
                    char firstChar = word.charAt(0);
                    if (newSentence && Character.isLowerCase(firstChar)) {
                        firstChar = Character.toUpperCase(firstChar);
                    }
                    sb.append(firstChar);
                    if (word.length() > 1) {
                        sb.append(word.substring(1).toLowerCase());
                    }
                    sb.append(" ");
                    newSentence = (word.endsWith(".") || word.endsWith("?") || word.endsWith("!"));
                }
                writer.println(sb.toString().trim());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("Done!");
    }
}
