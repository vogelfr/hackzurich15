package ch.schmurvey.schmurvey;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static ch.schmurvey.schmurvey.ApplicationState.surveyListLength;
import static ch.schmurvey.schmurvey.ApplicationState.getSurveyIndex;
import static ch.schmurvey.schmurvey.ApplicationState.getTypeNextQuestion;

public class MultipleCheckActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_survey_multiple_check);

        TextView textview = (TextView) findViewById(R.id.single_survey_multiple_choice);
        textview.setText(ApplicationState.getCurrentQuestion().getQuestion());

        String[] currentAnswers = ApplicationState.currentSurvey.questions.get(getSurveyIndex()).getAnswers();
        ArrayAdapter myArrayAdapter = new ArrayAdapter(MultipleCheckActivity.this, android.R.layout.simple_list_item_multiple_choice, currentAnswers);

        setListAdapter(myArrayAdapter);
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String clickedElement = ApplicationState.currentSurvey.questions.get(ApplicationState.getSurveyIndex()).getAnswers()[position];
        Toast.makeText(this, clickedElement+ " has been clicked.", Toast.LENGTH_SHORT).show();

        //TODO save state
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ApplicationState.setSurveyIndex(ApplicationState.getSurveyIndex() - 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_survey_multiple_check, menu);
        //hide menu
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void submitButtonClicked (View view) {

        Log.d("QUESTION", "surveyIndex = " + String.valueOf(getSurveyIndex()));
        // TODO save sate
        if (getSurveyIndex() + 1 != surveyListLength) { //next question exists.
            if (getTypeNextQuestion() == ApplicationState.QuestionType.SINGLE_CHOICE ){
                Log.d("QUESTION", "Next question is of type " + String.valueOf(ApplicationState.QuestionType.SINGLE_CHOICE));
                ApplicationState.incrementSurveyIndex();
                Intent intent = new Intent(this, SingleRadioActivity.class);
                startActivity(intent);
            } else if (getTypeNextQuestion() == ApplicationState.QuestionType.MULTIPLE_CHOICE) {
                Log.d("QUESTION", "Next question is of type " + String.valueOf(ApplicationState.QuestionType.SINGLE_CHOICE));
                ApplicationState.incrementSurveyIndex();
                Intent intent = new Intent(this, MultipleCheckActivity.class);
                startActivity(intent);
            }
        } else { //no more question.
            //TODO result screen after final question?
            Toast.makeText(this, "no more questions", Toast.LENGTH_SHORT).show();
        }
    }
}
