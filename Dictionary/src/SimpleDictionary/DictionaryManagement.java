package SimpleDictionary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DictionaryManagement {

    public Dictionary dictionary = new Dictionary();

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

    public void showAllWords() {
        for (Word w : dictionary.data) {
            printWord(w);
        }
    }

    public void insertFromFile() {
        String filePath = "data.txt";
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            dictionary.data = new Gson().fromJson(content, new TypeToken<ArrayList<Word>>() {
            }.getType());
            System.out.println("Successfully read from file.");
            sortData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try {
            FileWriter myWriter = new FileWriter("data.txt");
            myWriter.write(new Gson().toJson(dictionary.data, new TypeToken<ArrayList<Word>>() {
            }.getType()));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
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

    public List<Word> findAllWordContain(String find) {
        List<Word> words = new ArrayList<Word>();

        for (Word w : dictionary.data) {
            if (w.getWordTarget().equals(find)) {
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

    public void search(String word) {
        for (Word w : dictionary.data) {
            if (w.getWordTarget().equals(word)) {
                printWord(w);
                return;
            }
        }
        System.out.println("Cannot find " + word + "in database");
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
        System.out.println(w.getWordTarget() + ": " + w.getWordExplain());
    }

    public void printWords(List<Word> words) {
        for (Word w : words) {
            printWord(w);
        }
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
