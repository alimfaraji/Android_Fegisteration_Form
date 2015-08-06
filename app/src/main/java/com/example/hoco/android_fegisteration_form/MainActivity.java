package com.example.hoco.android_fegisteration_form;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private void registerUser(String username, String password) throws Exception{
        SharedPreferences shP = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        if (shP.contains(username))
            throw new Exception();
        SharedPreferences.Editor editor = shP.edit();
        editor.putString(username, password);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText userNameField = (EditText)findViewById(R.id.username_field);

        final EditText passwordField = (EditText)findViewById(R.id.password_field);

        Button signUpButton = (Button)findViewById(R.id.sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameField.getText().toString();
                String password = passwordField.getText().toString();
                try {
                    registerUser( username, password);
                    Toast.makeText(MainActivity.this, R.string.sign_up_message, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, R.string.sign_up_message_fail, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button signInButton = (Button)findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameField.getText().toString();
                String password = passwordField.getText().toString();
                if (isValidUserAndPass(username, password)) {
                    Toast.makeText(MainActivity.this, R.string.sign_in_message, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(this, showNameActivity.class);
//                    MainActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, R.string.sign_in_message_fail, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isValidUserAndPass(String username, String password){
        SharedPreferences shP = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        if (!shP.contains(username))
            return false;
        String rightPassword = shP.getString(username, getString(R.string.invalid_pass));
        Log.d("right password is : ", rightPassword);
        Log.d("and you typed : ", password);
        if (rightPassword.equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
