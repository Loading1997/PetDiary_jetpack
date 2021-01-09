package com.lzh.petdiary_jetpack.ui;

import android.annotation.SuppressLint;
import android.renderscript.Sampler;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MutableDataSource<Key, Value> extends PageKeyedDataSource<Key,Value>{
    public List<Value> data = new ArrayList<>();
    public PagedList<Value> buildNewPagedList(PagedList.Config config){
        @SuppressLint("RestrictedApi")
        PagedList<Value> pageList = new PagedList.Builder<Key, Value>(this, config)
                //提供异步工作的线程池
                .setFetchExecutor(ArchTaskExecutor.getIOThreadExecutor())
                .setNotifyExecutor(ArchTaskExecutor.getMainThreadExecutor())
                .build();
        return pageList;
    }
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Key> params, @NonNull LoadInitialCallback<Key, Value> callback) {
        callback.onResult(data, null, null);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Key> params, @NonNull LoadCallback<Key, Value> callback) {
        callback.onResult(Collections.emptyList(), null);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Key> params, @NonNull LoadCallback<Key, Value> callback) {
        callback.onResult(Collections.emptyList(), null);
    }
}
