package pl.automatedplayground.myloader.loader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.automatedplayground.myloader.R;
import pl.automatedplayground.myloader.loader.data.GenericDataProvider;

/**
 * Created by adrian on 20.07.15.
 */
public class LoaderFragment<DATAPROVIDER extends GenericDataProvider> extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default_loader,null);
        return view;
    }
}
