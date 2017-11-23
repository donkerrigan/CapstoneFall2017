package bluefalcons.mapquizapp;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import bluefalcons.mapquizapp.NetworkLayer.LoginNetwork;
import bluefalcons.mapquizapp.NetworkLayer.RegisterNetwork;
import bluefalcons.mapquizapp.NetworkLayer.ServerConnection;

public class RegisterActivity extends AppCompatActivity {
    private RegisterNetwork mRegisterNet;
    private String uUserDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegisterNet = RegisterNetwork.getInstance(this);
        mRegisterNet.SetSocket(ServerConnection.getInstance().GetSocket());
        mRegisterNet.SetupSocketListeners();

        final Button registerButton = (Button) findViewById(R.id.bRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HideKeyboard(RegisterActivity.this);
                //Will probably need refactoring once server is setup/connected
                String uname = "";
                String pass = "";
                String ID = "user";

                boolean inputError = false;

                if (!(((EditText) findViewById(R.id.etRegisterUsername)).getText().toString()).equals(""))
                    uname = ((EditText) findViewById(R.id.etRegisterUsername)).getText().toString();
                else
                    inputError = true;
                if (!(((EditText) findViewById(R.id.etRegisterPassword)).getText().toString()).equals(""))
                    pass = ((EditText) findViewById(R.id.etRegisterPassword)).getText().toString();
                else
                    inputError = true;

                if (!inputError) {
                    uUserDoc = JavaJsonConverter.ConvertJavaUserToJson(ID, uname, pass);
                    mRegisterNet.SignUp(uUserDoc);
                }
                else
                {
                    //Some notification of needing an input
                }
            }
        });

        final Button backRegButton = (Button) findViewById(R.id.bRegisterBack);
        backRegButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Callback method for when the server responds to a sign up request.
     */
    public void SignUpCallback ( boolean result){
        if (result) {
            Snackbar.make(this.getCurrentFocus(), "Signup Successful!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Intent intent = new Intent(RegisterActivity.this, AppNavigation.class);
            intent.putExtra("moved_user", uUserDoc);
            startActivity(intent);
        } else {
            Snackbar.make(this.getCurrentFocus(), "Signup Unsuccessful", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    public static void HideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
