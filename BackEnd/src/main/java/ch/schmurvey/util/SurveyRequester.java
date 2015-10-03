package ch.schmurvey.util;

import ch.schmurvey.EmptySurveyMessage;
import ch.schmurvey.RequestSurveyIdsMessage;
import ch.schmurvey.RequestSurveyMessage;
import ch.schmurvey.SurveyIdsMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class SurveyRequester {

    static final int port = 28349;
    static final String server = "40.127.175.48";

    public static void main(String[] args) {
        SurveyRequester w = new SurveyRequester();
        w.test();
    }

    void test() {
        try {
            Socket s = new Socket(server, port);
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            oos.writeObject(new RequestSurveyIdsMessage());
            SurveyIdsMessage ids = (SurveyIdsMessage) ois.readObject();
            for (long id : ids.surveyIds) {
                oos.writeObject(new RequestSurveyMessage(id));
                EmptySurveyMessage surveyMessage = (EmptySurveyMessage) ois.readObject();
                System.out.print(surveyMessage.emptySurvey.toString());
            }
            ois.close();
            oos.close();
            s.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
