package bluefalcons.mapquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import bluefalcons.mapquizapp.NetworkLayer.AppNavigationNetwork;
import bluefalcons.mapquizapp.NetworkLayer.ServerConnection;
import bluefalcons.mapquizapp.NetworkLayer.UserNetwork;

public class QuizEndActivity extends AppCompatActivity {

    private Quizzes quiz;
    private String quizDoc;
    private String userDoc;
    private String outputUserDoc;
    private User uUser;
    private int points = 0;
    private UserNetwork mUserNet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_end);

        mUserNet = UserNetwork.getInstance();
        mUserNet.SetSocket(ServerConnection.getInstance().GetSocket());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            quizDoc = bundle.getString("moved_quiz");
            userDoc = bundle.getString("moved_user");
            uUser = JavaJsonConverter.ConvertJsonToJavaUser(userDoc);
            points = bundle.getInt("moved_points");
        }

        if (quizDoc != null)
        {
            quiz = JavaJsonConverter.ConvertJsonToJavaQuiz(quizDoc);
            Log.i("Final Output: ", quiz.toString());

            ((TextView)findViewById(R.id.tvQuizEndTitle)).setText(quiz.title);
            ((TextView)findViewById(R.id.tvQuizPoints)).setText(String.valueOf(points));

            if(points == 50) {
                ((TextView) findViewById(R.id.tvEndMessage)).setText("A Perfect Score!");
            }
            else if(points == 40){
                ((TextView) findViewById(R.id.tvEndMessage)).setText("Well done!");
            }
            else if(points == 30){
                ((TextView) findViewById(R.id.tvEndMessage)).setText("Not bad!");
            }
            else if(points == 20){
                ((TextView) findViewById(R.id.tvEndMessage)).setText("You can always do better!");
            }
            else if(points == 10){
                ((TextView) findViewById(R.id.tvEndMessage)).setText("Everyone starts somewhere!");
            }
            else if(points == 0){
                ((TextView) findViewById(R.id.tvEndMessage)).setText("Better luck next time ...");
            }

            Button closeQuizButton = (Button) findViewById(R.id.bCloseQuiz);
            closeQuizButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    uUser.UpdateScore(points);
                    outputUserDoc = JavaJsonConverter.ConvertUserObjectToJson(uUser);

                    //Server command - Update user to account for score change
                    mUserNet.UpdateScore(outputUserDoc);

                    Intent intent = new Intent(QuizEndActivity.this, AppNavigation.class);
                    intent.putExtra("moved_user", outputUserDoc);
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
