package com.example.magiccouchdemo.dataBase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class OptionViewModel extends AndroidViewModel {
    private OptionDao OptionDao;
    private LiveData<List<Option>> Options;

    public OptionViewModel(@NonNull Application application) {
        super(application);
        OptionDatabase optionDatabase = OptionDatabase.getOptionDataBase(application);
        OptionDao = optionDatabase.getOptionDao();
    }

    public void insertOptions(Option... Option){
        new OptionViewModel.InsertAsyncTask(OptionDao).execute(Option);
    }

    public void updateOptions(Option... Option){
        new OptionViewModel.UpdateAsyncTask(OptionDao).execute(Option);
    }

    public void deleteOptions(Option... Options){
        new OptionViewModel.DeleteAsyncTask(OptionDao).execute(Options);
    }

    public void deleteAllOptions(){
        new OptionViewModel.DeleteAllAsyncTask(OptionDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Option,Void,Void> {
        private OptionDao OptionDao;
        InsertAsyncTask(OptionDao OptionDao) {
            this.OptionDao = OptionDao;
        }

        @Override
        protected Void doInBackground(Option... options) {
            OptionDao.insertOptions(options);
            return null;
        }


    }

    static class UpdateAsyncTask extends AsyncTask<Option,Void,Void>{
        private OptionDao OptionDao;
        UpdateAsyncTask(OptionDao OptionDao) {
            this.OptionDao = OptionDao;
        }

        @Override
        protected Void doInBackground(Option... options) {
            OptionDao.updateOptions(options);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Option,Void,Void>{
        private OptionDao OptionDao;
        DeleteAsyncTask(OptionDao optionDao) {
            this.OptionDao = optionDao;
        }

        @Override
        protected Void doInBackground(Option... options) {
            OptionDao.deleteOptions(options);
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
