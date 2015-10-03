package ch.schmurvey.schmurvey;

import android.app.Application;

/**
 * Stores current information about Survey and what question user is on.
 */
public class ApplicationState extends Application {
    static int surveyIndex, surveyListLength;
    static String[] testList = {"Survey 1", "Survey 2", "Yet another Survey", "even more options","test", "it goes on..."};

    static public enum QuestionType {SINGLE_CHOICE, MULTIPLE_CHOICE}
    static public QuestionType currentTestType = QuestionType.MULTIPLE_CHOICE;
    static public QuestionType nextTestType = QuestionType.MULTIPLE_CHOICE;


    //static public Type nextType = Type.MULTIPLE_CHOICE;

    //TODO determine type of next Survey element.

    public static void setSurveyIndex(int surveyIndex) {
        ApplicationState.surveyIndex = surveyIndex;
    }

    public static void incrementSurveyIndex(int surveyIndex) {
        ApplicationState.surveyIndex++;
    }

    public static int getSurveyIndex() {
        return surveyIndex;
    }



}
