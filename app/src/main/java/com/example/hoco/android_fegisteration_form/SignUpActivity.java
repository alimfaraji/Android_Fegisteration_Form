package com.example.hoco.android_fegisteration_form;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    public static boolean isValidBirthday(String s) {
        if (s == null || s.equals("") || s.equals(" ") || s.equals("  "))
            return true;
        if (!s.matches("^\\d{4}[\\/\\-](0?[1-9]|1[012])[\\/\\-](0?[1-9]|[12][0-9]|3[01])$"))
            return false;
        return true;
    }

    public static boolean isValidPhoneNumber(String s) {
        return true;
    }

    public static boolean isValidEmailAddress(String s) {
        if (s == null || s == "" || s.length() < 1)
            return false;
        if (!s.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
            return false;
        return true;
    }

    public static boolean isValidLastName(String s) {
        if (s == null || s == "" || s.length() < 1)
            return false;
        return true;
    }

    public static boolean isValidFirstName(String s) {
        if (s == null || s == "" || s.length() < 1)
            return false;
        return true;
    }

    public static boolean isValidUsername(String username) {
        if (username == null || username == "" || username.length() < 1)
            return false;
        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password == null)
            return false;
        return password.length() >= 6;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        member.setUsername(intent.getStringExtra(getString(R.string.username)));
        member.setPassword(intent.getStringExtra(getString(R.string.password)));

        usernameField = (EditText) findViewById(R.id.username_field_sign_up);
        passwordField = (EditText) findViewById(R.id.password_field_sign_up);
        firstNameField = (EditText) findViewById(R.id.first_name_sign_up);
        lastNameField = (EditText) findViewById(R.id.last_name_sign_up);
        emailAddressField = (EditText) findViewById(R.id.email_address_sign_up);
        phoneNumberField = (EditText) findViewById(R.id.phone_number_sign_up);
        birthdayField = (EditText) findViewById(R.id.birthday_sign_up);

        usernameField.setText(member.getUsername());
        passwordField.setText(member.getPassword());
    }

    public void signUpAndLeaveThisActivity(View view) {
        try {
            registerUser();
            goToShowNameActivity();
        } catch (UsernameExistsException e) {
            usernameField.setError(getString(R.string.username_exists_exception));
//            Toast.makeText(this,getString(R.string.username_exists_exception), Toast.LENGTH_LONG ).show();
            colorAllExcept(usernameField);
//            usernameField.setText(null);
//            userNameField.setHint("username");
//            passwordField.setHint("password");
//            usernameField.setTextColor(Color.RED);
//            usernameField.setHintTextColor(Color.RED);
        } catch (UsernameInvalidException e) {
            usernameField.setError(getString(R.string.username_invalid_exception));
//            Toast.makeText(this,getString(R.string.username_invalid_exception), Toast.LENGTH_LONG ).show();
//            usernameField.setTextColor(Color.RED);
            colorAllExcept(usernameField);
        } catch (PasswordInvalidException e) {
            passwordField.setError(getString(R.string.password_invalid_exception));
//            Toast.makeText(this,getString(R.string.password_invalid_exception), Toast.LENGTH_LONG ).show();
//            passwordField.setHintTextColor(Color.RED);
            colorAllExcept(passwordField);
        } catch (FirstNameInvalidException e) {
            firstNameField.setError(getString(R.string.first_name_invalid_exception));
//            Toast.makeText(this,getString(R.string.first_name_invalid_exception), Toast.LENGTH_LONG ).show();
//            firstNameField.setHintTextColor(Color.RED);
            colorAllExcept(firstNameField);
        } catch (LastNameInvalidException e) {
            lastNameField.setError(getString(R.string.last_name_invalid_exception));
//            Toast.makeText(this,getString(R.string.last_name_invalid_exception), Toast.LENGTH_LONG ).show();
//            lastNameField.setHintTextColor(Color.RED);
//            lastNameField.setTextColor(Color.RED);
            colorAllExcept(lastNameField);
        } catch (EmailAddressInvalidException e) {
            emailAddressField.setError(getString(R.string.email_address_invalid_exception));
//            Toast.makeText(this,getString(R.string.email_address_invalid_exception), Toast.LENGTH_LONG ).show();
//            emailAddressField.setTextColor(Color.RED);
//            emailAddressField.setHintTextColor(Color.RED);
            colorAllExcept(emailAddressField);
        } catch (PhoneNumberInvalidException e) {

        } catch (BirthdayInvalidException e) {
            birthdayField.setError(getString(R.string.birthday_invalid_exception));
//            Toast.makeText(this,getString(R.string.birthday_invalid_exception), Toast.LENGTH_LONG ).show();
//            birthdayField.setTextColor(Color.RED);
//            birthdayField.setHintTextColor(Color.RED);
            colorAllExcept(birthdayField);
        } finally {
            passwordField.setText(null);
        }
    }

    private void colorAllExcept(EditText field) {
        usernameField.setTextColor(Color.BLACK);
        passwordField.setTextColor(Color.BLACK);
        firstNameField.setTextColor(Color.BLACK);
        lastNameField.setTextColor(Color.BLACK);
        emailAddressField.setTextColor(Color.BLACK);
        phoneNumberField.setTextColor(Color.BLACK);
        birthdayField.setTextColor(Color.BLACK);

        field.setTextColor(Color.RED);

        usernameField.setHintTextColor(Color.BLACK);
        passwordField.setHintTextColor(Color.BLACK);
        firstNameField.setHintTextColor(Color.BLACK);
        lastNameField.setHintTextColor(Color.BLACK);
        emailAddressField.setHintTextColor(Color.BLACK);
        phoneNumberField.setHintTextColor(Color.BLACK);
        birthdayField.setHintTextColor(Color.BLACK);

        field.setHintTextColor(Color.RED);
    }

    private void goToShowNameActivity() {
        Intent intent = new Intent(SignUpActivity.this, showNameActivity.class);
        intent.putExtra(getString(R.string.logedInUsername), member.getUsername());
        startActivity(intent);
        finish();
    }

    private void registerUser() throws UsernameExistsException, UsernameInvalidException, PasswordInvalidException
            , FirstNameInvalidException, LastNameInvalidException, EmailAddressInvalidException,
            PhoneNumberInvalidException, BirthdayInvalidException {
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
