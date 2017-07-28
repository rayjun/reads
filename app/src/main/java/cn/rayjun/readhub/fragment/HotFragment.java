package cn.rayjun.readhub.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
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
import cn.rayjun.readhub.adapter.HotAdapter;
import cn.rayjun.readhub.adapter.HotNewsRecyclerOnScrollListener;
import cn.rayjun.readhub.api.HotNewsApi;
import cn.rayjun.readhub.api.impl.HotNewsApiImpl;
import cn.rayjun.readhub.entity.HotNewsEntity;
import cn.rayjun.readhub.entity.model.HotNews;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static cn.pedant.SweetAlert.SweetAlertDialog.PROGRESS_TYPE;


public class HotFragment extends Fragment {


    @BindView(R.id.title)
    TextView newsTitle;

    @BindView(R.id.hotRecycler)
    RecyclerView recyclerView;

    @BindView(R.id.hotSwipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    HotAdapter hotAdapter;

    HotNewsApiImpl hotNewsApi;

    HashMap<String, Object> params;


    SweetAlertDialog dialog;



    List<HotNews> hotNewsesList = new ArrayList<>();

    public HotFragment() {
        // Required empty public constructor
        hotNewsApi = new HotNewsApiImpl();
        params = new HashMap<>();
        params.put("pageSize", 10);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.hot, container, false);
        ButterKnife.bind(this, view);

        dialog = new SweetAlertDialog(getContext(), PROGRESS_TYPE);
        dialog.setTitleText("加载中...");
        dialog.setCancelable(false);
        dialog.show();


        newsTitle.setText("热门话题");

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(new HotNewsRecyclerOnScrollListener(llm) {
            @Override
            protected void onLoadMore() {
                HashMap<String, Object> loadMoreParams = new HashMap<String, Object>();
                loadMoreParams.put("pageSize", 10);
                if(hotNewsesList.size() > 0)
                    loadMoreParams.put("lastCursor", hotNewsesList.get(hotNewsesList.size() - 1).getOrder());

                Toast.makeText(getContext(), "正在加载更多", Toast.LENGTH_SHORT).show();
                getHtNewsData(loadMoreParams);
            }
        });
        hotAdapter = new HotAdapter();

        recyclerView.setAdapter(hotAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), llm.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(() -> getHtNewsData(params));

        getHtNewsData(params);

        return view;
    }

    public void getHtNewsData(Map<String, Object> params) {
        hotNewsApi.getHotNews(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotNewsEntity>() {
                    @Override
                    public void accept(@NonNull HotNewsEntity hotNewsEntity) throws Exception {
                        refreshNewsData(hotNewsEntity.getData());
                        if(dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Logger.e(throwable.getMessage());
                    }
                });
    }

    public void refreshNewsData(List<HotNews> hotNews) {
        this.hotNewsesList.addAll(hotNews);
        hotAdapter.setHotNewses(this.hotNewsesList);
        hotAdapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

}
