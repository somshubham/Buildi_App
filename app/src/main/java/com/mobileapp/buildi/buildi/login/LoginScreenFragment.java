package com.mobileapp.buildi.buildi.login;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileapp.buildi.buildi.R;

/**
 * A placeholder fragment containing a simple view.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class LoginScreenFragment extends Fragment {

    public LoginScreenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_login_screen,container,false);

//        Intent intent=new Intent().getExtras();
//        intent.getExtras("data");

        Bundle extras = getActivity().getIntent().getExtras();
        String data=extras.getString("data");

TextView textView2= (TextView)rootView.findViewById(R.id.textView2);
        textView2.setText(""+data);




    return  rootView;

    }
}
