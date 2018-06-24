package com.example.manhl_000.bkrypto.Histories;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manhl_000.bkrypto.Data.DataAccess;
import com.example.manhl_000.bkrypto.R;

public class Day extends Fragment {
    private static String mess = "";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.history_tab1, container, false);
        TextView textView = rootView.findViewById(R.id.day_history_content);
        textView.setText(mess);
        return rootView;
    }

    public void setMsg(String msg) {
        mess = msg;
    }
}