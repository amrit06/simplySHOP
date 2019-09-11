package com.example.simplyshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class spinnerCustomAdapter extends ArrayAdapter<spinnerDataModel>{

    public spinnerCustomAdapter(Context context, ArrayList spinnerList){
        super(context, 0, spinnerList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_row, parent, false);
        }

        ImageView icon = convertView.findViewById(R.id.spinnerIcon);
        TextView label = convertView.findViewById(R.id.spinnerLabel);


        spinnerDataModel data = getItem(position);
        if(data != null){
            icon.setImageResource(data.getImgId());
            label.setText(data.getLabel());
        }

        return convertView;
    }


}
