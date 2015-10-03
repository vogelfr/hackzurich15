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

public class MultipleCheckActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_survey_multiple_check);


        ArrayAdapter myArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, ApplicationState.testList);
        setListAdapter(myArrayAdapter);
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);




        String clickedElement = ApplicationState.testList[position];
        Toast.makeText(this, clickedElement + " clicked. Type: " + ApplicationState.currentTestType, Toast.LENGTH_SHORT).show();

        //TODO save state
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_survey_multiple_check, menu);
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
        Intent intent = new Intent(this, MultipleCheckActivity.class);
        startActivity(intent);
        //TODO start single or multiple choice.
    }
}
