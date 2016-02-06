package cos.test.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import cos.test.R;
import cos.test.adapter.TelAdapter;
import cos.test.model.Telecast;

public class TelecastActivity extends AppCompatActivity {

    private RecyclerView rvTel;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    public ArrayList<Telecast> telecasts = new ArrayList<>();
    private final String NUMBER = "number",
            TELECAST = "telecast";
    int number;
    String telecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telecast);

        Intent intent = getIntent();
        number = intent.getIntExtra(NUMBER, 0);
        telecast = intent.getStringExtra(TELECAST);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbMain);
        toolbar.setTitle(telecast);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rvTel = (RecyclerView)findViewById(R.id.rvTel);
        rvTel.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvTel.setLayoutManager(layoutManager);
        new TelThread().execute();
        adapter = new TelAdapter(telecasts);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
            rvTel.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
