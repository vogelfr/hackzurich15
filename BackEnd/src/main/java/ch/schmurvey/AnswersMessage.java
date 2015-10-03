package ch.schmurvey;

import ch.schmurvey.common.QuestionAnswer;

import java.util.List;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class AnswersMessage implements IMessage {
    final long surveyId;
    final List<QuestionAnswer> answers;

    public AnswersMessage(long surveyId, List<QuestionAnswer> answers) {
        this.surveyId = surveyId;
        this.answers = answers;
    }
}
