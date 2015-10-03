package ch.schmurvey;

import ch.schmurvey.common.EmptySurvey;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class EmptySurveyMessage implements IMessage {
    final EmptySurvey emptySurvey;
    final long userId;

    public EmptySurveyMessage(EmptySurvey emptySurvey, long userId) {
        this.emptySurvey = emptySurvey;
        this.userId = userId;
    }
}
