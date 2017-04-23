package jiyun.com.oschinawork.modle.bean.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/22.
 */

public class MyHepler extends SQLiteOpenHelper {
    public MyHepler(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table chao(id Integer primary key,name varchar(20),pwd varchar(30),cookie varchar(30),url varchar(100))";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
