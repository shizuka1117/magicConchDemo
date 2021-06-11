package com.example.magiccouchdemo.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Option.class},version = 2,exportSchema = false)
public abstract class OptionDatabase extends RoomDatabase {
    private static OptionDatabase INSTANCE;
    static synchronized OptionDatabase getOptionDataBase(Context context) {
        if ( INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), OptionDatabase.class, "option_database")
                    .addMigrations(new Migration(1, 2)
                    {
                        @Override
                        public void migrate(SupportSQLiteDatabase database)
                        {
                            database.execSQL("drop table Option");
                            database.execSQL("create table Option(" +
                                    "id INTEGER PRIMARY KEY NOT NULL," +
                                    "ParentId INTEGER," +
                                    "optionName TEXT, " +
                                    "value INTEGER," +
                                    "times INTEGER," +
                                    "date TEXT)");
                        }
                    })
                    //下面注释表示允许主线程进行数据库操作，但是不推荐这样做。
                    //他可能造成主线程lock以及anr
                    //所以我们的操作都是在新线程完成的
                    // .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract OptionDao getOptionDao();
}
