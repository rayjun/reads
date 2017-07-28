package cn.rayjun.readhub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.rayjun.readhub.adapter.HotNewsDetailAdapter;
import cn.rayjun.readhub.entity.model.HotNews;
import cn.rayjun.readhub.entity.model.HotNewsDetail;

public class HotNewsDetailActivity extends Activity {


    Context context;

    @BindView(R.id.header_title)
    TextView pageTitle;

    @BindView(R.id.back)
    ImageButton backButton;

    @BindView(R.id.news_detail_title)
    TextView title;

    @BindView(R.id.news_detail_abstract)
    TextView newsAbstract;

    @BindView(R.id.news_detail_time)
    TextView newsTime;

    @BindView(R.id.news_detail_list)
    RecyclerView newsDetailRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_detail);
        context = HotNewsDetailActivity.this;
        ButterKnife.bind(this);

        pageTitle.setText("新闻详情");

        Intent intent = getIntent();
        Gson gson = new Gson();
        HotNews hotnews = gson.fromJson(intent.getStringExtra("hotNews"), HotNews.class);


        title.setText(hotnews.getTitle());
        newsAbstract.setText(hotnews.getNewsAbstract());
        newsTime.setText(hotnews.getCreatedAt().substring(0, 19).replace("T", " "));

        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        newsDetailRecyclerView.setLayoutManager(llm);
        HotNewsDetailAdapter hotNewsDetailAdapter = new HotNewsDetailAdapter(hotnews.getHotNewsDetailList());
        newsDetailRecyclerView.setAdapter(hotNewsDetailAdapter);
        newsDetailRecyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, llm.getOrientation());
        newsDetailRecyclerView.addItemDecoration(dividerItemDecoration);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
