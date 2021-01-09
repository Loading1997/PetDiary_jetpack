package com.lzh.petdiary_jetpack.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lzh.petdiary_jetpack.utils.PixUtils;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class PDImageView extends AppCompatImageView {
    public PDImageView(@NonNull Context context) {
        this(context, null);
    }

    public PDImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PDImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindingAdapter(value = {"image_url","isCicle"}, requireAll = false)
    public static void setImageUrl(PDImageView view, String imgUrl, boolean isCircle){
        RequestBuilder<Drawable> builder = Glide.with(view).load(imgUrl);
        if(isCircle){
            builder.transform(new CircleCrop());
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && layoutParams.width > 0 && layoutParams.height > 0) {
            builder.override(layoutParams.width, layoutParams.height);
        }
        builder.into(view);

    }
    public void bindData(int widthPx, int heightPx, int marginLeft,String imgUrl){
        bindData(widthPx, heightPx,marginLeft, PixUtils.getScreenWidth(), PixUtils.getScreenWidth(), imgUrl);
    }

    public void bindData(int widthPx, int heightPx, int marginLeft,int maxWidth, int maxHeight,String imgUrl){
        if(widthPx <=0 || heightPx <=0){

                Glide.with(this).load(imgUrl).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        int height = resource.getIntrinsicHeight();
                        int width = resource.getIntrinsicWidth();
                        setSize(width, height,marginLeft,maxWidth, maxHeight);

                        setImageDrawable(resource);
                    }
                });
                return;
        }
        setSize(widthPx, heightPx,PixUtils.dp2px(marginLeft), maxWidth, maxHeight);
        setImageUrl(this, imgUrl, false);
    }

    private void setSize(int width, int height,int  marginLeft,int maxWidth, int maxHeight) {
        int finalWidth, finalHeight = 0;
        if(width > height){
            finalWidth = maxWidth;

        }else{
            finalHeight = maxHeight;
            finalWidth = (int) ((width)/(height*1.0f/finalHeight));
        }
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(finalWidth,finalHeight);
        params.leftMargin = height > width ? PixUtils.dp2px(marginLeft) : 0;
        setLayoutParams(params);
    }

    public void setBlurImageUrl(String coverUrl, int radius){
          Glide.with(this).load(coverUrl).override(50)
                  .transform(new BlurTransformation())
                  .dontAnimate()
                  .into(new SimpleTarget<Drawable>() {
                      @Override
                      public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                          setBackground(resource);
                      }
                  });
    }
}
