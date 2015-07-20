package pl.automatedplayground.myloader.loader;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import pl.automatedplayground.myloader.loader.listeners.ErrorOrProblemFragmentListener;

/**
 * Created by adrian on 20.07.15.
 */
public abstract class ErrorOrProblemFragment extends Fragment {
    private ErrorOrProblemFragmentListener mListener;

    final public ErrorOrProblemFragment setRetryActionWorker(ErrorOrProblemFragmentListener worker) {
        mListener = worker;
        return this;
    }

    final protected ErrorOrProblemFragmentListener getRetryListener() {
        return mListener;
    }

    final public void onRetryClicked() {
        if (mListener != null)
            mListener.onRetry();
        else
            Toast.makeText(getActivity(), this.getClass().getName() + " - no RetryListener set", Toast.LENGTH_LONG).show();
    }
}
