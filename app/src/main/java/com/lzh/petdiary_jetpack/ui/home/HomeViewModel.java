package com.lzh.petdiary_jetpack.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PagedList;

import com.alibaba.fastjson.TypeReference;
import com.lzh.libnetwork.ApiResponse;
import com.lzh.libnetwork.ApiService;
import com.lzh.libnetwork.JsonCallback;
import com.lzh.libnetwork.Request;
import com.lzh.petdiary_jetpack.ui.AbsViewModel;
import com.lzh.petdiary_jetpack.model.Feed;
import com.lzh.petdiary_jetpack.ui.MutableDataSource;
import com.lzh.petdiary_jetpack.utils.MLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class HomeViewModel extends AbsViewModel<Feed> {

    private volatile boolean witchCache = true;
    private MutableLiveData<PagedList<Feed>> cacheLiveData = new MutableLiveData<>();
    //做同步位的标记
    private AtomicBoolean loadAfter = new AtomicBoolean(false);

//    private MutableLiveData<PagedList<Feed>> cacheLiveData = new MutableDataSource()
    @Override
    public DataSource createDataSource() {
          return mDataSrouce;
    }

    public MutableLiveData<PagedList<Feed>> getCacheLiveData() {
        return cacheLiveData;
    }

    ItemKeyedDataSource<Integer,Feed> mDataSrouce =  new ItemKeyedDataSource<Integer, Feed>() {
        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Feed> callback) {
            //加载初始化数据的
            loadData(0, callback);
            witchCache = false;
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Feed> callback) {
            //加载分页数据的
            loadData(params.key, callback);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Feed> callback) {
            callback.onResult(Collections.emptyList());
            //是能够向前加载的
        }

        @NonNull
        @Override
        public Integer getKey(@NonNull Feed item) {
            return item.id;
        }
    };

    private void loadData(int key, ItemKeyedDataSource.LoadCallback<Feed> callback) {
        ///feed/queryHotFeedsList
/*
        Request request = ApiService.get("/feeds/queryHotFeedsList")
                .addParam("feedType", "all")
                .addParam("userId", 0)
                .addParam("feedId", key)
                .addParam("pageCount", 10)
                .responseType(new TypeReference<ArrayList<Feed>>() {
                }.getType());
*/
        if(key > 0){
            loadAfter.set(true);
        }
        Request request = ApiService.get("/feeds/queryHotFeedsList")
                .addParam("feedType", "")
//                .addParam("userId", 0)
                .addParam("feedId", key)
                .addParam("pageCount", 10)
                .responseType(new TypeReference<ArrayList<Feed>>() {
                }.getType());

        if(witchCache){
            request.cacheStrategy(Request.CACHE_ONLY);
            request.execute(new JsonCallback<List<Feed>>() {
                @Override
                public void onCacheSuccess(ApiResponse<List<Feed>> response) {
                    //Log.e("xxx", "onCacheSuccess: "+ response.body.size());
                    //高端玩法
                    Log.e("xxx", "onCacheSuccess: "+ response.body);
                    List<Feed> body = response.body;
                    MutableDataSource dataSource = new MutableDataSource<Integer,Feed>();
                    dataSource.data.addAll(response.body);
                    PagedList pageList = dataSource.buildNewPagedList(config);
                    cacheLiveData.postValue(pageList);

                }
            });
        }

        try {
            Request netRequest = witchCache ? request.clone():request;
            netRequest.cacheStrategy(key == 0 ?Request.NET_CHACHE :Request.NET_ONLY );
            ApiResponse<List<Feed>> response = netRequest.execute();

            List<Feed> data = response.body == null? Collections.emptyList(): response.body;
            callback.onResult(data);
            if(key > 0){
                //通过liveData发送数据 告诉UI层 是否应该主动关闭上拉加载分页的动画
                getBoundaryPageData().postValue(data.size() >0 );
                loadAfter.set(false);
            }

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        MLog.e("loadData: key: " + key);

    }

    @SuppressLint("RestrictedApi")
    public void loadAfter(int id, ItemKeyedDataSource.LoadCallback<Feed> callback) {
        if (loadAfter.get()){
            callback.onResult(Collections.emptyList());
            return ;
        }
        ArchTaskExecutor.getIOThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                loadData(id, callback);
            }
        });
    }
}