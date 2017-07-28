package cn.rayjun.readhub.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.rayjun.readhub.HotNewsDetailActivity;
import cn.rayjun.readhub.NewsShowActivity;
import cn.rayjun.readhub.R;
import cn.rayjun.readhub.entity.model.HotNews;
import cn.rayjun.readhub.entity.model.TechNews;
import cn.rayjun.readhub.util.TimeUtil;

/**
 * Created by ray on 26/07/2017.
 */

public class TechAdapter extends RecyclerView.Adapter<TechAdapter.TechViewHolder> {

    public List<TechNews> getTechNewses() {
        return techNewses;
    }

    public void setTechNewses(List<TechNews> techNewses) {
        this.techNewses = techNewses;
    }

    List<TechNews> techNewses;

    Context context;


    public TechAdapter() {
        this.techNewses = new ArrayList<>();
    }

    public TechAdapter(List<TechNews> techNewses) {
        this.techNewses = techNewses;
    }

    @Override
    public TechViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tech_item, parent, false);

        context = parent.getContext();

        return new TechViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TechViewHolder holder, int position) {
        holder.techNewsTitile.setText(techNewses.get(position).getTitle());
        holder.techNewsAbstract.setText(techNewses.get(position).getNewsAbstract());

        String dateTimeStr = techNewses.get(position).getPublishDate().substring(0, 19).replace("T", " ");

        holder.techNewsTime.setText(TimeUtil.showTime(TimeUtil.stringToDate(dateTimeStr, null), null));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewsShowActivity.class);

            TechNews techNews = techNewses.get(position);

            intent.putExtra("title", techNews.getTitle());
            intent.putExtra("url", techNews.getUrl());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return techNewses.size();
    }

    class TechViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hot_news_title)
        TextView techNewsTitile;

        @BindView(R.id.hot_news_abstract)
        TextView techNewsAbstract;

        @BindView(R.id.hot_news_time)
        TextView techNewsTime;

        public TechViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
