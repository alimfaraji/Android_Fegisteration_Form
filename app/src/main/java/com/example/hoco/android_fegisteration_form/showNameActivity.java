package com.example.hoco.android_fegisteration_form;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class showNameActivity extends AppCompatActivity {
    /**
     * user will sign out after this amount of time
     */
    static final int showNameActivity_TIME_OUT = 60000 * 2;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_name);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        username = intent.getStringExtra(getString(R.string.logedInUsername));

        TextView showMessage = (TextView)findViewById(R.id.logedInMessage);
        showMessage.setText(username + " you have logged in");

        final TextView timeLeft = (TextView)findViewById(R.id.timeLeft);
        new CountDownTimer(showNameActivity_TIME_OUT, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timeLeft.setText("done!");
                Intent intent = new Intent(showNameActivity.this, MainActivity.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }.start();

    }

    public void goToShowProfileActivity(View view) {
        Intent intent = new Intent(showNameActivity.this, ShowProfileActivity.class);
        intent.putExtra(getString(R.string.logedInUsername), username);
        startActivity(intent);
    }

    public void goToEditProfileActivity(View view){
        Intent intent = new Intent(showNameActivity.this, EditProfileActivity.class);
        intent.putExtra(getString(R.string.logedInUsername), username);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_name, menu);
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
}
