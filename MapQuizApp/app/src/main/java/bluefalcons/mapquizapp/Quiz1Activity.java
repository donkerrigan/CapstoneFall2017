package bluefalcons.mapquizapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Quiz1Activity extends AppCompatActivity {


    private String outputQuizDoc;
    private String quizDoc;
    private String userDoc;
    private String outputUserDoc;
    private User uUser;
    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            quizDoc = bundle.getString("moved_quiz");
            outputQuizDoc = quizDoc;
            userDoc = bundle.getString("moved_user");
            uUser = JavaJsonConverter.ConvertJsonToJavaUser(userDoc);
        }
        Quizzes quiz;
        if (quizDoc != null)
        {
            quiz = JavaJsonConverter.ConvertJsonToJavaQuiz(quizDoc);
            Log.i("Final Output: ", quiz.toString());

            final Quizzes inputQuiz = quiz;

            Questions[] inputQuestions = (inputQuiz.questions);
            final Questions question = inputQuestions[0];

            QuestionOptions[] qOptions = question.options;
            QuestionOptions option0 = qOptions[0];
            QuestionOptions option1 = qOptions[1];
            QuestionOptions option2 = qOptions[2];
            QuestionOptions option3 = qOptions[3];

            TextView quizTitle = (TextView) findViewById(R.id.tvQuizTitle);
            quizTitle.setText(quiz.title);

            TextView questionQuestionText = (TextView) findViewById(R.id.tvQuestionText);
            questionQuestionText.setText(question.questionText);

            final TextView questionExplanationText = (TextView) findViewById(R.id.tvExplanationText);

            final Button continueButton = (Button) findViewById(R.id.bEndQuestion1);
            continueButton.setEnabled(false);

            final Button option0Button = (Button) findViewById(R.id.bOption0);
            option0Button.setText(option0.optionText);

            final Button option1Button = (Button) findViewById(R.id.bOption1);
            option1Button.setText(option1.optionText);

            final Button option2Button = (Button) findViewById(R.id.bOption2);
            option2Button.setText(option2.optionText);

            final Button option3Button = (Button) findViewById(R.id.bOption3);
            option3Button.setText(option3.optionText);


                option0Button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (question.answer == 0) {
                        option0Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                        points = 10;
                    } else if (question.answer == 1) {
                        option0Button.setBackgroundColor(Color.RED);
                        option1Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else if (question.answer == 2) {
                        option0Button.setBackgroundColor(Color.RED);
                        option2Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else if (question.answer == 3) {
                        option0Button.setBackgroundColor(Color.RED);
                        option3Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else {
                        Log.i("Question as String: ", question.toString());
                    }


                }

                });

            option1Button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (question.answer == 0) {
                        option0Button.setBackgroundColor(Color.GREEN);
                        option1Button.setBackgroundColor(Color.RED);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else if (question.answer == 1) {
                        option1Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                        points = 10;
                    } else if (question.answer == 2) {
                        option1Button.setBackgroundColor(Color.RED);
                        option2Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else if (question.answer == 3) {
                        option1Button.setBackgroundColor(Color.RED);
                        option3Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else {
                        Log.i("Question as String: ", question.toString());
                    }


                }

            });

            option2Button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (question.answer == 0) {
                        option0Button.setBackgroundColor(Color.GREEN);
                        option2Button.setBackgroundColor(Color.RED);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else if (question.answer == 1) {
                        option2Button.setBackgroundColor(Color.RED);
                        option1Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else if (question.answer == 2) {
                        option2Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                        points = 10;
                    } else if (question.answer == 3) {
                        option2Button.setBackgroundColor(Color.RED);
                        option3Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else {
                        Log.i("Question as String: ", question.toString());
                    }


                }

            });

            option3Button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (question.answer == 0) {
                        option3Button.setBackgroundColor(Color.RED);
                        option0Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else if (question.answer == 1) {
                        option3Button.setBackgroundColor(Color.RED);
                        option1Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else if (question.answer == 2) {
                        option3Button.setBackgroundColor(Color.RED);
                        option2Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                    } else if (question.answer == 3) {
                        option3Button.setBackgroundColor(Color.GREEN);
                        option0Button.setEnabled(false);
                        option1Button.setEnabled(false);
                        option2Button.setEnabled(false);
                        option3Button.setEnabled(false);
                        questionExplanationText.setText(question.explanation);
                        continueButton.setEnabled(true);
                        points = 10;
                    } else {
                        Log.i("Question as String: ", question.toString());
                    }


                }

            });


            continueButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    outputUserDoc = JavaJsonConverter.ConvertUserObjectToJson(uUser);

                    Intent intent = new Intent(Quiz1Activity.this, Quiz2Activity.class);
                    intent.putExtra("moved_quiz", outputQuizDoc);
                    intent.putExtra("moved_user", outputUserDoc);
                    intent.putExtra("moved_points", points);
                    startActivity(intent);
                }
            });
        }


    }
    @Override
    public void onBackPressed() {

        return;
    }
}
