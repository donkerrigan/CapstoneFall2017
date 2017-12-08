package bluefalcons.mapquizapp.NetworkLayer;

import com.github.nkzawa.socketio.client.Socket;

import bluefalcons.mapquizapp.Leaderboard;
import bluefalcons.mapquizapp.ScoresActivity;

/**
 * Created by Don Kerrigan on 12/7/2017.
 */

public class LeaderboardNetwork {
    private static LeaderboardNetwork mLeaderboardNetInstance;
    private static ScoresActivity mScoresActivity;
    private Socket mSocket;

    private LeaderboardNetwork(){

    }

    public static LeaderboardNetwork getInstance(ScoresActivity scoresActivity){
        mScoresActivity = scoresActivity;
        if(mLeaderboardNetInstance==null){
            mLeaderboardNetInstance = new LeaderboardNetwork();
        }
        return mLeaderboardNetInstance;
    }

    public void SetSocket(Socket socket){
        mSocket = socket;
    }

    public void GetLeaderboard(String leaderboard){

        mSocket.emit("getHighScores", leaderboard);
    }

    public void SetupSocketListeners() {
        //Listener for login response from server following a request
        mSocket.on("getHighScores", (data) -> {
            try {
                if (data[0] != null)
                    mScoresActivity.HighScoreCallback(data[0].toString());
                else
                    mScoresActivity.HighScoreCallback(data[0].toString());
            } catch (Exception e) {
            }
        });
    }
}
