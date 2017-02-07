package com.mobileapp.buildi.buildi.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapp.buildi.buildi.R;

/**
 * Created by som on 31/01/17.
 */

public class Plans extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.plans, null);

//        etTitle("My new title");
//        getActionBar().setIcon(R.drawable.my_icon);

        return  rootView;
    }

}