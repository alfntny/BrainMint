import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Alfin\\OneDrive\\Documents\\Java\\yes.txt"; // Change accordingly for your own file path
        String[] words = new String[1000]; 
        int[] counts = new int[1000]; 
        int wordCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineWords = line.toLowerCase().split("\\W+"); 
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
        } catch (IOException e) {
            e.printStackTrace();
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
/*  yes.txt contains :
In a small village, there lived a wise old man who was known for his knowledge and kindness. 
Every day, people would come to him seeking advice on various matters. He would listen patiently and offer thoughtful solutions. 
One day, a young boy approached him with a heavy heart. The boy had lost his favorite toy and was very upset. 
The old man smiled gently and said, "Sometimes, we must let go of things we love to make room for new experiences." 
The boy pondered this and realized that while he missed his toy, he could create new memories with his friends. 
From that day on, he learned to cherish both the past and the present.*/