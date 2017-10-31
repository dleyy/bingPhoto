package com.dleyy.bingphoto.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dleyy.bingphoto.Bean.DownloadedImage;
import com.dleyy.bingphoto.R;
import com.dleyy.bingphoto.Utils.LoadImage;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.File;

/**
 * Created by dleyy on 2017/10/25.
 */
public class DownLoadListAdapter extends RecyclerArrayAdapter<DownloadedImage> {

    private Context context;

    public DownLoadListAdapter(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_download_image_list, null);
        return new MyHolder(v);
    }

    public class MyHolder extends BaseViewHolder<DownloadedImage> {

        private SimpleDraweeView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (SimpleDraweeView) itemView.findViewById(R.id.item_image);
        }

        @Override
        public void setData(DownloadedImage data) {
            imageView.setImageURI("file://"+data.getImagePath());
        }
    }

}
