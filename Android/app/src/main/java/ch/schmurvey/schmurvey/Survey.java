package ch.schmurvey.schmurvey;

import android.util.Log;
import java.util.ArrayList;

/**
 * Created by Pascal on 03.10.2015.
 */
public class Survey {
    public String surveyName, surveyAuthor;
    public ArrayList<SurveyQuestion> questions;
    public int surveyLength;


    public Survey(String name, String author){
        this.surveyName = name;
        this.surveyAuthor = author;
        this.questions =  new ArrayList<>();
        this.surveyLength = 0;

    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public void setSurveyAuthor(String surveyAuthor) {
        this.surveyAuthor = surveyAuthor;
    }

    public void addQuestion(String SurveyQuestion, String[] answers, boolean multipleChoice){
        SurveyQuestion question = new SurveyQuestion(SurveyQuestion, answers);
        if (multipleChoice){
            Log.d("QUESTION", "MULTIPLE_CHOICE set");
            question.setQuestionType(ApplicationState.QuestionType.MULTIPLE_CHOICE);
        } else {
            Log.d("QUESTION", "SINGLE_CHOICE set");
            question.setQuestionType(ApplicationState.QuestionType.SINGLE_CHOICE);
        }
        this.questions.add(question);
        this.surveyLength++;
    }


}
