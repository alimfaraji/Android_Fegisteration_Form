package com.example.hoco.android_fegisteration_form;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class showNameActivity extends AppCompatActivity {
    /**
     * user will sign out after this amount of time
     */
    static final int showNameActivity_TIME_OUT = 60000 * 5;
    String[] menu;
    DrawerLayout dLayout;
    ListView dList;
    ArrayAdapter<String> adapter;

    /**
     * @param time secondes
     * @return formated time like : 2:45
     */
    private String formatedTime(long time) {
        String ret = "";
        ret = ret + time / 60;
        ret = ret + ":" + time % 60;
        return ret;
    }

    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_name);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        final TextView timeLeft = (TextView) findViewById(R.id.timeLeft);
        new CountDownTimer(showNameActivity_TIME_OUT, 1000) {

            public void onTick(long millisUntilFinished) {
                timeLeft.setText(formatedTime(millisUntilFinished / 1000));
            }

            public void onFinish() {
                Intent intent = new Intent(showNameActivity.this, MainActivity.class);
                startActivity(intent);

                // close this activity
                finish();
            }
        }.start();

        menu = new String[]{"Edit Profile", "Show Profile", "QR code", "Contact Us"};
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dList = (ListView) findViewById(R.id.left_drawer);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);

        dList.setAdapter(adapter);
        dList.setSelector(android.R.color.holo_blue_dark);


        if (savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putString("Menu", menu[1]);
            Fragment detail = new ShowProfileFragment();
            detail.setArguments(args);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
        }

        dList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            @TargetApi(11)
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {

                dLayout.closeDrawers();
                Bundle args = new Bundle();
                args.putString("Menu", menu[position]);
                Fragment detail = new ShowProfileFragment();
                switch (position) {
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
                    case 3:
                        Intent sendMailIntent = new Intent(Intent.ACTION_SEND);
                        sendMailIntent.setType("text/html");
                        sendMailIntent.putExtra(Intent.EXTRA_PHONE_NUMBER, getString(R.string.phone_number_contact_us));
                        sendMailIntent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.email_contact_us));
                        sendMailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject_contact_us));

                        startActivity(Intent.createChooser(sendMailIntent, getString(R.string.title_bar_chooser_contact_us)));
                        break;
                    default:
                        detail = new ShowProfileFragment();
                }
                detail.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();

            }

        });

    }

    @TargetApi(11)
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //  The Intents Fairy has delivered us some data!
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                Bundle args = new Bundle();
                args.putString(getString(R.string.format_barcode), contents);
                args.putString(getString(R.string.contents_barcode), format);
                Fragment showBarcodeFrag = new ShowBarcodeContentFragment();
                showBarcodeFrag.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, showBarcodeFrag).commit();
                // Handle successful scan
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }
}
