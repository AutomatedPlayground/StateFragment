package pl.automatedplayground.myloader.loader;

import android.support.v4.app.Fragment;

import pl.automatedplayground.myloader.loader.data.DataModel;

/**
 * Created by adrian on 20.07.15.
 */
public class DataFragment<DATAMODEL extends DataModel> extends Fragment {
    protected DATAMODEL mData;

    public DataFragment setData(DATAMODEL data) {
        mData = data;
        return this;
    }
}
