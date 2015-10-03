package ch.schmurvey.common;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class QuestionAnswer {
    final Question question;
    final String answerText;

    public QuestionAnswer(Question question, String answerText) {
        this.question = question;
        this.answerText = answerText;
    }
}
