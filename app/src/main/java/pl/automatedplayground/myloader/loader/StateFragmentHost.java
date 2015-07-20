package pl.automatedplayground.myloader.loader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import icepick.Icepick;
import icepick.Icicle;
import pl.automatedplayground.myloader.R;
import pl.automatedplayground.myloader.loader.data.DataModel;
import pl.automatedplayground.myloader.loader.data.FragmentStates;
import pl.automatedplayground.myloader.loader.data.GenericDataProvider;
import pl.automatedplayground.myloader.loader.listeners.ErrorOrProblemFragmentListener;
import pl.automatedplayground.myloader.loader.listeners.NoDataActionWorker;
import pl.automatedplayground.myloader.loader.listeners.ResponseListener;

/**
 * Created by adrian on 20.07.15.
 */
public abstract class StateFragmentHost<DATAMODEL extends DataModel, DATAPROVIDER extends GenericDataProvider<DATAMODEL>, NODATAWORKER extends NoDataActionWorker, LOADER extends LoaderFragment<DATAPROVIDER>,
        RETRY extends ErrorOrProblemFragment, NODATA extends NoDataFragment<NODATAWORKER>, DATA extends DataFragment<DATAMODEL>> extends Fragment implements ErrorOrProblemFragmentListener {


    private static int staticID = 1;
    @Icicle
    private int generatedID = -1;
    @Icicle
    private FragmentStates currentMode = null;

    private DATAPROVIDER mProvider;

    public DATAPROVIDER getDataProvider() {
        if (mProvider == null)
            setDataProvider(bindDataProvider());
        return mProvider;
    }

    public void setDataProvider(DATAPROVIDER mProvider) {
        this.mProvider = mProvider;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public void onResume() {
        setDataProvider(bindDataProvider());
        if (currentMode == FragmentStates.LOADER || (currentMode == FragmentStates.DATA && mProvider.shouldDataBeReloaded(getDataModelSimpleInstance())))
            setState(FragmentStates.LOADER, true);
        super.onResume();
    }

    @Override
    public void onPause() {
        getDataProvider().cancelAllRequests();
        super.onPause();
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_statefragmenthost, null);
        v.setId(getGeneratedID());
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (mProvider!=null && currentMode == null)
            setState(FragmentStates.LOADER, true);
    }

    /**
     * Get child fragment
     *
     * @return
     */
    public final Fragment getFragment() {
        return getFragmentManager().findFragmentByTag(getTagForChild());
    }

    /**
     * Connection error, custom user retry
     */
    @Override
    public void onRetry() {
        setState(FragmentStates.LOADER, true);
    }

    /**
     * Set state
     *
     * @param mode
     * @param loadEvenIfLoader
     * @return
     */
    final private StateFragmentHost setState(FragmentStates mode, boolean loadEvenIfLoader) {
        if (currentMode != null && currentMode == mode && !loadEvenIfLoader)
            return this;
        if (!isStateAllowed(mode)) {
            if (currentMode == null)
                return setState(getDefaultState(), true);
            return this;
        }
        currentMode = mode;
        showFragmentForCurrentMode();

        return this;
    }

    final private void showFragmentForCurrentMode() {
        getFragmentManager().beginTransaction().replace(getGeneratedID(), createFragmentForMode(currentMode), getTagForChild()).commit();
        if (currentMode == FragmentStates.LOADER) {
            // load data
            getDataProvider().prebufferData(new ResponseListener<DATAMODEL>() {
                @Override
                public void onResponseReceiver(DATAMODEL response) {
                    setState(FragmentStates.DATA, false);
                }

                @Override
                public void onError(Object cause) {
                    setState(FragmentStates.ERROR_OR_PROBLEM, false);
                }
            });
        }
    }

    private Fragment createFragmentForMode(FragmentStates currentMode) {
        Fragment output = null;
        switch (currentMode) {
            case ERROR_OR_PROBLEM:
                output = createFragmentForRetry(getDataProvider()).setRetryActionWorker(this);
            case DATA:
                output = createFragmentForView(getDataProvider()).setData(getDataProvider().getDataForModelClass(getDataModelSimpleInstance()));
            case NODATA:
                output = createFragmentForNoData(getDataProvider()).setActionWorker(createNoDataActionWorker());
            case LOADER:
            default:
                output = createFragmentForLoader(getDataProvider());
        }
        output.setRetainInstance(true);
        return output;
    }

    /**
     * Provide unique tag per instance
     *
     * @return
     */
    final private String getTagForChild() {
        return getClass().getName().toString() + getGeneratedID();
    }


    /**
     * Provide unique ID per instance
     *
     * @return
     */
    final private int getGeneratedID() {
        if (generatedID == -1)
            generatedID = staticID++;
        return generatedID;
    }


    /**
     * Create worker for actions made on no-data screen. Can be null
     *
     * @return
     */
    protected abstract NODATAWORKER createNoDataActionWorker();

    /**
     * Get "new" instance for generics initialization
     *
     * @return
     */
    protected abstract DATAMODEL getDataModelSimpleInstance();

    /**
     * Create or bind data provider
     *
     * @return
     */
    protected abstract DATAPROVIDER bindDataProvider();

    /**
     * Get default state, it will be always on allowed list
     *
     * @return
     */
    abstract protected FragmentStates getDefaultState();

    /**
     * Check if state is allowed
     *
     * @param state
     * @return
     */
    abstract protected boolean isStateAllowed(FragmentStates state);

    /**
     * Create fragment for Loader function
     *
     * @param dataprovider
     * @return
     */
    abstract protected LOADER createFragmentForLoader(DATAPROVIDER dataprovider);

    /**
     * Create fragment for Retry/Refresh function
     *
     * @param dataprovider
     * @return
     */
    abstract protected RETRY createFragmentForRetry(DATAPROVIDER dataprovider);

    /**
     * Create fragment for no-data view
     *
     * @param dataprovider
     * @return
     */
    abstract protected NODATA createFragmentForNoData(DATAPROVIDER dataprovider);

    /**
     * Create fragment for data view
     *
     * @param dataprovider
     * @return
     */
    abstract protected DATA createFragmentForView(DATAPROVIDER dataprovider);
}
