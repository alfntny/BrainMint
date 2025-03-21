
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Alfin\\OneDrive\\Documents\\Java\\yes.txt"; // Change this to your file path
        String[] words = new String[1000]; 
        int[] counts = new int[1000]; 
        int wordCount = 0;

        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().toLowerCase(); 
                String[] lineWords = line.split("\\W+"); 
                for (String word : lineWords) {
                    if (!word.isEmpty()) {
                        boolean found = false;
                        for (int i = 0; i < wordCount; i++) {
                            if (words[i].equals(word)) {
                                counts[i]++;
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            words[wordCount] = word;
                            counts[wordCount] = 1;
                            wordCount++;
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        for (int i = 0; i < wordCount; i++) {
            for (int j = i + 1; j < wordCount; j++) {
                if (counts[i] < counts[j]) {
                    int tempCount = counts[i];
                    counts[i] = counts[j];
                    counts[j] = tempCount;

                    String tempWord = words[i];
                    words[i] = words[j];
                    words[j] = tempWord;
                }
            }
        }

        System.out.println("Top 5 most frequent words:");
        for (int i = 0; i < Math.min(5, wordCount); i++) {
            System.out.println(words[i] + ": " + counts[i]);
        }
    }
}