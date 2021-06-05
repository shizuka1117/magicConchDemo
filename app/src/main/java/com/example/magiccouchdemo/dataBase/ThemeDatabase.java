package com.example.magiccouchdemo.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Theme.class},version = 1,exportSchema = false)
public abstract class ThemeDatabase extends RoomDatabase {
    private static ThemeDatabase INSTANCE;
    static synchronized ThemeDatabase getThemeDataBase(Context context) {
        if ( INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ThemeDatabase.class, "theme_database")
                //下面注释表示允许主线程进行数据库操作，但是不推荐这样做。
                //他可能造成主线程lock以及anr
                //所以我们的操作都是在新线程完成的
                // .allowMainThreadQueries()
                .build();
        }
        return INSTANCE;
    }
    public abstract ThemeDao getThemeDao();
}
