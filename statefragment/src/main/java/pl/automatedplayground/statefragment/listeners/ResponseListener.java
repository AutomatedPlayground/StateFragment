package pl.automatedplayground.statefragment.listeners;

/*
   Created by Adrian Skupień (automatedplayground@gmail.com) on 20.07.15.
   Copyright (c) 2015 Automated Playground under Apache 2.0 License
*/
public interface ResponseListener<T> {
    public void onResponseReceiver(T response);

    public void onError(Object cause);
}
