package ch.schmurvey;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class RequestStatsMessage implements IMessage {
    final long surveyId;

    public RequestStatsMessage(long surveyId) {
        this.surveyId = surveyId;
    }
}
