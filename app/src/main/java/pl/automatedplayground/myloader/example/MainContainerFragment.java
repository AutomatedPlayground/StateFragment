package pl.automatedplayground.myloader.example;

import pl.automatedplayground.myloader.loader.ErrorOrProblemFragment;
import pl.automatedplayground.myloader.loader.LoaderFragment;
import pl.automatedplayground.myloader.loader.NoDataFragment;
import pl.automatedplayground.myloader.loader.StateFragmentHost;
import pl.automatedplayground.myloader.loader.data.FragmentStates;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainContainerFragment extends StateFragmentHost<SimpleDataModel, SimpleDataProvider, SimpleActionWorker, LoaderFragment<SimpleDataProvider>,
        SimpleDataFragment, NoDataFragment<SimpleActionWorker>, ErrorOrProblemFragment> {

    SimpleDataProvider mProvider = new SimpleDataProvider();

    @Override
    protected SimpleActionWorker createNoDataActionWorker() {
        return new SimpleActionWorker() {
        };
    }

    @Override
    protected SimpleDataModel getDataModelSimpleInstance() {
        return new SimpleDataModel();
    }

    @Override
    protected SimpleDataProvider bindDataProvider() {
        return mProvider;
    }

    @Override
    protected FragmentStates getDefaultState() {
        return FragmentStates.LOADER;
    }

    @Override
    protected boolean isStateAllowed(FragmentStates state) {
        return true;
    }

    @Override
    protected LoaderFragment createFragmentForLoader(SimpleDataProvider simpleDataProvider) {
        return new LoaderFragment<SimpleDataProvider>();
    }

    @Override
    protected ErrorOrProblemFragment createFragmentForRetry(SimpleDataProvider simpleDataProvider) {
        return new ErrorOrProblemFragment();
    }

    @Override
    protected NoDataFragment<SimpleActionWorker> createFragmentForNoData(SimpleDataProvider simpleDataProvider) {
        return new NoDataFragment<SimpleActionWorker>() {
        };
    }

    @Override
    protected SimpleDataFragment createFragmentForView(SimpleDataProvider simpleDataProvider) {
        return new SimpleDataFragment();
    }
}
