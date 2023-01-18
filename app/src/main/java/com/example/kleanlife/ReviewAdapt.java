package com.example.kleanlife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewAdapt extends BaseAdapter {

    Context context;
    ArrayList<Review> arrayList;

    public ReviewAdapt(Context context, ArrayList<Review> arrayList) {
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
        convertView  = inflater.inflate(R.layout.show_review_design, null);
        TextView date, rev, giver;

        date = convertView.findViewById(R.id.textView61);
        rev = convertView.findViewById(R.id.textView59);
        giver = convertView.findViewById(R.id.textView60);

        Review review1 = arrayList.get(position);
        String reView = review1.getReview();
        String revGiver = review1.getReviewGiver();
        String d = review1.getDate();

        date.setText(d);
        rev.setText(reView);
        giver.setText(revGiver);

        return convertView;
    }
}
