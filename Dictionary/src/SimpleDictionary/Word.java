package SimpleDictionary;

public class Word {

    private String word_target;
    private String word_explain;

    public Word(String target, String explain) {
        this.word_target = target;
        this.word_explain = explain;
    }

    public Word() {
    }

    public String getWordTarget() {
        return word_target;
    }

    public void setWordTarget(String target) {
        this.word_target = target;
    }

    public String getWordExplain() {
        return word_explain;
    }

    public void setWordExplain(String explain) {
        this.word_explain = explain;
    }

    @Override
    public String toString() {
        return word_target;
    }

}
