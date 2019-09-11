package com.example.simplyshop;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HOME_FRAGMENT extends Fragment {
    private Context homeContext;

    public HOME_FRAGMENT(Context context){
        this.homeContext = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeView =  inflater.inflate(R.layout.home_fragment, container, false);
        return homeView;
    }
}
