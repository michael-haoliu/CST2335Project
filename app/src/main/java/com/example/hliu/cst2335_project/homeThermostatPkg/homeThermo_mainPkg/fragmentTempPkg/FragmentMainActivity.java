package com.example.hliu.cst2335_project.homeThermostatPkg.homeThermo_mainPkg.fragmentTempPkg;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.example.hliu.cst2335_project.R;

public class FragmentMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_fragment_main);

        Fragment editFragment = new Fragment_editTemp();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.tempContainer_h, editFragment)
                .commit();
    }
}
