package ch.schmurvey.schmurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewSurveyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int spinnerSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_survey);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers_short, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_survey, menu);
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

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        spinnerSelected = parent.getSelectedItemPosition();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        spinnerSelected = 1;
    }

    public void submitButtonClicked (View view) {
        EditText title = (EditText) findViewById(R.id.newSurveyTitle);

        //TODO: Fill in real username
        Survey survey = new Survey(title.getText().toString(), "Username");

        Intent intent = new Intent(this, NewSurveyQuestionsActivity.class);
        intent.putExtra("numberOfQuestions", spinnerSelected+1);
        Log.d("QUESTION", String.valueOf(spinnerSelected));
        intent.putExtra("survey", survey);
        intent.putExtra("currentQuestion", 0);
        startActivity(intent);
    }
}
