package com.mobileapp.buildi.buildi.login;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.AndroidHttpClient;
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
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobileapp.buildi.buildi.R;
import com.mobileapp.buildi.buildi.forgotpassword.ForgotPassword;
import com.mobileapp.buildi.buildi.home.Home;
import com.mobileapp.buildi.buildi.home.ProjectListPage;
import com.mobileapp.buildi.buildi.register.Register;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class LoginFragment extends Fragment {


    private ProgressBar spinner;
    String responseServer;
    String data=null;
    String[] jsonResult;
    String[] userData;
    public LoginFragment() {
    }
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

     //   RequestParams params = new RequestParams();
        Button creatAccount =(Button)rootView.findViewById(R.id.button2);
        TextView textView=(TextView)rootView.findViewById(R.id.textView);


        final TextView email=(EditText)rootView.findViewById(R.id.editText);
        final TextView password=(EditText)rootView.findViewById(R.id.editText2);


        final Button login=(Button)rootView.findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailValue = email.getText().toString();
                String passwordValue = password.getText().toString();


                Log.i("Data", "email=" + emailValue + " pass=" + passwordValue);

                //create an object of asyncT......and execute............
                AsyncT asyncT = new AsyncT();
               asyncT.execute(""+emailValue,""+passwordValue);



            }
        });

      // Register clicked ...........

        creatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Register.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

      //forgot password clicked .......

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ForgotPassword.class);
                startActivity(intent);
                getActivity().finish();
            }
        });





        return  rootView;
    }




    /* Inner class to get response */
    class AsyncT extends AsyncTask<String , Void, String> {



        //convert the string into json object.....

        private String[] getUserDatafromJsonString(String JsonStr)
                throws JSONException {


            JSONObject UserJson = new JSONObject(JsonStr);
            String success=UserJson.getString("success");
            String message=UserJson.getString("message");
            jsonResult=new String[6];
            jsonResult[0]=success;
            jsonResult[1]=message;


            JSONObject response=UserJson.getJSONObject("response");
            Log.i("response",""+response);




            JSONObject user=response.getJSONObject("user");
            String firstName=user.getString("firstName");
            int mobileNumber=user.getInt("mobileNumber");
            String lastName=user.getString("lastName");
            String userId=user.getString("_id");

            jsonResult[2]=userId;
            jsonResult[3]=firstName;
            jsonResult[4]=lastName;
            jsonResult[5]=""+mobileNumber;



            Log.i("PrintData",""+success+" message="+message+"   response="+response+" id="+userId);
            Log.i("mywing"," id="+userId+" fn="+firstName+" ln="+lastName+""+mobileNumber);




return  jsonResult;
        }










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
                //making a intent to login as sucess.........
//                Intent intent=new Intent(getActivity(),LoginScreen.class);
//                intent.putExtra("data",""+data);
//                startActivity(intent);
//
//                getActivity().finish();



                try {


                 userData=new String[6];
                 userData=getUserDatafromJsonString(data);


                }
                catch (Exception e)
                {
Log.i("Error in json",""+e);
                }


                SharedPreferences sharedpreferences =getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("firstname", userData[3]);
                editor.putString("Phone", userData[5]);
                editor.putString("userId", userData[2]);
                editor.putString("lastname",userData[4]);
                editor.commit();



                Intent intent=new Intent(getActivity(), ProjectListPage.class);

                //intent.putExtra("data",""+userData);
                intent.putExtra("userId",""+userData[2]);
                intent.putExtra("firstname",""+userData[3]);
                intent.putExtra("lastname",""+userData[4]);
                intent.putExtra("mobileNumber",""+userData[5]);

                startActivity(intent);

                getActivity().finish();








            }


        //progress bar ends ...............

  @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://warm-depths-10529.herokuapp.com/login");

            try {


                         String username=params[0];
                         String password=params[1];


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", ""+username));
                nameValuePairs.add(new BasicNameValuePair("password", ""+password));


               // Log.i("res",""+jsonobj.toString());
                Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                // Use UrlEncodedFormEntity to send in proper format which we need
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
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
