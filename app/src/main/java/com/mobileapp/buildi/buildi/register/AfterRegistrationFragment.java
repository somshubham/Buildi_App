package com.mobileapp.buildi.buildi.register;

import android.app.Fragment;
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
public class AfterRegistrationFragment extends Fragment {

    public AfterRegistrationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View rootView=inflater.inflate(R.layout.fragment_after_registration,container,false);

//        Intent intent=new Intent().getExtras();
//        intent.getExtras("data");

        Bundle extras = getActivity().getIntent().getExtras();
        String data=extras.getString("data");

        TextView textView2= (TextView)rootView.findViewById(R.id.textView3);
        textView2.setText(""+data);




        return  rootView;

    }
}
