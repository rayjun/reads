package cn.rayjun.readhub.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ray on 26/07/2017.
 */

public abstract class HotNewsRecyclerOnScrollListener extends
        RecyclerView.OnScrollListener {


    private boolean loading = true;

    int firstVisibleItem, visibleItemCount, totalItemCount, previousTotal = 0;

    private LinearLayoutManager linearLayoutManager;

    public HotNewsRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if(loading) {
            if(totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        if(!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            loading = true;
            onLoadMore();
        }
    }


    protected abstract void onLoadMore();
}
