package com.mobileapp.buildi.buildi.login;

import java.io.IOException;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class TestLogin {
    OkHttpClient client = new OkHttpClient();
    // code request code here
    String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // post request code here

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    // test data
    String bowlingJson(String player1, String player2) {
        return "{'username':'somshubhamsandroid@gmail.com',"
                + "'password':'12345'"
               + "}";
    }

    String doPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void main(String[] args) throws IOException {

        // issue the Get request
        TestMain example = new TestMain();
        String getResponse = example.doGetRequest("http://www.vogella.com");
       // System.out.println(getResponse);


        // issue the post request

        String json = example.bowlingJson("x", "x");
        String postResponse = example.doPostRequest("https://warm-depths-10529.herokuapp.com/login", json);
        System.out.println(postResponse);
    }
}

