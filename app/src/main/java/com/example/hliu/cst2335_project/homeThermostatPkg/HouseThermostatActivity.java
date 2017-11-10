package com.example.hliu.cst2335_project.homeThermostatPkg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.example.hliu.cst2335_project.R;

import java.util.ArrayList;
import java.util.TreeMap;

public class HouseThermostatActivity extends Activity {

    private ArrayList<Integer> arrayList;

    private TreeMap<Integer, Double> treeMap;

    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_thermostat);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.button_addNewTemp);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseThermostatActivity.this, AddNewTempActivity.class);
                startActivity(intent);
            }
        });

    }
}
