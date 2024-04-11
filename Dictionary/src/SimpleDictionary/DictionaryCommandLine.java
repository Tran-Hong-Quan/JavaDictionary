package SimpleDictionary;

import java.util.Scanner;

public class DictionaryCommandLine {
    // Example usage

    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public static int status = 0;
    public static void main(String[] args) {
        DictionaryCommandLine cmd = new DictionaryCommandLine();
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            switch (status) {
                case 0:
                    status = cmd.printMenu(scanner);
                    if (status == 0) {
                        System.out.println("Action not supported.");
                        try {
                            System.out.wait(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
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

    public void dictionaryExportToFile() {
        dictionaryManagement.saveToFile();
    }

    public void dictionarySearcher(String search) {
//        dictionaryManagement.printWords(dictionaryManagement.findAllWordContain(search));
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
        int currentPage = 1;
        int totalPage = this.dictionaryManagement.dictionary.data.size() / 20 + 1;
        boolean stay = true;
        while (stay) {
            clearConsole();
            dictionaryManagement.printWords(this.dictionaryManagement.dictionary.data.subList(currentPage*20, (currentPage+1)*20-1), currentPage);
            System.out.println("[1] Next page\t[2] Previous page\t[3] Choose a word\t[4] Back to main menu");
            int cmd = scanner.nextInt();
            switch (cmd) {
                case 1:
                    if (currentPage < totalPage) {
                        currentPage++;
                    } else {
                        System.out.println("This is the last page");
                    }
                    break;
                case 2:
                    if (currentPage > 1) {
                        currentPage--;
                        dictionaryManagement.printWords(this.dictionaryManagement.dictionary.data.subList((currentPage - 1) * 20, currentPage * 20), currentPage);
                    } else {
                        System.out.println("This is the first page");
                        try {
                            System.out.wait(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    stay = false;
                    showWord(this.dictionaryManagement.getWord(scanner.nextInt()));
                    break;
                case 4:
                    stay = false;
                    break;
                default:
                    System.out.println("Action not supported.");
                    try {
                        System.out.wait(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
        }
        scanner.nextLine();
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

    public void showWord(Word word){

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
    public void lookup(){

    }
    public void search(Scanner scanner) {
        scanner.nextLine();
        boolean continueSearch = true;
        while (continueSearch){
            clearConsole();
            System.out.print("Searching for: ");
            String word = scanner.nextLine();
            Word founded = dictionaryManagement.search(word);
            if (founded == null){
                return;
            }
            boolean stay = true;
            while (stay){
                System.out.println("[1] Pronunciation\t[2] Search another word\t[3] Back to main menu");
                int cmd = scanner.nextInt();
                switch (cmd) {
                    case 1:
                        dictionaryManagement.playPronunciation(founded);
                        break;
                    case 2:
                        stay = false;
                        scanner.nextLine();
                        break;
                    case 3:
                        stay = false;
                        continueSearch = false;
                        break;
                    default:
                        System.out.println("Invalid command, press any key to continue....");
                        scanner.next();
                        break;
                }
            }
        }

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
