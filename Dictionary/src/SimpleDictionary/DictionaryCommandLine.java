package SimpleDictionary;

import java.util.Scanner;

public class DictionaryCommandLine {
    // Example usage

    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public static void main(String[] args) {
        DictionaryCommandLine cmd = new DictionaryCommandLine();
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            DictionaryCommandLine.clearConsole();
            int a = cmd.printMenu(scanner);

            switch (a) {
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
                    cmd.display(scanner);
                    break;
                case 5:
                    cmd.lookUp(scanner);
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
                    return;
            }
        }
    }

    public void dictionaryBasic(Scanner scanner) {
        dictionaryManagement.insertFromCommandline(scanner);
        dictionaryManagement.showAllWords();
    }

    public void dictionaryExportToFile() {
        dictionaryManagement.saveToFile();
    }

    public void dictionarySearcher(String search) {
        dictionaryManagement.printWords(dictionaryManagement.findAllWordContain(search));
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

    public int printMenu(Scanner scanner) {
        System.out.println("Welcome to My Application!");
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

    public void display(Scanner scanner) {
        dictionaryManagement.showAllWords();
        scanner.nextLine();
        System.out.print("Load data successfully, press any button to continue");
        scanner.nextLine();
    }

    public void lookUp(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Import word to look up: ");
        String word = scanner.nextLine();
        dictionaryManagement.printWords(dictionaryManagement.findAllWordContain(word));
        System.out.print("Load data successfully, press any button to continue");
        scanner.nextLine();
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

    public void search(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Import word to look up: ");
        String word = scanner.nextLine();
        dictionaryManagement.search(word);
        System.out.print("Press any button to continue");
        scanner.nextLine();
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
