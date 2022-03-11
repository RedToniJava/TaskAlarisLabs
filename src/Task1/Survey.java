package Task1;

public class Survey {

    private int number;
    private String question;
    private String[] answers;

    public Survey(int number, String question, String[] answers) {
        this.answers = answers;
        this.number = number;
        this.question = question;

    }

    public int getNumber() {
        return number;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }


}
