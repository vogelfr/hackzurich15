package ch.schmurvey.schmurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class NewSurveyQuestionsActivity extends Activity {

    private Survey survey;
    private int numberOfQuestions;
    private int currentQuestion;
    ArrayList<String> answerList;
    ArrayAdapter<String> adapter;
    EditText answerInput;
    EditText questionInput;
    RadioButton multipleChoice;
    RadioButton singleChoice;
    RadioGroup radioGroup;

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

        answerInput = (EditText) findViewById(R.id.answerInput);
        questionInput = (EditText) findViewById(R.id.questionInput);
        multipleChoice = (RadioButton) findViewById(R.id.multipleChoiceButton);
        singleChoice = (RadioButton) findViewById(R.id.singleChoiceButton);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

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

    public void onAddQuestionClick(View view) {
        String answer = answerInput.getText().toString().toUpperCase();
        answerInput.setText("");
        if (!answerList.contains(answer)) {
            adapter.add(answer);
        } else {
            Toast.makeText(this, "Answer already contained", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSubmitClick(View view) {
        String question = questionInput.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if (question == "") {
            questionInput.setError("Required field");
            focusView = questionInput;
            cancel = true;
        }

        if (answerList.isEmpty()) {
            answerInput.setError("Answers needed");
            focusView = answerInput;
            cancel = true;
        }

        if (radioGroup.getCheckedRadioButtonId() == -1) {
            singleChoice.setError("Required input");
            focusView = radioGroup;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            survey.addQuestion(question, (String[]) answerList.toArray(), multipleChoice.isChecked());
            currentQuestion++;
            Intent intent;
            if (currentQuestion < numberOfQuestions) {
                Toast.makeText(this, "Question submitted", Toast.LENGTH_SHORT);
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Question submitted", Toast.LENGTH_SHORT);
                intent = new Intent(this, NewSurveyQuestionsActivity.class);
                intent.putExtra("numberOfQuestions", numberOfQuestions);
                intent.putExtra("currentQuestion", currentQuestion);
                intent.putExtra("survey", survey);
                startActivity(intent);
            }
        }
    }
}
