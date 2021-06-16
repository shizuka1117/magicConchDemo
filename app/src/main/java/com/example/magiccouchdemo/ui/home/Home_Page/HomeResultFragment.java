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
         * 设置result的渐入效果
         */
        ObjectAnimator mFadeInObjectAnimator;
        //由于是淡入，从底部向中心点移动，移动距离为150像素
        mFadeInObjectAnimator = ObjectAnimator.ofFloat(textView, "translationY", 150, 0);
        //动画执行时间设置为500毫秒
        mFadeInObjectAnimator.setDuration(500);
        //动画推迟100毫秒执行
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
                //当前动画时间点的动画值
                float value = (float) animation.getAnimatedValue();
                //动画值在150-0之间变化，而alpha在0-1之间变化，故而alue/150
                textView.setAlpha(1 - value/150);
            }
        });
        mFadeInObjectAnimator.start();

        GifDrawable gifDrawable = (GifDrawable)binding.gif1.getDrawable();
        gifDrawable.start();
        gifDrawable.setLoopCount(1);//设置播放一次


        /**
         * Handler 定义处理事项
         */
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    textView.setText(model.getResult());
                }
                super.handleMessage(msg);
            };
        };

        /**
         * 定时器设置
         */
        Timer timer =  new Timer();
        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                /**
                 *   这个run 的使用就是开启了一个新的线程，
                 *   在这个子线程中是无法更新UI 的，比如更新TextView 的显示内容
                 */
                Message message = new Message();
                //message 定义，是为了给Handler向UI发送信息作为媒介
                message.what = 1;
                handler.sendMessage(message);
                //Handler 发送message。这里发送回去调用handler类中的回掉函数：handlerMessage()）
            }
        };

        timer.schedule(timertask,1000*1);


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
                if(model.getId()==0)//如果是新theme，插入
                    themeViewModel.insertThemes(theme);
                else{//否则设置id，更新
                    theme.setT_id(model.getId());
                    themeViewModel.updateThemes(theme);
                }
                themeViewModel.getMaxShortThemeID();
                Log.d("maxID", maxThemeID.getValue()+"");
                for(int i = 0; i<model.getmList().size(); i++){
                    Option option = model.getmList().get(i);
                    if(option.getParentId()==0){//如果option的父ID为0，表明之前是插入，设置父ID
                        //if(maxThemeID.getValue()!=null)
                            option.setParentId(model.getMaxThemeID().intValue()+1);
                    }
                    if(option.getId()==0)//如果option的ID为0，插入
                        optionViewModel.insertOptions(option);
                    else//否则更新
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