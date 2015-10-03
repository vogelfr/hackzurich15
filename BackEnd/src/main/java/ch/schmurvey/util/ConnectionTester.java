package ch.schmurvey.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class ConnectionTester {

    static final int port = 28349;
    static final String server = "40.127.175.48";
    //static final String server = "localhost";

    public static void main(String[] args) {
        ConnectionTester w = new ConnectionTester();
        w.test();
    }

    void test() {
        try {
            Socket s = new Socket(server, port);
            System.out.println(String.format("Opened socket."));
            s.getOutputStream().write(42);
            int r = s.getInputStream().read();
            System.out.println(String.format("Received answer %d.", r));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
