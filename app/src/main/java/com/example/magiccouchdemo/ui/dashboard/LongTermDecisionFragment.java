package com.example.magiccouchdemo.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.Theme;
import com.example.magiccouchdemo.dataBase.ThemeViewModel;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionList;
import com.example.magiccouchdemo.ui.home.Home_Page.decisionRecycleAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LongTermDecisionFragment extends Fragment {
    //for database

    ThemeViewModel themeViewModel;

    //
    private RecyclerView Rv;
    private LongTermDecisionAdapter myAdapter;
    //private SwipeRefreshLayout swipe_refresh;
    private FloatingActionButton floatingActionButton;

    public LongTermDecisionFragment(){

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        View view = inflater.inflate(R.layout.recycle_view_list2, container, false);

        Rv = (RecyclerView)view.findViewById(R.id.recycle_view2);
        //swipe_refresh = (SwipeRefreshLayout)view.findViewById(R.id.refresh_long);

        floatingActionButton =(FloatingActionButton)view.findViewById(R.id.add_option);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_longTermFragment_to_longTermDecSetFragment);
            }

        });

        // ???????????????RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);
        myAdapter = new LongTermDecisionAdapter();
        Rv.setAdapter(myAdapter);

        //??????????????????
        //Theme item1 = new Theme(null,"????????????????????????15??????","long");
        //Theme item2 = new Theme(null,"??????????????????????????????","long");
        //Theme item3 = new Theme(null,"???ta?????????????????????","long");
        //Theme item4 = new Theme(null,"???????????????????????????","long");
        //Theme item5 = new Theme(null,"???????????????????????????111","long");
        Theme item0 = new Theme(null,"????????????????????????????????????????????????","long");


        //database??????
        themeViewModel = ViewModelProviders.of(this.getActivity()).get(ThemeViewModel.class);
        //themeViewModel.deleteAllThemes();
        //themeViewModel.insertThemes(item1,item2,item3,item4,item5);

        /*swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {  //??????????????????
            @Override
            public void onRefresh() {
                myAdapter.setDataList(themes);
            }
        });*/
        themeViewModel.getAllLongTermThemeLive().observe(this.getViewLifecycleOwner(),new Observer<List<Theme>>(){
            @Override
            public void onChanged(List<Theme> themes) {
                myAdapter.setDataList(themes);
                if (myAdapter.getItemCount()==0){
                    themeViewModel.insertThemes(item0);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        setOnListViewClickListener();//??????????????????

        return view;

    }

    private void setOnListViewClickListener() {
        myAdapter.setOnItemClickListener(new LongTermDecisionAdapter.onItemClickListener() {
            //????????????,??????????????????/??????
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemLongClick(CardView view, int position) {
                //Toast.makeText(getActivity(), "onLongClick" + position, Toast.LENGTH_SHORT).show();
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                popupMenu.getMenuInflater().inflate(R.menu.window_menu, popupMenu.getMenu());
                //??????PopupMenu?????????????????????
                popupMenu.setGravity(Gravity.END);

                //???????????????????????????????????????
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:
                                //??????????????????????????????delete
                                delete(position);
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }

        });

    }

    private void delete(int position) {
        if(themeViewModel.getAllLongTermThemeLive()==null){
            Toast.makeText(getActivity(), "?????????????????????????????????", Toast.LENGTH_SHORT).show();
            return;
        }
        showInformation("??????????????????",position);
    }

    /**
     * ????????????
     */
    private void beSureDelete(int position) {
        Theme theme = myAdapter.getDataList().get(position);
        themeViewModel.deleteThemes(theme);
        myAdapter.getDataList().remove(theme);
        myAdapter.notifyItemRemoved(position);
        myAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), "????????????", Toast.LENGTH_SHORT).show();
    }

    private void showInformation(String msg, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setTitle("??????");

        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // ????????????
                //Toast.makeText(getActivity(), "??????????????????", Toast.LENGTH_SHORT).show();
                //?????????????????????buSureDelete
                beSureDelete(position);
            }
        });

        builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
