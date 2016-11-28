package com.example.celiachu.informe;
//Copy from here to the end, skip the package name because it might have trouble compiling on your computer.
//Package name also differs in AndroidMAnifest file and the res folder.

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Button;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.accountkit.AccountKit;


public class MainActivity extends Activity {

    //Creating Views & Widgets to connect to UI elements
    private EditText eMailorPhone, urlToSummarize;
    private Button submiteMailorPhone, submitURL;
    private TextView replyFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        //Linking variables in code to UI elements
        eMailorPhone = (EditText) findViewById(R.id.eMailorPhone);
        urlToSummarize = (EditText) findViewById(R.id.urlToSummarize);

        submiteMailorPhone = (Button) findViewById(R.id.submitMailorPhone);
        submitURL = (Button) findViewById(R.id.submitURL);

        replyFromServer = (TextView) findViewById(R.id.replyFromServer);

        //onClick listeners to handle what happens when their respective buttons are clicked
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

    //AsyncTask responsible for POST methods. Needs to be placed in AsyncTask because the Main
    //Thread can not be held up with loading taskss; needs to happen in the background.
    private class myRunner extends AsyncTask<String, String, String> {

        //Main Task. Executes in another thread, in the background.
        @Override
        protected String doInBackground(String... urlToSummarize) {

            HttpURLConnection client = null;
            StringBuilder replyFromServer = new StringBuilder();

            //HTTP Connections operations need to be placed in a try block in case an error is encountered
            try {

                URL url = new URL("http://posttestserver.com/post.php");
                client = (HttpURLConnection) url.openConnection();

                //Set properties for the HTTP Request
                client.setRequestMethod("POST");
                client.setDoOutput(true);
                client.setDoInput(true);
                client.setRequestProperty("Accept-Charset", "UTF-8");

                //Encode the request, UTF-8
                byte[] encodedRequest = ("URL=" + urlToSummarize[0]).getBytes("UTF-8");

                Log.v("Test",encodedRequest.toString());

                //Write to OutputSteam
                OutputStream os = client.getOutputStream();
                os.write(encodedRequest);
                os.flush();
                os.close();
                int responseCode = client.getResponseCode();

                //Read from InputStream, Build the string line by line
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

            //Send the built string. After doInBackground executes it goes to onPostExecute
            return replyFromServer.toString();
        }

        //Update the TextView UI element with whatever the server send back
        @Override
        protected void onPostExecute(String result) {

            replyFromServer.setText(result);
        }
    }
}
