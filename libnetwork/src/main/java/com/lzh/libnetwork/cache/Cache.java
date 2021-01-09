package com.lzh.libnetwork.cache;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.Relation;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "cache"
    //,foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "key",onDelete = ForeignKey.RESTRICT, onUpdate = ForeignKey.SET_DEFAULT)}
    //indices 复合主键
    //,indices = {@Index(value ={"key","id"})}
    )//表名
public class Cache implements Serializable {
    //PrimaryKey 必须要有,且不为空,autoGenerate 主键的值是否由Room自动生成,默认false
    @PrimaryKey(autoGenerate = false)
    @NonNull
    public String key;

    //@ColumnInfo(name = "_data"),指定该字段在表中的列的名字
    public byte[] data;

    //parentColumn:当前表的列名， ventityColumn: User表的列名 projection数组：需要查询出来的字段
    //@Relation(entity = User.class, parentColumn = "id",  = "id", projection = {})
    //User对象会被映射到表里面去
/*    @Embedded
    public User user;*/

    //存取数据库时将mDate转换成Long类型，读取时，将long类型变成Date类型
    @TypeConverters(value ={DateConverter.class})
    public Date mDate;

}

//public class ForeignTable implements Serializable {
//    @PrimaryKey
//    @NonNull
//    public String foreign_key;
//
//    //@ColumnInfo(name = "_data")
//    public byte[] foreign_data;
//}
