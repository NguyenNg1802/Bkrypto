package com.example.manhl_000.bkrypto.Tutorials;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manhl_000.bkrypto.R;

/**
 * Created by manhl_000 on 4/25/2018.
 */

public class HowToSolve extends Fragment {
    private static String mess = "Solved";
    private static byte[] image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tutorial_tab2, container, false);

        //load How to solve
        ImageView imageView = rootView.findViewById(R.id.solve_image);
//        imageView.setImageResource(R.drawable.caesar_tut);
        Bitmap bm = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bm);

        TextView textView = rootView.findViewById(R.id.solve_text);
        textView.setText(mess);


        return rootView;
    }

    public void setImage(byte[] img) {
        image = img;
    }


    public void setMsg(String msg) {
        mess = msg;
    }

}
