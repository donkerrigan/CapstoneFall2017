/**
 *
 */
package bluefalcons.mapquizapp;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;

public class JavaJsonConverter
{
    public static void ConvertJavaQuizToJson(String jTitle, String jInfo, String[] jQuestions, Integer[] jAnswers, String[] jExplanation, String[] jOptions, double lat, double lon)
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
    }


}

class Quizzes
{
    public String title;
    public String info;
    public List<Questions> questions = new ArrayList<Questions>();
    public double latitude;
    public double longitude;
}

class Questions
{
    public String questionText;
    public int answer;
    public String explanation;
    public List<QuestionOptions> options = new ArrayList<QuestionOptions>();
}

class QuestionOptions
{
    public int optionID;
    public String optionText;
}
