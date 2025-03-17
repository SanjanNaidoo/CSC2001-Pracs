/**
 * Represents a statement in the knowledge base.
 * Statement consists of a term, a descriptive sentence about the term, and the confidence of the statement.
 */
public class Statement {
    public String term;
    public String sentence;
    public double confidence;

    public Statement(String term, String sentence, double confidence) {
        this.term = term;
        this.sentence = sentence;
        this.confidence = confidence;
    }
}
