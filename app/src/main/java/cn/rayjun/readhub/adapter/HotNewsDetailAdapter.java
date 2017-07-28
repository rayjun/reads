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
import cn.rayjun.readhub.entity.model.HotNewsDetail;

/**
 * Created by ray on 26/07/2017.
 */

public class HotNewsDetailAdapter extends RecyclerView.Adapter<HotNewsDetailAdapter.HotNewsDetailViewHolder> {


    List<HotNewsDetail> hotNewsDetailList;

    Context context;


    public HotNewsDetailAdapter() {
        this.hotNewsDetailList = new ArrayList<>();
    }

    public HotNewsDetailAdapter(List<HotNewsDetail> list) {
        this.hotNewsDetailList = list;
    }


    @Override
    public HotNewsDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_detail_item, parent, false);

        context = parent.getContext();

        return new HotNewsDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotNewsDetailViewHolder holder, int position) {

        holder.hotDetailItemTitle.setText(hotNewsDetailList.get(position).getTitle());
        holder.hotDetailItemSite.setText(hotNewsDetailList.get(position).getSiteName());
        holder.hotDetailItemName.setText(hotNewsDetailList.get(position).getAuthorName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewsShowActivity.class);

            HotNewsDetail hotNewsDetail = hotNewsDetailList.get(position);

            if(null != hotNewsDetail.getMobileUrl() && ! "".equals(hotNewsDetail.getMobileUrl())) {
                intent.putExtra("url",hotNewsDetail.getMobileUrl());
            }
            else {
                intent.putExtra("url",hotNewsDetail.getUrl());
            }
            intent.putExtra("title", hotNewsDetail.getTitle());

            context.startActivity(intent);
        });
    }


    public List<HotNewsDetail> getHotNewsDetailList() {
        return hotNewsDetailList;
    }

    public void setHotNewsDetailList(List<HotNewsDetail> hotNewsDetailList) {
        this.hotNewsDetailList = hotNewsDetailList;
    }

    @Override
    public int getItemCount() {
        return hotNewsDetailList.size();
    }

    class HotNewsDetailViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.news_detail_item_title)
        TextView hotDetailItemTitle;

        @BindView(R.id.news_detail_item_site)
        TextView hotDetailItemSite;

        @BindView(R.id.news_detail_item_name)
        TextView hotDetailItemName;


        public HotNewsDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
