package com.lzh.petdiary_jetpack.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public abstract  class AbsViewModel<T> extends ViewModel {
    private final LiveData<PagedList<T>> pageData;
    protected  PagedList.Config config;
    private DataSource dataSource;

    private MutableLiveData<Boolean> boundaryPageData = new MutableLiveData<>();
    public AbsViewModel(){
        config = new PagedList.Config.Builder()
                .setPageSize(10)
                //不想让他初始化完立马加载下一页， 色值InitalLoadingSize > pagesize
                .setInitialLoadSizeHint(12)
                //.setMaxSize(100)
                //.setEnablePlaceholders(false)
                //.setPrefetchDistance()
                .build();

        pageData = new LivePagedListBuilder(factory, config)
                .setInitialLoadKey(0)
                //.setFetchExecutor()
                .setBoundaryCallback(callback)
                .build();

/*        pageData.observeForever(new Observer<PagedList<T>>() {
            @Override
            public void onChanged(PagedList<T> pagedList) {
                dapater.submitList();
            }
        });*/
    }

    public LiveData<PagedList<T>> getPageData(){
        return pageData;
    }
    public DataSource getDataSource(){
        return dataSource;
    }

    public MutableLiveData<Boolean> getBoundaryPageData() {
        return boundaryPageData;
    }

    PagedList.BoundaryCallback<T> callback = new PagedList.BoundaryCallback<T>() {
        @Override
        public void onZeroItemsLoaded() {
            boundaryPageData.postValue(false);
        }

        //第一条数据被加载
        @Override
        public void onItemAtFrontLoaded(@NonNull T itemAtFront) {
            Log.e("xxx", "第一条数据被加载出来");
            boundaryPageData.postValue(true);
        }

        //最后一条数据被加载
        @Override
        public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
            //新提交的PageList中最后一条数据被加载到列表
        }
    };
    DataSource.Factory factory = new DataSource.Factory() {
        @NonNull
        @Override
        public DataSource create() {
            if (dataSource == null || dataSource.isInvalid()) {
                dataSource = createDataSource();
            }
            return dataSource;
        }
    };

    public  abstract  DataSource createDataSource();
}
