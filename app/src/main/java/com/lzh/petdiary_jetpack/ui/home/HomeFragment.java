package com.lzh.petdiary_jetpack.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.lzh.libnavannotation.FragmentDestination;
import com.lzh.petdiary_jetpack.R;
import com.lzh.petdiary_jetpack.model.Feed;
import com.lzh.petdiary_jetpack.ui.AbsListFragment;
import com.lzh.petdiary_jetpack.ui.MutableDataSource;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

@FragmentDestination(pageUrl = "main/tabs/home", asStarter = true)
public class HomeFragment extends AbsListFragment<Feed, HomeViewModel> {

    private HomeViewModel homeViewModel;


    @Override
    protected void afterCreateView() {
        mViewModel.getCacheLiveData().observe(this, new Observer<PagedList<Feed>>() {
            @Override
            public void onChanged(PagedList<Feed> feeds) {
                //缓存数据就能更新到列表
                adapter.submitList(feeds);
            }
        });
    }

    @Override
    public PagedListAdapter getAdapter() {
//        String feedType = getArguments() == null? "all":getArguments().getString("feedType");
        String feedType = "all";
        return new FeedAdapter(getContext(), feedType);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        //只要有一次返回了空数据， pageing框架不会再帮我们做数据的分页逻辑了，就要自己触发
        //分页逻辑
        Feed feed = adapter.getCurrentList().get(adapter.getItemCount() -1);
        mViewModel.loadAfter(feed.id, new ItemKeyedDataSource.LoadCallback<Feed>(){
            @Override
            public void onResult(@NonNull List<Feed> data) {
                PagedList.Config config = adapter.getCurrentList().getConfig();
                if(data != null && data.size() > 0){
                    MutableDataSource dataSource = new MutableDataSource<>();
                    dataSource.data.addAll(data);
                    PagedList pagedList = dataSource.buildNewPagedList(config);
                    submitList(pagedList);
                }
            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mViewModel.getDataSource().invalidate();
    }
}