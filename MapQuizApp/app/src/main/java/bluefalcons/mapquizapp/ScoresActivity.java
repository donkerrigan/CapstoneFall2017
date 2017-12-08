package bluefalcons.mapquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import bluefalcons.mapquizapp.NetworkLayer.LeaderboardNetwork;
import bluefalcons.mapquizapp.NetworkLayer.ServerConnection;

public class ScoresActivity extends AppCompatActivity {

    private String userDoc;
    private String outputUserDoc = null;
    private Leaderboard leaderboard = null;
    private TextView[] userTextViews, scoreTextViews;
    private LeaderboardNetwork mLeaderboardNet;

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

        mLeaderboardNet = LeaderboardNetwork.getInstance(this);
        mLeaderboardNet.SetSocket(ServerConnection.getInstance().GetSocket());
        mLeaderboardNet.SetupSocketListeners();

        userTextViews = new TextView[15];
        scoreTextViews = new TextView[15];

        userTextViews[0] = (TextView)findViewById(R.id.tvUser1);
        userTextViews[1] = (TextView)findViewById(R.id.tvUser2);
        userTextViews[2] = (TextView)findViewById(R.id.tvUser3);
        userTextViews[3] = (TextView)findViewById(R.id.tvUser4);
        userTextViews[4] = (TextView)findViewById(R.id.tvUser5);
        userTextViews[5] = (TextView)findViewById(R.id.tvUser6);
        userTextViews[6] = (TextView)findViewById(R.id.tvUser7);
        userTextViews[7] = (TextView)findViewById(R.id.tvUser8);
        userTextViews[8] = (TextView)findViewById(R.id.tvUser9);
        userTextViews[9] = (TextView)findViewById(R.id.tvUser10);
        userTextViews[10] = (TextView)findViewById(R.id.tvUser11);
        userTextViews[11] = (TextView)findViewById(R.id.tvUser12);
        userTextViews[12] = (TextView)findViewById(R.id.tvUser13);
        userTextViews[13] = (TextView)findViewById(R.id.tvUser14);
        userTextViews[14] = (TextView)findViewById(R.id.tvUser15);

        scoreTextViews[0] = (TextView)findViewById(R.id.tvScore1);
        scoreTextViews[1] = (TextView)findViewById(R.id.tvScore2);
        scoreTextViews[2] = (TextView)findViewById(R.id.tvScore3);
        scoreTextViews[3] = (TextView)findViewById(R.id.tvScore4);
        scoreTextViews[4] = (TextView)findViewById(R.id.tvScore5);
        scoreTextViews[5] = (TextView)findViewById(R.id.tvScore6);
        scoreTextViews[6] = (TextView)findViewById(R.id.tvScore7);
        scoreTextViews[7] = (TextView)findViewById(R.id.tvScore8);
        scoreTextViews[8] = (TextView)findViewById(R.id.tvScore9);
        scoreTextViews[9] = (TextView)findViewById(R.id.tvScore10);
        scoreTextViews[10] = (TextView)findViewById(R.id.tvScore11);
        scoreTextViews[11] = (TextView)findViewById(R.id.tvScore12);
        scoreTextViews[12] = (TextView)findViewById(R.id.tvScore13);
        scoreTextViews[13] = (TextView)findViewById(R.id.tvScore14);
        scoreTextViews[14] = (TextView)findViewById(R.id.tvScore15);


        //TODO Some method of retrieving leaderboard users from db
        leaderboard = new Leaderboard();
        leaderboard.users = new String[15];
        leaderboard.scores = new Integer[15];
        leaderboard.users[0] = "Don";
        leaderboard.scores[0] = 100;

        Gson gson = new Gson();
        String temp = gson.toJson(leaderboard);

        mLeaderboardNet.GetLeaderboard(temp);


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

    public void HighScoreCallback(String highScores){
        if(highScores==null)
            return;
        Gson gson = new Gson();
        leaderboard = gson.fromJson(highScores, Leaderboard.class);
        String [] users = leaderboard.getUsers();
        Integer[] scores = leaderboard.getScores();
        View currentView = this.getCurrentFocus();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<users.length; i++)
                {
                    if(users[i]!=null && scores[i]!=null) {
                        userTextViews[i].setText(users[i]);
                        scoreTextViews[i].setText(String.valueOf(scores[i]));
                    }
                }
            }
        });
    }
}
