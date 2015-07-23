package pl.automatedplayground.statefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import pl.automatedplayground.statefragment.listeners.ErrorOrProblemFragmentListener;

/*
   Created by Adrian Skupień (automatedplayground@gmail.com) on 20.07.15.
   Copyright (c) 2015 Automated Playground under Apache 2.0 License
*/
public class ErrorOrProblemFragment extends Fragment {
    private ErrorOrProblemFragmentListener mListener;

    final public ErrorOrProblemFragment setRetryActionWorker(ErrorOrProblemFragmentListener worker) {
        mListener = worker;
        return this;
    }

    final protected ErrorOrProblemFragmentListener getRetryListener() {
        return mListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default_retry, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getRetryListener() != null)
                    getRetryListener().onRetry();
                else
                    Toast.makeText(getActivity(), getClass().getName().toString() + " - RetryListener not set!", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


    final public void onRetryClicked() {
        if (mListener != null)
            mListener.onRetry();
        else
            Toast.makeText(getActivity(), this.getClass().getName() + " - no RetryListener set", Toast.LENGTH_LONG).show();
    }
}
