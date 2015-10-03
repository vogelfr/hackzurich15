package ch.schmurvey;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class StatsMessage implements IMessage {
    final long surveyId;
    // TODO add field for stats.

    public StatsMessage(long surveyId) {
        this.surveyId = surveyId;
    }
}
