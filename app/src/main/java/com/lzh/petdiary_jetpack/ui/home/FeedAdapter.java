package com.lzh.petdiary_jetpack.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lzh.petdiary_jetpack.databinding.LayoutFeedTypeImageBinding;
import com.lzh.petdiary_jetpack.databinding.LayoutFeedTypeVideoBinding;
import com.lzh.petdiary_jetpack.model.Feed;

public class FeedAdapter extends PagedListAdapter<Feed,FeedAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private Context context;
    private String mCategory;

    protected FeedAdapter(Context context, String category){
        super(new DiffUtil.ItemCallback<Feed>() {
            @Override
            public boolean areItemsTheSame(@NonNull Feed oldItem, @NonNull Feed newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Feed oldItem, @NonNull Feed newItem) {
                return oldItem.equals(newItem);
            }
        });
        inflater = LayoutInflater.from(context);
        this.context = context;
        mCategory = category;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;

        if(viewType == Feed.TYPE_VIDEO){
            binding  =  LayoutFeedTypeVideoBinding.inflate(inflater);
        }else{

           binding =  LayoutFeedTypeImageBinding.inflate(inflater);
        }
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        Feed feed = getItem(position);
        return feed.itemType;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public ViewHolder(@NonNull View itemView, ViewDataBinding binding) {
            super(itemView);
            mBinding = binding;
        }
        public void bindData(Feed item){
            if(mBinding instanceof LayoutFeedTypeImageBinding){
                LayoutFeedTypeImageBinding imageBinding = (LayoutFeedTypeImageBinding) mBinding;
                imageBinding.setFeed(item);
                imageBinding.feedImage.bindData(item.width, item.height, 16, item.cover);
            }else{
                LayoutFeedTypeVideoBinding videoBinding = (LayoutFeedTypeVideoBinding) mBinding;
                videoBinding.setFeed(item);
                videoBinding.listPlayView.bindData(mCategory, item.width, item.height, item.cover, item.url);
            }
        }
    }
}
