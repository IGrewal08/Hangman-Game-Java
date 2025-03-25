import java.util.*;
import javax.swing.SwingUtilities;
import java.io.*;

public class Main{

    public static Set<String> dictionary;

    public static void main(String[] args) {
    
    setDictionary();

    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new HangmanGame();
        }
    });
        
    //build method to take all words in dictionary txt file and select words depending on chosen difficulty (words length)
    }

    public static void setDictionary() {

        dictionary = new HashSet<>();

        try {
            Scanner scanner = new Scanner(new File("lib/dictionary.txt"));

            while (scanner.hasNext()) {
                dictionary.add(scanner.next().toLowerCase());
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found Error");
        }

    }

    public static Set<String> getDictionary(int rand) {
        Set<String> formatedDictionary = new HashSet<>();

        for (String s: dictionary) {
            if (s.length() == rand) {
                formatedDictionary.add(s);
            }
        }

        return formatedDictionary; //this works
    }
}
