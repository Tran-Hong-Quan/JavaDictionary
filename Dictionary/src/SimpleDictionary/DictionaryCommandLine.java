package SimpleDictionary;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DictionaryCommandLine {
    // Example usage

    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public static int status = 0;
    public static void main(String[] args) {
        DictionaryCommandLine cmd = new DictionaryCommandLine();
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are using a Simple English - Vietnamese Dictionary created by T.H.Quan, N.D.Hung and P.T.Phong");
        System.out.println("This is our final assignment on the course \" INT2204 - Object Oriented Programming\" at VNU-UET");
        System.out.println("Word information in this dictionary is used for testing purpose only. " +
                "We are not responsible for the correctness of the translation.");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean exit = false;
        while (!exit) {
            switch (status) {
                case 0:
                    status = cmd.printMenu(scanner);
                    if (status == 0) {
                        exit = true;
                    }
                    break;
                case 1:
                    cmd.addWord(scanner);
                    break;
                case 2:
                    cmd.remove(scanner);
                    break;
                case 3:
                    cmd.updateWord(scanner);
                    break;
                case 4:
                    cmd.showAllWords(scanner);
                    break;
                case 5:
                    cmd.lookup(scanner);
                    break;
                case 6:
                    cmd.search(scanner);
                    break;
                case 7:
                    break;
                case 8:
                    cmd.loadData(scanner);
                    break;
                case 9:
                    cmd.saveData(scanner);
                    break;
                default:
                    System.out.println("Action not supported.");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
        cmd.exit();
    }

    public void exit() {
        this.dictionaryManagement.endProgram();
    }
    public void dictionaryExportToFile() {
        dictionaryManagement.saveToFile();
    }

    public void addWord(Scanner scanner) {
        dictionaryManagement.insertFromCommandline(scanner);
    }

    public void remove(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Input word to remove: ");
        String wordToRemove = scanner.nextLine();
        boolean isSuccess = dictionaryManagement.removeWord(wordToRemove);

        if (isSuccess) {
            System.out.println("Remove " + wordToRemove + " successfully");
        } else {
            System.out.println("There's no " + wordToRemove + " to remove");
        }
    }
    public void showAllWords(Scanner scanner) {
        showWords(this.dictionaryManagement.dictionary.data, scanner);
    }
    public void showWords(List<Word> words, Scanner scanner) {
        int currentPage = 1;
        int totalPage = words.size() / 20 + 1;
        boolean stay = true;
        while (stay) {
            clearConsole();
            if (currentPage == totalPage) {
                dictionaryManagement.printWords(words.subList((currentPage-1)*20, words.size()), currentPage);
            } else {
                dictionaryManagement.printWords(words.subList((currentPage-1)*20, (currentPage)*20), currentPage);
            }
//            dictionaryManagement.printWords(this.dictionaryManagement.dictionary.data.subList((currentPage-1)*20, (currentPage)*20), currentPage);
            System.out.println("Page " + currentPage + "/" + totalPage);
            System.out.println("[1] Next page\t[2] Previous page\t[3] Choose a word\t[4] Back to main menu");
            int cmd;
            try {
                cmd = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid command, try again.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                continue;
            }
            switch (cmd) {
                case 1:
                    if (currentPage < totalPage) {
                        currentPage++;
                    } else {
                        System.out.println("This is the last page.");
                    }
                    break;
                case 2:
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println("This is the first page.");
                    }
                    break;
                case 3:
                    stay = false;
                    System.out.print("Choose a word index to look up: ");
                    int index;
                    try {
                        index = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println(scanner.next() + "is not a valid index, try again.");
                        stay = true;
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    }
                    showWord(this.dictionaryManagement.getWord(index-1));
                    break;
                case 4:
                    stay = false;
                    status = 0;
                    break;
                default:
                    System.out.println("Action not supported.");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
            }
        }
        scanner.nextLine();
    }
    public int printMenu(Scanner scanner) {
        clearConsole();
        System.out.println("EV Dictionary, please choose an action:");
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Look up");
        System.out.println("[6] Search");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");
        System.out.print("Your action: ");

        return scanner.nextInt();
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void showWord(Word word){
        Scanner scanner = new Scanner(System.in);
        boolean stay = true;
        while (stay){
            clearConsole();
            dictionaryManagement.printWord(word);
            System.out.println("[1] Pronunciation\t[2] Looking for another word\t[3] Back to main menu");
            int cmd = scanner.nextInt();
            switch (cmd) {
                case 1:
                    dictionaryManagement.playPronunciation(word);
                    break;
                case 2:
                    stay = false;
                    scanner.nextLine();
                    status = 5;
                    break;
                case 3:
                    stay = false;
                    status = 0;
                    break;
                default:
                    System.out.println("Action not supported.");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
    }
    public void updateWord(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Import word to update: ");
        String word = scanner.nextLine();
        int index = dictionaryManagement.searchIndex(word);
        if (index == -1) {
            System.out.print("Cannot find word " + word + ", press any key to continue....");
        } else {
            System.out.print("Update Vietnamese explanation: ");
            String wordExplain = scanner.nextLine();
            Word newWord = dictionaryManagement.getWord(index);
            newWord.setWordExplain(wordExplain);
            System.out.print("Update word " + word + " successfully, press any key to continue....");
        }
        scanner.nextLine();
    }
    public void lookup(Scanner scanner) {
        scanner.nextLine();
        clearConsole();
        System.out.print("Enter the word to look up: ");
        String word = scanner.nextLine();
        Word founded = dictionaryManagement.lookup(word);
        if (founded == null){
            System.out.print("Cannot find the word \" " + word + "\". Continue searching? [Y/N]");
            boolean stay = true;
            while (stay) {
                String cmd = scanner.nextLine();
                if (cmd.equals("Y") || cmd.equals("y")) {
                    status = 6;
                    stay = false;
                } else if (cmd.equals("N") || cmd.equals("n")) {
                    status = 0;
                    stay = false;
                }
            }
            return;
        }
        showWord(founded);
        scanner.nextLine();
    }
    public void search(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Searching for: ");
        String subString = scanner.nextLine();
        List<Word> result = dictionaryManagement.search(subString);
        if (result == null) {
            System.out.print("Cannot find any result for \"" + subString + "\".\n");
            System.out.print("Continue searching? [Y/N]");
            while (true) {
                if (scanner.nextLine().equals("Y")) {
                    status = 6;
                    break;
                } else if (scanner.nextLine().equals("N")) {
                    status = 0;
                    break;
                }
            }
            return;
        }
        showWords(result, scanner);
    }

    public void loadData(Scanner scanner) {
        dictionaryManagement.insertFromFile();
        scanner.nextLine();
        System.out.print("Load data successfully, press any button to continue");
        scanner.nextLine();
    }

    public void saveData(Scanner scanner) {
        dictionaryManagement.saveToFile();
        scanner.nextLine();
        System.out.print("Save data successfully, press any button to continue");
        scanner.nextLine();
    }

}

/**
 * TODO
 * - Implement search function
 * - Handle exception when user input invalid index
 * - Update the UI/UX
 */