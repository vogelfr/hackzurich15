package ch.schmurvey.schmurvey;

import java.io.Serializable;

/**
 * Created by Pascal on 03.10.2015.
 */
public class SurveyQuestion implements Serializable {
    public String question;
    public String[] answers;
    public ApplicationState.QuestionType questionType;
    public boolean questionTypeBool;

    public SurveyQuestion(String question, String[] answers){
        this.question = question;
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
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }
}
