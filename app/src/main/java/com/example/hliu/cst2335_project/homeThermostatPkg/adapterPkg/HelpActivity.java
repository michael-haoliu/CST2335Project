package com.example.hliu.cst2335_project.homeThermostatPkg.adapterPkg;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hliu.cst2335_project.R;

public class HelpActivity extends Activity {

    private String helpItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_activity_help);

        TextView textView_help = findViewById(R.id.helptextID_h);


        helpItem = getIntent().getStringExtra("helpItem");
        switch(helpItem){
            case "helpAdd":

                textView_help.setText(Html.fromHtml (getString(R.string.helpText_stringHTML_Add)) );

                        Toast.makeText(getApplicationContext(), "Navigation bar add Clicked!",
                                Toast.LENGTH_SHORT).show();
                break;
            case "helpDelete":
                textView_help.setText(Html.fromHtml(  getString(R.string.helpText_stringHTML_delete)));
                        Toast.makeText(getApplicationContext(), "Navigation bar delete Clicked!",
                                Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
