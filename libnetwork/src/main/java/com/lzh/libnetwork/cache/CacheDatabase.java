package com.lzh.libnetwork.cache;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.lzh.libcommon.AppGlobals;

//数据读取、存储时数据转换器,比如将写入时将Date转换成Long存储，读取时把Long转换Date返回
//@TypeConverters(DateConverter.class)
//指定有哪些表@Database, exportSchema会导出一个json文件
@Database(entities = {Cache.class}, version = 1, exportSchema = true)
public abstract class CacheDatabase extends RoomDatabase {
    private static final CacheDatabase database;

    static{
        //创建一个内存数据库
        //但是这种数据库只存在于内存中，会随着进程被杀后丢失
//        Room.inMemoryDatabaseBuilder()
         database = Room.databaseBuilder(AppGlobals.getApplication(), CacheDatabase.class, "petdiary_chche")
                //是否允许在主线程开启查询
            .allowMainThreadQueries()
                //数据库创建和打开后的回调
                //.addCallback();
                //设置查询的线程池
                //.setQueryExecutor()
//            .openHelperFactory()
        //.setJournalModel();
                //数据库升级异常后的回滚
//        .fallbackToDestructiveMigration()
        //数据库升级异常后根据指定版本进行回滚
//        .fallbackToDestructiveMigrationFrom()
//        .addMigrations(CacheDatabase.sMigration)
                .build();

    }

    public abstract  CacheDao getCache();

    public static CacheDatabase get(){
        return database;
    }
/*    static Migration sMigration = new Migration(1,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table teacher rename to student");;
            database.execSQL("alter table teacher add column teacher_arg INTEGER NOT NULL default 0");
        }
    };*/
}
