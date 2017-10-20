package com.dleyy.bingphoto.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dleyy.bingphoto.Bean.SectorBean;
import com.dleyy.bingphoto.R;
import com.dleyy.bingphoto.widget.SectorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dleyy on 2017/9/27.
 */
public class MineFragment extends Fragment {

    private SectorView sectorView;
    private List<SectorBean> mDates;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        sectorView = (SectorView) v.findViewById(R.id.mySector);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();

    }

    public void initDatas() {
        mDates = new ArrayList<>();
        SectorBean sectorBean = new SectorBean("test1", 0.3f, "1313", Color.RED);
        SectorBean sectorBean0 = new SectorBean("test1", 0.3f, "1313", Color.GRAY);
        SectorBean sectorBean1 = new SectorBean("test1", 0.3f, "1313", Color.WHITE);
        SectorBean sectorBean3 = new SectorBean("test1", 0.1f, "1313", Color.BLUE);
        mDates.add(sectorBean);
        mDates.add(sectorBean0);
        mDates.add(sectorBean1);
        mDates.add(sectorBean3);
        sectorView.setDatas(mDates);

    }
}
