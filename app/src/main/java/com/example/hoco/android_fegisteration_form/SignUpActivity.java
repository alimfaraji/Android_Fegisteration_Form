package com.example.hoco.android_fegisteration_form;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hoco.android_fegisteration_form.exceptions.BirthdayInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.EmailAddressInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.FirstNameInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.LastNameInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.PasswordInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.PhoneNumberInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.UsernameExistsException;
import com.example.hoco.android_fegisteration_form.exceptions.UsernameInvalidException;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameField, passwordField, firstNameField, lastNameField, emailAddressField, phoneNumberField, birthdayField;
    Button signUpButton;
    Member member = new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        member.setUsername(intent.getStringExtra(getString(R.string.username)));
        member.setPassword(intent.getStringExtra(getString(R.string.password)));

        usernameField = (EditText)findViewById(R.id.username_field_sign_up);
        passwordField = (EditText)findViewById(R.id.password_field_sign_up);
        firstNameField = (EditText)findViewById(R.id.first_name_sign_up);
        lastNameField = (EditText)findViewById(R.id.last_name_sign_up);
        emailAddressField = (EditText)findViewById(R.id.email_address_sign_up);
        phoneNumberField = (EditText)findViewById(R.id.phone_number_sign_up);
        birthdayField = (EditText)findViewById(R.id.birthday_sign_up);

        usernameField.setText(member.getUsername());
        passwordField.setText(member.getPassword());
    }

    public void signUpAndLeaveThisActivity(View view){
        try{
            registerUser();
            goToShowNameActivity();
        }catch(UsernameExistsException e){
            usernameField.setError("username exists, try another one");
//            usernameField.setText(null);
//            userNameField.setHint("username");
//            passwordField.setHint("password");
            usernameField.setTextColor(Color.RED);
//            usernameField.setHintTextColor(Color.RED);
        }catch (UsernameInvalidException e){
            usernameField.setError("Invalid username");
            usernameField.setTextColor(Color.RED);
        }catch (PasswordInvalidException e){
            passwordField.setError("password is invalid");
            passwordField.setHintTextColor(Color.RED);
        }catch (FirstNameInvalidException e){
            firstNameField.setError("you Should enter first name");
            firstNameField.setHintTextColor(Color.RED);
        }catch (LastNameInvalidException e){
            lastNameField.setError("you should enter last name");
            lastNameField.setHintTextColor(Color.RED);
        }catch (EmailAddressInvalidException e){
            emailAddressField.setError("you should enter email address");
            emailAddressField.setHintTextColor(Color.RED);
        }catch (PhoneNumberInvalidException e){

        }catch (BirthdayInvalidException e){

        }finally {
            passwordField.setText(null);
        }
    }

    private void goToShowNameActivity() {
        Intent intent = new Intent(SignUpActivity.this, showNameActivity.class);
        intent.putExtra(getString(R.string.logedInUsername), member.getUsername());
        startActivity(intent);
    }

    private void registerUser() throws UsernameExistsException, UsernameInvalidException, PasswordInvalidException
            , FirstNameInvalidException, LastNameInvalidException, EmailAddressInvalidException,
            PhoneNumberInvalidException, BirthdayInvalidException
    {
        SharedPreferences shP = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        if (shP.contains(getString(R.string.username) + ":" + usernameField.getText().toString()))
            throw new UsernameExistsException();
        if (!isValidUsername(usernameField.getText().toString()))
            throw new UsernameInvalidException();
        if (!isValidPassword(passwordField.getText().toString()))
            throw new PasswordInvalidException();
        if (!isValidFirstName(firstNameField.getText().toString()))
            throw new FirstNameInvalidException();
        if (!isValidLastName(lastNameField.getText().toString()))
            throw new LastNameInvalidException();
        if (!isValidEmailAddress(emailAddressField.getText().toString()))
            throw new EmailAddressInvalidException();
        if (!isValidPhoneNumber(phoneNumberField.getText().toString()))
            throw new PhoneNumberInvalidException();
        if (!isValidBirthday(birthdayField.getText().toString()))
            throw new BirthdayInvalidException();

        Member tmp = new Member(usernameField.getText().toString(), passwordField.getText().toString(),
                firstNameField.getText().toString(), lastNameField.getText().toString(), emailAddressField.getText().toString(),
                phoneNumberField.getText().toString(), birthdayField.getText().toString());

        member = tmp;

        SharedPreferences.Editor editor = shP.edit();
        editor.putString(getString(R.string.username) + ":" + member.getUsername(), member.getPassword());
        editor.commit();

        shP = getSharedPreferences(getString(R.string.pref_profile), Context.MODE_PRIVATE);
        editor = shP.edit();
        editor.putString(member.getUsername(), member.generateCode());
        editor.commit();
    }

    public static boolean isValidBirthday(String s) {
        return true;
    }

    public static boolean isValidPhoneNumber(String s) {
        return true;
    }

    public static boolean isValidEmailAddress(String s) {
        if ( s== null || s == "" || s.length()<1)
            return false;
        return true;
    }

    public static boolean isValidLastName(String s) {
        if ( s== null || s == "" || s.length()<1)
            return false;
        return true;
    }

    public static boolean isValidFirstName(String s) {
        if ( s == null || s == "" || s.length() < 1)
            return false;
        return true;
    }

    public static boolean isValidUsername(String username){
        if (username == null || username == "" || username.length()<1)
            return false;
        return true;
    }

    public static boolean isValidPassword(String password){
        if(password == null )
            return false;
        return password.length() >= 6;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
