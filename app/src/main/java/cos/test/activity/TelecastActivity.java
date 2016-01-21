package cos.test.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import cos.test.R;
import cos.test.adapter.TelAdapter;
import cos.test.model.Telecast;

public class TelecastActivity extends AppCompatActivity {

    private ListView lvTel;
    public ArrayList<Telecast> telecasts = new ArrayList<>();
    public TelAdapter adapter;
    final String NUMBER = "number";
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telecast);

        Intent intent = getIntent();
        number = intent.getIntExtra(NUMBER, 0);

        lvTel = (ListView) findViewById(R.id.listTel);
        new TelThread().execute();
        adapter = new TelAdapter(TelecastActivity.this, telecasts);
    }

    public class TelThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... arg) {

            Document doc;
            try {
                doc = Jsoup.connect("http://tv.cmlt.tv/program?channel=" + number + "&time=0").get();

                Elements names = doc.select(".program_name");
                Elements times = doc.select(".program_time");

                telecasts.clear();

                for (int i = 0; i < names.size(); i++) {
                    String time = times.get(i).text();
                    String name = names.get(i).text();
                    Telecast tel = new Telecast(name, time);
                    telecasts.add(tel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            lvTel.setAdapter(adapter);
        }
    }

}
