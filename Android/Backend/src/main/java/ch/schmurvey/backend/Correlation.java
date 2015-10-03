package ch.schmurvey.backend;

import ch.schmurvey.common.AnswerOption;
import ch.schmurvey.common.Question;

/**
 * Created by Balz Guenat on 03.10.2015.
 *
 * This class holds information about the correlation of two answer options.
 */
public class Correlation implements Comparable {

    final AnswerOption answer1;
    final AnswerOption answer2;
    final double correlationCoefficient;

    public Correlation(AnswerOption answer1, AnswerOption answer2, double correlationCoefficient) {
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.correlationCoefficient = correlationCoefficient;
    }

    public int compareTo(Object o) {
        if (o != null && o instanceof Correlation) {
            return Double.compare(correlationCoefficient, ((Correlation) o).correlationCoefficient);
        } else
            return -1;
    }
}
