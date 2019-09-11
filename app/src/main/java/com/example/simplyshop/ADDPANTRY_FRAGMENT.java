package com.example.simplyshop;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class ADDPANTRY_FRAGMENT extends Fragment {

    private  EditText id, name, price, quantity, location;
    private int imgID;
    private Button b1;
    private ArrayList<String> productInfo;

    private ArrayList<spinnerDataModel> data;
    private spinnerCustomAdapter spinnerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View addFragView = inflater.inflate(R.layout.pantryadd_fragment, container, false);


        b1 = (Button) addFragView.findViewById(R.id.pantrySubmit);
        id = (EditText) addFragView.findViewById(R.id.idInput);
        name = (EditText) addFragView.findViewById(R.id.nameInput);
        price = (EditText) addFragView.findViewById(R.id.priceInput);
        quantity = (EditText) addFragView.findViewById(R.id.quantityInput);
        location = (EditText) addFragView.findViewById(R.id.locationInput);

        productInfo = new ArrayList();

        data = new ArrayList<>();
        String[] spinnerMenu = getResources().getStringArray(R.array.spinnerMenu);

        // change icon to fruits and vegetable later
        data.add(new spinnerDataModel(spinnerMenu[0], R.drawable.ic_home_black_24dp));
        data.add(new spinnerDataModel(spinnerMenu[1], R.drawable.ic_add_shopping_cart_black_24dp));
        data.add(new spinnerDataModel(spinnerMenu[2], R.drawable.ic_delete_black_24dp));
        data.add(new spinnerDataModel(spinnerMenu[3], R.drawable.ic_local_pizza_black_24dp));

        final Spinner spinner = addFragView.findViewById(R.id.spinner_pantry);
        spinnerAdapter = new spinnerCustomAdapter(getContext(), data);
        spinner.setAdapter(spinnerAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerDataModel clickedItem = (spinnerDataModel) adapterView.getItemAtPosition(i);
                imgID = clickedItem.getImgId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receiveUserInput();
                if(!productInfo.isEmpty()){
                    writeToDatabase();
                    Toast.makeText(getContext(), "Data retrieved" , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(), "Please Enter Input first Before Submitting" , Toast.LENGTH_LONG).show();
                }
            }
        });

        return addFragView;
    }

    public void receiveUserInput(){
        productInfo.add(id.getText().toString());
        productInfo.add(name.getText().toString());
        productInfo.add(price.getText().toString());
        productInfo.add(quantity.getText().toString());
        productInfo.add(location.getText().toString());
        productInfo.add(Integer.toString(imgID));
    }

    public void writeToDatabase(){
        DATABASEMANGAER myDB = new DATABASEMANGAER(getContext());
        myDB.createRow( Integer.parseInt(productInfo.get(0)),
                productInfo.get(1),
                productInfo.get(2),
                productInfo.get(3),
                productInfo.get(4),
                productInfo.get(5));
        myDB.close();
    }

}