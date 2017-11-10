package com.example.hliu.cst2335_project.homeThermostatPkg.adapterPkg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.hliu.cst2335_project.homeThermostatPkg.DTO.DTO_TemperatureSetting;

/**
 * Created by H.LIU on 2017-11-08.
 */

public class TempSettingWin extends ArrayAdapter<DTO_TemperatureSetting> {

    public TempSettingWin(@NonNull Context context) {
        super(context, 0);
    }
//    public TempSettingWin(@NonNull Context context, int resource) {
//        super(context, 0);
//    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public DTO_TemperatureSetting getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
