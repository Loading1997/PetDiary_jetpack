package com.lzh.petdiary_jetpack.model;

import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.databinding.Bindable;

import java.io.Serializable;
import java.util.Objects;

public class Feed implements Serializable {

    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;
    /**
     * id : 428
     * itemId : 1578976510452
     * itemType : 2
     * createTime : 1578976510452
     * duration : 8
     * feeds_text : 2020他来了，他真菲尼迪搜集哦
     * authorId : 15875841111
     * activityIcon : null
     * activityText : 2021新年快乐
     * width : 320
     * height : 240
     * url : https://www.runoob.com/try/demo_source/movie.mp4
     * cover : https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F40%2F27687%2Fef3ca43d9b02924c_480x640.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612410626&t=0838f682e0cd5b93c08899759f656a2a
     */

    public int id;
    public long itemId;
    public int itemType;
    public long createTime;
    public double duration;
    public String feeds_text;
    public long authorId;
    public String activityIcon;
    public String activityText;
    public int width;
    public int height;
    public String url;
    public String cover;

    public User author;
    public Comment topComment;
    public Ugc ugc;



    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof Feed))
            return false;
        Feed newFeed = (Feed) obj;
        return id == newFeed.id
                && itemId == newFeed.itemId
                && itemType == newFeed.itemType
                && createTime == newFeed.createTime
                && duration == newFeed.duration
                && TextUtils.equals(feeds_text, newFeed.feeds_text)
                && authorId == newFeed.authorId
                && TextUtils.equals(activityIcon, newFeed.activityIcon)
                && TextUtils.equals(activityText, newFeed.activityText)
                && width == newFeed.width
                && height == newFeed.height
                && TextUtils.equals(url, newFeed.url)
                && TextUtils.equals(cover, newFeed.cover)
                && (author != null && author.equals(newFeed.author))
                && (topComment != null && topComment.equals(newFeed.topComment))
                && (ugc != null && ugc.equals(newFeed.ugc));
    }
/*    @Override
    public int hashCode() {
        return Objects.hash(id, itemId, itemType, createTime, duration, feeds_text, authorId, activityIcon, activityText, width, height, url, cover, author, topComment, ugc);
    }*/

    @Override
    public String toString() {
        return itemId + " "+ activityText;
    }
}
