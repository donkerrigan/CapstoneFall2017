package bluefalcons.mapquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = (Button) findViewById(R.id.bLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    //Insert logic for logging in here
                    //Testing movement of class objects
                    String[] questions = new String[5];
                    questions[0] = "First Question?";
                    questions[1] = "Second Question?";
                    questions[2] = "Third Question?";
                    questions[3] = "Fourth Question?";
                    questions[4] = "Fifth Question?";
                    Integer[] answers = {1, 0, 2, 3, 2};
                    String[] explanations = new String[5];
                    explanations[0] = "Explanation of Question 1 Answer";
                    explanations[1] = "Explanation of Question 2 Answer";
                    explanations[2] = "Explanation of Question 3 Answer";
                    explanations[3] = "Explanation of Question 4 Answer";
                    explanations[4] = "Explanation of Question 5 Answer";
                    String[] options = new String[20];
                    options[0] = "Q1A1";
                    options[1] = "Q1A2";
                    options[2] = "Q1A3";
                    options[3] = "Q1A4";
                    options[4] = "Q2A1";
                    options[5] = "Q2A2";
                    options[6] = "Q2A3";
                    options[7] = "Q2A4";
                    options[8] = "Q3A1";
                    options[9] = "Q3A2";
                    options[10] = "Q3A3";
                    options[11] = "Q3A4";
                    options[12] = "Q4A1";
                    options[13] = "Q4A2";
                    options[14] = "Q4A3";
                    options[15] = "Q4A4";
                    options[16] = "Q5A1";
                    options[17] = "Q5A2";
                    options[18] = "Q5A3";
                    options[19] = "Q5A4";
                    String quiz = JavaJsonConverter.ConvertJavaQuizToJson("Pensacola, Florida", "A quiz about the city of Pensacola", questions, answers, explanations, options, 30.4213, -87.2169);

                    Intent intent = new Intent(LoginActivity.this, Quiz1Activity.class);

                    intent.putExtra("moved_quiz",quiz);
                     /////////////////////////////
                    //Insert logic for logging in here
                    startActivity(intent);

            }
        });

        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        registerLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
