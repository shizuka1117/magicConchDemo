package com.example.magiccouchdemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.magiccouchdemo.ui.home.Option;

@Dao
public interface OptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void InsertOptions(Option... option);

    @Update
    public void updateOptions(Option... option);

    @Delete
    public void deleteOptions(Option... option);

    @Query("select * from option")
    public Option[] loadAllOptions();
}
