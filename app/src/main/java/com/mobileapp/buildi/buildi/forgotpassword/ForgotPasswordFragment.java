package com.mobileapp.buildi.buildi.forgotpassword;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mobileapp.buildi.buildi.R;
import com.mobileapp.buildi.buildi.login.Login;

/**
 * A placeholder fragment containing a simple view.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class ForgotPasswordFragment extends Fragment {

    public ForgotPasswordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_forgot_password,container,false);

        Button login=(Button)rootView.findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(), Login.class);
                startActivity(intent);
                getActivity().finish();


            }
        });



        return  rootView;
    }
}
