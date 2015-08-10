package com.example.hoco.android_fegisteration_form;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {
    String username;
    EditText editProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Intent intent = getIntent();
        username = intent.getStringExtra(getString(R.string.logedInUsername));
        editProfile = (EditText)findViewById(R.id.edit_profile_field);
    }

    public void saveAndGoToShowProfileActivity(View view) {
        SharedPreferences shP = getSharedPreferences(getString(R.string.pref_profile), MODE_PRIVATE);
        SharedPreferences.Editor editor = shP.edit();
        editor.putString(username, editProfile.getText().toString());
        editor.commit();
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(EditProfileActivity.this, ShowProfileActivity.class);
        intent.putExtra(getString(R.string.logedInUsername), username);
        startActivity(intent);
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
