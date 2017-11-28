package com.dleyy.bingphoto.view.fragment;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.dleyy.bingphoto.Bean.SectorBean;
import com.dleyy.bingphoto.R;
import com.dleyy.bingphoto.widget.SectorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dleyy on 2017/9/27.
 */
public class MineFragment extends Fragment {

    private static String TAG = "MineFragment";

    private SectorView sectorView;
    private List<SectorBean> mDates;
    private Button animationButton;
    private Button scaleButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        sectorView = (SectorView) v.findViewById(R.id.mySector);
        animationButton = (Button) v.findViewById(R.id.trans);
        scaleButton = (Button) v.findViewById(R.id.scan);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();

    }

    public void initDatas() {

        final Animation animation = AnimationUtils.loadAnimation(
                getActivity(), R.anim.translate_animation);

        mDates = new ArrayList<>();
        SectorBean sectorBean = new SectorBean("test1", 0.3f, "1313", Color.RED);
        SectorBean sectorBean0 = new SectorBean("test1", 0.3f, "1313", Color.GRAY);
        SectorBean sectorBean1 = new SectorBean("test1", 0.3f, "1313", Color.YELLOW);
        SectorBean sectorBean3 = new SectorBean("test1", 0.1f, "1313", Color.BLUE);
        sectorBean.setSectorName("RED");
        sectorBean0.setSectorName("GRAY");
        sectorBean1.setSectorName("YELLOW");
        sectorBean3.setSectorName("BLUE");
        mDates.add(sectorBean);
        mDates.add(sectorBean0);
        mDates.add(sectorBean1);
        mDates.add(sectorBean3);
        sectorView.setDatas(mDates);

        sectorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: clicked");
                ObjectAnimator animator = ObjectAnimator.ofFloat(v,"popLength",0,30);
                animator.setDuration(1999);
                animator.start();
            }
        });

        animationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sectorView.startAnimation(animation);
            }
        });

        scaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sectorView.startAnimation(AnimationUtils.loadAnimation
                        (getActivity(), R.anim.scale_animation));
            }
        });
    }
}
