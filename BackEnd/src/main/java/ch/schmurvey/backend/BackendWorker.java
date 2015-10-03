package ch.schmurvey.backend;

import ch.schmurvey.RequestSurveyIdsMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class BackendWorker {

    static final int port = 28349;
    static final String dbConnectionString = "" +
            "jdbc:sqlserver://mihbe9kbq4.database.windows.net:1433;" +
            "database=schmurvey;" +
            "user=guenatb@mihbe9kbq4;" +
            "password={your_password_here};" +
            "encrypt=true;" +
            "trustServerCertificate=false;" +
            "hostNameInCertificate=*.database.windows.net;" +
            "loginTimeout=30";

    Connection connection;

    public static void main(String[] args) {
        BackendWorker w = new BackendWorker();
        w.listen();
    }

    public BackendWorker() {
        try {
            connection = DriverManager.getConnection(dbConnectionString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) try { connection.close(); } catch(Exception e) {}
        }
    }

    public void handle() {
        // Declare the JDBC objects.
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement prepsInsertPerson = null;
        PreparedStatement prepsUpdateAge = null;

        try {
            connection = DriverManager.getConnection(dbConnectionString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // Close the connections after the data has been handled.
            if (prepsInsertPerson != null) try { prepsInsertPerson.close(); } catch(Exception e) {}
            if (prepsUpdateAge != null) try { prepsUpdateAge.close(); } catch(Exception e) {}
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
            if (statement != null) try { statement.close(); } catch(Exception e) {}
            if (connection != null) try { connection.close(); } catch(Exception e) {}
        }
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
