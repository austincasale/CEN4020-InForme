package com.example.celiachu.informe;

import android.icu.util.Output;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRunner postRun = new myRunner();
        postRun.execute();

    }

    private class myRunner extends AsyncTask<String, String, String> {

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected String doInBackground(String... blah) {

            HttpURLConnection client = null;

            try {

                URL url = new URL("http://posttestserver.com/post.php");
                client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("POST");
                client.setDoOutput(true);
                client.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
                client.setDoInput(true);
                client.setReadTimeout(10000);
                client.setConnectTimeout(15000);
                client.setRequestProperty("User-Agent", "Mozilla/5.0");


                Map.Entry<String, String> request = new AbstractMap.SimpleEntry("URL", url.toString());

                Log.v("Test", request.getValue().toString() + "," + request.getKey().toString());

                String encodedRequest = request.getKey() + "=" +  request.getValue();

                Log.v("Test", '|' + encodedRequest +'|');

                OutputStream os = client.getOutputStream();
                os.write(encodedRequest.getBytes("UTF-8"));
                os.flush();
                os.close();


                int responseCode = client.getResponseCode();

                //Log.v("Test", Integer.toString(responseCode));

/*
                InputStream is = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

;               StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "\n");
                }
                Log.v("Test", "Here");*/

                //Log.v("OutputStream", outputPost.toString());
                //outputPost.flush();
                //outputPost.close();
                //client.connect();

            } catch (MalformedURLException error) {
                Log.v("Malformed URL", "Malformed URL");
            } catch (SocketTimeoutException error) {
                Log.v("Socket Timeout","Socket Timeout");
            } catch (IOException error) {
                Log.v("Socket Timeout","Socket Timeout");
            } finally {
                if (client != null)
                    client.disconnect();
            }

            return null;
        }
    }
}
