package bluefalcons.mapquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoresActivity extends AppCompatActivity {

    private String userDoc;
    private String outputUserDoc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        //Get user doc sent with intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            userDoc = bundle.getString("moved_user");
            outputUserDoc = userDoc;
        }

        //TODO Some method of retrieving leaderboard users from db

        //Go back to Main Activity with user preserved
        final Button closeButton = (Button)findViewById(R.id.bCloseScores);
        closeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScoresActivity.this, AppNavigation.class);
                if(outputUserDoc != null)
                {
                    intent.putExtra("moved_user", outputUserDoc);
                }
                startActivity(intent);
            }
        });
    }
}
