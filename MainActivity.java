package com.example.celiachu.informe;
//Copy from here to the end, skip the package name because it might have trouble compiling on your computer.
//Package name also differs in AndroidMAnifest file and the res folder.


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.Map;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends Activity {

    private EditText input, input2;
    private Button submit, submit2;
    private TextView replyFromServer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.editText);
        input2 = (EditText) findViewById(R.id.editText2);

        submit = (Button) findViewById(R.id.button2);
        submit2 = (Button) findViewById(R.id.button3);

        submit.setOnClickListener (new OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlToSummarize = input.getText().toString();
                Log.v("Test", urlToSummarize);
            }
        });

        submit2.setOnClickListener (new OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlToSummarize = input2.getText().toString();

                Log.v("Test", urlToSummarize);

                myRunner postRun = new myRunner();

                postRun.execute(urlToSummarize);
            }

        });

    }


    private class myRunner extends AsyncTask<String, String, String> {

        @Override
        protected void onPostExecute(String result) {

            replyFromServer = (TextView) findViewById(R.id.textView3);
            replyFromServer.setText(result);
        }

        @Override
        protected String doInBackground(String... urlToSummarize) {

            HttpURLConnection client = null;
            StringBuilder replyFromServer = new StringBuilder();

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


                Map.Entry<String, String> request = new AbstractMap.SimpleEntry("URL", urlToSummarize[0]);

                Log.v("Test", request.getValue().toString() + "," + request.getKey().toString());

                String encodedRequest = request.getKey() + "=" +  request.getValue();

                OutputStream os = client.getOutputStream();
                os.write(encodedRequest.getBytes("UTF-8"));
                os.flush();
                os.close();

                int responseCode = client.getResponseCode();

                InputStream is = client.getInputStream();

                BufferedReader br = null;
                String line;

                br = new BufferedReader(new InputStreamReader(is));

                while((line = br.readLine()) != null){
                    replyFromServer.append(line);
                }


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

            return replyFromServer.toString();
        }
    }
}
