package ch.schmurvey.schmurvey;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static ch.schmurvey.schmurvey.ApplicationState.*;

public class SingleRadioActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_survey);

        //initialize List and add items

        ArrayAdapter myArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, testList);
        setListAdapter(myArrayAdapter);

        final ListView listview = getListView();
        listview.setItemsCanFocus(false);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);




    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String clickedElement = testList[position];
        Toast.makeText(SingleRadioActivity.this, clickedElement+ " has been clicked.", Toast.LENGTH_SHORT).show();

        //// TODO save sate
        if (getSurveyIndex() + 1 != surveyListLength) { //next question exists.
            if (nextTestType == QuestionType.SINGLE_CHOICE ){
                Intent intent = new Intent(this, SingleRadioActivity.class);
                startActivity(intent);
            } else if (nextTestType == QuestionType.MULTIPLE_CHOICE) {
                Intent intent = new Intent(this, MultipleCheckActivity.class);
                startActivity(intent);
            }
        } else { //no more question.
            //TODO result screen after final question?
            Toast.makeText(SingleRadioActivity.this, "no more questions", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_single_survey, menu);

        //hide
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
}