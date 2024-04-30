package UI_Dictionary;

import javax.speech.synthesis.Synthesizer;

public class Word {
    private int id;
    private String word;
    private String html;
    private String pronounce;
    private String description;

    // Constructors
    public Word() {
    }

    public Word(int id, String word, String html, String pronounce, String description) {
        this.id = id;
        this.word = word;
        this.html = html;
        this.pronounce = pronounce;
        this.description = description;
    }
    
    public Word(String word, String html){
        this.word = word;
        this.html = html;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void playPronunciation(Synthesizer synthesizer){
        synthesizer.speakPlainText(word, null);
        try {
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // toString() method for debugging purposes
    @Override
    public String toString() {
        return word;
    }
}
