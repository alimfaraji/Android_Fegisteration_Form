package com.example.hoco.android_fegisteration_form;

/**
 * Created by dorna on 8/11/15.
 */

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(11)
public class ShowProfileFragment extends Fragment {
    TextView firstNameField, lastNameField, emailAddressField, phoneNumberField, birthdayField, usernameField;
    Member member = new Member();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        View view = inflater.inflate(R.layout.fragment_show_profile, container, false);

        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra(getString(R.string.logedInUsername));

        SharedPreferences shP = getActivity().getSharedPreferences(getString(R.string.pref_profile), Context.MODE_PRIVATE);
        String s = shP.getString(username, getString(R.string.noProfileToShow));

        member = Member.getMemberFromCode(s);

        usernameField = (TextView) view.findViewById(R.id.username_field_profile_page);
        usernameField.setText(member.getUsername());

        firstNameField = (TextView) view.findViewById(R.id.firstname_field_profile_page);
        firstNameField.setText(member.getFirstname());

        lastNameField = (TextView) view.findViewById(R.id.lastname_field_profile_page);
        lastNameField.setText(member.getLastname());

        emailAddressField = (TextView) view.findViewById(R.id.emailAddress_field_profile_page);
        emailAddressField.setText(member.getEmailAddress());

        phoneNumberField = (TextView) view.findViewById(R.id.phone_number_field_profile_page);
        phoneNumberField.setText(member.getPhoneNumber());

        birthdayField = (TextView) view.findViewById(R.id.birthday_field_profile_page);
        birthdayField.setText(member.getBirthday());

        return view;
    }

}