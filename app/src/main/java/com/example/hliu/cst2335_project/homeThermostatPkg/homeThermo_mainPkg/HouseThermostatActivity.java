package com.example.hliu.cst2335_project.homeThermostatPkg.homeThermo_mainPkg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.example.hliu.cst2335_project.R;
import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class HouseThermostatActivity extends Activity {

    private ArrayList<Integer> arrayList;

    private TreeMap<Integer, Double> listTemp;

    private FloatingActionButton floatingActionButton;
    private final static int ADD_TEMP_REQUEST_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_thermostat);

        listTemp = new TreeMap<>();
        listTemp.put(0, 15.0);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.button_addNewTemp);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseThermostatActivity.this, AddNewTempActivity.class);

                //--------pass the current treelist to add
                intent.putExtra("treeMap", listTemp);
                //-------------------

                TreeMap<Integer, Double> test = (TreeMap<Integer, Double>) intent.getExtras().get("treeMap");
                System.out.println(" test treemap " + test.size());
                startActivity(intent);
                startActivityForResult(intent, ADD_TEMP_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ADD_TEMP_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){

                listTemp = new TreeMap<>((Map<Integer, Double>) data.getExtras().get("treeMap"));
                // testing display to the window
                DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting();
                for (Map.Entry<Integer, Double> entry : listTemp.entrySet() ) {
                    Integer time_key = entry.getKey();
                    Double temp = entry.getValue();
                    newTemp.setTemp(temp);
                    newTemp.setTimeOfWeek(time_key);
                    Log.i("list temp", "returned to main " + newTemp.toString());
                }
            }

        }

    }
}
