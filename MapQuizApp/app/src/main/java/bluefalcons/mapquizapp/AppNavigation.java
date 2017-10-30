package bluefalcons.mapquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class AppNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            String[] questions = new String[5];
            questions[0] = "First Question?";
            questions[1] = "Second Question?";
            questions[2] = "Third Question?";
            questions[3] = "Fourth Question?";
            questions[4] = "Fifth Question?";
            Integer[] answers = {1, 0, 2, 3, 2};
            String[] explanations = new String[5];
            explanations[0] = "Explanation of Question 1 Answer";
            explanations[1] = "Explanation of Question 2 Answer";
            explanations[2] = "Explanation of Question 3 Answer";
            explanations[3] = "Explanation of Question 4 Answer";
            explanations[4] = "Explanation of Question 5 Answer";
            String[] options = new String[20];
            options[0] = "Q1A1";
            options[1] = "Q1A2";
            options[2] = "Q1A3";
            options[3] = "Q1A4";
            options[4] = "Q2A1";
            options[5] = "Q2A2";
            options[6] = "Q2A3";
            options[7] = "Q2A4";
            options[8] = "Q3A1";
            options[9] = "Q3A2";
            options[10] = "Q3A3";
            options[11] = "Q3A4";
            options[12] = "Q4A1";
            options[13] = "Q4A2";
            options[14] = "Q4A3";
            options[15] = "Q4A4";
            options[16] = "Q5A1";
            options[17] = "Q5A2";
            options[18] = "Q5A3";
            options[19] = "Q5A4";

            String quiz = JavaJsonConverter.ConvertJavaQuizToJson("Pensacola, Florida", "A quiz about the city of Pensacola", questions, answers, explanations, options, 30.4213, -87.2169);
            JavaJsonConverter.ConvertJsonToJavaQuiz(quiz);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
