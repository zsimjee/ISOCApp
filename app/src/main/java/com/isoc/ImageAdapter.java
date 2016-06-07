package com.isoc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by z on 5/19/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);

            WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x / 4;

            imageView.setLayoutParams(new GridView.LayoutParams(width, width));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.btn_todayisoc,
            R.drawable.btn_sponsoriftar,
            R.drawable.btn_eidprayerinfo,
            R.drawable.btn_power1000,
            R.drawable.btn_videos,
            R.drawable.btn_zakat,
            R.drawable.btn_generaldonation,
            R.drawable.btn_joincommittee,
            R.drawable.btn_sadaqatulfitr
    };
}