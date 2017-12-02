package gaetanv.robotmaster;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final Button getApi = (Button) findViewById(R.id.GetApi);

        getApi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String urlString = "http://192.168.99.100:8080/api/card/";
                TextView textHome = (TextView) findViewById(R.id.TextHome);
                try {
                    URL cardEndpoint = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) cardEndpoint.openConnection();
                    conn.setRequestProperty("Accept","application/json");
                    conn.setRequestMethod("GET");
                    conn.connect();
                    if (conn.getResponseCode() == 200) {
                        InputStream responseBody = conn.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                        StringBuilder sb=new StringBuilder();
                        char[] buffer = new char[4096];
                        for(int len; (len = responseBodyReader.read(buffer)) > 0;) {
                            sb.append(buffer, 0, len);
                        }
                        textHome.setText(sb.toString());
                        getApi.setText("BUT NOW IT IS IN MAINTENANCE");
                    } else {
                        textHome.setText("ERROR API");
                    }
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        });

    }

}
