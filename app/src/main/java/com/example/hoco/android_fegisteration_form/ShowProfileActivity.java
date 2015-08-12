package com.example.hoco.android_fegisteration_form;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

import org.w3c.dom.Text;

public class ShowProfileActivity extends Activity {
    TextView firstNameField, lastNameField, emailAddressField, phoneNumberField, birthdayField, usernameField;
    Member member = new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

//        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String username = intent.getStringExtra(getString(R.string.logedInUsername));
//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

//        TextView profileText = (TextView)findViewById(R.id.profile_string);

        SharedPreferences shP = getSharedPreferences(getString(R.string.pref_profile), MODE_PRIVATE);
        String s = shP.getString(username, getString(R.string.noProfileToShow));

//        member = Member.getMemberFromCode(s);
//
//        usernameField = (TextView)findViewById(R.id.username_field_profile_page);
//        usernameField.setText(member.getUsername());
//
//        firstNameField = (TextView)findViewById(R.id.firstname_field_profile_page);
//        firstNameField.setText(member.getFirstname());
//
//        lastNameField = (TextView)findViewById(R.id.lastname_field_profile_page);
//        lastNameField.setText(member.getLastname());
//
//        emailAddressField = (TextView)findViewById(R.id.emailAddress_field_profile_page);
//        emailAddressField.setText(member.getEmailAddress());
//
//        phoneNumberField = (TextView)findViewById(R.id.phone_number_field_profile_page);
//        phoneNumberField.setText(member.getPhoneNumber());
//
//        birthdayField = (TextView)findViewById(R.id.birthday_field_profile_page);
//        birthdayField.setText(member.getBirthday());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_profile, menu);
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
//String[] menu;
//    DrawerLayout dLayout;
//    ListView dList;
//    ArrayAdapter<String> adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_profile);
//
//
//        menu = new String[]{"Edit Profile","Show Profile","QR code","Contact Us"};
//        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        dList = (ListView) findViewById(R.id.left_drawer);
//
//        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
//
//        dList.setAdapter(adapter);
//        dList.setSelector(android.R.color.holo_blue_dark);
//
//        dList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @Override
//            @TargetApi(11)
//            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
//
//                dLayout.closeDrawers();
//                Bundle args = new Bundle();
//                args.putString("Menu", menu[position]);
//                Fragment detail = new DetailFragment();
//                detail.setArguments(args);
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
//
//            }
//
//        });
//    }
}
