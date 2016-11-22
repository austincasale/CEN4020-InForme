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

    private EditText eMailorPhone, urlToSummarize;
    private Button submiteMailorPhone, submitURL;
    private TextView replyFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eMailorPhone = (EditText) findViewById(R.id.eMailorPhone);
        urlToSummarize = (EditText) findViewById(R.id.urlToSummarize);

        submiteMailorPhone = (Button) findViewById(R.id.submitMailorPhone);
        submitURL = (Button) findViewById(R.id.submitURL);

        submiteMailorPhone.setOnClickListener (new OnClickListener() {
            @Override
            public void onClick(View v) {

                String LoginInfo = eMailorPhone.getText().toString();
                Log.v("Test", LoginInfo);
            }
        });

        submitURL.setOnClickListener (new OnClickListener() {
            @Override
            public void onClick(View v) {

                String summarizeThis = urlToSummarize.getText().toString();

                myRunner postRun = new myRunner();

                postRun.execute(summarizeThis);
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

                byte[] encodedRequest = (request.getKey() + "=" +  request.getValue()).getBytes("UTF-8");

                OutputStream os = client.getOutputStream();
                os.write(encodedRequest);
                os.flush();
                os.close();
                int responseCode = client.getResponseCode();

                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;
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
