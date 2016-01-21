package cos.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cos.test.model.Channel;
import cos.test.R;

public class ChannelAdapter extends ArrayAdapter<Channel> {

    ArrayList<Channel> channels;

    public ChannelAdapter(Context context, ArrayList<Channel> channels) {
        super(context, R.layout.channel_item, channels);
        this.channels = channels;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.channel_item, parent, false);
        }

        Channel currChannel = channels.get(position);

        TextView channel = (TextView) v.findViewById(R.id.tvChannel);
        channel.setText(currChannel.getName());

        return v;
    }
}
