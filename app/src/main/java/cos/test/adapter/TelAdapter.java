package cos.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cos.test.R;
import cos.test.model.Telecast;

public class TelAdapter extends RecyclerView.Adapter<TelAdapter.ViewHolder> {

    private List<Telecast> telList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View view;
        TextView tvTime, tvName;

        public ViewHolder(View v) {
            super(v);
            tvTime = (TextView) v.findViewById(R.id.tvTime);
            tvName = (TextView) v.findViewById(R.id.tvName);
        }
    }

    public TelAdapter(List<Telecast> telList) {
        this.telList = telList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tel_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Telecast telecast = telList.get(position);

        holder.tvTime.setText(telecast.getTime());
        holder.tvName.setText(telecast.getName());
    }

    @Override
    public int getItemCount() {
        return telList.size();
    }
}
