package pl.automatedplayground.myloader.loader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.automatedplayground.myloader.R;
import pl.automatedplayground.myloader.loader.listeners.NoDataActionWorker;

/**
 * Created by adrian on 20.07.15.
 */
public class NoDataFragment<ACTIONWORKER extends NoDataActionWorker> extends Fragment {
    private ACTIONWORKER mActionWorker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_defaultnodata,null);
        return view;
    }

    final public NoDataFragment setActionWorker(ACTIONWORKER worker){
        mActionWorker = worker;
        return this;
    }
}
