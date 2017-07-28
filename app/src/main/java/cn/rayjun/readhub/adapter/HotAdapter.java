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
import cn.rayjun.readhub.R;
import cn.rayjun.readhub.entity.model.HotNews;
import cn.rayjun.readhub.util.TimeUtil;

/**
 * Created by ray on 26/07/2017.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.HotViewHolder> {

    public List<HotNews> getHotNewses() {
        return hotNewses;
    }

    public void setHotNewses(List<HotNews> hotNewses) {
        this.hotNewses = hotNewses;
    }

    List<HotNews> hotNewses;

    Context context;


    public HotAdapter() {
        this.hotNewses = new ArrayList<>();
    }

    public HotAdapter(List<HotNews> hotNewses) {
        this.hotNewses = hotNewses;
    }

    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_item, parent, false);

        context = parent.getContext();

        return new HotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        holder.hotNewsTitile.setText(hotNewses.get(position).getTitle());
        holder.hotNewsAbstract.setText(hotNewses.get(position).getNewsAbstract());
        holder.hotNewsCount.setText(hotNewses.get(position).getHotNewsDetailList().size()+" 个新闻");

        String dateTimeStr = hotNewses.get(position).getCreatedAt().substring(0, 19).replace("T", " ");

        holder.hotNewsTime.setText(TimeUtil.showTime(TimeUtil.stringToDate(dateTimeStr, null), null));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, HotNewsDetailActivity.class);
            Gson gson = new Gson();
            intent.putExtra("hotNews", gson.toJson(hotNewses.get(position)));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return hotNewses.size();
    }

    class HotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hot_news_title)
        TextView hotNewsTitile;

        @BindView(R.id.hot_news_abstract)
        TextView hotNewsAbstract;

        @BindView(R.id.hot_news_count)
        TextView hotNewsCount;

        @BindView(R.id.hot_news_time)
        TextView hotNewsTime;

        public HotViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
