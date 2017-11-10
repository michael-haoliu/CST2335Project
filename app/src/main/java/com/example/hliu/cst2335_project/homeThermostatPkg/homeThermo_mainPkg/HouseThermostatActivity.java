package com.example.hliu.cst2335_project.homeThermostatPkg.homeThermo_mainPkg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.hliu.cst2335_project.R;
import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;
import com.example.hliu.cst2335_project.homeThermostatPkg.adapterPkg.TempSetting_Adapter;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class HouseThermostatActivity extends Activity {

    private ArrayList<String> arrayList;

    private TreeMap<Integer, Double> listTemperature;

    private FloatingActionButton floatingActionButton;
    private final static int ADD_TEMP_REQUEST_CODE = 10;
    private ListView listView;

    private String[] stringList = {"12", "32"};


//    private ArrayAdapter<String> stringArrayAdapter;
    private TempSetting_Adapter tempSetting_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_thermostat);

        listView = (ListView) findViewById(R.id.listView_tempList);
        arrayList = new ArrayList<>();

        listTemperature = new TreeMap<>();
//        listTemperature.put(0, 20.0);
//-------------------------------------
        // if the arrayList is saved;
        if( (listTemperature !=null) && (!listTemperature.isEmpty()) ){

            DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting();
            for (Map.Entry<Integer, Double> entry : listTemperature.entrySet() ) {
                Integer time_key = entry.getKey();
                Double temp = entry.getValue();
                newTemp.setTemp(temp);
                newTemp.setTimeOfWeek(time_key);
                Log.i("list temp", "returned to main - stored data " + newTemp.toString());
                arrayList.add(newTemp.displayTime());
            }
//            tempSetting_adapter.notifyDataSetChanged();
        }

//        arrayList.add("22");
//        arrayList.add("33");
//---------------------------------------------------------

        floatingActionButton = (FloatingActionButton)findViewById(R.id.button_addNewTemp);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseThermostatActivity.this, AddNewTempActivity.class);

                //--------pass the current treelist to add
                intent.putExtra("treeMap", listTemperature);
                //-------------------

                TreeMap<Integer, Double> test = (TreeMap<Integer, Double>) intent.getExtras().get("treeMap");
//                System.out.println(" test treemap " + test.size());
//                startActivity(intent);
                startActivityForResult(intent, ADD_TEMP_REQUEST_CODE);
            }
        });

        //---------------listview

        tempSetting_adapter = new TempSetting_Adapter(this, arrayList);
        listView.setAdapter(tempSetting_adapter);

    }// end  method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode == ADD_TEMP_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){

                listTemperature = new TreeMap<>((Map<Integer, Double>) data.getExtras().get("treeMap"));


//                int time_return = data.getIntExtra("newItem_time", 0);
//                double temp_return = data.getDoubleExtra("newItem_temp", -99999);

//                listTemperature.put(time_return, temp_return);
//
//                arrayList.add(new DTO_TemperatureSetting(time_return, temp_return).displayTime());



                // testing display to the window
                DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting();
                for (Map.Entry<Integer, Double> entry : listTemperature.entrySet() ) {
                    Integer time_key = entry.getKey();
                    Double temp = entry.getValue();
                    newTemp.setTemp(temp);
                    newTemp.setTimeOfWeek(time_key);
                    Log.i("list temp", "returned to main " + newTemp.toString());

                    arrayList.add(newTemp.displayTime());
                }

//                tempSetting_adapter = new TempSetting_Adapter(this, arrayList);
//                listView.setAdapter(tempSetting_adapter);

                tempSetting_adapter.notifyDataSetChanged();

            }// end if
        }// end if

//        if(listTemperature !=null && (!listTemperature.isEmpty())){
//            stringList = listTemperature.keySet().toArray(new String[listTemperature.size()]);
//        }
//        arrayList.add("55");
//        tempSetting_adapter.notifyDataSetChanged();

//        stringList = stringList2;
//        stringArrayAdapter.notifyDataSetChanged();

//        //---------------listview
//        String[] stringList = {"12", "32"};
//        if(stringList != null){
//            listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stringList) );
//        }
    }


//    private ArrayList<String> getArrayListTodisplay(){
//        // testing display to the window
//        DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting();
//        for (Map.Entry<Integer, Double> entry : listTemperature.entrySet() ) {
//            Integer time_key = entry.getKey();
//            Double temp = entry.getValue();
//            newTemp.setTemp(temp);
//            newTemp.setTimeOfWeek(time_key);
//            Log.i("list temp", "returned to main " + newTemp.toString());
//
//            arrayList.add(newTemp.displayTime());
//        }
//
//    }

}// end of class
