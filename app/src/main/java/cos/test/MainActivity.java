package cos.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    public ArrayList<Telecast> telList = new ArrayList<>();
    public TelAdapter telAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.list);
        new NewThread().execute();
        telAdapter = new TelAdapter(MainActivity.this, telList);
    }

    public class NewThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... arg) {

            Document doc;
            try {
                doc = Jsoup.connect("http://tv.cmlt.tv/program?channel=133&time=0").get();

                Elements names = doc.select(".program_name");
                Elements times = doc.select(".program_time");

                telList.clear();

                for (int i = 0; i < names.size(); i++) {
                    String time = times.get(i).text();
                    String name = names.get(i).text();
                    Telecast tel = new Telecast(name, time);
                    telList.add(tel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            lv.setAdapter(telAdapter);
        }
    }
}
