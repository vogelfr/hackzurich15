package ch.schmurvey.schmurvey;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pascal on 03.10.2015.
 */
public class Survey {
    public String SurveyName, SurveyAuthor;
    public ArrayList<SurveyQuestion> questions;


    public Survey(String name, String author){
        this.SurveyName = name;
        this.SurveyAuthor = author;
        this.questions =  new ArrayList<>();

    }

    public void setSurveyName(String surveyName) {
        SurveyName = surveyName;
    }

    public void setSurveyAuthor(String surveyAuthor) {
        SurveyAuthor = surveyAuthor;
    }

    public void addQuestion(String SurveyQuestion, String[] answers, boolean MULTIPLE_CHOICE){
        SurveyQuestion question = new SurveyQuestion(SurveyQuestion, answers);
        if (MULTIPLE_CHOICE){
            question.setQuestionType(ApplicationState.QuestionType.MULTIPLE_CHOICE);
        } else {
            question.setQuestionType(ApplicationState.QuestionType.SINGLE_CHOICE);
        }
        this.questions.add(question);
    }


}
