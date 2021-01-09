package com.lzh.petdiary_jetpack.model;

import java.io.Serializable;

public class Ugc implements Serializable {
    /**
     * likeCount : 0
     * shareCount : 0
     * commentCount : 0
     * hasFavorite : false
     * hasLiked : false
     * hasdiss : false
     * hasDissed : false
     */
    public int likeCount;
    public int shareCount;
    public int commentCount;
    public boolean hasFavorite;
    public boolean hasLiked;
    public boolean hasdiss;

    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof Ugc)){
            return false;
        }
        Ugc newUgc = (Ugc)obj;
        return likeCount ==newUgc.likeCount
                &&shareCount == newUgc.shareCount
                &&commentCount == newUgc.commentCount
                &&hasdiss == newUgc.hasdiss
                &&hasLiked == newUgc.hasLiked
                &&hasFavorite == newUgc.hasFavorite
                ;
    }
}
