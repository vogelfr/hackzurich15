package ch.schmurvey.schmurvey;

import android.app.Application;
import android.util.Log;

/**
 * Stores current information about Survey and what question user is on.
 */
public class ApplicationState extends Application {
    private static int surveyIndex;
    static int surveyListLength;
    //TODO set length when reading data in.

    static public enum QuestionType {SINGLE_CHOICE, MULTIPLE_CHOICE}
    static public QuestionType currentTestType = QuestionType.MULTIPLE_CHOICE;
    static public boolean questionTypeMultipleChoice = true;


    static public Survey currentSurvey;


    public static void setSurveyIndex(int surveyIndex) {
        ApplicationState.surveyIndex = surveyIndex;
    }

    public static SurveyQuestion getCurrentQuestion(){

        return currentSurvey.questions.get(getSurveyIndex());
    }

    public static void incrementSurveyIndex() {
        ApplicationState.surveyIndex++;
    }

    //decement current Question index, always bigger than 0
    public static void decrementSurveyIndex(){
        ApplicationState.surveyIndex--;
        if(surveyIndex < 0){
            surveyIndex = 0;
        }
    }

    public static int getSurveyIndex() {
        return surveyIndex;
    }

    //returns type of next question, null if end of list.
    public static QuestionType getTypeNextQuestion(){
        if (surveyIndex == 0) { //first question
            return currentSurvey.questions.get(0).getQuestionType();
        } else {
            if (surveyIndex + 1 < surveyListLength){//next element exists

                return currentSurvey.questions.get(surveyIndex +1).getQuestionType();
            } else {
                return null;
            }
        }
    }

}
