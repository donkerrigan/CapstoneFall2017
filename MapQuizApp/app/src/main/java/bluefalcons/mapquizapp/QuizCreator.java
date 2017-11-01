package bluefalcons.mapquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class QuizCreator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_creator);


        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses Submit button

                boolean inputError = false;

                String jTitle = "";
                String jInfo = "";

                if(!((EditText) findViewById(R.id.quizTitleEdit)).getText().toString().equals("") && !((EditText) findViewById(R.id.quizDescEdit)).getText().toString().equals(""))
                {
                    jTitle = ((EditText) findViewById(R.id.quizTitleEdit)).getText().toString();
                    jInfo = ((EditText) findViewById(R.id.quizDescEdit)).getText().toString();
                }

                //Add all questions to String Array
                String[] jQuestions = new String[5];

                if(!((EditText)findViewById(R.id.question1TextField)).getText().toString().equals(""))
                {
                    jQuestions[0] = ((EditText)findViewById(R.id.question1TextField)).getText().toString();
                }
                else
                {
                    jQuestions[0] = "";
                }
                if(!((EditText)findViewById(R.id.question2TextField)).getText().toString().equals(""))
                {
                    jQuestions[1] = ((EditText)findViewById(R.id.question2TextField)).getText().toString();
                }
                else
                {
                    jQuestions[1] = "";
                }
                if(!((EditText)findViewById(R.id.question3TextField)).getText().toString().equals(""))
                {
                    jQuestions[2] = ((EditText)findViewById(R.id.question3TextField)).getText().toString();
                }
                else
                {
                    jQuestions[2] = "";
                }
                if(!((EditText)findViewById(R.id.question4TextField)).getText().toString().equals(""))
                {
                    jQuestions[3] = ((EditText)findViewById(R.id.question4TextField)).getText().toString();
                }
                else
                {
                    jQuestions[3] = "";
                }
                if(!((EditText)findViewById(R.id.question5TextField)).getText().toString().equals(""))
                {
                    jQuestions[4] = ((EditText)findViewById(R.id.question5TextField)).getText().toString();
                }
                else
                {
                    jQuestions[4] = "";
                }


                //Add all answers to String Array
                Integer[] jAnswers = new Integer[5];

                if(!((EditText) findViewById(R.id.question1Answer)).getText().toString().equals(""))
                {
                        jAnswers[0] = Integer.valueOf(((EditText) findViewById(R.id.question1Answer)).getText().toString());
                }
                else
                {
                    inputError = true;
                }
                if(!((EditText) findViewById(R.id.question2Answer)).getText().toString().equals(""))
                {
                        jAnswers[1] = Integer.valueOf(((EditText) findViewById(R.id.question2Answer)).getText().toString());
                }
                else
                {
                    inputError = true;
                }
                if(!((EditText) findViewById(R.id.question3Answer)).getText().toString().equals(""))
                {
                        jAnswers[2] = Integer.valueOf(((EditText) findViewById(R.id.question3Answer)).getText().toString());
                }
                else
                {
                    inputError = true;
                }
                if(!((EditText) findViewById(R.id.question4Answer)).getText().toString().equals(""))
                {
                    jAnswers[3] = Integer.valueOf(((EditText) findViewById(R.id.question4Answer)).getText().toString());
                }
                else
                {
                    inputError = true;
                }
                if(!((EditText) findViewById(R.id.question5Answer)).getText().toString().equals(""))
                {
                    jAnswers[4] = Integer.valueOf(((EditText) findViewById(R.id.question5Answer)).getText().toString());
                }
                else
                {
                    inputError = true;
                }


                //Add all explanations to String Array
                String[] jExplanation = new String[5];


                if(!((EditText)findViewById(R.id.question1AnswerDesc)).getText().toString().equals(""))
                {
                    jExplanation[0] = ((EditText)findViewById(R.id.question1AnswerDesc)).getText().toString();
                }
                else
                {
                    jExplanation[0] = "";
                }
                if(!((EditText)findViewById(R.id.question1AnswerDesc)).getText().toString().equals(""))
                {
                    jExplanation[1] = ((EditText)findViewById(R.id.question2AnswerDesc)).getText().toString();
                }
                else
                {
                    jExplanation[1] = "";
                }
                if(!((EditText)findViewById(R.id.question1AnswerDesc)).getText().toString().equals(""))
                {
                    jExplanation[2] = ((EditText)findViewById(R.id.question3AnswerDesc)).getText().toString();
                }
                else
                {
                    jExplanation[2] = "";
                }
                if(!((EditText)findViewById(R.id.question1AnswerDesc)).getText().toString().equals(""))
                {
                    jExplanation[3] = ((EditText)findViewById(R.id.question4AnswerDesc)).getText().toString();
                }
                else
                {
                    jExplanation[3] = "";
                }
                if(!((EditText)findViewById(R.id.question1AnswerDesc)).getText().toString().equals(""))
                {
                    jExplanation[4] = ((EditText)findViewById(R.id.question5AnswerDesc)).getText().toString();
                }
                else
                {
                    jExplanation[4] = "";
                }


                //Add all options to String Array
                String[] jOptions = new String[20];


                if(!((EditText)findViewById(R.id.question1Option0)).getText().toString().equals(""))
                {
                    jOptions[0] = ((EditText)findViewById(R.id.question1Option0)).getText().toString();
                }
                else
                {
                    jOptions[0] = "";
                }
                if(!((EditText)findViewById(R.id.question1Option1)).getText().toString().equals(""))
                {
                    jOptions[1] = ((EditText)findViewById(R.id.question1Option1)).getText().toString();
                }
                else
                {
                    jOptions[1] = "";
                }
                if(!((EditText)findViewById(R.id.question1Option2)).getText().toString().equals(""))
                {
                    jOptions[2] = ((EditText)findViewById(R.id.question1Option2)).getText().toString();
                }
                else
                {
                    jOptions[2] = "";
                }
                if(!((EditText)findViewById(R.id.question1Option3)).getText().toString().equals(""))
                {
                    jOptions[3] = ((EditText)findViewById(R.id.question1Option3)).getText().toString();
                }
                else
                {
                    jOptions[3] = "";
                }
                if(!((EditText)findViewById(R.id.question2Option0)).getText().toString().equals(""))
                {
                    jOptions[4] = ((EditText)findViewById(R.id.question2Option0)).getText().toString();
                }
                else
                {
                    jOptions[4] = "";
                }
                if(!((EditText)findViewById(R.id.question2Option1)).getText().toString().equals(""))
                {
                    jOptions[5] = ((EditText)findViewById(R.id.question2Option1)).getText().toString();
                }
                else
                {
                    jOptions[5] = "";
                }
                if(!((EditText)findViewById(R.id.question2Option2)).getText().toString().equals(""))
                {
                    jOptions[6] = ((EditText)findViewById(R.id.question2Option2)).getText().toString();
                }
                else
                {
                    jOptions[6] = "";
                }
                if(!((EditText)findViewById(R.id.question2Option3)).getText().toString().equals(""))
                {
                    jOptions[7] = ((EditText)findViewById(R.id.question2Option3)).getText().toString();
                }
                else
                {
                    jOptions[7] = "";
                }
                if(!((EditText)findViewById(R.id.question3Option0)).getText().toString().equals(""))
                {
                    jOptions[8] = ((EditText)findViewById(R.id.question3Option0)).getText().toString();
                }
                else
                {
                    jOptions[8] = "";
                }
                if(!((EditText)findViewById(R.id.question3Option1)).getText().toString().equals(""))
                {
                    jOptions[9] = ((EditText)findViewById(R.id.question3Option1)).getText().toString();
                }
                else
                {
                    jOptions[9] = "";
                }
                if(!((EditText)findViewById(R.id.question3Option2)).getText().toString().equals(""))
                {
                    jOptions[10] = ((EditText)findViewById(R.id.question3Option2)).getText().toString();
                }
                else
                {
                    jOptions[10] = "";
                }
                if(!((EditText)findViewById(R.id.question3Option3)).getText().toString().equals(""))
                {
                    jOptions[11] = ((EditText)findViewById(R.id.question3Option3)).getText().toString();
                }
                else
                {
                    jOptions[11] = "";
                }
                if(!((EditText)findViewById(R.id.question4Option0)).getText().toString().equals(""))
                {
                    jOptions[12] = ((EditText)findViewById(R.id.question4Option0)).getText().toString();
                }
                else
                {
                    jOptions[12] = "";
                }
                if(!((EditText)findViewById(R.id.question4Option1)).getText().toString().equals(""))
                {
                    jOptions[13] = ((EditText)findViewById(R.id.question4Option1)).getText().toString();
                }
                else
                {
                    jOptions[13] = "";
                }
                if(!((EditText)findViewById(R.id.question4Option2)).getText().toString().equals(""))
                {
                    jOptions[14] = ((EditText)findViewById(R.id.question4Option2)).getText().toString();
                }
                else
                {
                    jOptions[14] = "";
                }
                if(!((EditText)findViewById(R.id.question4Option3)).getText().toString().equals(""))
                {
                    jOptions[15] = ((EditText)findViewById(R.id.question4Option3)).getText().toString();
                }
                else
                {
                    jOptions[15] = "";
                }
                if(!((EditText)findViewById(R.id.question5Option0)).getText().toString().equals(""))
                {
                    jOptions[16] = ((EditText)findViewById(R.id.question5Option0)).getText().toString();
                }
                else
                {
                    jOptions[16] = "";
                }
                if(!((EditText)findViewById(R.id.question5Option1)).getText().toString().equals(""))
                {
                    jOptions[17] = ((EditText)findViewById(R.id.question5Option1)).getText().toString();
                }
                else
                {
                    jOptions[17] = "";
                }
                if(!((EditText)findViewById(R.id.question5Option2)).getText().toString().equals(""))
                {
                    jOptions[18] = ((EditText)findViewById(R.id.question5Option2)).getText().toString();
                }
                else
                {
                    jOptions[18] = "";
                }
                if(!((EditText)findViewById(R.id.question5Option3)).getText().toString().equals(""))
                {
                    jOptions[19] = ((EditText)findViewById(R.id.question5Option3)).getText().toString();
                }
                else
                {
                    jOptions[19] = "";
                }
                

                //initialize latitude and longitude to invalid values
                int lat = 91;
                int lon = 181;

                //change latitude and longitude iff the input values are valid
                if(!((EditText)findViewById(R.id.quizLatEdit)).getText().toString().equals(""))
                {
                    lat = Integer.valueOf(((EditText) findViewById(R.id.quizLatEdit)).getText().toString());
                }
                if(!((EditText) findViewById(R.id.quizLongEdit)).getText().toString().equals(""))
                {
                    lon = Integer.valueOf(((EditText) findViewById(R.id.quizLongEdit)).getText().toString());
                }

                int i;

                //Check that all questions are not empty
                for(i=0; i<jQuestions.length; i++)
                {
                    if(jQuestions[i].equals(""))
                    {
                        inputError = true;
                    }
                }

                //Check that all explanations are not empty
                for(i=0; i<jExplanation.length; i++)
                {
                    if(jExplanation[i].equals(""))
                    {
                        inputError = true;
                    }
                }

                //Check that all options are not empty
                for(i=0; i<jOptions.length; i++)
                {
                    if(jOptions[i].equals(""))
                    {
                        inputError = true;
                    }
                }

                if(jTitle.equals(""))
                {
                    inputError = true;
                }
                if(jInfo.equals(""))
                {
                    inputError = true;
                }

                TextView errorText = (TextView)findViewById(R.id.errorText);

                //check that all inputs are not empty and that latitude and longitude are valid entries
                if(lat <= 90 && lat >= -90 && lon <= 180 && lon >= -180 && !inputError)
                {
                    JavaJsonConverter.ConvertJavaQuizToJson(jTitle, jInfo, jQuestions, jAnswers, jExplanation, jOptions, lat, lon);

                    Intent intent = new Intent(QuizCreator.this, AppNavigation.class);
                    startActivity(intent);
                }
                else
                {
                    //Show input error message
                    if(lat > 90 || lat < -90 || lon > 180 || lon < -180 )
                    {
                        //Coordinate Values are invalid.
                        errorText.setText("Coordinate values are invalid.");
                    }
                    else if(inputError = true)
                    {
                        //Not all fields are filled
                        errorText.setText("Required fields are empty.");
                    }
                    else
                    {
                        //There was an error formatting quiz
                        errorText.setText("There was an error formatting quiz.");
                    }
                }
            }
        });


        final Button cancelButton = (Button)findViewById(R.id.backButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QuizCreator.this, AppNavigation.class);
                startActivity(intent);
            }
        });

    }
}
