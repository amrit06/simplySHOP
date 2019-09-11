package com.example.simplyshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PANTRYLIST_CUSTOMADAPTER extends ArrayAdapter {
    Context context;
    ArrayList pantryItem;

    PANTRYLIST_CUSTOMADAPTER(Context context, ArrayList pantryItem){
        super(context, R.layout.pantry_eachrow, pantryItem);
        this.context = context;
        this.pantryItem = pantryItem;
    }

    public class VIEWHOLDER{
        TextView label = null;
        CheckBox checkBox = null;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView;
        VIEWHOLDER viewholder;

        if(convertView == null){
            viewholder = new VIEWHOLDER();
            LayoutInflater newInflator = LayoutInflater.from(parent.getContext());
            convertView = newInflator.inflate(R.layout.pantry_eachrow, parent, false);

            viewholder.label = (TextView) convertView.findViewById(R.id.rowLabe);
            viewholder.checkBox = (CheckBox) convertView.findViewById(R.id.rowCheckBox);

            rowView = convertView;
            convertView.setTag(viewholder);
        }else{
            viewholder = (VIEWHOLDER) convertView.getTag();
            rowView = convertView;
        }

        final PANTRY_DATAMODEL data = (PANTRY_DATAMODEL) pantryItem.get(position); // pantryitem is arraylist of object not string remember that

        viewholder.label.setText(data.list);
        viewholder.checkBox.setChecked(data.checked);

        viewholder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!data.checked){
                    data.checked = true;
                }else{
                    data.checked = false;
                }

            }
        });
        return rowView;
    }
}
