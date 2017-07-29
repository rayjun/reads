package cn.rayjun.readhub.fragment;


import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.rayjun.readhub.R;
import cn.rayjun.readhub.adapter.DevAdapter;
import cn.rayjun.readhub.adapter.HotNewsRecyclerOnScrollListener;
import cn.rayjun.readhub.api.impl.DevNewsApiImpl;
import cn.rayjun.readhub.entity.DevNewsEntity;
import cn.rayjun.readhub.entity.model.DevNews;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static cn.pedant.SweetAlert.SweetAlertDialog.PROGRESS_TYPE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DevFragment extends Fragment {


    @BindView(R.id.title)
    TextView newsTitle;

    @BindView(R.id.devRecycler)
    RecyclerView recyclerView;

    @BindView(R.id.devSwipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    DevAdapter devAdapter;

    DevNewsApiImpl devNewsApi;

    HashMap<String, Object> params;

    List<DevNews> devNewsList = new ArrayList<>();

    long lastCursor = 0;

    SweetAlertDialog dialog;

    public DevFragment() {
        devNewsApi = new DevNewsApiImpl();
        params = new HashMap<>();
        params.put("pageSize", 10);
        params.put("lastCursor", System.currentTimeMillis());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dev, container, false);

        ButterKnife.bind(this, view);

        dialog = new SweetAlertDialog(getContext(), PROGRESS_TYPE);
        dialog.setTitleText("加载中...");
        dialog.setCancelable(false);
        dialog.show();

        newsTitle.setText("开发者资讯");

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(new HotNewsRecyclerOnScrollListener(llm) {
            @Override
            protected void onLoadMore() {
                HashMap<String, Object> loadMoreParams = new HashMap<String, Object>();
                loadMoreParams.put("pageSize", 10);

                if(lastCursor == 0) {
                    lastCursor = System.currentTimeMillis();
                }
                else {
                    lastCursor = lastCursor - 120 * 60 * 1000;
                }
                loadMoreParams.put("lastCursor", lastCursor);

                Toast.makeText(getContext(), "正在加载更多", Toast.LENGTH_SHORT).show();
                getDevNewsData(loadMoreParams);
            }
        });

        devAdapter = new DevAdapter();
        recyclerView.setAdapter(devAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), llm.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(() -> getDevNewsData(params));

        getDevNewsData(params);

        return view;
    }


    public void getDevNewsData(Map<String, Object> params) {
        devNewsApi.getDevNews(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((DevNewsEntity devNewsEntity) -> {
                    refreshNewsData(devNewsEntity.getData());
                    if(dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }, (@NonNull Throwable throwable) -> {
                    Logger.e(throwable.getMessage());
                });
    }


    public void refreshNewsData(List<DevNews> devNewses) {
        this.devNewsList.addAll(devNewses);
        devAdapter.setDevNewses(this.devNewsList);
        devAdapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

}
