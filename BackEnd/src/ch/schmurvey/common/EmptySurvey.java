package ch.schmurvey.common;

import java.util.List;
import java.util.Map;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class EmptySurvey {
    long surveyId;
    User submitter;
    List<Question> questions;
    /**
     * Is null if survey is not filled out.
     */
    Map<Question, QuestionAnswer> answers;
}
