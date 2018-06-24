package com.example.manhl_000.bkrypto.Puzzles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhl_000.bkrypto.R;

import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter<puzzle_row> {
    private Context context;
    private ArrayList<puzzle_row> arrayList;

    public CustomArrayAdapter(Context context, ArrayList<puzzle_row> objects) {
        super(context, 0, objects);
        this.context = context;
        this.arrayList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.emo_row, parent, false);
        }

        TextView t1 = (TextView)convertView.findViewById(R.id.emo_name);
        t1.setText(getItem(position).getText());

        ImageView img = (ImageView)convertView.findViewById(R.id.emo_img);
        if (arrayList.get(position).isB() != 0) {
            img.setVisibility(View.VISIBLE);
        }
        else {
            img.setVisibility(View.GONE);
        }
        img.setImageResource(getItem(position).getImg());

        return convertView;
    }
}
