import java.util.*;

public class HangmanManager {

    private static drawHangman draw;

    // Stores all words depending on chosen difficulty
    private Set<String> dictionary;
    // Stores guessed letters
    private Set<Character> guessedLetters;

    // Stores number of guesses left
    private int guessesLeft = 6;
    // Stores the length of the chosen words
    private int wordLength;
    // stores current pattern
    public String pattern = "";

    HangmanManager(final Set<String> dictionary, final int wordLength) {

        draw = new drawHangman();

        this.dictionary = dictionary;

        String newPattern = "";

        for (int i = 0; i < wordLength; i++) {
            newPattern += "- ";
        }

        guessedLetters = new TreeSet<Character>();
        guessesLeft = 6;

        this.pattern = newPattern;
        this.wordLength = wordLength;

    }

    public drawHangman getDrawing() {
        return draw;
    }

    // Returns the number of guesses the user has left
    public int getGuessesLeft() {
        return guessesLeft;

    }

    // Returns a Set of guessed letters
    public String getGuessedLetters() {
        if (guessedLetters.isEmpty()) {
            return "";
        } else {
            return guessedLetters.toString();
        }
    }

    public String getPattern() {
        return pattern;

    }

    public void replayGame() {
        draw.setDraw();
    }

    public Set<String> getDictionary() {
        if (dictionary.isEmpty())
            throw new IllegalAccessError("Empty Dictionary");
        return dictionary;
    }

    public void update(final char guess) {
        Boolean doesExist = guessedLetters.contains(guess);
        String keyPattern = " ";
        int largestSet = 0;
        Map<String, Set<String>> map = new HashMap<>();

        if (guessesLeft < 1 || dictionary.isEmpty())
            throw new IllegalStateException("Out of guesses or storage error", null);

        guessedLetters.add(guess);

        buildPattern(map);

        for (String key : map.keySet()) {
            if (largestSet < (map.get(key)).size()) {
                largestSet = (map.get(key)).size();
                keyPattern = key;
            }
        }

        if (!keyPattern.contains(String.valueOf(guess))) {
            if (doesExist) {
            } else {
                guessesLeft--;
            }
        }

        draw.setDraw(guessesLeft);
        dictionary.clear();
        dictionary.addAll(map.get(keyPattern));
        this.pattern = keyPattern;
    }

    public void buildPattern(final Map<String, Set<String>> map) {
        if (map.isEmpty())
            throw new NullPointerException("Pattern map is empty");
        String newPattern;

        for (String s : dictionary) {
            newPattern = "";
            for (int i = 0; i < wordLength; i++) {
                if (guessedLetters.contains(s.charAt(i))) {
                    newPattern += " " + s.charAt(i);

                } else {
                    newPattern += " -";

                }
            }
            newPattern = newPattern.substring(1);
            // Check if map has this pattern
            if (!map.containsKey(newPattern)) {
                Set<String> words = new HashSet<>();
                // Put element in map
                map.put(newPattern, words);
                // Add word to this new element
                map.get(newPattern).add(s);

                // Map already has this patten
            } else {
                // Simply just add this word to element with matching pattern (our key in
                // TreeMap)
                map.get(newPattern).add(s);
            }
        }
    }

}