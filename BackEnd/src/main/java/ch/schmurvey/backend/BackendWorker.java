package ch.schmurvey.backend;

import ch.schmurvey.RequestSurveyIdsMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class BackendWorker {

    static final int port = 28349;

    public static void main(String[] args) {
        BackendWorker w = new BackendWorker();
        w.listen();
    }

    void listen() {
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket cs = ss.accept();
            int msg = cs.getInputStream().read();
            System.out.println(String.format("Received value %d.", msg));
            cs.getOutputStream().write(msg);
            cs.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
