package com.example.magiccouchdemo.dataBase;

import android.graphics.Path;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.magiccouchdemo.dataBase.Option;

import java.lang.reflect.Array;
import java.util.List;

@Dao
public interface OptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertOptions(Option... option);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertOptions(List<Option> options);

    @Update
    public void updateOptions(Option... option);

    @Delete
    public void deleteOptions(Option... option);

    @Query("DELETE FROM Option")
    void deleteAllOptions();

    @Query("select * from Option")
    public LiveData<List<Option>> loadAllOptions();

    @Query("select * from Option where option.id = :id")
    //select * from Theme where theme.t_id = :id
    public LiveData<Option> loadOptionById(int id);

    @Query("select * from Option where option.ParentId = :parentId")
    public LiveData<List<Option>> loadOptionsByParent(int parentId);

    @Query("select * from option where option.ParentId = :parentId and option.times = 0")
    public LiveData<List<Option>> loadNewOptionByParent(int parentId);

    @Query("select * from option where option.ParentId = :parentId and option.times > 0")
    public LiveData<List<Option>> loadHistoryOptionByParent(int parentId);
}
