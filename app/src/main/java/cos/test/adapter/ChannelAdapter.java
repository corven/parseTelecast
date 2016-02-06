package cos.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cos.test.ItemClickListener;
import cos.test.R;
import cos.test.activity.TelecastActivity;
import cos.test.model.Channel;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ViewHolder> {

    private Context context;
    private List<Channel> listChannels;
    private final String NUMBER = "number";

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener clickListener;
        TextView tvChannel;
        ImageView imgChannel;

        public ViewHolder(View v) {
            super(v);
            tvChannel = (TextView) v.findViewById(R.id.tvChannel);
            imgChannel = (ImageView) v.findViewById(R.id.imgChannel);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition(), false);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
    }

    public ChannelAdapter(Context context, List<Channel>listChannels) {
        this.context = context;
        this.listChannels = listChannels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.channel_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Channel currChannel = listChannels.get(position);

        holder.tvChannel.setText(currChannel.getName());
        Picasso.with(context).load(currChannel.getUrl()).into(holder.imgChannel);

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, TelecastActivity.class);
                intent.putExtra(NUMBER, listChannels.get(position).getNumb());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listChannels.size();
    }
}
