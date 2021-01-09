package com.lzh.petdiary_jetpack.model;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    /**
     * id : 1250
     * userId : 15875841111
     * name : 东方不败
     * avatar : https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F40%2F27687%2Fef3ca43d9b02924c_480x640.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612410626&t=0838f682e0cd5b93c08899759f656a2a
     * description : 这是东方不败的描述
     * likeCount : 0
     * topCommentCount : 0
     * followCount : 0
     * followerCount : 0
     * qqOpenId : FE4DIEJFJDKSJFKLJDSKJFKLSD
     * expires_time : 1586696666666
     * score : 0
     * historyCount : 0
     * commentCount : 0
     * favoriteCount : 0
     * feedCount : 0
     * hasFollow : false
     */

    public int id;
    public long userId;
    public String name;
    public String avatar;
    public String description;
    public int likeCount;
    public int topCommentCount;
    public int followCount;
    public int followerCount;
    public String qqOpenId;
    public long expires_time;
    public int score;
    public int historyCount;
    public int commentCount;
    public int favoriteCount;
    public int feedCount;
    public boolean hasFollow;

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof Feed))
            return false;
        User newUser = (User) obj;
        return TextUtils.equals(name, newUser.name)
                && TextUtils.equals(avatar, newUser.avatar)
                && TextUtils.equals(description, newUser.description)
                && likeCount == newUser.likeCount
                && topCommentCount == newUser.topCommentCount
                && followCount == newUser.followCount
                && followerCount == newUser.followerCount
                && qqOpenId == newUser.qqOpenId
                && expires_time == newUser.expires_time
                && score == newUser.score
                && historyCount == newUser.historyCount
                && commentCount == newUser.commentCount
                && favoriteCount == newUser.favoriteCount
                && feedCount == newUser.feedCount
                && hasFollow == newUser.hasFollow;
    }

/*    @Override
    public int hashCode() {
        return Objects.hash(name, avatar, description, likeCount, topCommentCount, followCount, followerCount, qqOpenId, expires_time, score, historyCount, commentCount, favoriteCount, feedCount, hasFollow);
    }*/
}
