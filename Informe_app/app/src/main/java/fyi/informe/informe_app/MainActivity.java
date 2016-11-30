package fyi.informe.informe_app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
import java.util.ArrayList;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.json.*;

public class MainActivity extends Activity {

    //Creating Views & Widgets to connect to UI elements
    private EditText urlToSummarize;
    private Button submitURL, submitRandomArticle;
    private TextView replyFromServer;

    private String APIKEY = "187a1a081bbf4b8a8e1c65020fccb7af";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replyFromServer = (TextView) findViewById(R.id.replyFromServer);
        replyFromServer.setMovementMethod(new ScrollingMovementMethod());

        //Linking variables in code to UI elements
        urlToSummarize = (EditText) findViewById(R.id.urlToSummarize);

        //submiteMailorPhone = (Button) findViewById(R.id.submitMailorPhone);
        submitURL = (Button) findViewById(R.id.submitURL);
        submitRandomArticle = (Button) findViewById(R.id.randomArticle);

        replyFromServer = (TextView) findViewById(R.id.replyFromServer);

        submitRandomArticle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                GETtask GETrandomArticle = new GETtask();

                GETrandomArticle.execute(" ");
            }

        });

        submitURL.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String summarizeThis = urlToSummarize.getText().toString();

                POSTtask POST = new POSTtask();

                POST.execute(summarizeThis);
            }

        });

    }

    //AsyncTask responsible for POST methods. Needs to be placed in AsyncTask because the Main
    //Thread can not be held up with loading taskss; needs to happen in the background.
    private class POSTtask extends AsyncTask<String, String, String> {

        //Main Task. Executes in another thread, in the background.
        @Override
        protected String doInBackground(String... urlToSummarize) {

            HttpURLConnection client = null;
            StringBuilder replyFromServer = new StringBuilder();

            //HTTP Connections operations need to be placed in a try block in case an error is encountered
            try {

                URL url = new URL("http://informe.fyi:5000/");
                client = (HttpURLConnection) url.openConnection();

                //Set properties for the HTTP Request
                client.setRequestMethod("POST");
                client.setDoOutput(true);
                client.setDoInput(true);

                //Encode the request, UTF-8
                byte[] encodedRequest = ("UserUrl=" + urlToSummarize[0]).getBytes("UTF-8");

                //Write to OutputSteam
                OutputStream os = client.getOutputStream();
                os.write(encodedRequest);
                os.flush();
                os.close();
                int responseCode = client.getResponseCode();

                //Read from InputStream, Build the string line by line
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    replyFromServer.append(line);
                }

            } catch (MalformedURLException error) {
                Log.v("Malformed URL", "Malformed URL");
            } catch (SocketTimeoutException error) {
                Log.v("Socket Timeout", "Socket Timeout");
            } catch (IOException error) {
                Log.v("Socket Timeout", "Socket Timeout");
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

            String html = result;
            Document doc = Jsoup.parse(html);
            Element summary = doc.select("section").first().select("p").first();

            replyFromServer.setText(summary.text());
        }
    }

    //Uses an API to retrieve a random popular article to be summarized with other asynctask
    private class GETtask extends AsyncTask<String, String, ArrayList<String>> {

        //Main Task. Executes in another thread, in the background.
        @Override
        protected ArrayList<String> doInBackground(String... blah) {

            HttpURLConnection client = null;
            StringBuilder replyFromServer = new StringBuilder();

            ArrayList<String> workingUrls = new ArrayList<String>();
            workingUrls.add("the-guardian-uk");

            ArrayList<String> urlStrings = new ArrayList<String>();

            //HTTP Connections operations need to be placed in a try block in case an error is encountered
            for (int i = 0; i < workingUrls.size(); i++) {

                try {

                    URL url = new URL("https://newsapi.org/v1/articles?source=" + workingUrls.get(i)
                            + "&sortBy=latest" + "&apiKey=" + APIKEY);
                    client = (HttpURLConnection) url.openConnection();


                    //Set properties for the HTTP Request
                    client.setRequestMethod("GET");
                    client.setDoInput(true);

                    int responseCode = client.getResponseCode();

                    //Read from InputStream, Build the string line by line
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String line;
                    while ((line = br.readLine()) != null) {
                        replyFromServer.append(line);
                    }
                    br.close();

                    //Parsing JSON
                    try {
                        JSONObject JSONresult = new JSONObject(replyFromServer.toString());

                        JSONArray ArticleData = JSONresult.getJSONArray("articles");

                        for (int j = 0; j < ArticleData.length(); j++) {

                            JSONObject temp = ArticleData.getJSONObject(j);

                            urlStrings.add(temp.getString("url"));
                        }

                    } catch (JSONException error) {

                    }

                } catch (MalformedURLException error) {
                    Log.v("Malformed URL", "Malformed URL");
                } catch (SocketTimeoutException error) {
                    Log.v("Socket Timeout", "Socket Timeout");
                } catch (IOException error) {
                    Log.v("Socket Timeout", "Socket Timeout");
                } finally {
                    if (client != null)
                        client.disconnect();
                }

                replyFromServer.setLength(0);
            }

            return urlStrings;
        }

        //Calls POST async task to summarize random URL
        @Override
        protected void onPostExecute(ArrayList<String> result) {

            Random rand = new Random();

            POSTtask POST = new POSTtask();

            POST.execute(result.get(rand.nextInt(result.size())));

        }
    }
}