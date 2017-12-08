package bluefalcons.mapquizapp;

/**
 * Created by Don Kerrigan on 12/7/2017.
 */

public class Leaderboard {
    public String[] users = new String[15];
    public Integer[] scores = new Integer[15];

    public String[] getUsers(){
        users[0] = "Don";
        return users;
    }

    public Integer[] getScores(){
        scores[0] = 100;
        return scores;
    }
}
