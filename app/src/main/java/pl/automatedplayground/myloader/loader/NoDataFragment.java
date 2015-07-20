package pl.automatedplayground.myloader.loader;

import android.support.v4.app.Fragment;

import pl.automatedplayground.myloader.loader.listeners.NoDataActionWorker;

/**
 * Created by adrian on 20.07.15.
 */
public class NoDataFragment<ACTIONWORKER extends NoDataActionWorker> extends Fragment {
    private ACTIONWORKER mActionWorker;

    final public NoDataFragment setActionWorker(ACTIONWORKER worker){
        mActionWorker = worker;
        return this;
    }
}
