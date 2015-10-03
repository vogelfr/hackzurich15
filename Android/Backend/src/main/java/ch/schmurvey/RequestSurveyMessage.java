package ch.schmurvey;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class RequestSurveyMessage implements IMessage {
    public final long surveyId;

    public RequestSurveyMessage(long surveyId) {
        this.surveyId = surveyId;
    }
}
