package com.dleyy.bingphoto.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dleyy.bingphoto.Bean.DownloadedImage;
import com.dleyy.bingphoto.R;
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
        View v = View.inflate(context, R.layout.item_download_image_list, parent);
        return new MyHolder(v);
    }

    public class MyHolder extends BaseViewHolder<DownloadedImage> {

        private ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
        }

        @Override
        public void setData(DownloadedImage data) {
            File file = new File(data.getImagePath());
            if (file.exists()) {
                imageView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
            } else {
                imageView.setBackground(context.getDrawable(R.drawable.ic_load_img_fail));
            }
        }
    }
}
