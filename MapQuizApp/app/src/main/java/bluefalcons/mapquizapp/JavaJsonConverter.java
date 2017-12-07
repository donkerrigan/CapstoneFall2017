/**
 *
 */
package bluefalcons.mapquizapp;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class JavaJsonConverter
{
    public static String ConvertJavaQuizToJson(String jTitle, String jInfo, String[] jQuestions, Integer[] jAnswers, String[] jExplanation, String[] jOptions, double lat, double lon)
    {
        Quizzes quiz = new Quizzes();
        quiz.title = jTitle;
        quiz.info = jInfo;
        quiz.latitude = lat;
        quiz.longitude = lon;

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();

        Gson gson = builder.create();

        Questions questionSet;
        QuestionOptions option;
        int i, j, k = 0;
        for(i=0; i<5; i++)
        {
            questionSet = new Questions();
            questionSet.questionText = jQuestions[i];
            questionSet.answer = jAnswers[i];
            questionSet.explanation = jExplanation[i];
            for(j=0; j<4; j++)
            {
                option = new QuestionOptions();
                option.optionID = j;
                option.optionText = jOptions[j+k];
                questionSet.options[j] = option;
            }
            k += 4;
            quiz.questions[i] = questionSet;
        }

        Log.i("Json Output", gson.toJson(quiz));
        return gson.toJson(quiz);
    }

    public static String ConvertQuizObjectToJson(Quizzes quiz)
    {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        return gson.toJson(quiz);
    }

    public static Quizzes ConvertJsonToJavaQuiz(String quizDoc)
    {
        Gson gson = new Gson();

        Quizzes quiz = gson.fromJson(quizDoc, Quizzes.class);

        Log.i("", quiz.toString());
        return quiz;
    }

    public static String ConvertJavaUserToJson(String iD, String uname, String pwd)
    {
        User user = new User();
        user.SetUserID(iD);
        user.SetUsername(uname);
        user.SetPassword(pwd);
        user.UpdateScore(0);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        Log.i("Json Output", gson.toJson(user));
        return gson.toJson(user);
    }

    public static String ConvertUserObjectToJson(User user)
    {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();
        return gson.toJson(user);
    }

    public static User ConvertJsonToJavaUser(String userDoc)
    {
        Gson gson = new Gson();

        User user = gson.fromJson(userDoc, User.class);

        Log.i("", user.toString());
        return user;
    }

    public static Quizzes[] ConvertPingResponse(String quizzes){
        Log.d("SERVER QUIZ OBJs", quizzes.length() + "");
        Gson gson = new Gson();
        Quizzes[] quizArray = gson.fromJson(quizzes, Quizzes[].class);
        Log.d("ARRAY OBJ", "testing");
        return quizArray;
    }
}


class Quizzes
{
    public String title;
    public String info;
    public Questions[] questions = new Questions[5];
    public double latitude;
    public double longitude;

    public String toString()
    {
        //To make sure everything is properly in its place
        return "Title: " + title + ", Info: " + info + ", Question: " + questions.toString() + "\n";
    }
}

class Questions
{
    public String questionText;
    public int answer;
    public String explanation;
    public QuestionOptions[] options = new QuestionOptions[4];

    public String toString()
    {
        //To make sure everything is properly in its place
        return questionText + ", Answer: " + answer + ", Explanation: " + explanation + ", Options: " + options.toString() + "\n";
    }
}

class QuestionOptions
{
    public int optionID;
    public String optionText;

    public String toString()
    {
        //To make sure everything is properly in its place
        return String.valueOf(optionID) + " " + optionText;
    }
}

class User
{
    private String userID;
    private String username;
    private String password;
    private int score;

    public void SetUsername(String uname)
    {
        this.username = uname;
    }

    public String GetUsername()
    {
        return this.username;
    }

    public void SetPassword(String pass)
    {
        this.password = pass;
    }

    public String GetPassword()
    {
        return this.password;
    }

    public void SetUserID(String uID)
    {
        this.userID = uID;
    }

    public String GetUserID()
    {
        return this.userID;
    }

    public int GetScore(){return this.score;}

    public void UpdateScore(int points){this.score+=points;}


    public String toString()
    {
        return this.GetUserID() + " " + this.GetUsername() + " " + this.GetPassword() + " " + this.GetScore();
    }
}