package com.example.hoco.android_fegisteration_form;

/**
 * Created by dorna on 8/11/15.
 */

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hoco.android_fegisteration_form.exceptions.BirthdayInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.EmailAddressInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.FirstNameInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.LastNameInvalidException;
import com.example.hoco.android_fegisteration_form.exceptions.PhoneNumberInvalidException;

@TargetApi(11)
public class EditProfileFragment extends Fragment implements View.OnClickListener {
    EditText firstNameField, lastNameField, emailAddressField, phoneNumberField, birthdayField;
    Member member = new Member();
    Button button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        Intent intent = getActivity().getIntent();
        member.setUsername(intent.getStringExtra(getString(R.string.logedInUsername)));

        firstNameField = (EditText) view.findViewById(R.id.first_name_sign_up);
        lastNameField = (EditText) view.findViewById(R.id.last_name_sign_up);
        emailAddressField = (EditText) view.findViewById(R.id.email_address_sign_up);
        phoneNumberField = (EditText) view.findViewById(R.id.phone_number_sign_up);
        birthdayField = (EditText) view.findViewById(R.id.birthday_sign_up);

        button = (Button) view.findViewById(R.id.save_button_sign_up);
        button.setOnClickListener(this);

        SharedPreferences shP = getActivity().getSharedPreferences(getString(R.string.pref_profile), Context.MODE_PRIVATE);
        member = Member.getMemberFromCode(shP.getString(member.getUsername(), ""));

        firstNameField.setText(member.getFirstname());
        lastNameField.setText(member.getLastname());
        emailAddressField.setText(member.getEmailAddress());
        phoneNumberField.setText(member.getPhoneNumber());
        birthdayField.setText(member.getBirthday());

        return view;
    }

    public void onClick(View view) {
        try {
            updateProfile();
            Toast.makeText(getActivity(), getString(R.string.saved_button_edit_profile), Toast.LENGTH_LONG).show();
            goToShowProfileFragment();
        } catch (FirstNameInvalidException e) {
            firstNameField.setError(getString(R.string.first_name_invalid_exception));
            firstNameField.setHintTextColor(Color.RED);
        } catch (LastNameInvalidException e) {
            lastNameField.setError(getString(R.string.last_name_invalid_exception));
            lastNameField.setHintTextColor(Color.RED);
        } catch (EmailAddressInvalidException e) {
            emailAddressField.setError(getString(R.string.email_address_invalid_exception));
            emailAddressField.setHintTextColor(Color.RED);
        } catch (PhoneNumberInvalidException e) {

        } catch (BirthdayInvalidException e) {

        }
    }

    private void goToShowProfileFragment() {
        //
    }

    private void updateProfile() throws
            FirstNameInvalidException, LastNameInvalidException, EmailAddressInvalidException,
            PhoneNumberInvalidException, BirthdayInvalidException {
        SharedPreferences shP = getActivity().getSharedPreferences(getString(R.string.pref_name), Context.MODE_PRIVATE);
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

        Member tmp = new Member(member.getUsername(), shP.getString(getString(R.string.username) + ":" + member.getUsername(), " "),
                firstNameField.getText().toString(), lastNameField.getText().toString(), emailAddressField.getText().toString(),
                phoneNumberField.getText().toString(), birthdayField.getText().toString());

        member = tmp;

        shP = getActivity().getSharedPreferences(getString(R.string.pref_profile), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shP.edit();
        editor.putString(member.getUsername(), member.generateCode());
        editor.commit();
    }


}
