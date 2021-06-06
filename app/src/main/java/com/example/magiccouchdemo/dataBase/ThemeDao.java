package com.example.magiccouchdemo.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao  //Database access object
public interface ThemeDao {
    @Insert
    void insertTheme(Theme... themes);

    @Delete
    void deleteTheme(Theme... themes);

    @Update
    void updateTheme(Theme... themes);

    @Query("DELETE FROM Theme")
    void deleteAllThemes();

    @Query("select * from Theme where theme.type = 'long' ")
    LiveData<List<Theme>> getAllLongTermDecision();

    @Query("select * from Theme where theme.type = 'short'")
    LiveData<List<Theme>> getAllShortTermDecision();
}
