package ch.schmurvey.schmurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewSurveyQuestionsActivity extends Activity {

    private Survey survey;
    private int numberOfQuestions;
    private int currentQuestion;
    ArrayList<String> answerList;
    ArrayAdapter<String> adapter;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_survey_questions);

        Intent intent = getIntent();
        survey = (Survey) intent.getSerializableExtra("survey");
        numberOfQuestions = intent.getIntExtra("numberOfQuestions", 1);
        currentQuestion = intent.getIntExtra("currentQuestion", 1);
        answerList = new ArrayList<>(1);
        adapter = new ArrayAdapter<>(this, R.layout.textview_layout, answerList);

        ListView listView = (ListView) findViewById(R.id.listViewAnswers);
        listView.setAdapter(adapter);

        editText = (EditText) findViewById(R.id.answerInput);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_survey_questions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action baacr will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        String answer = editText.getText().toString().toUpperCase();
        editText.setText("");
        if (!answerList.contains(answer)) {
            adapter.add(answer);
        } else {
            Toast.makeText(this, "Answer already contained", Toast.LENGTH_SHORT).show();
        }
    }
}
