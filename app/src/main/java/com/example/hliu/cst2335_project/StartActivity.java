package com.example.hliu.cst2335_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hliu.cst2335_project.homeThermostatPkg.AddNewTempActivity;
// start activity version 3;

//public class StartActivity extends Activity {
public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar_startPage);
        setSupportActionBar(toolbar);

//        Menu toolbar_auto =(Menu)findViewById(R.id.ic_auto);
//        toolbar_auto.  setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(StartActivity.this, AutomobileActivity.class);
//                startActivity(intent);
//
//            }
//        });

//        TextView toolbar_auto =(TextView)findViewById(R.id.;
//        toolbar_auto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(StartActivity.this, AutomobileActivity.class);
//                startActivity(intent);
//
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.ic_activity:
                intent = new Intent(StartActivity.this, ActivityTrackingActivity.class);
                startActivity(intent);
                return true;

            case R.id.ic_auto:
                Log.i("Start activity", "icon atuomobile clicked");
                intent = new Intent(StartActivity.this, AutomobileActivity.class);
                startActivity(intent);
                return true;

            case R.id.ic_food:
                intent = new Intent(StartActivity.this, FoodInformaitonActivity.class);
                startActivity(intent);
                return true;
            case R.id.ic_house:
                intent = new Intent(StartActivity.this, AddNewTempActivity.class);
//                intent = new Intent(StartActivity.this, HouseThermostatActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

//        return super.onOptionsItemSelected(item);
    }
}
