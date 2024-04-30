package SimpleDictionary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class DictionaryManagement {

    public Dictionary dictionary = new Dictionary();
    private Synthesizer synthesizer;
    public DictionaryManagement(){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        try {
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
        } catch (EngineException e) {
            e.printStackTrace();
            return;
        }
        try {
            synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.insertFromFile();
    }
    public void insertFromCommandline(Scanner scanner) {
        // Prompt for the number of words to insert
        System.out.print("Enter the number of words to insert: ");
        int numWords = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numWords; i++) {
            System.out.println("Word " + (i + 1) + ":");
            System.out.print("English word: ");
            String wordTarget = scanner.nextLine();
            System.out.print("Vietnamese explanation: ");
            String wordExplain = scanner.nextLine();

            // Create a new Word object and add it to the dictionary
            Word word = new Word(wordTarget, wordExplain);
            dictionary.data.add(word);
        }

        System.out.println("Words inserted successfully!");
    }

    public void insertFromFile() {
        String filePath = "Dictionary/data.txt";
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            dictionary.data = new Gson().fromJson(content, new TypeToken<ArrayList<Word>>() {
            }.getType());
            System.out.println("Import data successfully!");
            sortData();
        } catch (IOException e) {
            System.out.println("An error occurred while importing the data!");
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try {
            FileWriter myWriter = new FileWriter("Dictionary/data.txt");
            myWriter.write(new Gson().toJson(dictionary.data, new TypeToken<ArrayList<Word>>() {
            }.getType()));
            myWriter.close();
            System.out.println("Export data successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred, please try again!");
            e.printStackTrace();
        }
    }

    public void addWord(Word word) {
        dictionary.data.add(word);
    }

    public boolean removeWord(String wordTarget) {
        for (Word w : dictionary.data) {
            if (w.getWordTarget().equals(wordTarget)) {
                dictionary.data.remove(w);
                return true;
            }
        }
        return false;
    }
    public void endProgram() {
        try {
            synthesizer.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Word> findAllWordContain(String find) {
        List<Word> words = new ArrayList<Word>();

        for (Word w : dictionary.data) {
            if (w.getWordTarget().startsWith(find)) {
                words.add(w);
            }
        }

        Collections.sort(words, new Comparator<Word>() {
            @Override
            public int compare(Word s1, Word s2) {
                return s1.getWordTarget().compareTo(s2.getWordTarget());
            }
        });

        return words;
    }

    public Word lookup(String word) {
        for (Word w : dictionary.data) {
            if (w.getWordTarget().equals(word)) {
                printWord(w);
                return w;
            }
        }
        System.out.println("Cannot find " + word + " in the dictionary");
        return null;
    }

    public List<Word> search(String word) {
        List<Word> words = new ArrayList<Word>();
        int totalFounded = 0;
        for (Word w : dictionary.data) {
            if (w.getWordTarget().toLowerCase().contains(word)) {
                words.add(w);
                totalFounded++;
            }
        }
        if (totalFounded == 0) {
            return null;
        }
        return words;
    }
    public int searchIndex(String word) {
        for (int i = 0; i < dictionary.data.size(); i++) {
            Word w = dictionary.data.get(i);
            if (w.getWordTarget().equals(word)) {
                printWord(w);
                return i;
            }
        }
        return -1;
    }

    public void update(int location, Word newWord) {
        dictionary.data.set(location, newWord);
    }

    public boolean isContain(String word) {
        for (Word w : dictionary.data) {
            if (w.getWordTarget().equals(word)) {
                return true;
            }
        }
        return false;
    }

    public void printWord(Word w) {
        System.out.printf("%-30s | %-30s\n", w.getWordTarget(), w.getWordExplain());
    }

    public void printWords(List<Word> words, int pageNumber) {
        System.out.printf("No\t| %-30s | Vietnamese\n", "English");
        for (Word w : words) {
            System.out.print((words.indexOf(w) + 1 + (pageNumber-1)*20) + "\t| ");
            printWord(w);
        }
    }
    public void playPronunciation(Word word) {
        word.playPronunciation(this.synthesizer);
    }
    public void sortData() {
        Collections.sort(dictionary.data, new Comparator<Word>() {
            @Override
            public int compare(Word s1, Word s2) {
                return s1.getWordTarget().compareTo(s2.getWordTarget());
            }
        });
    }

    public Word getWord(int index) {
        return dictionary.data.get(index);
    }
}
