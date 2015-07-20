package pl.automatedplayground.myloader.loader.data;

import pl.automatedplayground.myloader.loader.listeners.ResponseListener;

/*
   Created by Adrian Skupień (automatedplayground@gmail.com) on 20.07.15.
   Copyright (c) 2015 Automated Playground under Apache 2.0 License
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
