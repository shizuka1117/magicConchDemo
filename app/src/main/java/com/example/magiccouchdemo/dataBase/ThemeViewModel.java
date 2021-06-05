package com.example.magiccouchdemo.dataBase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ThemeViewModel extends AndroidViewModel {
    private ThemeDao themeDao;
    private LiveData<List<Theme>> allLongTheme;
    private LiveData<List<Theme>> allShortTheme;

    public ThemeViewModel(@NonNull Application application) {
        super(application);
        ThemeDatabase themeDatabase = ThemeDatabase.getThemeDataBase(application);
        themeDao = themeDatabase.getThemeDao();
        allLongTheme = themeDao.getAllLongTermDecision();
        allShortTheme = themeDao.getAllShortTermDecision();
    }

    public void insertThemes(Theme... themes){
        new InsertAsyncTask(themeDao).execute(themes);
    }

    public void updateThemes(Theme... themes){
        new UpdateAsyncTask(themeDao).execute(themes);
    }

    public void deleteThemes(Theme... themes){
        new DeleteAsyncTask(themeDao).execute(themes);
    }

    public void deleteAllThemes(){
        new DeleteAllAsyncTask(themeDao).execute();
    }

    public LiveData<List<Theme>> getAllLongTermThemeLive() {
        return allLongTheme;
    }

    public LiveData<List<Theme>> getAllShortTermThemeLive() {
        return allShortTheme;
    }

    static class InsertAsyncTask extends AsyncTask<Theme,Void,Void> {
        private ThemeDao themeDao;
        InsertAsyncTask(ThemeDao themeDao) {
            this.themeDao = themeDao;
        }

        @Override
        protected Void doInBackground(Theme... themes) {
            themeDao.insertTheme(themes);
            return null;
        }


    }

    static class UpdateAsyncTask extends AsyncTask<Theme,Void,Void>{
        private ThemeDao themeDao;
        UpdateAsyncTask(ThemeDao themeDao) {
            this.themeDao = themeDao;
        }

        @Override
        protected Void doInBackground(Theme... themes) {
            themeDao.updateTheme(themes);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Theme,Void,Void>{
        private ThemeDao themeDao;
        DeleteAsyncTask(ThemeDao themeDao) {
            this.themeDao = themeDao;
        }

        @Override
        protected Void doInBackground(Theme... themes) {
            themeDao.deleteTheme(themes);
            return null;
        }
    }


    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private ThemeDao themeDao;
        DeleteAllAsyncTask(ThemeDao themeDao) {
            this.themeDao = themeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            themeDao.deleteAllThemes();
            return null;
        }
    }
}
