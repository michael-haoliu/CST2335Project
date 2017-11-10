package com.example.hliu.cst2335_project.homeThermostatPkg.homeThermo_mainPkg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.hliu.cst2335_project.R;
import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;

import java.util.Map;
import java.util.TreeMap;

public class AddNewTempActivity extends Activity {

    private Button button_save;
    private Button button_newRule;
    private Button button_cancel;
    private NumberPicker numberPicker_hour;
    private NumberPicker numberPicker_min;
    private Spinner spinner_day;
    private double temperature;

    private final double default_temp = 20.0;

    private TreeMap<Integer, Double> listTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_activity_add_new_temp);

        numberPicker_hour = (NumberPicker)findViewById(R.id.numberPicker_hours);
        numberPicker_min = (NumberPicker)findViewById(R.id.numberPicker_min);

        numberPicker_hour.setMinValue(0);
        numberPicker_hour.setMaxValue(23);
        numberPicker_min.setMinValue(0);
        numberPicker_min.setMaxValue(59);

        button_save = (Button)findViewById(R.id.button_save);
        button_newRule = (Button)findViewById(R.id.button_cancel);
        button_cancel = (Button)findViewById(R.id.button_cancel);

        spinner_day = (Spinner) findViewById(R.id.spinner);

        final EditText temp_editText = (EditText) findViewById(R.id.temperature);


        listTemp = new TreeMap<>((Map<Integer, Double>) getIntent().getExtras().get("treeMap"));
        if(listTemp== null){
            listTemp = new TreeMap<>();
        }else{
            DTO_TemperatureSetting tTemp = new DTO_TemperatureSetting();
            for (Map.Entry<Integer, Double> entry : listTemp.entrySet() ) {
                Integer time_key = entry.getKey();
                Double temp = entry.getValue();
                tTemp.setTemp(temp);
                tTemp.setTimeOfWeek(time_key);
                Log.i("list temp", "click the save button - - treeMap Input " + tTemp.toString());
            }
        }

//---------------------------------------------
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String day;
                int hourInput, minInput;

                day = spinner_day.getSelectedItem().toString();
//                Log.i("list temp", "click the save button " + day );

                hourInput = numberPicker_hour.getValue();
                minInput = numberPicker_min.getValue();
//                Log.i("list temp", "click the save button " + hourInput + " " + minInput);

                String s = temp_editText.getText().toString();
                s= s.trim();
                if((!s.equals(""))){
                    temperature = Double.parseDouble( s );
                }else{
                    temperature = default_temp;
                }
//                Log.i("list temp", "click the save button " + temperature);

//                DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting();
                DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting(day, hourInput, minInput, temperature);
//                Log.i("list temp", "click the save button " + newTemp.toString());

                //------------------add to the treemap
                listTemp.put(newTemp.getTimeOfWeek(), temperature);


                for (Map.Entry<Integer, Double> entry : listTemp.entrySet() ) {
                    Integer time_key = entry.getKey();
                    Double temp = entry.getValue();
                    DTO_TemperatureSetting dto = new DTO_TemperatureSetting(time_key, temp);
//                    newTemp.setTemp(temp);
//                    newTemp.setTimeOfWeek(time_key);
                    Log.i("list temp", "click the save button - added treeMap " + dto.toString());
                }

         //------------------------------------------------
                //--------pass the current treelist to add
                Intent resultIntent = new Intent();
                resultIntent.putExtra("treeMap", listTemp);

                resultIntent.putExtra("newItem_time", newTemp.getTimeOfWeek());
                resultIntent.putExtra("newItem_temp", newTemp.getTemp());
                //-------------------
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

    }

    private boolean isValidTime(){
//        String day = spinner_day.getSelectedItem().toString();
//        int hourInput = numberPicker_hour.getValue();
//        int minInput = numberPicker_min.getMinValue();
//
//        String s = ((EditText)findViewById(R.id.temperature)).toString();
//        temperature = Double.parseDouble( s );
//
//        DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting(day, hourInput, minInput, temperature);


        return true;
    }


}
