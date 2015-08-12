package com.example.hoco.android_fegisteration_form;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class showNameActivity extends Activity {
    /**
     * user will sign out after this amount of time
     */
    static final int showNameActivity_TIME_OUT = 60000 * 2;
    String username;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_name);
//
//        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);
//
//        Intent intent = getIntent();
//        username = intent.getStringExtra(getString(R.string.logedInUsername));
//
//        TextView showMessage = (TextView)findViewById(R.id.logedInMessage);
//        showMessage.setText(username + " you have logged in");
//
//        final TextView timeLeft = (TextView)findViewById(R.id.timeLeft);
//        new CountDownTimer(showNameActivity_TIME_OUT, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                timeLeft.setText("seconds remaining: " + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//                timeLeft.setText("done!");
//                Intent intent = new Intent(showNameActivity.this, MainActivity.class);
//                startActivity(intent);
//
//                // close this activity
//                finish();
//            }
//        }.start();
//
//    }
//
//    public void goToShowProfileActivity(View view) {
//        Intent intent = new Intent(showNameActivity.this, ShowProfileActivity.class);
//        intent.putExtra(getString(R.string.logedInUsername), username);
//        startActivity(intent);
//    }
//
//    public void goToEditProfileActivity(View view){
//        Intent intent = new Intent(showNameActivity.this, EditProfileActivity.class);
//        intent.putExtra(getString(R.string.logedInUsername), username);
//        startActivity(intent);
//    }
//
//    public void QROnClick(View view){
//        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//
//        // This flag clears the called app from the activity stack, so users arrive in the expected
//        // place next time this application is restarted.
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//
//        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//        startActivityForResult(intent, 0);
//    }
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                //  The Intents Fairy has delivered us some data!
//                String contents = intent.getStringExtra("SCAN_RESULT");
//                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
//                // Handle successful scan
//            } else if (resultCode == RESULT_CANCELED) {
//                // Handle cancel
//            }
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_show_name, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;
    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);


        menu = new String[]{"Edit Profile","Show Profile","QR code","Contact Us"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);

        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_dark);

        dList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            @TargetApi(11)
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {

                dLayout.closeDrawers();
                Bundle args = new Bundle();
                args.putString("Menu", menu[position]);
                Fragment detail = new DetailFragment();
                detail.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();

            }

        });
        Bundle args = new Bundle();
        args.putString("Menu", menu[1]);
        Fragment detail = new DetailFragment();
        detail.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();


    }

}
