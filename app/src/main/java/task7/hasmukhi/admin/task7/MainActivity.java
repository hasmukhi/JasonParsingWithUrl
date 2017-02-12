package task7.hasmukhi.admin.task7;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {

    private List<Model> arrayListData = new ArrayList<Model>();
    private ListView lsview;
    private CustomList listadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        HttpsURLConnection connection = null;

        URL url = null;
        try{
            url = new URL("https://jsonplaceholder.typicode.com/posts");
            try {
                connection = (HttpsURLConnection) url.openConnection();   //bund connection
                connection.connect();


                InputStream stream = connection.getInputStream();   //data get to the net

                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));//


                StringBuffer buffer = new StringBuffer();
                String line = " ";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String bufferString = buffer.toString();


                String title;
                String body;
                int userid;
                int id;
                lsview = (ListView) findViewById(R.id.listview);
                listadapter = new CustomList(this, arrayListData);
                lsview.setAdapter(listadapter);
                try {
                    JSONArray rootArray = new JSONArray(bufferString);

                    for (int i = 0; i < rootArray.length(); i++) {

                        JSONObject myobj = rootArray.getJSONObject(i);

                        userid = myobj.getInt("userId");
                        id = myobj.getInt("id");
                        title = myobj.getString("title");
                        body = myobj.getString("body");

                        Model md = new Model();
                        md.setUserid(userid);
                        md.setId(id);
                        md.setTitle(title);
                        md.setBody(body);

                        arrayListData.add(md);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
