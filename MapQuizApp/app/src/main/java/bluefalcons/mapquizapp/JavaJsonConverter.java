/**
 *
 */
package bluefalcons.mapquizapp;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
                questionSet.options.add(option);
            }
            k += 4;
            quiz.questions.add(questionSet);
        }

        Log.i("Json Output", gson.toJson(quiz));
        ConvertJsonToJavaQuiz(gson.toJson(quiz));
        return gson.toJson(quiz);
    }

    public static Quizzes ConvertJsonToJavaQuiz(String quizDoc)
    {
        Gson gson = new Gson();

        Quizzes quiz = gson.fromJson(quizDoc, Quizzes.class);

        Log.i("", quiz.toString());
        return quiz;//.toString();
    }

    public static String ConvertJavaUserToJson(String uname, String pwd, String name, int age)
    {
        User user = new User();
        user.SetName(name);
        user.SetAge(age);
        user.SetUsername(uname);
        user.SetPassword(pwd);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        Log.i("Json Output", gson.toJson(user));
        return gson.toJson(user);
    }

    public static String ConvertJsonToJavaUser(String userDoc)
    {
        Gson gson = new Gson();

        User user = gson.fromJson(userDoc, User.class);

        Log.i("", user.toString());
        return user.toString();
    }

}


class Quizzes
{
    public String title;
    public String info;
    public List<Questions> questions = new ArrayList<Questions>();
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
    public List<QuestionOptions> options = new ArrayList<QuestionOptions>();

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
    private String username;
    private String password;
    private String name;
    private int age;



    public void SetUsername(String uname)
    {
        this.username = uname;
    }

    public String GetUsername(User user)
    {
        return this.username;
    }

    public void SetPassword(String pass)
    {
        this.password = pass;
    }

    public String GetPassword(User user)
    {
        return this.password;
    }

    public void SetName(String name)
    {
        this.name = name;
    }

    public String GetName(User user)
    {
        return this.name;
    }

    public void SetAge(int userAge)
    {
        this.age = userAge;
    }

    public int GetAge(User user)
    {
        return this.age;
    }


    public String toString()
    {
        return "User's Name: " + name + ", User's Age: " + age + ", Username: " + username + ", Password: " + password;
    }
}