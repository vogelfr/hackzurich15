package ch.schmurvey.backend;

import ch.schmurvey.common.AnswerOption;
import ch.schmurvey.common.EmptySurvey;
import ch.schmurvey.common.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class CorrelationFinder {

    /**
     * Holds statistical information about one variable (that variable being an answer option).
     */
    class StatInfoPackage {
        final long answerId;
        final double frequency;

        StatInfoPackage(long answerId, double frequency) {
            this.answerId = answerId;
            this.frequency = frequency;
        }

    }

    /**
     *
     * @param surveyId
     * @return Returns the (up to) three most correlated correlations
     */
    static List<Correlation> getCorrelations(long surveyId) {
        EmptySurvey survey = null;
        List<Correlation> correlations = new ArrayList<Correlation>();
        for (Question q1 : survey.questions) {
            for (Question q2 : survey.questions) {
                if (!q1.equals(q2)) {
                    for (AnswerOption ao1 : q1.answerOptions) {
                        for (AnswerOption ao2 : q2.answerOptions) {
                            if (!ao1.equals(ao2)) {
                                correlations.add(calculateCorrelation(ao1, ao2));
                            }
                        }
                    }
                }
            }
        }
        Collections.sort(correlations, Collections.reverseOrder());
        return correlations.subList(0, 3);
    }

    static Correlation calculateCorrelation(AnswerOption answer1, AnswerOption answer2) {
        int n = 1;
        boolean[] a1 = new boolean[n], a2 = new boolean[n];
        double[] freq1 = new double[n], freq2 = new double[n];
        // TODO get from DB
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double d1, d2;
            if (a1[i])
                d1 = 1.0 - freq1[i];
            else
                d1 = -freq1[i];

            if (a2[i])
                d2 = 1.0 - freq2[i];
            else
                d2 = -freq2[i];
            sum += d1 * d2;
        }
        double covariance = sum / n;
        return new Correlation(answer1, answer2, covariance);
    }

}
