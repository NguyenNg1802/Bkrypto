package com.example.manhl_000.bkrypto.Tutorials;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manhl_000.bkrypto.R;

/**
 * Created by manhl_000 on 4/25/2018.
 */

public class Example extends Fragment {
    private static String mess = "Solved";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tutorial_tab3, container, false);
        TextView textView = rootView.findViewById(R.id.example_text);
        textView.setText(mess);

        return rootView;
    }

    public void setMsg(String msg) {
        mess = msg;
    }
}