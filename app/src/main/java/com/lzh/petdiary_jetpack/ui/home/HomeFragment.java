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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.lzh.libnavannotation.FragmentDestination;
import com.lzh.petdiary_jetpack.R;
import com.lzh.petdiary_jetpack.model.Feed;
import com.lzh.petdiary_jetpack.ui.AbsListFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

@FragmentDestination(pageUrl = "main/tabs/home", asStarter = true)
public class HomeFragment extends AbsListFragment<Feed, HomeViewModel> {

    private HomeViewModel homeViewModel;


    @Override
    protected void afterCreateView() {

    }

    @Override
    public PagedListAdapter getAdapter() {
//        String feedType = getArguments() == null? "all":getArguments().getString("feedType");
        String feedType = "all";
        return new FeedAdapter(getContext(), feedType);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}