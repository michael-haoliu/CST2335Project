package com.example.hliu.cst2335_project.PkgHouse.homeThermo_mainPkg;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.hliu.cst2335_project.PkgHouse.DTO.DTO_TemperatureSetting;
import com.example.hliu.cst2335_project.R;

import java.util.Map;
import java.util.TreeMap;

public class AddNewTempActivity extends Activity {

    private Button button_save;
//    private Button button_newRule;
    private Button button_cancel;
    private NumberPicker numberPicker_hour;
    private NumberPicker numberPicker_min;
    private Spinner spinner_day;
    private double temperature;

    private final double default_temp = 20.0;
    private DTO_TemperatureSetting newTemp;

    private TreeMap<Integer, Double> listTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_activity_add_new_temp);

        numberPicker_hour = (NumberPicker)findViewById(R.id.numberPicker_hours_h);
        numberPicker_min = (NumberPicker)findViewById(R.id.numberPicker_min_h);

        numberPicker_hour.setMinValue(0);
        numberPicker_hour.setMaxValue(23);
        numberPicker_min.setMinValue(0);
        numberPicker_min.setMaxValue(59);

        button_save = (Button)findViewById(R.id.button_save_h);
        button_cancel = (Button)findViewById(R.id.button_cancel_h);

        spinner_day = (Spinner) findViewById(R.id.spinner_h);

        final EditText temp_editText = (EditText) findViewById(R.id.temperature_h);


        listTemp = new TreeMap<>((Map<Integer, Double>) getIntent().getExtras().get("treeMap"));
        if(listTemp == null){
            listTemp = new TreeMap<>();
        }else{

            //test code
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

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
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
                // check if the time exist


                newTemp = new DTO_TemperatureSetting(day, hourInput, minInput, temperature);
//                Log.i("list temp", "click the save button " + newTemp.toString());

                // time rule exists
                if(listTemp.get(newTemp.getTimeOfWeek()) !=null){
                    // 1. over write
                    // 2. cancel; restart
                    AlertDialog.Builder dialogListener = new AlertDialog.Builder(AddNewTempActivity.this);
                    dialogListener.setTitle(R.string.title_addNew_h);
                    dialogListener.setMessage(R.string.timeExist_overwriteQuestion_h);
                    dialogListener.setPositiveButton(R.string.OK_h, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //------------------add to the treemap
                            addNewTempItem_exit(newTemp);
                        }
                    });

                    dialogListener.setNegativeButton(R.string.CANCEL_h, null);
                    dialogListener.create();
                    dialogListener.show();

                }else{
                    addNewTempItem_exit(newTemp);
                }

//                //------------------add to the treemap
//                listTemp.put(newTemp.getTimeOfWeek(), temperature);
//
//                for (Map.Entry<Integer, Double> entry : listTemp.entrySet() ) {
//                    Integer time_key = entry.getKey();
//                    Double temp = entry.getValue();
//                    DTO_TemperatureSetting dto = new DTO_TemperatureSetting(time_key, temp);
////                    newTemp.setTemp(temp);
////                    newTemp.setTimeOfWeek(time_key);
//                    Log.i("list temp", "click the save button - added treeMap " + dto.toString());
//                }
            }
        });
    }

    private void addNewTempItem_exit(DTO_TemperatureSetting newTemp){
        listTemp.put(newTemp.getTimeOfWeek(), newTemp.getTemp());
        //--------pass the current treelist to add
        Intent resultIntent = new Intent();
        resultIntent.putExtra("treeMap", listTemp);

        resultIntent.putExtra("newItem_time", newTemp.getTimeOfWeek());
        resultIntent.putExtra("newItem_temp", newTemp.getTemp());
        //-------------------
        setResult(Activity.RESULT_OK, resultIntent);

        //display for testing
        for (Map.Entry<Integer, Double> entry : listTemp.entrySet() ) {
            Integer time_key = entry.getKey();
            Double temp = entry.getValue();
            DTO_TemperatureSetting dto = new DTO_TemperatureSetting(time_key, temp);
            Log.i("list temp", "click the save button - added treeMap " + dto.toString());
        }
        finish();
    }



//    private boolean isTimeExist(int time){
//        listTemp
//
////        String day = spinner_day.getSelectedItem().toString();
////        int hourInput = numberPicker_hour.getValue();
////        int minInput = numberPicker_min.getMinValue();
////
////        String s = ((EditText)findViewById(R.id.temperature)).toString();
////        temperature = Double.parseDouble( s );
////
////        DTO_TemperatureSetting newTemp = new DTO_TemperatureSetting(day, hourInput, minInput, temperature);
//
//
//        return true;
//    }


}
