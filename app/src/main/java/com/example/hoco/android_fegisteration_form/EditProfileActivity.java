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
import android.widget.Toast;

import com.example.hoco.android_fegisteration_form.exceptions.BirthdayInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.EmailAddressInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.FirstNameInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.LastNameInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.PasswordInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.PhoneNumberInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.UsernameExistsException;
import com.example.hoco.android_fegisteration_form.exceptions.UsernameInvalidException;

public class EditProfileActivity extends AppCompatActivity {
    EditText firstNameField, lastNameField, emailAddressField, phoneNumberField, birthdayField;
    Member member = new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        member.setUsername(intent.getStringExtra(getString(R.string.logedInUsername)));

        firstNameField = (EditText)findViewById(R.id.first_name_sign_up);
        lastNameField = (EditText)findViewById(R.id.last_name_sign_up);
        emailAddressField = (EditText)findViewById(R.id.email_address_sign_up);
        phoneNumberField = (EditText)findViewById(R.id.phone_number_sign_up);
        birthdayField = (EditText)findViewById(R.id.birthday_sign_up);

        SharedPreferences shP = getSharedPreferences(getString(R.string.pref_profile), MODE_PRIVATE);
        member = Member.getMemberFromCode(shP.getString(member.getUsername(), ""));

        firstNameField.setText(member.getFirstname());
        lastNameField.setText(member.getLastname());
        emailAddressField.setText(member.getEmailAddress());
        phoneNumberField.setText(member.getPhoneNumber());
        birthdayField.setText(member.getBirthday());
    }

    public void signUpAndLeaveThisActivity(View view) {
        try{
            updateProfile();
            goToShowNameActivity();
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

        }
    }

    private void goToShowNameActivity() {
        Intent intent = new Intent(EditProfileActivity.this, showNameActivity.class);
        intent.putExtra(getString(R.string.logedInUsername), member.getUsername());
        startActivity(intent);
    }

    private void updateProfile() throws
             FirstNameInvalidException, LastNameInvalidException, EmailAddressInvalidException,
            PhoneNumberInvalidException, BirthdayInvalidException
    {
        SharedPreferences shP = getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
        if (!SignUpActivity.isValidFirstName(firstNameField.getText().toString()))
            throw new FirstNameInvalidException();
        if (!SignUpActivity.isValidLastName(lastNameField.getText().toString()))
            throw new LastNameInvalidException();
        if (!SignUpActivity.isValidEmailAddress(emailAddressField.getText().toString()))
            throw new EmailAddressInvalidException();
        if (!SignUpActivity.isValidPhoneNumber(phoneNumberField.getText().toString()))
            throw new PhoneNumberInvalidException();
        if (!SignUpActivity.isValidBirthday(birthdayField.getText().toString()))
            throw new BirthdayInvalidException();

        Member tmp = new Member(member.getUsername(), shP.getString(getString(R.string.username)+":"+member.getUsername(), " "),
                firstNameField.getText().toString(), lastNameField.getText().toString(), emailAddressField.getText().toString(),
                phoneNumberField.getText().toString(), birthdayField.getText().toString());

        member = tmp;

        shP = getSharedPreferences(getString(R.string.pref_profile), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shP.edit();
        editor.putString(member.getUsername(), member.generateCode());
        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
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
