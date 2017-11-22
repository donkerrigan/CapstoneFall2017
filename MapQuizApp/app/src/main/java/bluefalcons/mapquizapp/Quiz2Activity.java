package bluefalcons.mapquizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Quiz2Activity extends AppCompatActivity {

    String outputDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);


        String quizDoc = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            quizDoc = bundle.getString("moved_quiz");
        Quizzes quiz;
        if (quizDoc != null)
        {
            quiz = JavaJsonConverter.ConvertJsonToJavaQuiz(quizDoc);
            Log.i("Final Output: ", quiz.toString());

            final String outputDoc = quizDoc;

            final Quizzes inputQuiz = quiz;

            List inputQuestions = (inputQuiz.questions);
            final Questions question = (Questions) inputQuestions.get(1);

            List qOptions = question.options;
            QuestionOptions option0 = (QuestionOptions) qOptions.get(0);
            QuestionOptions option1 = (QuestionOptions) qOptions.get(1);
            QuestionOptions option2 = (QuestionOptions) qOptions.get(2);
            QuestionOptions option3 = (QuestionOptions) qOptions.get(3);

            TextView quizTitle = (TextView) findViewById(R.id.tvQuizTitle);
            quizTitle.setText(quiz.title);

            TextView questionQuestionText = (TextView) findViewById(R.id.tvQuestionText);
            questionQuestionText.setText(question.questionText);

            final TextView questionExplanationText = (TextView) findViewById(R.id.tvExplanationText);

            final Button continueButton = (Button) findViewById(R.id.bEndQuestion2);
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
                    } else {
                        Log.i("Question as String: ", question.toString());
                    }


                }

            });


            continueButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {



                    Intent i = new Intent(Quiz2Activity.this, Quiz3Activity.class);

                    i.putExtra("moved_quiz", outputDoc);

                    startActivity(i);


                }
            });
        }


    }
    @Override
    public void onBackPressed() {

        return;
    }
}