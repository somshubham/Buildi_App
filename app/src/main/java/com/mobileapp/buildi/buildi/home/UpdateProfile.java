package com.mobileapp.buildi.buildi.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileapp.buildi.buildi.R;
import com.mobileapp.buildi.buildi.login.LoginFragment;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by som on 22/01/17.
 */

public class UpdateProfile extends Fragment


    {
        private ProgressBar spinner;
        String responseServer;
        String data=null;
        TextView firstName,lastName,mobileNumber;
        String firstNameValue;
        String lastNameValue;
        String mobileNumberValue;
        String userId;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.updateprofile_layout,null);

             firstName=(EditText) rootView.findViewById(R.id.editText);
             lastName=(EditText) rootView.findViewById(R.id.editText3);
             mobileNumber=(EditText) rootView.findViewById(R.id.editText2);



            Button button=(Button)rootView.findViewById(R.id.button);

          userId="58793459cb71890004b2278a";//     58793459cb71890004b2278a

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firstNameValue=firstName.getText().toString();
                    lastNameValue=lastName.getText().toString();
                    mobileNumberValue=mobileNumber.getText().toString();


                    Toast.makeText(getActivity(), "hi"+firstNameValue+" "+lastNameValue+""+mobileNumberValue, Toast.LENGTH_SHORT).show();




                    AsyncT asyncT = new AsyncT();
                    asyncT.execute(""+firstNameValue,""+lastNameValue,""+mobileNumberValue,""+userId);




                }
            });









            return rootView;

    }




        /* Inner class to get response */
        class AsyncT extends AsyncTask<String , Void, String> {


            //progress bar ..................


            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Please Wait While Updating the Profile",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //making a intent to login as sucess.........
//                Intent intent=new Intent(getActivity(),LoginScreen.class);
//                intent.putExtra("data",""+data);
//                startActivity(intent);
//
//                getActivity().finish();

                Intent intent=new Intent(getActivity(), Home.class);
                intent.putExtra("data",""+data);
                startActivity(intent);

                getActivity().finish();








            }


            //progress bar ends ...............

            @Override
            protected String doInBackground(String... params) {
                HttpClient httpclient = new DefaultHttpClient();
               // HttpPost httppost = new HttpPost("https://warm-depths-10529.herokuapp.com/updateProfile/?userId="+userid);

                String userid=""+params[3];
                HttpPost httppost = new HttpPost("https://warm-depths-10529.herokuapp.com/updateProfile/?userId="+userid);

                try {


                    String firstname=params[0];
                    String lastname=params[1];
                    String mobile=params[2];

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("username", ""+firstname));
                    nameValuePairs.add(new BasicNameValuePair("lastName", ""+lastname));
                    nameValuePairs.add(new BasicNameValuePair("mobileNumber",""+mobile));

                    // Log.i("res",""+jsonobj.toString());
                    Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                    // Use UrlEncodedFormEntity to send in proper format which we need
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    // Execute HTTP Post Request
                    HttpResponse response = httpclient.execute(httppost);
                    InputStream inputStream = response.getEntity().getContent();
                    LoginFragment.InputStreamToStringExample str = new LoginFragment.InputStreamToStringExample();
                    responseServer = str.getStringFromInputStream(inputStream);
                    Log.e("response", "response -----" + responseServer);



                    data=responseServer;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return responseServer;
            }


            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(String.valueOf(aVoid));

                data=responseServer;





            }
        }

        public static class InputStreamToStringExample {

            public static void main(String[] args) throws IOException {

                // intilize an InputStream
                InputStream is =
                        new ByteArrayInputStream("file content..blah blah".getBytes());

                String result = getStringFromInputStream(is);

                System.out.println(result);
                System.out.println("Done");

            }

            // convert InputStream to String
            public static String getStringFromInputStream(InputStream is) {

                BufferedReader br = null;
                StringBuilder sb = new StringBuilder();

                String line;
                try {

                    br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return sb.toString();
            }

        }

}
