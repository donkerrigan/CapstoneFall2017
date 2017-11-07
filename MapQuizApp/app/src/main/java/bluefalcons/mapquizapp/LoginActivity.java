package bluefalcons.mapquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = (Button) findViewById(R.id.bLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    //Insert logic for logging in here
                    Intent intent = new Intent(LoginActivity.this, AppNavigation.class);
                    //Insert logic for logging in here
                    startActivity(intent);

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
}
