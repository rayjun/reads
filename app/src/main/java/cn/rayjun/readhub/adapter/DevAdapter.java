package cn.rayjun.readhub.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.rayjun.readhub.NewsShowActivity;
import cn.rayjun.readhub.R;
import cn.rayjun.readhub.entity.model.DevNews;
import cn.rayjun.readhub.util.TimeUtil;

/**
 * Created by ray on 26/07/2017.
 */

public class DevAdapter extends RecyclerView.Adapter<DevAdapter.DevViewHolder> {

    public List<DevNews> getDevNewses() {
        return devNewses;
    }

    public void setDevNewses(List<DevNews> devNewses) {
        this.devNewses = devNewses;
    }

    List<DevNews> devNewses;

    Context context;


    public DevAdapter() {
        this.devNewses = new ArrayList<>();
    }

    public DevAdapter(List<DevNews> devNewses) {
        this.devNewses = devNewses;
    }

    @Override
    public DevViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dev_item, parent, false);

        context = parent.getContext();

        return new DevViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DevViewHolder holder, int position) {
        holder.devNewsTitile.setText(devNewses.get(position).getTitle());
        holder.devNewsAbstract.setText(devNewses.get(position).getNewsAbstract());

        String dateTimeStr = devNewses.get(position).getPublishDate().substring(0, 19).replace("T", " ");

        holder.devNewsTime.setText(TimeUtil.showTime(TimeUtil.stringToDate(dateTimeStr, null), null));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewsShowActivity.class);

            DevNews devNews = devNewses.get(position);

            intent.putExtra("title", devNews.getTitle());
            intent.putExtra("url", devNews.getUrl());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return devNewses.size();
    }

    class DevViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.dev_news_title)
        TextView devNewsTitile;

        @BindView(R.id.dev_news_abstract)
        TextView devNewsAbstract;

        @BindView(R.id.dev_news_time)
        TextView devNewsTime;

        public DevViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
