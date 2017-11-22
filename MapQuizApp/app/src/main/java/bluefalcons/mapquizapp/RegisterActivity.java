package bluefalcons.mapquizapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import bluefalcons.mapquizapp.NetworkLayer.LoginNetwork;
import bluefalcons.mapquizapp.NetworkLayer.RegisterNetwork;
import bluefalcons.mapquizapp.NetworkLayer.ServerConnection;

public class RegisterActivity extends AppCompatActivity {
    private RegisterNetwork mRegisterNet;

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
                //Will probably need refactoring once server is setup/connected
                String uname="";
                String pass="";
                String name="";
                int age=0;
                boolean inputError = false;

                if(!(((EditText) findViewById(R.id.etRegisterUsername)).getText().toString()).equals(""))
                    uname = ((EditText) findViewById(R.id.etRegisterUsername)).getText().toString();
                else
                    inputError = true;
                if(!(((EditText) findViewById(R.id.etRegisterPassword)).getText().toString()).equals(""))
                    pass = ((EditText) findViewById(R.id.etRegisterPassword)).getText().toString();
                else
                    inputError = true;
                if(!(((EditText) findViewById(R.id.etRegisterName)).getText().toString()).equals(""))
                    name = ((EditText) findViewById(R.id.etRegisterName)).getText().toString();
                else
                    inputError = true;
                if(!(((EditText) findViewById(R.id.etRegisterAge)).getText().toString()).equals(""))
                    age = Integer.valueOf(((EditText) findViewById(R.id.etRegisterAge)).getText().toString());
                else
                    inputError = true;

                if(!inputError)
                {
                    mRegisterNet.SignUp(JavaJsonConverter.ConvertJavaUserToJson(name, uname, pass));//, age);
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
            //Insert logic for register->login in here
            startActivity(intent);
        } else {
            Snackbar.make(this.getCurrentFocus(), "Signup Unsuccessful", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }
}
