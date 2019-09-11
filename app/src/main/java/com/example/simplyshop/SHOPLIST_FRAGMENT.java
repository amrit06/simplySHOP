package com.example.simplyshop;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SHOPLIST_FRAGMENT extends Fragment {
    private Context shopListContext;

    public SHOPLIST_FRAGMENT(Context context){
        this.shopListContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View shopListView = inflater.inflate(R.layout.shop_fragment, container, false);
        return shopListView;
    }
}
