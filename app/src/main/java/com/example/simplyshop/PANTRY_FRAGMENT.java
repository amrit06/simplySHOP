package com.example.simplyshop;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class PANTRY_FRAGMENT extends Fragment {

    private LISTFRAG listPage;
    private ADDPANTRY_FRAGMENT addPantryPage;
    private Button add, listButton, edit;
    private ListView pantryList;
    private FragmentActivity mycontext;

    private Context pantryContext;


    private ArrayList<String> userInput;
    private ArrayList pantryRecord;
    private PANTRYLIST_CUSTOMADAPTER customAdapter;



    public PANTRY_FRAGMENT(Context context){
        this.pantryContext = context;
    }


    @Override
    public void onAttach(Context context) {
        mycontext = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pantryView = inflater.inflate(R.layout.pantry_fragment, container, false);

        listPage = new LISTFRAG();
        addPantryPage = new ADDPANTRY_FRAGMENT();
        add = (Button) pantryView.findViewById(R.id.pantryadd_button);
        listButton = (Button) pantryView.findViewById(R.id.showList_button);
        pantryList = pantryView.findViewById(R.id.list_pantry); // list view

        // display fragment
      /*  mycontext
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.pantry_subFragment, listPage)
                .addToBackStack("listPage")
                .commit();*/


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mycontext
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pantry_subFragment, addPantryPage )
                        .addToBackStack("addPantryPage")
                        .commit();
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mycontext
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.pantry_subFragment, listPage )
                        .addToBackStack("addPantryPage")
                        .commit();
            }
        });

//        setHasOptionsMenu(true);
        return pantryView;
    }


    // if we need menu instead of button. but doesn't work need fixing
   /* @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
      inflater.inflate(R.menu.pantry_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()){
            case R.id.addPantry:
                selectedFragment = addPantryPage;
                break;
            case R.id.list_pantry:
                selectedFragment = listPage;
                break;
        }
        mycontext
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.pantry_subFragment, selectedFragment)
                .commit();
        return super.onOptionsItemSelected(item);
    }
    */

}
