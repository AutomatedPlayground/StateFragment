package pl.automatedplayground.statefragment;

import android.support.v4.app.Fragment;

import pl.automatedplayground.statefragment.data.DataModel;

/*
   Created by Adrian Skupień (automatedplayground@gmail.com) on 20.07.15.
   Copyright (c) 2015 Automated Playground under Apache 2.0 License
*/
public class DataFragment<DATAMODEL extends DataModel> extends Fragment {
    protected DATAMODEL mData;

    public DATAMODEL getData() {
        return mData;
    }

    public DataFragment setData(DATAMODEL data) {
        mData = data;
        return this;
    }
}
