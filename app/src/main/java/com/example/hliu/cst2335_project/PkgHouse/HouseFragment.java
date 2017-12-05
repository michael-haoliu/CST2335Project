package com.example.hliu.cst2335_project.PkgHouse;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hliu.cst2335_project.PkgHouse.homeThermo_mainPkg.HouseThermostatActivity;
import com.example.hliu.cst2335_project.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HouseFragment extends Fragment {
    View view;

    public HouseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_house, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = new Intent(view.getContext(), HouseThermostatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.menu_house);
    }
}
