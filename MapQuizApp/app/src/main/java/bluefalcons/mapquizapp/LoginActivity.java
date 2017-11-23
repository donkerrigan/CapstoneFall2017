package bluefalcons.mapquizapp;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.nkzawa.socketio.client.Socket;

import bluefalcons.mapquizapp.NetworkLayer.LoginNetwork;
import bluefalcons.mapquizapp.NetworkLayer.ServerConnection;

public class LoginActivity extends AppCompatActivity {
    private LoginNetwork mLoginNet;
    private ServerConnection mServer;
    private User mUser;
    private String mUserDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mServer = ServerConnection.getInstance();
        mLoginNet = LoginNetwork.getInstance(this);
        mLoginNet.SetSocket(mServer.GetSocket());
        mLoginNet.SetupSocketListeners();

        final Button loginButton = (Button) findViewById(R.id.bLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HideKeyboard(LoginActivity.this);
                mUserDoc = JavaJsonConverter.ConvertJavaUserToJson("cxD45",
                        ((EditText)findViewById(R.id.etUsername)).getText().toString(),
                        ((EditText)findViewById(R.id.etPassword)).getText().toString());
                mLoginNet.Login(mUserDoc);
            }
        });

        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        registerLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    public void LoginCallback ( boolean result){
        if (result) {
            Snackbar.make(this.getCurrentFocus(), "Login Successful!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            Intent intent = new Intent(LoginActivity.this, AppNavigation.class);
            intent.putExtra("moved_user", mUserDoc);
            startActivity(intent);
        } else {
            Snackbar.make(this.getCurrentFocus(), "Sorry, could not log you in.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
