package ch.schmurvey.common;

import java.util.List;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class Question {
    long questionId;
    long surveyId;
    EmptySurvey emptySurvey;
    String questionText;
    List<AnswerOption> answerOptions;
}
