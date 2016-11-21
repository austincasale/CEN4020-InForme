package fyi.informe.informme;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /*   HttpURLConnection client = null;
        try {
            URL url = new URL("http://posttestserver.com/post.php/");
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            client.setRequestProperty("Key","Value");
            client.setDoOutput(true);
            OutputStream os = client.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write("ghbj");
            writer.flush();
            writer.close();
            os.close();
            //Log.v("OutputStream", outputPost.toString());
            //outputPost.flush();
            //outputPost.close();
        } catch (MalformedURLException error) {
            //Log.v("Malformed URL", "Malformed URL");
        } catch (SocketTimeoutException error) {
            //Log.v("Socket Timeout","Socket Timeout");
        } catch (IOException error) {
            //Log.v("IO Exception","IO Excpetion");
        }
        finally {
            if(client != null)
                client.disconnect();
        }*/
    }
}
