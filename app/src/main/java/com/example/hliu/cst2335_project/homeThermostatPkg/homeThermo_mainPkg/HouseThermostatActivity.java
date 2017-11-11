package com.example.hliu.cst2335_project.homeThermostatPkg.homeThermo_mainPkg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hliu.cst2335_project.R;
import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;
import com.example.hliu.cst2335_project.homeThermostatPkg.adapterPkg.TempSetting_Adapter;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class HouseThermostatActivity extends Activity {

    private ArrayList<String> arrayListString_listView;

    private TreeMap<Integer, Double> listTemperature;

    private FloatingActionButton floatingActionButton;
    private final static int ADD_TEMP_REQUEST_CODE = 10;
    private ListView listView;

//    private String[] stringList = {"12", "32"};
//    private ArrayAdapter<String> stringArrayAdapter;
    private TempSetting_Adapter tempSetting_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_thermostat);

        listView = (ListView) findViewById(R.id.listView_tempList);
        arrayListString_listView = new ArrayList<>();

        listTemperature = new TreeMap<>();
//        listTemperature.put(0, 20.0);
//-------------------------------------
        // if the arrayListString_listView is saved;
//        if( (listTemperature !=null) && (!listTemperature.isEmpty()) ){
//
//            DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting();
//            for (Map.Entry<Integer, Double> entry : listTemperature.entrySet() ) {
//                Integer time_key = entry.getKey();
//                Double temp = entry.getValue();
//                newTemp.setTemp(temp);
//                newTemp.setTimeOfWeek(time_key);
//                Log.i("list temp", "returned to main - stored data " + newTemp.toString());
//                arrayListString_listView.add(newTemp.displayTime());
//            }
////            tempSetting_adapter.notifyDataSetChanged();
//        }

//        arrayListString_listView.add("22");
//        arrayListString_listView.add("33");
//---------------------------------------------------------

        floatingActionButton = (FloatingActionButton)findViewById(R.id.button_addNewTemp);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HouseThermostatActivity.this, AddNewTempActivity.class);

                //--------pass the current treelist to add
                intent.putExtra("treeMap", listTemperature);
                //-------------------
//                TreeMap<Integer, Double> test = (TreeMap<Integer, Double>) intent.getExtras().get("treeMap");
//                System.out.println(" test treemap " + test.size());
//                startActivity(intent);
                startActivityForResult(intent, ADD_TEMP_REQUEST_CODE);
            }
        });

        //---------------listview
        tempSetting_adapter = new TempSetting_Adapter(this, arrayListString_listView);
        listView.setAdapter(tempSetting_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView_selected = (TextView) view.findViewById(R.id.textView_textItem_h);

                Toast.makeText(getApplicationContext(), "Selected Item Name is " + textView_selected.getText().toString(), Toast.LENGTH_LONG)
                        .show();
            }
        });

    }// end  method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ADD_TEMP_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){

                listTemperature = new TreeMap<>((Map<Integer, Double>) data.getExtras().get("treeMap"));
                int time_return = data.getIntExtra("newItem_time", 0);
                double temp_return = data.getDoubleExtra("newItem_temp", -900);
                Toast.makeText(getApplicationContext(), "New Temperature rule is added: \n" + (new DTO_TemperatureSetting(time_return, temp_return)).toString(), Toast.LENGTH_LONG)
                        .show();


                if(!arrayListString_listView.isEmpty()){
                    arrayListString_listView.clear();
                }
                // testing display to the window
                DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting();
                for (Map.Entry<Integer, Double> entry : listTemperature.entrySet() ) {
                    Integer time_key = entry.getKey();
                    Double temp = entry.getValue();
                    newTemp.setTemp(temp);
                    newTemp.setTimeOfWeek(time_key);
                    Log.i("list temp", "returned to main " + newTemp.toString());

                    // testing
//                    DTO_TemperatureSetting testObj = new DTO_TemperatureSetting( (newTemp.toString()) );
//                    Log.i("list temp", "returned to main test1 " + testObj.toString());
//
//                    DTO_TemperatureSetting testObj2 = new DTO_TemperatureSetting( (newTemp.displayTime()) );
//                    Log.i("list temp", "returned to main test2 " + testObj2.toString());
                    arrayListString_listView.add(newTemp.displayTime());
                }
//                listView.setAdapter(tempSetting_adapter);
                tempSetting_adapter.notifyDataSetChanged();

            }// end if
        }// end if



//        arrayListString_listView.add("55");
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
//            arrayListString_listView.add(newTemp.displayTime());
//        }
//
//    }

}// end of class
