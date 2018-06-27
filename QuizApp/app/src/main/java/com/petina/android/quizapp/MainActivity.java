package com.petina.android.quizapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    int curIndex = 1;
    int totalCorrect = 0;
    Object[] answerKey = new Object[10];
    Object[] answers = new Object[10];

    CheckBox myA1CheckBox;
    CheckBox myA2CheckBox;
    CheckBox myA3CheckBox;
    CheckBox myA4CheckBox;

    RadioGroup q2RadioGroup;
    RadioGroup q4RadioGroup;
    RadioGroup q5RadioGroup;
    RadioGroup q6RadioGroup;
    RadioGroup q7RadioGroup;
    RadioGroup q8RadioGroup;
    RadioGroup q9RadioGroup;
    RadioGroup q10RadioGroup;

    LinearLayout q1View;
    LinearLayout q2View;
    LinearLayout q3View;
    LinearLayout q4View;
    LinearLayout q5View;
    LinearLayout q6View;
    LinearLayout q7View;
    LinearLayout q8View;
    LinearLayout q9View;
    LinearLayout q10View;

    EditText myEditQuestion;
    HashMap<Integer, RadioGroup> RGMap = new HashMap<Integer, RadioGroup>();
    HashMap<Integer, LinearLayout> LLView = new HashMap<Integer, LinearLayout>();

    Button nextButton;
    Button resetButton;
    Button previousButton;
    Button checkScoreButton;

    TextView myTitleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateFieldObjects();
        repaintButton(curIndex);
        initializeAnswer();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("curIndex", curIndex);
        savedInstanceState.putSerializable("answers", answers);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        disableView(curIndex);
        curIndex = savedInstanceState.getInt("curIndex");
        answers = (Object[]) savedInstanceState.getSerializable("answers");
        populateFieldObjects();
        initializeAnswer();
        updateTitle(curIndex);
        enableView(curIndex);
        repaintButton(curIndex);
        for (int index = 0; index < answers.length; index++) {
            if (answers[index] != null) {
                correctQuiz(index + 1);
            }
        }
    }

    /**
     * This function takes all the UI variables declared at global level and reference them to the mapped UI items and put them into the hash table.
     * This function gets called whenever the instance state gets initialized/restored.
     */
    private void populateFieldObjects() {
        q1View = findViewById(R.id.question_1_view_id);
        q2View = findViewById(R.id.question_2_view_id);
        q3View = findViewById(R.id.question_3_view_id);
        q4View = findViewById(R.id.question_4_view_id);
        q5View = findViewById(R.id.question_5_view_id);
        q6View = findViewById(R.id.question_6_view_id);
        q7View = findViewById(R.id.question_7_view_id);
        q8View = findViewById(R.id.question_8_view_id);
        q9View = findViewById(R.id.question_9_view_id);
        q10View = findViewById(R.id.question_10_view_id);

        nextButton = findViewById(R.id.next_button);
        resetButton = findViewById(R.id.reset_quiz);
        previousButton = findViewById(R.id.previous_button);
        checkScoreButton = findViewById(R.id.check_score_button);

        myA1CheckBox = findViewById(R.id.q1_check1);
        myA2CheckBox = findViewById(R.id.q1_check2);
        myA3CheckBox = findViewById(R.id.q1_check3);
        myA4CheckBox = findViewById(R.id.q1_check4);

        myEditQuestion = findViewById(R.id.question_3_answer);

        myTitleView = findViewById(R.id.got_title);

        q2RadioGroup = findViewById(R.id.question_2_radio_group);
        q4RadioGroup = findViewById(R.id.question_4_radio_group);
        q5RadioGroup = findViewById(R.id.question_5_radio_group);
        q6RadioGroup = findViewById(R.id.question_6_radio_group);
        q7RadioGroup = findViewById(R.id.question_7_radio_group);
        q8RadioGroup = findViewById(R.id.question_8_radio_group);
        q9RadioGroup = findViewById(R.id.question_9_radio_group);
        q10RadioGroup = findViewById(R.id.question_10_radio_group);

        RGMap.put(2, q2RadioGroup);
        RGMap.put(4, q4RadioGroup);
        RGMap.put(5, q5RadioGroup);
        RGMap.put(6, q6RadioGroup);
        RGMap.put(7, q7RadioGroup);
        RGMap.put(8, q8RadioGroup);
        RGMap.put(9, q9RadioGroup);
        RGMap.put(10, q10RadioGroup);

        LLView.put(1, q1View);
        LLView.put(2, q2View);
        LLView.put(3, q3View);
        LLView.put(4, q4View);
        LLView.put(5, q5View);
        LLView.put(6, q6View);
        LLView.put(7, q7View);
        LLView.put(8, q8View);
        LLView.put(9, q9View);
        LLView.put(10, q10View);
    }

    /**
     * Reinitialize the answer and reset answer list states.
     */
    private void initializeAnswer() {
        answerKey[0] = new String("1|2||4");
        answerKey[1] = new Integer(1);
        answerKey[2] = new String("killed");
        answerKey[3] = new Integer(4);
        answerKey[4] = new Integer(1);
        answerKey[5] = new Integer(1);
        answerKey[6] = new Integer(5);
        answerKey[7] = new Integer(1);
        answerKey[8] = new Integer(2);
        answerKey[9] = new Integer(3);
    }

    /**
     * Dictates the state (visibility and whether they should be enabled or disabled) of each button at a given question and
     *
     * @param questionId is really the question id for the trivia question
     */
    private void repaintButton(int questionId) {
        switch (questionId) {
            case 1:
                nextButton.setVisibility(View.VISIBLE);
                nextButton.setEnabled(true);
                previousButton.setVisibility(View.VISIBLE);
                previousButton.setEnabled(false);
                checkScoreButton.setVisibility(View.GONE);
                resetButton.setVisibility(View.GONE);
                break;
            case 10:
                nextButton.setVisibility(View.GONE);
                nextButton.setEnabled(false);
                resetButton.setVisibility(View.GONE);
                checkScoreButton.setVisibility(View.VISIBLE);
                break;
            default:
                nextButton.setVisibility(View.VISIBLE);
                nextButton.setEnabled(true);
                previousButton.setVisibility(View.VISIBLE);
                previousButton.setEnabled(true);
                checkScoreButton.setVisibility(View.GONE);
                resetButton.setVisibility(View.GONE);
        }
    }

    /**
     * Clears out the radio button state
     *
     * @param questionId is really the question id for the trivia question
     */
    private void clearRadioGroup(int questionId) {
        RadioGroup radioGroup = RGMap.get(questionId);
        radioGroup.clearCheck();
    }

    /**
     * pop up toast msg for android with default positioning.
     *
     * @param msg message to display in the toast
     */
    private void popToast(String msg) {
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * turn the trivia question back to the new state
     *
     * @param v is the view object passed in from the UI
     */
    public void resetQuiz(View v) {
        disableView(curIndex);
        curIndex = 1;
        totalCorrect = 0;
        //empty answer array
        Arrays.fill(answers, null);
        for (int i = 1; i < answerKey.length; i++) {
            if (i != 2)
                clearRadioGroup(i + 1);
        }
        myEditQuestion.setText("");
        initializeAnswer();
        repaintButton(curIndex);
        enableView(curIndex);
        updateTitle(curIndex);
        for (int index = 0; index < 10; index++) {
            clearBackgroundColor(index + 1);
        }


    }

    /**
     * reset the checkbox to unchecked if it's checked.
     * @param vMyCheckBox is the variable that referenced to the passed in checkbox
     */
    private void resetCheckBox(CheckBox vMyCheckBox) {
        vMyCheckBox.setBackgroundColor(Color.TRANSPARENT);
        if (vMyCheckBox.isChecked())
            vMyCheckBox.setChecked(false);
    }

    /**
     * Go through all the radio button giving a group id and clear the background state back to transparent
     *
     * @param questionId question index in the trivia
     */
    private void clearBackgroundColor(int questionId) {
        if (questionId == 1) {
            resetCheckBox(myA1CheckBox);
            resetCheckBox(myA2CheckBox);
            resetCheckBox(myA3CheckBox);
            resetCheckBox(myA4CheckBox);

        } else if (questionId != 3) {
            RadioGroup radioGroup = RGMap.get(questionId);
            int childList = radioGroup.getChildCount();
            for (int i = 0; i < childList; i++) {
                View myChildView = radioGroup.getChildAt(i);
                if (myChildView instanceof RadioButton) {
                    myChildView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        } else {
            myEditQuestion.setBackgroundColor(Color.TRANSPARENT);
        }

    }

    /**
     * update the title on the screen
     * @param currentQuestion current question index
     */
    private void updateTitle(int currentQuestion) {
        String myTitle = getString(R.string.current_question, Integer.toString(currentQuestion));
        myTitleView.setText(myTitle);
    }

    /**
     * enable view group
     * @param questionId current question id
     */
    private void enableView(int questionId) {
        LinearLayout myCurrentView = LLView.get(questionId);
        myCurrentView.setVisibility(View.VISIBLE);
    }

    /**
     * disable view group
      * @param questionId current question id
     */
    private void disableView(int questionId) {
        LinearLayout myCurrentView = LLView.get(questionId);
        myCurrentView.setVisibility(View.GONE);
    }

    /**
     * Collect return Q1 answer into string
     */
    private void paintCheckBoxBackground(CheckBox vCheckBox, boolean colorFlag) {
        if (colorFlag)
            vCheckBox.setBackgroundColor(Color.GREEN);
        else
            vCheckBox.setBackgroundColor(Color.RED);

    }

    /**
     * turn the answer of question 1 (checkboxes) into a string answer by concactenation and separated by pipe char.
     */
    private String transformQ1Answer() {
        String output = "";
        output = output + ((myA1CheckBox.isChecked()) ? "1" : "");
        output = output + (output != "" ? "|" : "") + ((myA2CheckBox.isChecked()) ? "2" : "");
        output = output + (output != "" ? "|" : "") + ((myA3CheckBox.isChecked()) ? "3" : "");
        output = output + (output != "" ? "|" : "") + ((myA4CheckBox.isChecked()) ? "4" : "");
        return output;

    }

    /**
     * turn the answer of question 1 (checkboxes) into a string answer by concactenation and separated by pipe char.
     * @param q1Answer is the concatened answer from question 1.
     */
    private void repaintQ1Background(String q1Answer) {
        if (q1Answer.indexOf("1") >= 0)
            paintCheckBoxBackground(myA1CheckBox, true);
        else {
            paintCheckBoxBackground(myA1CheckBox, false);
        }
        if (q1Answer.indexOf("2") >= 0)
            paintCheckBoxBackground(myA2CheckBox, true);
        else {
            paintCheckBoxBackground(myA2CheckBox, false);
        }
        if (q1Answer.indexOf("3") >= 0)
            paintCheckBoxBackground(myA3CheckBox, false);

        if (q1Answer.indexOf("4") >= 0)
            paintCheckBoxBackground(myA4CheckBox, true);
        else {
            paintCheckBoxBackground(myA4CheckBox, false);
        }
    }

    /**
     * check if at a radio button's checked given a group
     * @param questionIndex question index
     */
    private boolean validateRadioCheck(int questionIndex) {
        RadioGroup radioGroup = RGMap.get(questionIndex);
        if (radioGroup.getCheckedRadioButtonId() != -1) {
            return true;
        } else
            return false;
    }

    /**
     * This handles the sequence of events when the next button's clicked
     * @param v the view that trigger the action
     */
    public void clickNextButton(View v) {
        boolean proceedFlag = false;
        if (curIndex == 1) {
            if (transformQ1Answer().equals("")) {
                String myMSg = getString(R.string.radio_validation);
                popToast(myMSg);
            } else
                proceedFlag = true;
        } else if (curIndex != 3) {
            if (validateRadioCheck(curIndex)) {
                proceedFlag = true;
            } else {
                String myMSg = getString(R.string.radio_validation);
                popToast(myMSg);
            }
        } else {
            String myInput = myEditQuestion.getText().toString().trim();
            if (myInput.equals("")) {
                String myMSg = getString(R.string.edit_text_validation);
                popToast(myMSg);
            } else {
                proceedFlag = true;
            }
        }
        if (proceedFlag) {
            if (curIndex < 10) {
                //clean up previous state
                correctQuiz(curIndex);
                //setting up new state
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 2 seconds
                        disableView(curIndex);
                        curIndex += 1;
                        enableView(curIndex);
                        updateTitle(curIndex);
                        repaintButton(curIndex);
                    }
                }, 1000);


            }
        }
    }

    /**
     * This handles the sequence of events when the previous button's clicked
     * @param v the view that trigger the action
     */
    public void clickPreviousButton(View v) {
        if (curIndex > 1) {
            //clean up previous state
            disableView(curIndex);
            //setting up new state
            curIndex -= 1;
            enableView(curIndex);
            updateTitle(curIndex);
        }
        repaintButton(curIndex);
    }

    /**
     * This function will calculate the result of the quiz
     * @param v the view that trigger the action
     */
    public void checkScore(View v) {
        if (!validateRadioCheck(curIndex)) {
            String myMSg = getString(R.string.radio_validation);
            popToast(myMSg);
        } else {
            totalCorrect = 0;
            correctQuiz(curIndex);
            for (int index = 0; index < answerKey.length; index++) {

                if (answers[index].toString().equals(answerKey[index].toString()))
                    totalCorrect += 1;
            }
            String myMSg = getString(R.string.final_answer, Integer.toString(totalCorrect));
            popToast(myMSg);
            resetButton.setVisibility(View.VISIBLE);
            checkScoreButton.setVisibility(View.GONE);
            nextButton.setVisibility(View.GONE);
            previousButton.setVisibility(View.GONE);
        }
    }

    /**
     * This function handles the sequece of events when calculate event
     * @param questionId determines the current to correct
     */
    public void correctQuiz(int questionId) {

        //need to store answer and check answer
        if (questionId == 1) {
            String myQ1Answer = transformQ1Answer();
            answers[questionId - 1] = myQ1Answer;
            repaintQ1Background(myQ1Answer);

        } else if (questionId != 3) {
            clearBackgroundColor(questionId);
            RadioGroup radioGroup = RGMap.get(questionId);
            int checkedRadioId = radioGroup.getCheckedRadioButtonId();
            RadioButton myCheckedRadio = (RadioButton) findViewById(checkedRadioId);
            int myCheckedIndex = radioGroup.indexOfChild(myCheckedRadio);
            int myKey = Integer.valueOf(answerKey[questionId - 1].toString());
            if (myKey == (myCheckedIndex + 1))
                myCheckedRadio.setBackgroundColor(Color.GREEN);
            else
                myCheckedRadio.setBackgroundColor(Color.RED);
            answers[questionId - 1] = myCheckedIndex + 1;
        } else {
            clearBackgroundColor(questionId);
            String myAnswer = myEditQuestion.getText().toString().trim().toLowerCase();
            String myKey = answerKey[questionId - 1].toString();
            if (myKey.equals(myAnswer)) {
                myEditQuestion.setBackgroundColor(Color.GREEN);
            } else
                myEditQuestion.setBackgroundColor(Color.RED);
            answers[questionId - 1] = myAnswer;
        }
    }
}
