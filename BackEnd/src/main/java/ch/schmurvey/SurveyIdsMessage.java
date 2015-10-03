package ch.schmurvey;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class SurveyIdsMessage implements IMessage {

    public final long[] surveyIds;

    public SurveyIdsMessage(long[] surveyIds) {
        this.surveyIds = surveyIds;
    }
}
