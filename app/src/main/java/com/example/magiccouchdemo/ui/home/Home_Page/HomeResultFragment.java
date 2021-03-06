package com.example.magiccouchdemo.ui.home.Home_Page;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magiccouchdemo.R;
import com.example.magiccouchdemo.dataBase.Option;
import com.example.magiccouchdemo.dataBase.OptionViewModel;
import com.example.magiccouchdemo.dataBase.Theme;
import com.example.magiccouchdemo.dataBase.ThemeViewModel;
import com.example.magiccouchdemo.databinding.FragmentHomeResultBinding;
import com.example.magiccouchdemo.ui.home.HomeViewModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import pl.droidsonroids.gif.GifDrawable;


public class HomeResultFragment extends Fragment {
    private HomeViewModel model;
    private FragmentHomeResultBinding binding;
    private ThemeViewModel themeViewModel;
    private OptionViewModel optionViewModel;
    private MutableLiveData<Integer> maxThemeID = new MutableLiveData<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        themeViewModel = new ViewModelProvider(this.getActivity()).get(ThemeViewModel.class);
        optionViewModel = new ViewModelProvider(this.getActivity()).get(OptionViewModel.class);
        model = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_result, container, false);
        binding.setLifecycleOwner(getActivity());
        TextView textView = binding.result;
        /**
         * ??????result???????????????
         */
        ObjectAnimator mFadeInObjectAnimator;
        //???????????????????????????????????????????????????????????????150??????
        mFadeInObjectAnimator = ObjectAnimator.ofFloat(textView, "translationY", 150, 0);
        //???????????????????????????500??????
        mFadeInObjectAnimator.setDuration(500);
        //????????????100????????????
        mFadeInObjectAnimator.setStartDelay(500);
        mFadeInObjectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                textView.setText(model.getResult());
            }
        });
        mFadeInObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //?????????????????????????????????
                float value = (float) animation.getAnimatedValue();
                //????????????150-0??????????????????alpha???0-1?????????????????????alue/150
                textView.setAlpha(1 - value/150);
            }
        });
        mFadeInObjectAnimator.start();

        GifDrawable gifDrawable = (GifDrawable)binding.gif1.getDrawable();
        gifDrawable.start();
        gifDrawable.setLoopCount(1);//??????????????????

        binding.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.homeFragment);
            }
        });

        binding.savebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Theme theme = new Theme(model.getTag().getValue(), model.getDecName().getValue(), "short");
                if(model.getId()==0)//????????????theme?????????
                    themeViewModel.insertThemes(theme);
                else{//????????????id?????????
                    theme.setT_id(model.getId());
                    themeViewModel.updateThemes(theme);
                }
                themeViewModel.getMaxShortThemeID();
                Log.d("maxID", maxThemeID.getValue()+"");
                for(int i = 0; i<model.getmList().size(); i++){
                    Option option = model.getmList().get(i);
                    if(option.getParentId()==0){//??????option??????ID???0????????????????????????????????????ID
                        //if(maxThemeID.getValue()!=null)
                            option.setParentId(model.getMaxThemeID().intValue()+1);
                    }
                    if(option.getId()==0)//??????option???ID???0?????????
                        optionViewModel.insertOptions(option);
                    else//????????????
                        optionViewModel.updateOptions(option);
                }
                NavController controller = Navigation.findNavController(v);
                getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
                model.clear();
                controller.navigate(R.id.homeFragment);
            }
        });
        return binding.getRoot();
    }

}