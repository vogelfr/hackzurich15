package ch.schmurvey.backend;

import ch.schmurvey.RequestSurveyIdsMessage;
import ch.schmurvey.RequestSurveyMessage;
import ch.schmurvey.common.EmptySurvey;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balz Guenat on 03.10.2015.
 */
public class NetworkWorker {

    static final int port = 28349;
    static final String dbConnectionString = "" +
            "jdbc:sqlserver://mihbe9kbq4.database.windows.net:1433;" +
            "database=schmurvey;" +
            "user=guenatb@mihbe9kbq4;" +
            "password={Schmassw0rd};" +
            "encrypt=true;" +
            "trustServerCertificate=false;" +
            "hostNameInCertificate=*.database.windows.net;" +
            "loginTimeout=30";

    Connection connection;

    public static void main(String[] args) {
        NetworkWorker w = new NetworkWorker();
        w.listen();
    }

    public NetworkWorker() {
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

    public Object handle(Object o) {
        throw new RuntimeException("Unable to handle Object.");
    }

    public List<Long> handle(RequestSurveyIdsMessage rqst) {
        // Declare the JDBC objects.
        Statement statement = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            stmt = connection.prepareStatement("SELECT * FROM survey;");
            resultSet = stmt.executeQuery();
            List<Long> surveyIds = new ArrayList<Long>();
            int max = 3;
            int i = 0;
            while (resultSet.next() && i++ < max) {
                surveyIds.add(resultSet.getLong("survey_id"));
            }
            return surveyIds;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
            if (statement != null) try { statement.close(); } catch(Exception e) {}
        }
        return null;
    }

    public EmptySurvey handle(RequestSurveyMessage rqst) {
        // Declare the JDBC objects.
        Statement statement = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            stmt = connection.prepareStatement("" +
                    "SELECT * FROM survey " +
                    "JOIN question ON survey.survey_id = question.survey_id " +
                    "JOIN answer_option ON question.question_id = answer_option.question_id " +
                    "WHERE survey_id = ?;");
            stmt.setLong(1, rqst.surveyId);
            resultSet = stmt.executeQuery();
            EmptySurvey survey = new EmptySurvey();
            if (resultSet.next())
                return survey;
            else
                throw new RuntimeException("Survey for this ID does not exist.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (stmt != null) try { stmt.close(); } catch(Exception e) {}
            if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}
            if (statement != null) try { statement.close(); } catch(Exception e) {}
        }
        return null;
    }

    void listen() {
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket cs = ss.accept();
            ObjectInputStream ois = new ObjectInputStream(cs.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(cs.getOutputStream());
            handle(ois.readObject());
            int msg = cs.getInputStream().read();
            System.out.println(String.format("Received value %d.", msg));
            cs.getOutputStream().write(msg);
            cs.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
