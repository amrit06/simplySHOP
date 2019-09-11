package com.example.simplyshop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class LISTFRAG extends Fragment {

    private ArrayList<String[]> tableRows; // arraylist to rows which are string
    private ArrayList objectRow; // arraylist to dataModelobjecteee
    private ListView list;
    private PANTRYLIST_CUSTOMADAPTER customadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View listFragView = inflater.inflate(R.layout.list_frag, container, false);

        objectRow = new ArrayList();
        tableRows = new ArrayList<>();

        retrieveDatabseContent();

        for (int i = 0 ; i < tableRows.size(); i++){
            String[] columns = new String[6];
            columns = tableRows.get(i);
            objectRow.add(new PANTRY_DATAMODEL(columns[1], 0, false));
        }


        list = (ListView) listFragView.findViewById(R.id.list_pantry);

        customadapter = new PANTRYLIST_CUSTOMADAPTER(getContext(), objectRow);
        list.setAdapter(customadapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PANTRY_DATAMODEL datamodel = (PANTRY_DATAMODEL) objectRow.get(i);
                datamodel.checked = !datamodel.checked; //set checked if not checked.
                customadapter.notifyDataSetChanged();
            }
        });


        return listFragView;
    }

    public void retrieveDatabseContent(){
        DATABASEMANGAER myDB = new DATABASEMANGAER(getContext());
        myDB.openReadable();
        tableRows = myDB.retrieveRows();
        myDB.close();
    }

}
