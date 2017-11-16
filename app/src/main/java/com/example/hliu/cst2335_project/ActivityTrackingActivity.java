package com.example.hliu.cst2335_project;

import android.app.Activity;
import android.os.Bundle;

public class ActivityTrackingActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationBar_help_h);
//        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                Toast.makeText(getApplicationContext(), "Navigation bar Clicked!",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }
}
