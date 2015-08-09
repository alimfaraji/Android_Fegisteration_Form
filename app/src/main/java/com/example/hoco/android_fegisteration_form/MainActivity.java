package com.example.hoco.android_fegisteration_form;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.hoco.android_fegisteration_form.exceptions.PasswordInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.UsernameExistsException;
import com.example.hoco.android_fegisteration_form.exceptions.UsernameInvalidException;

/**
 * a simple registration form, contains a textView ( asks user if he wants to signIn or singUp ),
 *                                        two buttons ( one for signIn and one for singUp )
 *                                        two editTexts ( one for username and one for password )
 * if user choose signUp, the app checks if the username is valid ( already taken or not - saved in sharedPreferences )
 *      if it was invalid, a toast shows, and says its invalid.
 *      if it was valid, a dialog shows, asks user if he wants to logIn or not.
 *          if he choose cancel, nothing will happen.
 *          if he choose logIn, he will logIn
 * if user choose signIn, the app checks if the username and password he enters is valid ( checks from sharedPreferences )
 *      if it was invalid, a toast shows that says username or password is wrong
 *      if it was valid, the "showNameActivity" starts. which welcome to the user.
 * @author alim & Dorna
 * @version %I%,%G%
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);

        final EditText userNameField = (EditText)findViewById(R.id.username_field);

        final EditText passwordField = (EditText)findViewById(R.id.password_field);

        Button signUpButton = (Button)findViewById(R.id.sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameField.getText().toString();
                String password = passwordField.getText().toString();
                try {
                    registerUser(username, password);
                    showDialogMessageAfterSignUp(username);
                    Toast.makeText(MainActivity.this, R.string.sign_up_message, Toast.LENGTH_SHORT).show();
                } catch (PasswordInvalidException e) {
//                    Toast.makeText(MainActivity.this, R.string.sign_up_message_fail, Toast.LENGTH_SHORT).show();
                    passwordField.setError("password should contain at least 6 letters");

                    passwordField.setText(null);

                    passwordField.setHint("invalid password");
                    passwordField.setHintTextColor(Color.RED);
                } catch (UsernameInvalidException e){
                    userNameField.setError("username invalid");
                } catch (UsernameExistsException e){
                    userNameField.setError("username exists, try another one");

                    userNameField.setText(null);
                    passwordField.setText(null);

                    userNameField.setHint("username");
                    passwordField.setHint("password");

                    userNameField.setHintTextColor(Color.RED);
                }
            }
        });

        //TODO : enhance
        Button signInButton = (Button)findViewById(R.id.sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userNameField.getText().toString();
                String password = passwordField.getText().toString();
                if (isValidUserAndPass(username, password)) {
                    startShowNameActivity(username);
                } else {
                    Toast.makeText(MainActivity.this, R.string.sign_in_message_fail, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * @param username username
     * @param password  password
     * @throws Exception if username exists
     */
    private void registerUser(String username, String password) throws UsernameExistsException, UsernameInvalidException, PasswordInvalidException{
        SharedPreferences shP = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        if (shP.contains(username))
            throw new UsernameExistsException();
        if (!isUsernameValid(username))
            throw new UsernameInvalidException();
        if (!isPasswordValid(password))
            throw new PasswordInvalidException();
        SharedPreferences.Editor editor = shP.edit();
        editor.putString(username, password);
        editor.commit();
    }

    private boolean isUsernameValid(String username){
        return true;
    }

    private boolean isPasswordValid(String password){
        if(password == null )
            return false;
        return password.length() >= 6;
    }

    /**
     * 2 buttons: log-in and cancel
     * @param username : if user clicks sign-in, starts another activity and show username
     */
    private void showDialogMessageAfterSignUp(final String username){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setPositiveButton(R.string.posetive_dialog_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startShowNameActivity(username);
            }
        });

        builder.setNegativeButton(R.string.negative_dialog_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //nothing to do
            }
        });

        builder.setMessage(R.string.dialog_message);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * we've got an activity named ShowNameActivity, this method starts it,
     * this activity show the username
     * @param username username
     */
    private void startShowNameActivity(String username){
        Intent intent = new Intent(MainActivity.this, showNameActivity.class);
        intent.putExtra(getString(R.string.usernameForShow), username);
        startActivity(intent);
    }

    /**
     *
     * @param username username
     * @param password password
     * @return if username with this password exists
     */
    private boolean isValidUserAndPass(String username, String password){
        SharedPreferences shP = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        if (!shP.contains(username))
            return false;
        String rightPassword = shP.getString(username, getString(R.string.invalid_pass));
//        Log.d("right password is : ", rightPassword);
//        Log.d("and you typed : ", password);
        return rightPassword.equals(password);
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
