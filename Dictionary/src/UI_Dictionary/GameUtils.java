package UI_Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameUtils {
    private Word answer;
    private List<Word> choices;
    private int correctChoices;
    private int correctAnswer;
    private int remainingQuestion;
    private String description;
    private String gameType;
    
    public GameUtils(){
        remainingQuestion = 5;
        correctChoices = 0;
        choices = new ArrayList<Word>();
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void loadRandomQuestion(List<Word> words){
        choices.clear();
        Random random = new Random();
        int index;
        for (int i = 0; i < 4; i++){
            index = random.nextInt(words.size());
            choices.add(words.get(index));
        }
        index = random.nextInt(choices.size());
        answer = choices.get(index);
        correctAnswer = index;
        description = answer.getDescription();
    }
    public void chooseAnswer(int key){
        remainingQuestion--;
        if (choices.get(key).getWord().equals(answer.getWord())){
            correctChoices++;
        }
    }
    public Word getAnswer(){
        return answer;
    }
    public List<Word> getChoices(){
        return choices;
    }
    public int getCorrectChoices(){
        return correctChoices;
    }
    public int getRemainingQuestion(){
        return remainingQuestion;
    }
    public String getDescription(){
        return description;
    }
    public void setRemainingQuestion(int remainingQuestion){
        this.remainingQuestion = remainingQuestion;
    }
    public boolean isEndGame(){
        return remainingQuestion == 0;
    }

    public void setCorrectChoices(int i) {
        correctChoices = i;
    }
}
