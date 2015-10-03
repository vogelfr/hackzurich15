package ch.schmurvey.schmurvey;


/**
 * Created by Pascal on 03.10.2015.
 */
public class SurveyQuestion {
    public String Question;
    public String[] answers;
    public ApplicationState.QuestionType questionType;


    public SurveyQuestion(String question, String[] answers){
        this.Question = question;
        this.answers = answers;
    }



    public void setQuestionType(ApplicationState.QuestionType questionType) {
        this.questionType = questionType;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;

    }

    public ApplicationState.QuestionType getQuestionType() {
        return questionType;
    }

    public String getQuestion() {
        return Question;
    }

    public String[] getAnswers() {
        return answers;
    }
}
