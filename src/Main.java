import javax.swing.SwingUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final Set<String> dictionary;

    static {
        dictionary = new HashSet<String>();

        try {
            Scanner scanner = new Scanner(new File("lib/dictionary.txt"));

            while (scanner.hasNext()) {
                dictionary.add(scanner.next().toLowerCase());
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    public static void main(final String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HangmanGame();
            }
        });
    }

    public static Set<String> getDictionary(final int rand) {
        Set<String> formattedDictionary = new HashSet<>();

        for (String s : dictionary) {
            if (s.length() == rand) {
                formattedDictionary.add(s);
            }
        }

        return formattedDictionary;
    }
}
