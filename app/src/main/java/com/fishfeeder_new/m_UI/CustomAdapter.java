package com.fishfeeder_new.m_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fishfeeder_new.R;
import com.fishfeeder_new.m_DataObject.Androidcamera;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Androidcamera> androidcameras;

    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Androidcamera> androidcameras) {
        this.c = c;
        this.androidcameras = androidcameras;

        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return androidcameras.size();
    }

    @Override
    public Object getItem(int position) {
        return androidcameras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        TextView nametxt= (TextView) convertView.findViewById(R.id.nameTxt);
        TextView timetxt= (TextView) convertView.findViewById(R.id.timeTxt);
        ImageView img= (ImageView) convertView.findViewById(R.id.movieImage);

        //BIND DATA
        Androidcamera androidcamera = androidcameras.get(position);

        nametxt.setText(androidcamera.getDate());
        timetxt.setText(androidcamera.getTime());

        //IMG
        PicassoClient.downloadImage(c, androidcamera.getImage(),img);


        return convertView;
    }
}
