package com.lzh.petdiary_jetpack.model;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Comment implements Serializable {

    /**
     * id : 1126
     * itemId : 1578976510452
     * commentId : 1579007787804000
     * userId : 15875841122
     * commentType : 1
     * createTime : 1579007787804
     * commentCount : 0
     * likeCount : 1001
     * commentText : 2021年新年快乐
     * imageUrl : null
     * videoUrl : null
     * width : 0
     * height : 0
     * hasLiked : false
     * author : {"id":0,"userId":15875841122,"name":"张三","avatar":"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdik.img.kttpdq.com%2Fpic%2F40%2F27687%2Fef3ca43d9b02924c_480x640.jpg&refer=http%3A%2F%2Fdik.img.kttpdq.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1612410626&t=0838f682e0cd5b93c08899759f656a2a","description":"这是张三的描述","likeCount":0,"topCommentCount":0,"followCount":0,"followerCount":0,"qqOpenId":"FE4DIEJFJDKSJFKLJDSKJFKLBB","expires_time":1586696666666,"score":0,"historyCount":0,"commentCount":0,"favoriteCount":0,"feedCount":0,"hasFollow":false}
     * ugc : {"likeCount":0,"shareCount":0,"commentCount":0,"hasFavorite":false,"hasLiked":false,"hasdiss":false,"hasDissed":false}
     */
    public int id;
    public long itemId;
    public long commentId;
    public long userId;
    public int commentType;
    public long createTime;
    public int commentCount;
    public int likeCount;
    public String commentText;
    public Object imageUrl;
    public Object videoUrl;
    public int width;
    public int height;
    public boolean hasLiked;
    public User author;
    public Ugc ugc;

    public boolean equals(@Nullable Object obj){
        if(obj == null || !(obj instanceof  Comment)){
            return false;
        }
        Comment newComment = (Comment) obj;
        return likeCount == newComment.likeCount
                &&hasLiked == newComment.hasLiked
                &&(author !=null && author.equals(newComment.author))
                &&(ugc !=null && ugc.equals(newComment.ugc));
    }
}
