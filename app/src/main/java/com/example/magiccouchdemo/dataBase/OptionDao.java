package com.example.magiccouchdemo.dataBase;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.magiccouchdemo.dataBase.Option;

import java.util.List;

@Dao
public interface OptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertOptions(Option... option);

    @Update
    public void updateOptions(Option... option);

    @Delete
    public void deleteOptions(Option... option);

    @Query("DELETE FROM option")
    void deleteAllOptions();

    @Query("select * from option")
    public LiveData<List<Option>> loadAllOptions();

    @Query("select * from option where ParentId = :parentId")
    public LiveData<List<Option>> loadOptionsByParent(int parentId);
}
