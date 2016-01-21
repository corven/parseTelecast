package cos.test.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import cos.test.R;
import cos.test.activity.TelecastActivity;
import cos.test.adapter.ChannelAdapter;
import cos.test.model.Channel;


public class FragmentChannels extends Fragment {

    ListView lvChannel;
    ArrayList<Channel> channels = new ArrayList<>();
    ChannelAdapter adapter;
    final String NUMBER = "number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_channels, container, false);
        lvChannel = (ListView) v.findViewById(R.id.listChannels);
        new ChannelThread().execute();
        adapter = new ChannelAdapter(getActivity(), channels);


        return v;
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
                    Channel channel = new Channel(name, numb);
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
            lvChannel.setAdapter(adapter);
            lvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), TelecastActivity.class);
                    intent.putExtra(NUMBER, channels.get(position).getNumb());
                    startActivity(intent);
                }
            });
        }
    }
}
