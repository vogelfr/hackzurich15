package ch.schmurvey.schmurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create test Survey
        Survey testSurvey = new Survey("Test Survey", "Mr Testie");
        String[] answer1 = {"Yes", "No", "Not sure"};
        String[] answer2 = {"blue", "not blue", "purple"};
        String[] answer3 = {"multiple choice1", "multiple2 ", "multiple 3"};

        testSurvey.addQuestion("Do you think this works", answer1 , false );
        testSurvey.addQuestion("Favourite colors?", answer2, true);
        testSurvey.addQuestion("Is this a multiple choice?", answer3, true);
        ApplicationState.currentSurvey = testSurvey;
        ApplicationState.setSurveyIndex(0);
        /*
        * end create test survey.
        * */



        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean firstTime = settings.getBoolean("firstTime", true);
        boolean loggedIn = settings.getBoolean("loggedIn", false);

        Log.d("PREFS", "firstTime = " + Boolean.toString(firstTime));
        Log.d("PREFS", "loggedIn = " + Boolean.toString(loggedIn));

        if (!loggedIn) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("loggedIn", loggedIn);
            intent.putExtra("firstTime", firstTime);
            intent.putExtra("prefsName", PREFS_NAME);
            startActivity(intent);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        if (id == R.id.add_survey) {
            //TODO check next question type
            Intent intent;
            if (ApplicationState.getTypeNextQuestion() == ApplicationState.QuestionType.MULTIPLE_CHOICE){
                Log.d("QUESTION", "MultipleCheckActivity will be created.");
                intent = new Intent(this, MultipleCheckActivity.class);
            } else {
                Log.d("QUESTION", "SingleRadioActivity will be created.");
                intent = new Intent(this, SingleRadioActivity.class);
            }

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
