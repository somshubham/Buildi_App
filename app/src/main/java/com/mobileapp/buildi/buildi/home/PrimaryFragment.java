package com.mobileapp.buildi.buildi.home;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileapp.buildi.buildi.R;
import com.mobileapp.buildi.buildi.login.LoginFragment;
import com.mobileapp.buildi.buildi.register.AfterRegistration;
import com.mobileapp.buildi.buildi.register.RegisterFragment;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class PrimaryFragment extends Fragment {
    String responseServer;
    String data;
    String response2Data;
    String jsonResultString;

    public static final String MyPREFERENCES = "MyPrefs" ;
    String uid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.primary_layout,null);


        View rootView = inflater.inflate(R.layout.primarylayoutfin,null);
        //acess the shared preference from the primary fragment ..................

        SharedPreferences sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
         uid = (sharedpreferences.getString("userId",""));
        String firstname=(sharedpreferences.getString("firstname",""));
        String lastname=(sharedpreferences.getString("lastname",""));
        String phone=(sharedpreferences.getString("phone",""));


        TextView textView2= (TextView)rootView.findViewById(R.id.textViewforUid);
        textView2.setText(""+uid);












        AsyncT asyncT = new AsyncT();
        asyncT.execute("","");














        LinearLayout button1=(LinearLayout) rootView.findViewById(R.id.projecttab);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent =new Intent(getActivity(),Home.class);
                startActivity(intent);



            }
        });

        Button button=(Button)rootView.findViewById(R.id.addProjectbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // Toast.makeText(getActivity(), "hi this is add project ....", Toast.LENGTH_SHORT).show();








                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(getActivity());
                View promptsView = li.inflate(R.layout.addproject, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //result.setText(userInput.getText());
                                        Toast.makeText(getActivity(), ""+userInput.getText(), Toast.LENGTH_SHORT).show();



                                        //create the object for the asyncT........and pass the value for creating the project ........
                                        AsyncT asyncT = new AsyncT();
                                        asyncT.execute(""+userInput.getText(),""+uid);

                                        Log.i("somu"+userInput.getText(),""+uid);








                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

























            }
        });





        return  rootView;




    }







    //  #################################################################################



    /* Inner class to get response */
    class AsyncT extends AsyncTask<String , Void, String> {





        private String getUserDatafromJsonString(String JsonStr)
                throws JSONException {


            JSONObject UserJson = new JSONObject(JsonStr);
            String success=UserJson.getString("success");
            String message=UserJson.getString("message");
            //jsonResult=new String[6];
            //jsonResult[0]=success;
            //jsonResult[1]=message;

            JSONArray response=UserJson.getJSONArray("response");
          //  JSONObject response=UserJson.getJSONObject("response");
            Log.i("Mydata",""+response);



            jsonResultString=""+response;

            Log.i("jsonString",""+jsonResultString);

            return  jsonResultString;
        }























        //progress bar ..................


        ProgressDialog loading;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // loading = ProgressDialog.show(getActivity(), "Please Wait While loggingin",null, true, true);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
          //  loading.dismiss();
          //  Intent intent=new Intent(getActivity(),Home.class);
          //  intent.putExtra("data",""+data);
          //  startActivity(intent);
          //  getActivity().finish();
            Log.i("somssss",""+data);




            try {
                String k=getUserDatafromJsonString(data);
                Log.i("mykvalue",""+k);






































            }catch (Exception e)
            {

            }


        }


        //progress bar ends ...............

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://warm-depths-10529.herokuapp.com/project");
            HttpGet httpget=new HttpGet("https://warm-depths-10529.herokuapp.com/project?user=58793459cb71890004b2278a");

            try {


                String projectName=params[0];
                String uid=params[1];



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("projectName", ""+projectName));
                nameValuePairs.add(new BasicNameValuePair("user", ""+uid));



                // Log.i("res",""+jsonobj.toString());
                Log.e("mainToPost", "mainToPost" + nameValuePairs.toString());

                // Use UrlEncodedFormEntity to send in proper format which we need
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                if(params[0].equals(null))

                {

                }else {
                    // Execute HTTP Post Request
                    HttpResponse response = httpclient.execute(httppost);


                    InputStream inputStream = response.getEntity().getContent();
                    InputStreamToStringExample str = new InputStreamToStringExample();
                    responseServer = str.getStringFromInputStream(inputStream);
                }



                HttpResponse response1=httpclient.execute(httpget);
                InputStream inputStream2 = response1.getEntity().getContent();
                InputStreamToStringExample str = new InputStreamToStringExample();
                response2Data = str.getStringFromInputStream(inputStream2);

           Log.e("Allproject",""+response2Data);
                Log.e("response", "response -----" + responseServer);

                data=response2Data;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(String.valueOf(aVoid));
            data=response2Data;
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










    // ###################################################################################
















}
