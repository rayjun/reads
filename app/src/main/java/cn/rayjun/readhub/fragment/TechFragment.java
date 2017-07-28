package cn.rayjun.readhub.fragment;


import android.os.Bundle;
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
import cn.rayjun.readhub.adapter.HotNewsRecyclerOnScrollListener;
import cn.rayjun.readhub.adapter.TechAdapter;
import cn.rayjun.readhub.api.impl.TechNewsApiImpl;
import cn.rayjun.readhub.entity.TechNewsEntity;
import cn.rayjun.readhub.entity.model.TechNews;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static cn.pedant.SweetAlert.SweetAlertDialog.PROGRESS_TYPE;


public class TechFragment extends Fragment {


    @BindView(R.id.title)
    TextView newsTitle;

    @BindView(R.id.techRecycler)
    RecyclerView recyclerView;

    @BindView(R.id.techSwipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    TechAdapter techAdapter;

    TechNewsApiImpl techNewsApi;

    HashMap<String, Object> params;


    List<TechNews> techNewsList = new ArrayList<>();

    long lastCursor = 0;

    SweetAlertDialog dialog;

    public TechFragment() {
        techNewsApi = new TechNewsApiImpl();
        params = new HashMap<>();
        params.put("pageSize", 10);
        params.put("lastCursor", System.currentTimeMillis());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.tech, container, false);

        ButterKnife.bind(this, view);

        dialog = new SweetAlertDialog(getContext(), PROGRESS_TYPE);
        dialog.setTitleText("加载中...");
        dialog.setCancelable(false);
        dialog.show();

        newsTitle.setText("科技动态");

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(new HotNewsRecyclerOnScrollListener(llm) {
            @Override
            protected void onLoadMore() {
                HashMap<String, Object> loadMoreParams = new HashMap<String, Object>();
                loadMoreParams.put("pageSize", 10);

                if(lastCursor == 0)
                    lastCursor = System.currentTimeMillis();
                else
                    lastCursor = lastCursor - 10 * 60 * 1000;

                loadMoreParams.put("lastCursor", lastCursor);

                Toast.makeText(getContext(), "正在加载更多", Toast.LENGTH_SHORT).show();

                getTechNewsData(loadMoreParams);
            }
        });

        techAdapter = new TechAdapter();
        recyclerView.setAdapter(techAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), llm.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(() -> getTechNewsData(params));

        getTechNewsData(params);

        return view;
    }

    public void getTechNewsData(Map<String, Object> params) {
        techNewsApi.getTechNews(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TechNewsEntity>() {
                    @Override
                    public void accept(@NonNull TechNewsEntity techNewsEntity) throws Exception {
                        refreshNewsData(techNewsEntity.getData());

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


    public void refreshNewsData(List<TechNews> techNewses) {
        this.techNewsList.addAll(techNewses);
        techAdapter.setTechNewses(this.techNewsList);
        techAdapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }


}
