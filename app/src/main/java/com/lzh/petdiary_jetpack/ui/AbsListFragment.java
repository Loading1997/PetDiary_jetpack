package com.lzh.petdiary_jetpack.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lzh.libcommon.EmptyView;
import com.lzh.petdiary_jetpack.R;
import com.lzh.petdiary_jetpack.databinding.LayoutRefreshViewBinding;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class AbsListFragment<T, M extends AbsViewModel<T>> extends Fragment implements OnRefreshListener, OnLoadMoreListener {

    protected LayoutRefreshViewBinding binding;
    protected RecyclerView recyclerView;
    protected EmptyView mEmptyView;
    private SmartRefreshLayout mRefreshLayout;
    protected PagedListAdapter<T, RecyclerView.ViewHolder> adapter;
    protected M mViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LayoutRefreshViewBinding.inflate(inflater, container, false);
        recyclerView = binding.recycleView;
        mEmptyView = binding.empytView;
        mRefreshLayout = binding.refreshView;


        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadMore(true);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);

        adapter = getAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(null);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.list_divider));
        recyclerView.addItemDecoration(decoration);
        afterCreateView();
        return binding.getRoot();

    }

    protected abstract void afterCreateView();

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //参数化类型
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        Type[] arguments = type.getActualTypeArguments();
        if(arguments.length >1){
            //从一个泛型类型中获取第二泛型参数的类型类。
            Type argument = arguments[1];
            Class modeClaz = ((Class) argument).asSubclass(AbsViewModel.class);
             mViewModel = (M) ViewModelProviders.of(this).get(modeClaz);
             mViewModel.getPageData().observe(this, new Observer<PagedList<T>>() {
                 @Override
                 public void onChanged(PagedList<T> pagedList) {
                     adapter.submitList(pagedList);
                 }
             });
             mViewModel.getBoundaryPageData().observe(this, new Observer<Boolean>() {
                 @Override
                 public void onChanged(Boolean hasData) {
                     finishRefresh(hasData);
                 }
             });
        }

    }

    public void submitList(PagedList<T> pagedList){
        if(pagedList.size() > 0){
            adapter.submitList(pagedList);
        }
        finishRefresh(pagedList.size() > 0);

    }
    public void finishRefresh(boolean hasData){
        PagedList<T> currentList = adapter.getCurrentList();
        hasData = hasData || currentList != null && currentList.size()> 0;
        RefreshState state = mRefreshLayout.getState();
        if (state.isFooter && state.isOpening) {
            mRefreshLayout.finishLoadMore();
        } else if (state.isHeader && state.isOpening) {
            mRefreshLayout.finishRefresh();
        }
        if(hasData){
            mEmptyView.setVisibility(View.GONE);
        }else{
            mEmptyView.setVisibility(View.VISIBLE);
        }

    }
    public abstract PagedListAdapter<T, RecyclerView.ViewHolder> getAdapter();


}
