package com.mobileapp.buildi.buildi.register;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobileapp.buildi.buildi.R;
import com.mobileapp.buildi.buildi.login.Login;
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
 * A placeholder fragment containing a simple view.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class RegisterFragment extends Fragment {
    private ProgressBar spinner;
    String responseServer;
    TextView email;
    TextView password;
    TextView rpassword;
    String data;
    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_register,container,false);


        Button login=(Button)rootView.findViewById(R.id.button2);
        Button signUp=(Button)rootView.findViewById(R.id.button);

        email=(EditText) rootView.findViewById(R.id.editText);
        password=(EditText) rootView.findViewById(R.id.editText2);
        rpassword=(EditText) rootView.findViewById(R.id.editText3);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailValue=email.getText().toString();
                final String passwordValue=password.getText().toString();
                final String rPasswordValue=rpassword.getText().toString();

                //create the object for the asyncT........and pass the value for registration ........
                AsyncT asyncT = new  AsyncT();
                asyncT.execute(""+emailValue,""+passwordValue,""+rPasswordValue);
                Log.i("Data","Email="+emailValue+" PasswordValue="+passwordValue+" Rpass="+rPasswordValue);



            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(getActivity(),Login.class);
                startActivity(intent);
                getActivity().finish();


            }
        });





        return  rootView;

    }




    /* Inner class to get response */
    class AsyncT extends AsyncTask<String , Void, String> {


        //progress bar ..................


        ProgressDialog loading;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(getActivity(), "Please Wait While loggingin",null, true, true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            loading.dismiss();
            Intent intent=new Intent(getActivity(),AfterRegistration.class);
            intent.putExtra("data",""+data);
            startActivity(intent);
            getActivity().finish();

        }


        //progress bar ends ...............

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://warm-depths-10529.herokuapp.com/signupLocal/?token=Android12345");

            try {


                String username=params[0];
                String password=params[1];
                String rPassword=params[2];


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("email", ""+username));
                nameValuePairs.add(new BasicNameValuePair("password", ""+password));
                nameValuePairs.add(new BasicNameValuePair("rpassword",""+rPassword));


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
            return null;
        }


        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(String.valueOf(aVoid));
data=responseServer;
            //txt.setText(responseServer);
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
        private static String getStringFromInputStream(InputStream is) {

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
