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
        SharedPreferences shP = getSharedPreferences("userPassPref", Context.MODE_PRIVATE);
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

        EditText userNameField = (EditText)findViewById(R.id.username_field);
        final String userNameString = userNameField.getText().toString();

        EditText passwordField = (EditText)findViewById(R.id.password_field);
        final String passwordString = passwordField.getText().toString();

        Button signUpButton = (Button)findViewById(R.id.sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    registerUser(userNameString, passwordString);
                    Toast.makeText(MainActivity.this, R.string.sign_up_message, Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, R.string.sign_up_message_fail, Toast.LENGTH_LONG).show();
                }
            }
        });

        Button signInButton = (Button)findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidUserAndPass(userNameString, passwordString)){
                    Toast.makeText(MainActivity.this, R.string.sign_in_message, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(this, showNameActivity.class);
//                    MainActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, R.string.sign_in_message_fail, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidUserAndPass(String username, String password){
        SharedPreferences shP = getSharedPreferences("userPassPref", Context.MODE_PRIVATE);
//        String ret = shP.getString(username, password);
//        if (ret == null)
//            return false;
//        return true;
        if (shP.contains(username))
            return true;
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
