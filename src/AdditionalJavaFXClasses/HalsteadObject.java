package AdditionalJavaFXClasses;

public class HalsteadObject {

    private String filename;
    private Integer programLength;
    private Integer programVocabulary;
    private Float programDifficulty;

    public HalsteadObject(String filename, Integer programLength, Integer Vocabulary, Float programDifficulty){
        this.filename = filename;
        this.programDifficulty = programDifficulty;
        this.programVocabulary = Vocabulary;
        this.programLength = programLength;
    }

    public String getFilename() {
        return filename;
    }

    public Float getProgramDifficulty() {
        return programDifficulty;
    }

    public Integer getProgramLength() {
        return programLength;
    }

    public Integer getProgramVocabulary() {
        return programVocabulary;
    }
}
