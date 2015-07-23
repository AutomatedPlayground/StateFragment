package pl.automatedplayground.myloader.example;
/*
   Created by Adrian Skupień (automatedplayground@gmail.com) on 20.07.15.
   Copyright (c) 2015 Automated Playground under Apache 2.0 License
*/

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pl.automatedplayground.myloader.R;
import pl.automatedplayground.myloader.loader.DataFragment;

public class SimpleDataFragment extends DataFragment<SimpleDataModel> {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example_data, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(getData().data);
        return view;
    }
}
