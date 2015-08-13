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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class showNameActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_show_name);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        final TextView timeLeft = (TextView)findViewById(R.id.timeLeft);
        new CountDownTimer(showNameActivity_TIME_OUT, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timeLeft.setText("done!");
                Intent intent = new Intent(showNameActivity.this, MainActivity.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }.start();

//        Button button = (Button)findViewById(R.id.buttonTest);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//
//                      // This flag clears the called app from the activity stack, so users arrive in the expected
//                      // place next time this application is restarted.
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//
//                        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//                        startActivityForResult(intent, 0);
//            }
//
//            public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//                if (requestCode == 0) {
//                    if (resultCode == RESULT_OK) {
//                        //  The Intents Fairy has delivered us some data!
//                        String contents = intent.getStringExtra("SCAN_RESULT");
//                        String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
//                        // Handle successful scan
//                    } else if (resultCode == RESULT_CANCELED) {
//                        // Handle cancel
//                    }
//                }
//            }
//        });

        menu = new String[]{"Edit Profile","Show Profile","QR code","Contact Us"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);

        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_dark);


        dList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            @TargetApi(11)
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {

                dLayout.closeDrawers();
                Bundle args = new Bundle();
                args.putString("Menu", menu[position]);
                Fragment detail = new ShowProfileFragment();
                switch (position){
                    case 0:
                        detail = new EditProfileFragment();
                        break;
                    case 1:
                        detail = new ShowProfileFragment();
                        break;
                    case 2:
                        Intent intent = new Intent("com.google.zxing.client.android.SCAN");

                      // This flag clears the called app from the activity stack, so users arrive in the expected
                      // place next time this application is restarted.
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                        startActivityForResult(intent, 0);
                        break;
                    default:
                        detail = new ShowProfileFragment();
                }
                detail.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();

            }

        });


        Bundle args = new Bundle();
        args.putString("Menu", menu[1]);
        Fragment detail = new ShowProfileFragment();
        detail.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();


    }
        public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //  The Intents Fairy has delivered us some data!
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast.makeText(this, contents, Toast.LENGTH_LONG).show();
                // Handle successful scan
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }

//    private class SlideMenuClickListener implements
//            ListView.OnItemClickListener
//    {
//        @Override
//        @TargetApi(11)
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//        {
//            displayView(position);
//        }
//    }
//    @TargetApi(11)
//    private void displayView(int position)
//    {
//
//        Bundle args = new Bundle();
//        Fragment detail = new ShowProfileFragment();
//        args.putString("Menu", menu[1]);
//
//        switch (position)
//        {
//            case 0:
//                //fragment = new HomeFragment();
//                args.putString("Menu", menu[0]);
//                detail = new EditProfileFragment();
//                break;
//            case 1:
//                //fragment = new FindPeopleFragment();
//                args.putString("Menu", menu[1]);
//                detail = new ShowProfileFragment();
//                break;
//
//            case 2:
//                //fragment = new PhotosFragment();
//                Intent intent2 = new Intent(this, MainActivity.class);
//                startActivity(intent2);
//                break;
//
//            default:
//                break;
//
//        }
//        detail.setArguments(args);
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
//
//        dList.setItemChecked(position, true);
//        dList.setSelection(position);
//        setTitle(menu[position]);
//        dLayout.closeDrawer(dList);
//    }

}
