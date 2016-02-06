package cos.test.activity;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import cos.test.R;
import cos.test.adapter.ChannelAdapter;
import cos.test.model.Channel;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Channel> channels = new ArrayList<>();
    private RecyclerView rvChannel;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbMain);

        setSupportActionBar(toolbar);

        rvChannel = (RecyclerView) findViewById(R.id.rvChannels);
        rvChannel.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvChannel.setLayoutManager(layoutManager);

        new ChannelThread().execute();
        adapter = new ChannelAdapter(this, channels);
    }

    public class ChannelThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... arg) {
            Document doc;
            try {
                doc = Jsoup.connect("http://tv.cmlt.tv/channels").get();
                Elements names = doc.select(".channel_btn");
                channels.clear();
                for (int i = 0; i < names.size(); i++) {
                    int numb = Integer.parseInt(names.get(i).attr("channelid"));
                    String name = names.get(i).attr("channelname");
                    String url = names.get(i).attr("ID");
                    StringBuilder sbUrl = new StringBuilder(url.subSequence(7, url.length()));
                    url = "http://tv.cmlt.tv/img/ch_btn/" + sbUrl + ".gif";
                    Channel channel = new Channel(name, numb, url);
                    channels.add(channel);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Сортировка, пока такая
            for (int i = 0; i < channels.size() - 1; i++) {
                for (int j = i; j < channels.size(); j++) {
                    if (channels.get(j).getNumb() < channels.get(i).getNumb()) {
                        Channel c = channels.get(i);
                        channels.set(i, channels.get(j));
                        channels.set(j, c);
                    }
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            rvChannel.setAdapter(adapter);
        }
    }
}
