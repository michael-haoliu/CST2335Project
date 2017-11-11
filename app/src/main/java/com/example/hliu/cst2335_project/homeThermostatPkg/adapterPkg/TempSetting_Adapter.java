package com.example.hliu.cst2335_project.homeThermostatPkg.adapterPkg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hliu.cst2335_project.R;

import java.util.ArrayList;

/**
 * Created by H.LIU on 2017-11-08.
 */

public class TempSetting_Adapter extends ArrayAdapter<String>{
//    public class TempSetting_Adapter extends ArrayAdapter<String> {
    Context mContext;
    ArrayList<String> arrayString_templist;
//    String[] arrayString_templist;
    LayoutInflater mInflater;

    public TempSetting_Adapter(@NonNull Context context, ArrayList<String> arrayString_templist) {
        super(context, 0);
        this.mContext =context;
        this.arrayString_templist = arrayString_templist;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return arrayString_templist.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return arrayString_templist.get(position);
//        return arrayString_templist[position];
    }


//    @Override
//    public long getItemId(int position) {
//        return arrayString_templist.get(position);
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View result = mInflater.inflate(R.layout.temp_activity_text_view_item,null);
        TextView textView = (TextView) result.findViewById(R.id.textView_textItem_h);
        textView.setText(getItem(position));

//        TextView textView = (TextView)mInflater.inflate(R.layout.temp_activity_text_view_item, null);
//        textView.setText(arrayString_templist[position]);
        return textView;
    }
}
