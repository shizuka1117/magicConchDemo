package com.example.magiccouchdemo.dataBase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class OptionViewModel extends AndroidViewModel {
    private OptionDao optionDao;
    private LiveData<List<Option>> Options;

    public OptionViewModel(@NonNull Application application) {
        super(application);
        OptionDatabase optionDatabase = OptionDatabase.getOptionDataBase(application);
        optionDao = optionDatabase.getOptionDao();
    }

    public void insertOptions(Option... option){
        new OptionViewModel.InsertAsyncTask(optionDao).execute(option);
    }

    public void updateOptions(Option... option){
        new OptionViewModel.UpdateAsyncTask(optionDao).execute(option);
    }

    public void deleteOptions(Option... options){
        new OptionViewModel.DeleteAsyncTask(optionDao).execute(options);
    }

    public void deleteAllOptions(){
        new OptionViewModel.DeleteAllAsyncTask(optionDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Option,Void,Void> {
        private OptionDao optionDao;
        InsertAsyncTask(OptionDao optionDao) {
            this.optionDao = optionDao;
        }

        @Override
        protected Void doInBackground(Option... options) {
            optionDao.insertOptions(options);
            return null;
        }


    }

    static class UpdateAsyncTask extends AsyncTask<Option,Void,Void>{
        private OptionDao optionDao;
        UpdateAsyncTask(OptionDao optionDao) {
            this.optionDao = optionDao;
        }

        @Override
        protected Void doInBackground(Option... options) {
            optionDao.updateOptions(options);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Option,Void,Void>{
        private OptionDao optionDao;
        DeleteAsyncTask(OptionDao optionDao) {
            this.optionDao = optionDao;
        }

        @Override
        protected Void doInBackground(Option... options) {
            optionDao.deleteOptions(options);
            return null;
        }
    }


    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private OptionDao optionDao;
        DeleteAllAsyncTask(OptionDao optionDao) {
            this.optionDao = optionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            optionDao.deleteOptions();
            return null;
        }
    }
}
