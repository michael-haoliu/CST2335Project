package com.example.hliu.cst2335_project.homeThermostatPkg.homeThermo_mainPkg.fragmentTempPkg;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hliu.cst2335_project.R;

/**
 * Created by H.LIU on 2017-11-12.
 */

public class Fragment_editTemp extends Fragment{
    public Fragment_editTemp(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.temp_fragment_temp_setting,container,false);
        return view;
    }
}
