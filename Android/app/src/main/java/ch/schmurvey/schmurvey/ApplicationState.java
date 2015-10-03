package ch.schmurvey.schmurvey;

import android.app.Application;

/**
 * Stores current information about Survey and what question user is on.
 */
public class ApplicationState extends Application {
    static int SurveyIndex, SurveyListLength;


    static public enum QuestionType {SINGLE_CHOICE, MULTIPLE_CHOICE}
    static public QuestionType currentTestType = QuestionType.MULTIPLE_CHOICE;


    static public Survey currentSurvey;



    public static void setSurveyIndex(int surveyIndex) {
        ApplicationState.SurveyIndex = surveyIndex;
    }

    public static SurveyQuestion getCurrentQuestion(){

        return currentSurvey.questions.get(getSurveyIndex());
    }

    public static void incrementSurveyIndex() {
        ApplicationState.SurveyIndex++;
    }

    public static int getSurveyIndex() {
        return SurveyIndex;
    }

    //returns type of next question, null if end of list.
    public static QuestionType getTypeNextQuestion(){
        if (SurveyIndex == 0) { //first question
            return currentSurvey.questions.get(0).getQuestionType();
        } else {
            if (SurveyIndex + 1 < SurveyListLength){
                //next element exists
                return currentSurvey.questions.get(SurveyIndex +1).getQuestionType();
            } else {
                return null;
            }
        }

    }


}
