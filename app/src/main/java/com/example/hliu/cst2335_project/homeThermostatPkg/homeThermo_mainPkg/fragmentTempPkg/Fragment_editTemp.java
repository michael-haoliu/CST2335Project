package com.example.hliu.cst2335_project.homeThermostatPkg.homeThermo_mainPkg.fragmentTempPkg;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hliu.cst2335_project.R;
import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by H.LIU on 2017-11-12.
 */

public class Fragment_editTemp extends Fragment{
    private Button button_save;
    private Button button_cancel;
    private Button button_newRule;
    private Button button_delete;


    private NumberPicker numberPicker_hour;
    private NumberPicker numberPicker_min;
    private Spinner spinner_day;
    private double temperature;

    private final double default_temp = 20.0;
    private DTO_TemperatureSetting newTemp;


    private TreeMap<Integer, Double> listTemp;
    private int time_msg;
    private double temperature_msg;

    private View myView;

    private static final int DELETE_ITEM =20;
    //--------------------------------------------
    public Fragment_editTemp() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temp_fragment_temp_setting, container,false);
        myView = view;
        return view;
    }

//    fragmentLayout_tempEdit_h

//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.i("Fragment temp", "Fragment onResume");
//    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        numberPicker_hour = myView.findViewById(R.id.numberPicker_hours_fg_h);
        numberPicker_min = myView.findViewById(R.id.numberPicker_min_fg_h);

        numberPicker_hour.setMinValue(0);
        numberPicker_hour.setMaxValue(23);
        numberPicker_min.setMinValue(0);
        numberPicker_min.setMaxValue(59);

        button_save = myView.findViewById(R.id.button_save_fg_h);
        button_cancel = myView.findViewById(R.id.button_cancel_fg_h);

        button_newRule = myView.findViewById(R.id.button_saveNewRule_fg_h);
        button_delete = myView.findViewById(R.id.button_delete_fg_h);


        spinner_day = myView.findViewById(R.id.spinner_fg_h);
        final EditText temp_editText = myView.findViewById(R.id.temperature_fg_h);

        //-------------get the data from main fragment
        listTemp = new TreeMap<>((Map<Integer, Double>) getActivity().getIntent().getExtras().get("treeMap"));
        time_msg = getActivity().getIntent().getIntExtra("newItem_time", 0);
        temperature_msg = getActivity().getIntent().getDoubleExtra("newItem_temp", -900);


        String str = (new DTO_TemperatureSetting(time_msg,temperature_msg)).toString();

        TextView title = myView.findViewById(R.id.stringText_mainfragement_title);
//        str = R.string.tempRule_selected_String_h + str;
        title.setText(str);

        if(listTemp == null){
            listTemp = new TreeMap<>();
        }else{
        }


        //-------------------------------------------

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newItem_time", time_msg);
                resultIntent.putExtra("newItem_temp", temperature_msg);


                getActivity().setResult(Activity.RESULT_CANCELED, resultIntent);
                getActivity().finish();
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                time_msg = getActivity().getIntent().getIntExtra("newItem_time", 0);
//                temperature_msg = getActivity().getIntent().getDoubleExtra("newItem_temp", -900);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("newItem_time", time_msg);
                resultIntent.putExtra("newItem_temp", temperature_msg);

                getActivity().setResult(DELETE_ITEM, resultIntent);
                getActivity().finish();
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

                    AlertDialog.Builder dialogListener = new AlertDialog.Builder(getActivity().getApplicationContext());
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
        getActivity().setResult(Activity.RESULT_OK, resultIntent);

        //display for testing
        for (Map.Entry<Integer, Double> entry : listTemp.entrySet() ) {
            Integer time_key = entry.getKey();
            Double temp = entry.getValue();
            DTO_TemperatureSetting dto = new DTO_TemperatureSetting(time_key, temp);
            Log.i("list temp", "click the save button - added treeMap " + dto.toString());
        }
        getActivity().finish();
    }

    private void deleteTempItem_exit(DTO_TemperatureSetting newTemp){

//        listTemp.put(newTemp.getTimeOfWeek(), newTemp.getTemp());
        //--------pass the current treelist to add
        Intent resultIntent = new Intent();

        resultIntent.putExtra("newItem_time", newTemp.getTimeOfWeek());
        resultIntent.putExtra("newItem_temp", newTemp.getTemp());

//        listTemp.remove(newTemp.getTimeOfWeek());
//        resultIntent.putExtra("treeMap", listTemp);

        //-------------------
//        getActivity().setResult(Activity.RESULT_OK, resultIntent);

        getActivity().setResult(DELETE_ITEM, resultIntent);

        //display for testing
        for (Map.Entry<Integer, Double> entry : listTemp.entrySet() ) {
            Integer time_key = entry.getKey();
            Double temp = entry.getValue();
            DTO_TemperatureSetting dto = new DTO_TemperatureSetting(time_key, temp);
            Log.i("list temp", "click the save button - added treeMap " + dto.toString());
        }
        getActivity().finish();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }

}
