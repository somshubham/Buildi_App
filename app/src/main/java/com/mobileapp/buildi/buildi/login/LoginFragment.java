package com.mobileapp.buildi.buildi.login;

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
import org.json.JSONObject;

import com.mobileapp.buildi.buildi.R;
import com.mobileapp.buildi.buildi.forgotpassword.ForgotPassword;
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


    String REGISTER_URL="https://warm-depths-10529.herokuapp.com/login";

    public LoginFragment() {
    }

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


               // login(emailValue, passwordValue);
                AsyncT asyncT = new AsyncT();
                asyncT.execute();
            }
        });






        creatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Register.class);
                startActivity(intent);
                getActivity().finish();

            }
        });



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


























//
//   private void login(String email,String password) {
//        class RegisterUser extends AsyncTask<String, Void, String> {
//            ProgressDialog loading;
//            RegisterUserClass ruc = new RegisterUserClass();
//
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(getActivity(), "Please Wait While loggingin",null, true, true);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//
//                HashMap<String, String> data = new HashMap<String,String>();
//                data.put("email",params[0]);
//                data.put("password",params[1]);
//                String result = ruc.sendPostRequest(REGISTER_URL,data);
//                Log.i("Result",""+result);
//                return  result;
//            }
//        }
//
//        RegisterUser ru = new RegisterUser();
//        ru.execute(email,password);
//    }
//














//
//    private void login(String email,String password) {
//
//        InputStream inputStream = null;
//        String result = "";
//        try {
//
//            URL url = new URL("https://warm-depths-10529.herokuapp.com/login");
//            // 1. create HttpClient
//            HttpClient httpclient = new DefaultHttpClient();
//
//            // 2. make POST request to the given URL
//            HttpPost httpPost = new HttpPost(String.valueOf(url));
//
//            String json = "";
//
//            // 3. build jsonObject
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("username", ""+email);
//            jsonObject.accumulate("password", ""+password);
//
//            // 4. convert JSONObject to JSON to String
//            json = jsonObject.toString();
//
//            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
//            // ObjectMapper mapper = new ObjectMapper();
//            // json = mapper.writeValueAsString(person);
//
//            // 5. set json to StringEntity
//            StringEntity se = new StringEntity(json);
//
//            // 6. set httpPost Entity
//            httpPost.setEntity(se);
//
//            // 7. Set some headers to inform server about the type of the content
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//
//            // 8. Execute POST request to the given URL
//            HttpResponse httpResponse = httpclient.execute(httpPost);
//
//            // 9. receive response as inputStream
//            inputStream = httpResponse.getEntity().getContent();
//
//            // 10. convert inputstream to string
//            if(inputStream != null) {
//                result = convertInputStreamToString(inputStream);
//                Log.i("data",""+result);
//            }
//            else {
//                result = "Did not work!";
//            }
//
//        } catch (Exception e) {
//            Log.d("InputStream", e.getLocalizedMessage());
//        }
//
//    }
//
//
//
//    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
//        String line = "";
//        String result = "";
//        while((line = bufferedReader.readLine()) != null)
//            result += ""+line;
//
//        inputStream.close();
//        return result;
//
//    }
//


    // ###############################################

    String responseServer;

    /* Inner class to get response */
    class AsyncT extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://warm-depths-10529.herokuapp.com/login");

            try {

                JSONObject jsonobj = new JSONObject();

                jsonobj.put("username", "somshubhamsandroid@gmail.com");
                jsonobj.put("password", "12345");

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("req", jsonobj.toString()));
           Log.i("res",""+jsonobj.toString());
                Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                // Use UrlEncodedFormEntity to send in proper format which we need
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                InputStream inputStream = response.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
                responseServer = str.getStringFromInputStream(inputStream);
                Log.e("response", "response -----" + responseServer);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

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
