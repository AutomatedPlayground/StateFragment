package pl.automatedplayground.myloader.example;
/*
   Created by Adrian Skupień (automatedplayground@gmail.com) on 20.07.15.
   Copyright (c) 2015 Automated Playground under Apache 2.0 License
*/

import java.util.Timer;
import java.util.TimerTask;

import pl.automatedplayground.myloader.loader.data.GenericDataProvider;
import pl.automatedplayground.myloader.loader.listeners.ResponseListener;

public class SimpleDataProvider implements GenericDataProvider<SimpleDataModel> {

    Timer timer;
    private SimpleDataModel data = null;

    @Override
    public void prebufferData(final ResponseListener<SimpleDataModel> responseListener) {
        if (data!=null && !shouldDataBeReloaded(data)) {
            responseListener.onResponseReceiver(data);
            return;
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                data = new SimpleDataModel().setData("Test");
                responseListener.onResponseReceiver(getDataForModelClass(new SimpleDataModel()));
                timer = null;
            }
        }, 5000);
    }

    @Override
    public SimpleDataModel getDataForModelClass(SimpleDataModel protoAlwaysNull) {
        return data;
    }

    @Override
    public boolean shouldDataBeReloaded(SimpleDataModel protoAlwaysNull) {
        return false;
    }

    @Override
    public void cancelAllRequests() {
        if (timer != null)
            timer.cancel();
        timer = null;
    }
}
