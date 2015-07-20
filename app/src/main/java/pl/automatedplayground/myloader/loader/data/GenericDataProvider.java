package pl.automatedplayground.myloader.loader.data;

import pl.automatedplayground.myloader.loader.listeners.ResponseListener;

/**
 * Created by adrian on 20.07.15.
 */
public interface GenericDataProvider<DATAMODEL extends DataModel> {
    /**
     * Prebuffer data
     *
     * @param responseListener
     */
    public void prebufferData(ResponseListener<DATAMODEL> responseListener);

    /**
     * Get data for data model
     *
     * @param protoAlwaysNull
     * @return
     */
    public DATAMODEL getDataForModelClass(DATAMODEL protoAlwaysNull);

    /**
     * Check if data is on current state, or should be reloaded
     *
     * @param protoAlwaysNull
     * @return
     */
    public boolean shouldDataBeReloaded(DATAMODEL protoAlwaysNull);

    /**
     * Cancel all request - function called when master view is destroyed or paused
     */
    public void cancelAllRequests();

}
