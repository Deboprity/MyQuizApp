package com.example.android.myquizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private int number_of_correct_answer = 0;
    private static final String answerFiveText = "Tiger";

    private RadioGroup radioGroup1, radioGroup2, radioGroup3;
    private RadioButton radioOneTwo;
    private RadioButton radioTwoTwo;
    private RadioButton radioThreeFour;
    private CheckBox checkFourOne, checkFourTwo, checkFourThree, checkFourFour, checkFourFive, checkFourSix;
    private EditText editFive;
    private String editFiveString;
    private Button viewScore;

    ScrollView questionLayout;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: Started");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.result_text_view);
        questionLayout = findViewById(R.id.question_layout);
        resultTextView.setVisibility(View.GONE);

        radioGroup1 = findViewById(R.id.radio_group_1);
        radioGroup2 = findViewById(R.id.radio_group_2);
        radioGroup3 = findViewById(R.id.radio_group_3);

        radioOneTwo = findViewById(R.id.radio_one_two);
        radioTwoTwo = findViewById(R.id.radio_two_two);
        radioThreeFour = findViewById(R.id.radio_three_four);

        checkFourOne = findViewById(R.id.check_four_one);
        checkFourTwo = findViewById(R.id.check_four_two);
        checkFourThree = findViewById(R.id.check_four_three);
        checkFourFour = findViewById(R.id.check_four_four);
        checkFourFive = findViewById(R.id.check_four_five);
        checkFourSix = findViewById(R.id.check_four_six);

        viewScore = findViewById(R.id.view_score_button);
        Button resetAnswer = findViewById(R.id.reset_button);

        viewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "viewScore.setOnClickListener: Started");

                int answer1 = radioGroup1.getCheckedRadioButtonId();
                int answer2 = radioGroup2.getCheckedRadioButtonId();
                int answer3 = radioGroup3.getCheckedRadioButtonId();

                editFive = findViewById(R.id.edit_five);
                if(null != editFive && null != editFive.getText()){
                    editFiveString = editFive.getText().toString();
                }

                if(answer1 == -1){
                    Toast.makeText(MainActivity.this, "Please Answer Question 1", Toast.LENGTH_LONG).show();
                    return;
                }else if(answer2 == -1){
                    Toast.makeText(MainActivity.this, "Please Answer Question 2", Toast.LENGTH_LONG).show();
                    return;
                }else if(answer3 == -1){
                    Toast.makeText(MainActivity.this, "Please Answer Question 3", Toast.LENGTH_LONG).show();
                    return;
                }else if(!checkFourOne.isChecked() && !checkFourTwo.isChecked() && !checkFourThree.isChecked() &&
                        !checkFourFour.isChecked() && !checkFourFive.isChecked() && !checkFourSix.isChecked()){
                    Toast.makeText(MainActivity.this, "Please Answer Question 4", Toast.LENGTH_LONG).show();
                    return;
                }else if(null == editFiveString || editFiveString.equals("")){
                    Toast.makeText(MainActivity.this, "Please Answer Question 5", Toast.LENGTH_LONG).show();
                    return;
                }

                if(answer1 == radioOneTwo.getId()){
                    Log.d(TAG, "Answer 1 is correct");
                    number_of_correct_answer += 1;
                }
                if(answer2 == radioTwoTwo.getId()){
                    Log.d(TAG, "Answer 2 is correct");
                    number_of_correct_answer += 1;
                }
                if(answer3 == radioThreeFour.getId()){
                    Log.d(TAG, "Answer 3 is correct");
                    number_of_correct_answer += 1;
                }

                if (checkFourOne.isChecked() && checkFourTwo.isChecked() && !checkFourThree.isChecked() &&
                        !checkFourFour.isChecked() && checkFourFive.isChecked() && checkFourSix.isChecked()) {
                    Log.d(TAG, "Answer 4 is correct");
                    number_of_correct_answer += 1;
                }

                if(editFiveString.equalsIgnoreCase(answerFiveText)) {
                    Log.d(TAG, "Answer 5 is correct");
                    number_of_correct_answer += 1;
                }


                questionLayout.setVisibility(View.GONE);
                resultTextView.setVisibility(View.VISIBLE);
                viewScore.setVisibility(View.GONE);
                resultTextView.setText(getString(R.string.score_text, number_of_correct_answer));

                Log.d(TAG, "viewScore.setOnClickListener: Ended");
            }
        });


        resetAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "resetAnswer.setOnClickListener: started");

                number_of_correct_answer = 0;

                radioGroup1.clearCheck();
                radioGroup2.clearCheck();
                radioGroup3.clearCheck();
                checkFourOne.setChecked(false);
                checkFourTwo.setChecked(false);
                checkFourThree.setChecked(false);
                checkFourFour.setChecked(false);
                checkFourFive.setChecked(false);
                checkFourSix.setChecked(false);
                editFive.setText("");

                questionLayout.setVisibility(View.VISIBLE);
                questionLayout.fullScroll(ScrollView.FOCUS_UP);
                resultTextView.setVisibility(View.GONE);
                viewScore.setVisibility(View.VISIBLE);

                Log.d(TAG, "resetAnswer.setOnClickListener: ended");
            }
        });

        Log.d(TAG, "onCreate: Ended");
    }
}
