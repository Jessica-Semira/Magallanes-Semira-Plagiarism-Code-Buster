package CodeBusterClasses;

public class PairScore {
    //this class shall contain the similarity score and its correpsonind name of pair;

    private float score;
    private String pair1Name;
    private String pair2Name;

    public PairScore(float score, String pair1, String pair2){

        this.score = score;
        this.pair1Name = pair1;
        this.pair2Name = pair2;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getScore() {
        return score;
    }

    public String getPair1Name() {
        return pair1Name;
    }

    public String getPair2Name() {
        return pair2Name;
    }

    public Boolean IsInPair(String name){
        //it will check if the inputted name contains the names in object
        return pair1Name.compareTo(name) == 0 || pair2Name.compareTo(name) == 0;
    }
}
