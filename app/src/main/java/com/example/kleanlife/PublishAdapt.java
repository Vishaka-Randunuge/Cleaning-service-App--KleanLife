package com.example.kleanlife;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PublishAdapt extends BaseAdapter {

    Context context;
    ArrayList<Publish> arrayList;


    public PublishAdapt(Context context, ArrayList<Publish> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView  = inflater.inflate(R.layout.show_design, null);
        TextView location, cost, date;
        ImageView imageView = convertView.findViewById(R.id.imageView8);

        location = convertView.findViewById(R.id.textView47);
        cost = convertView.findViewById(R.id.textView52);
        date = convertView.findViewById(R.id.textView54);

        Publish publish = arrayList.get(position);
        String loc = publish.getLocation();
        byte[] img = publish.getImage();
        String p = publish.getCost();
        String d = publish.getDate();

        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        location.setText("Location: " +loc);
        cost.setText("Cost: "+p);
        date.setText("Date: "+d);
        imageView.setImageBitmap(bitmap);

        return convertView;
    }
}
